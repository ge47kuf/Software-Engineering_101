import json
import pickledb


class ShardedDatabase:
    # Read books from JSON file

    def __init__(self):
        self.num_nodes = 10
        #  nodes ist eine liste von nodes nummeriert
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
            self.nodes[node_index].set(book, None)
            self.nodes[node_index].dump()

    def check_if_book_exists(self, book_name):
        node_index = self.hash_key(book_name)
        if self.nodes[node_index].exists(book_name):
            print(f"The book '{book_name}' is stored in database node {node_index}.")
        else:
            print(f"The book '{book_name}' is not found in the database.")
    
    ERROR_MESSAGE_INVALID_NODE = "The following Node doesn't exist."
    ERROR_MESSAGE_ALREADY_EMPTIED_NODE = "Node {} had already been emptied."
    INFO_MESSAGE_EMPTIED_NODE = "Node {} has been emptied."

    # TODO #1 implement empty_node method to delete all the data inside a particular
    def empty_node(self, node_index):
        #  check if index valid
        if 0 <= node_index <= 9:
            keys = list(self.nodes[node_index].getall())
            for key in keys:
                self.nodes[node_index].rem(key)  # remove key from nodes[index]
            self.nodes[node_index].dump()  # write nodes[index] back to db, safe the change
            return "Node {} has been emptied.".format(node_index)
        else:
            return "The following Node doesn't exist."

    # TODO #2 implement empty_nodes method to empty a set of nodes
    def empty_nodes(self, nodes_to_empty):
        for node in nodes_to_empty:
            self.empty_node(node)


with open('books.json', 'r') as json_file:
    books_data = json.load(json_file)

# Extract books list from JSON data
books = books_data['books']

sharded_db = ShardedDatabase()

