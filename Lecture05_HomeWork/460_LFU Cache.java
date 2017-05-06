/*
460. LFU Cache

Design and implement a data structure for Least Frequently Used (LFU) cache. 
It should support the following operations: get and put. 
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted. 

Follow up:
    Could you do both operations in O(1) time complexity?

Example: 
    LFUCache cache = new LFUCache( 2 "capacity"  );

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.get(3);       // returns 3.
    cache.put(4, 4);    // evicts key 1.
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4
*/

public class LFUCache {
    private class KeyNode { // used to store each element's key, value and frequency
        public int key;
        public int val;
        public int freq;
        public KeyNode (int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    private class FreqNode { // used to store elements with same frequency
        public int freq;
        public FreqNode prev;
        public FreqNode next;
        public Set<KeyNode> set; // keep the insertion order
        public FreqNode (int freq, FreqNode prev, FreqNode next) {
            this.freq = freq;
            this.prev = prev;
            this.next = next;
            set = new LinkedHashSet<>();
        }
    }

    private int capacity; // capacity of the cache
    private Map<Integer, KeyNode> keyMap; // key: frequency; value: each element
    private Map<Integer, FreqNode> freqMap; // key: frequency; value: each element list node
    private FreqNode head; // head node of 
    // Space complexity: O(n)

    public LFUCache(int capacity) {
        this.head = null;
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (keyMap.containsKey(key)) {
            KeyNode keyNode = keyMap.get(key);
            int val = keyNode.val;
            increase(key, val);
            return val;
        }
        return -1;
    }
    // Time complexity: O(1)

    public void put(int key, int val) {
        if (this.capacity == 0) return;
        if (keyMap.containsKey(key)) {
            increase(key, val);
            return;
        }
        if (keyMap.size() == this.capacity) {
            removeKeyNode(head);
        }
        insertKeyNode(key, val);
    }
    // Time complexity: O(1)

    public void increase(int key, int val) { // increase freq of key, update val if necessary - yes
        KeyNode keynode = keyMap.get(key);
        // update val
        keynode.val = val;
        FreqNode freqnode = freqMap.get(keynode.freq);
        keynode.freq += 1;
        FreqNode nextFreqNode = freqnode.next;
        if (nextFreqNode == null) {
            nextFreqNode = new FreqNode(keynode.freq, freqnode, null);
            freqnode.next = nextFreqNode;
            freqMap.put(keynode.freq, nextFreqNode);
        }
        if (nextFreqNode != null && nextFreqNode.freq > keynode.freq) {
            nextFreqNode = insertFreqNodePlus1(keynode.freq, freqnode);
        }
        unlinkKey(keynode, freqnode);
        linkKey(keynode, nextFreqNode);
    }

    public void removeKeyNode(FreqNode fnode) { // remove the head's oldest node - yes
        KeyNode knode = fnode.set.iterator().next();
        unlinkKey(knode, freqMap.get(knode.freq));
        keyMap.remove(knode.key);
    }
    
    public void insertKeyNode(int key, int val) { // Inserts a new KeyNode<key, value> with freq 1. - yes
        KeyNode keynode = new KeyNode(key, val);
        keyMap.put(key, keynode);
        if (!freqMap.containsKey(1)) {
            FreqNode freqnode = new FreqNode(1, null, head);
            freqnode.next = head;
            if (head != null)   head.prev = freqnode;
            head = freqnode;
            freqMap.put(1, freqnode);
        }
        linkKey(keynode, freqMap.get(1));
    }

    public FreqNode insertFreqNodePlus1(int freq, FreqNode freqnode) { // insert a new freqnode with new freq after given "freqnode" - yes
        FreqNode newfnode = new FreqNode(freq, freqnode, freqnode.next);
        freqMap.put(freq, newfnode);
        if (freqnode.next != null)  freqnode.next.prev = newfnode;
        freqnode.next = newfnode;
        return newfnode;
    }
    
    public void unlinkKey(KeyNode keynode, FreqNode freqnode) { // Unlink keyNode from freqNode - yes
        freqnode.set.remove(keynode);
        if (freqnode.set == null || freqnode.set.size() == 0)     deleteFreqNode(freqnode);
    }
    
    public void linkKey(KeyNode keynode, FreqNode freqnode) { // Link keyNode to freqNode - yes
        freqnode.set.add(keynode);
    }
    
    public void deleteFreqNode(FreqNode freqnode) { // delete freqnode if there is no appending keynode under this freq - yes
        FreqNode prev = freqnode.prev, next = freqnode.next;
        if (prev != null)   prev.next = next;
        if (next != null)   next.prev = prev;
        if (head == freqnode)   head = next;
        freqMap.remove(freqnode.freq);
    }

}