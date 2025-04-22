import json
import pickledb


class ShardedDatabase:
    def __init__(self):
        self.num_nodes = 10
        self.nodes = {i: pickledb.load(f"database_node_{i}.db", False) for i in range(0, 10)}
        self.store_books()

    def hash_key(self, book):
        # TODO : Hash the received key following the instructions provided
        first_letter = book[0]
        #  if letter not in alphabet -> return 9
        if not first_letter.isalpha():
            return 9
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

    def store_books(self):
        for book in books:
            # Map study courses to hash-modulo keys
            node_index = self.hash_key(book)
            self.nodes[node_index].set(book, node_index)
            self.nodes[node_index].dump()


with open('books.json', 'r') as json_file:
    books_data = json.load(json_file)

# Extract books list from JSON data
books = books_data['books']

# TODO : Initiliase a class object to create Database nodes of Pickledb
# shardedDatabase = ShardedDatabase.__init__() ist falsch dadie innit funktion gecallt wird
shardedDatabase = ShardedDatabase()

