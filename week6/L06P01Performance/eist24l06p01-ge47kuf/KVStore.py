from KVCachingStore import KVCachingStore


##############################################################
# Example usage: you can test your implementation here
# You can modify the cache_policy to "fifo","lru","mru","lfu" or "mfu" and the cache_size.
kv_store = KVCachingStore(cache_size=4, cache_policy='mru')

# Here are some examples of how to get the value of a key
# and how to put a key-value pair in the cache in the following code.
# The key should be a string.

# kv_store.put("1","1")
# kv_store.put("2","2")
# kv_store.put("3","3")
# kv_store.put("4","4")
# kv_store.get("3")
# kv_store.print_cache_state()

##############################################################

# Close the KV store to save changes to disk
kv_store.close()
