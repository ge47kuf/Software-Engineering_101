from sqlitedict import SqliteDict


class FileFolder:
    '''
    In this exercise, we utilize `sqlitedict` to implement the `FileFolder`.
    A quickstart into the library can be found [here](https://pypi.org/project/sqlitedict/)
    '''

    def __init__(self, source="filefolder.sqlite", cap=10, empty=False) -> None:
        '''
        Initialize a file folder with sqlite backend.
        source: path to sqlite file
        cap: maximum number of items in the folder
        empty: if True, empty the folder
        '''
        # self.__db = this.db = SqliteDict(source)
        self.__db = SqliteDict(source) # lib-modul: schnittstellen zu DB ueber src kennzeichnet
        self.__cap = cap
        if empty:
            self.__db.clear()
            self.__db.commit()

    # DB soll richtig open und close werden, hier ueber enter und exit
    def __enter__(self):
        '''
        This method is called when using a file folder with the "with" statement.
        '''
        return self

    def __exit__(self, type, value, traceback) -> None:
        '''
        This method is called when exiting a "with" statement.
        '''
        self.close()

    def close(self) -> None:
        '''
        Close the folder.
        '''
        self.__db.close()

    def put(self, file: str, content: str) -> bool:
        '''
        *TODO*
        Put a new item into the folder.
        Return True if the item was successfully inserted.
        Note: 
        1. Take the folder capacity into account.
        2. Call self.__db.commit() method for persistant write
        '''
        # db voll?
        if len(self.__db) >= self.__cap:
            return False
        # insert value = content with key = file, kein check noetig: if file not exist -> neu file:content create
        self.__db[file] = content
        # db commit
        self.__db.commit()
        return True

    def get(self, file: str) -> str or None:
        '''
        *TODO*
        Get the content of an item from the folder.
        Return None if the item does not exist.
        '''
        return self.__db.get(file, None)

    def remove(self, file: str) -> str or None:
        '''
        *TODO*
        Remove an item from the folder.
        Return its content.
        Return None if the item does not exist.
        '''
        # remove = pop-operation in stack, pop return content or none if key not exist
        content = self.__db.pop(file, None)
        self.__db.commit()
        return content

    def list(self):
        '''
        *TODO*
        List all the items currently stored in the folder.
        Return a list of tuples of all items (name, content) in the folder.
        '''
        # name = key = file, content = value = db[file]
        # return [(key, self.__db[key]) for key in self.__db.keys()]

        # alternative ansatz:
        resultList = []  # leere liste
        for key in self.__db.keys():
            value = self.__db[key]
            resultList.append((key, value))
        return resultList
