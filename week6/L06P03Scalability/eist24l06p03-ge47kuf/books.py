import json


# DO NOT EDIT
# function used for hashing the strings
def fnv1a_hash(key):
    prime = 16777619
    hash_val = 2166136261
    for char in key.encode():
        hash_val ^= char
        hash_val *= prime
    return hash_val


ERROR_MESSAGE = "The key already exists"


class ChordHash:

    def __init__(self, size=8):
        self.size = size
        self.nodes = {}
        for i in range(size):
            self.add_node(i)

    # TODO 1 implement a function adding a node
    def add_node(self, node_id):
        if node_id not in self.nodes:
            self.nodes[node_id] = set()
        else:
            raise exec(ERROR_MESSAGE)

    # TODO 2 implement the hash key function
    def hash_key(self, key):
        return fnv1a_hash(key) % self.size

    # TODO 3 implement the helper function find successor
    def find_successor(self, key):
        hashed_key = self.hash_key(key)
        sorted_nodes = sorted(self.nodes.keys())

        for node_id in sorted_nodes:
            if node_id > hashed_key:
                return node_id
        return sorted_nodes[0]

    # TODO 4 implement the function inserting a key (use the find_successor function)
    def insert_key(self, key):
        successor = self.find_successor(key)
        self.nodes[successor].add(key)

    # helper function for displaying the chord ring
    def print_ring(self):
        # Print the Chord ring
        print("Chord Ring:")
        for node_id in sorted(self.nodes.keys()):
            print(f"Node {node_id}: {self.nodes[node_id]}")


with open('books.json', 'r') as json_file:
    books_data = json.load(json_file)

# Extract books list from JSON data
books = books_data['books']

# Example usage
chord = ChordHash(size=8)

# Insert keys into the Chord ring
for book in books:
    chord.insert_key(book)

# Print the Chord ring
chord.print_ring()
