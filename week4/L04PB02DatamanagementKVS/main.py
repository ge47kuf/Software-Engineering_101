from filefolder import FileFolder
from random import randint, sample

if __name__ == "__main__":
  source = "./filefolder.sqlite"
  cap = 4

  with FileFolder(source=source, cap=cap) as ff:
    # put items into the folder
    print("---- PUT ----")
    for i in range(cap):  
      file, content = f"file {i}", f"content {i}"
      print(f"Inserting {file}:", ff.put(file, content))
    print("Inserted items", ff.list())

    # update a random file
    i = randint(0, cap-1)
    file, content = f"file {i}", f"content {i}"  
    print(f"Updating file {i}:", ff.put(file, content + " updated"))
    print("Items", ff.list())
  
    # get 2 random files
    print("---- GET ----")
    for i in sample(range(cap), 2):
      file = f"file {i}"
      print(f"Getting file {i}", ff.get(file))
  
    print("---- REMOVE ----")
    # remove two random files
    for i in sample(range(cap), 2):
      file = f"file {i}"
      print(f"Deleting file {i}:", ff.remove(file))
    print("Remaining items:", ff.list())
