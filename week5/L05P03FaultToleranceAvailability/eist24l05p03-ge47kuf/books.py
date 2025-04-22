import copy
import json
import pickledb


class ShardedDatabase:
    # Read books from JSON file

    def __init__(self):
        self.num_nodes = 10
        self.nodes = {i: pickledb.load(f"database_node_{i}.db", False)
                      for i in range(0, 10)}
        self.store_books()

    def hash_key(self, book):
        # For this example, we determine the node based on the first letter of the key

        if not book[0].isalpha():
            return 9
        first_letter = book[0].upper()
        if 'A' <= first_letter <= 'C':
            return 0
        elif 'D' <= first_letter <= 'F':
            return 1
        elif 'G' <= first_letter <= 'I':
            return 2
        elif 'J' <= first_letter <= 'L':
            return 3
        elif 'M' <= first_letter <= 'O':
            return 4
        elif 'P' <= first_letter <= 'R':
            return 5
        elif 'S' <= first_letter <= 'U':
            return 6
        elif 'V' <= first_letter <= 'X':
            return 7
        elif 'Y' <= first_letter <= 'Z':
            return 8
        else:
            return -1

    def store_books(self):
        for book in books:
            # Map study courses to hash-modulo keys
            node_index = self.hash_key(book)
            self.nodes[node_index].set(book, node_index)
            self.nodes[node_index].dump()

    def check_if_book_exists(self, book_name):
        node_index = self.hash_key(book_name)
        if self.nodes[node_index].exists(book_name):
            print("The book ", book_name, "is stored in database node ", node_index)
        else:
            print("The book ", book_name, "is not found in the database.")

    ERROR_MESSAGE_INVALID_NODE = "The following Node doesn't exist."
    ERROR_MESSAGE_ALREADY_EMPTIED_NODE = "Node {} had already been emptied."
    INFO_MESSAGE_EMPTIED_NODE = "Node {} has been emptied."

    def empty_node(self, node_index):
        if 0 <= node_index <= 9:
            # Clear all entries in the specified node
            all_keys = list(self.nodes[node_index].getall())

            if len(all_keys) == 0:
                return self.ERROR_MESSAGE_ALREADY_EMPTIED_NODE.format(node_index)

            for key in all_keys:
                self.nodes[node_index].rem(key)

            # Save the changes
            self.nodes[node_index].dump()
            return self.INFO_MESSAGE_EMPTIED_NODE.format(node_index)

        else:
            return self.ERROR_MESSAGE_INVALID_NODE

    def empty_nodes(self, nodes_to_empty):
        messages = []
        for node_index in nodes_to_empty:
            messages.append(self.empty_node(node_index))
        return messages

    # Todo 1: implement this method as stated in the exercise description
    def doesDBContainKey(self, key: str):
        for node in self.nodes.values():
            if node.exists(key):
                return True
        return False
    
    # Todo 2: implement this method as stated in the exercise description
    def doesDBContainKeys(self, keys: list):
        for key in keys:
            if not self.doesDBContainKey(key):
                return False
        return True

    ERROR_MESSAGE_INVALID_DELTA = "The values still in the database are not what they should be"
    replicate_nodes = None

    # Todo 3: implement this method as stated in the exercise description
    def empty_nodes_check_remaining(self, nodes_to_empty=None):
        if nodes_to_empty is None:
            nodes_to_empty = []

        original_data = {i: {key: self.nodes[i].get(key) for key in self.nodes[i].getall()}
                         for i in range(self.num_nodes)}

        for node_index in nodes_to_empty:
            self.empty_node(node_index)

        remaining_data = []
        deleted_data = []

        for node_index, data in original_data.items():
            if node_index in nodes_to_empty:
                for key, value in data.items():
                    if self.doesDBContainKey(key):
                        raise Exception(f"Error: Key {key} should have been deleted but still exists.")
                    deleted_data.append(value)
            else:
                for key, value in data.items():
                    if not self.doesDBContainKey(key):
                        raise Exception(f"Error: Key {key} should not have been deleted but does not exist.")
                    remaining_data.append(value)

        return remaining_data, deleted_data


    # Todo 4: implement this method as stated in the exercise description
    def create_replicates(self):
        return

    # Todo 5: implement this method as stated in the exercise description
    def recover_node(self, node_index):
        return

    # Todo 6: implement this method as stated in the exercise description
    def recover_nodes(self, nodes_to_recover):
        return

with open('books.json', 'r') as json_file:
    books_data = json.load(json_file)

# Extract books list from JSON data
books = books_data['books']

sharded_db = ShardedDatabase()

sharded_db.create_replicates()

nodes_to_be_emptied = [3,4]
still_available, deleted = sharded_db.empty_nodes_check_remaining(nodes_to_be_emptied)
print("Still available ", still_available)
print("Deleted ", deleted)

sharded_db.recover_nodes(nodes_to_be_emptied)

for node_index in nodes_to_be_emptied:
    original_contents = list(sharded_db.nodes[node_index].getall())
    print(original_contents)
