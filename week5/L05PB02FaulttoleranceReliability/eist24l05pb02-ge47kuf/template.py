import json

class KVStore:
    def __init__(self, initial_state):
        self.state = initial_state.copy()

    def __str__(self):
        return str(self.state)

    def apply_operation(self, operation):
        """Applies a given operation to the state."""
        key = operation["key"]
        if operation["action"] == "set":
            self.state[key] = operation["value"]
        elif operation["action"] == "delete":
            self.state.pop(key, None)

    def compare_states(self, other_state):
        """Compares a given state to the current state in the KVStore."""
        return self.state == other_state

class Logger:
    @staticmethod
    def generate_undo_log(operations, initial_state):
        undo_operations = []
        for op in reversed(operations):
            key = op["key"]
            if op["action"] == "set" and key in initial_state:
                undo_operations.append({"action": "set", "key": key, "value": initial_state[key]})
            elif op["action"] == "delete" and key in initial_state:
                undo_operations.append({"action": "set", "key": key, "value": initial_state[key]})
            elif op["action"] == "set" and key not in initial_state:
                undo_operations.append({"action": "delete", "key": key})
        return undo_operations

    @staticmethod
    def write_undo_log(file_name, undo_operations):
        with open(file_name, "w") as file:
            for op in undo_operations:
                file.write(json.dumps(op) + "\n")

    @staticmethod
    def apply_log(file_name, store: KVStore):
        with open(file_name, 'r') as file:
            for line in file:
                operation = json.loads(line)
                store.apply_operation(operation)

def main():
    # Step 1 - Define the initial KV-store state
    print("Initial KV-store state determined.")
    
    initial_kv_store = {
        "name": "John",
        "age": 25,
        "city": "New York",
        "salary": 50000,
        "status": "active"
    }

    # Step 2 - Define a list of operations to be applied to the KV-store
    operations = [{'action': 'delete', 'key': 'status'},
                  {'action': 'delete', 'key': 'salary'},
                  {'action': 'delete', 'key': 'city'},
                  {'action': 'set', 'key': 'name', 'value': 'Mark'},
                  {'action': 'set', 'key': 'age', 'value': 28},
                  {'action': 'set', 'key': 'country', 'value': 'USA'},
                  {'action': 'delete', 'key': 'position'},
                  {'action': 'delete', 'key': 'department'},
                  {'action': 'set', 'key': 'gender', 'value': 'male'},
                  {'action': 'delete', 'key': 'email'}]
    
    print("Operations:", operations)
    
    kv_store = KVStore(initial_kv_store)
    
    print("Operations applied to kv store.")
    for op in operations:  # call fuer jede operation in operations liste auf und apply es
        # TODO: Apply operations to the KV-store
        kv_store.apply_operation(op)

    comparison_kv_store = kv_store.state.copy()

    # Step 3 - Generate and write Undo Log
    print("Undo log generation..")
    # TODO: Generate undo log and write it to undo log file = einfach ein string als file
    undoOperation = Logger.generate_undo_log(operations, initial_kv_store)
    Logger.write_undo_log("undoLogFile", undoOperation)

    print("KV store after operations:", comparison_kv_store)

    # Step 4 - Apply Undo Log to the KV-store
    print("After applying undo log:")
    ###### TODO: Apply Undo Log here ###############
    Logger.apply_log("undoLogFile", kv_store.state)
    # undoLog angewendet um um operation rueckgaengig zu machen
    print(kv_store.state)

    # Step 5 - Apply Redo Log to the KV-store
    print("After applying redo log:")
    ###### TODO: Apply Redo Log here ###############
    Logger.apply_log("undoLogFile", kv_store.state)
    print(kv_store.state)

    # Step 6 - Comparison of initial state and the state after the log files
    if kv_store.compare_states(comparison_kv_store):
        print("Success! The final state matches the initial state.")
    else:
        print("Error: The final state does not match the initial state.")

if __name__ == "__main__":
    main()
