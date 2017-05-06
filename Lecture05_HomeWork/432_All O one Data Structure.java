/*
432. All O`one Data Structure

Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".

Challenge: Perform all these in O(1) time complexity. 
*/

public class AllOoneDataStructure {
    private class Bucket { // each Bucket contains all the keys with the same count
        public int count;
        public Set<String> keySet;
        public Bucket next;
        public Bucket pre;
        public Bucket(int cnt) {
            this.count = cnt;
            this.keySet = new HashSet<>();
        }
    }

    // maintain a doubly linked list of Buckets
    private Bucket head;
    private Bucket tail;
    // for accessing a specific Bucket among the Bucket list in O(1) time
    private Map<Integer, Bucket> countBucketMap;
    // keep track of count of keys
    private Map<String, Integer> keyCountMap;

    public AllOne() {
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        countBucketMap = new HashMap<>();
        keyCountMap = new HashMap<>();
    }
    
    public void inc(String key) { // Inserts a new key <Key> with value 1. Or increments an existing key by 1.
        if (keyCountMap.containsKey(key)) {
            changeKey(key, 1);
        } else {
            keyCountMap.put(key, 1);
            if (head.next.count != 1) 
                addBucketAfter(new Bucket(1), head);
            head.next.keySet.add(key);
            countBucketMap.put(1, head.next);
        }
    }
    // Time complexity: O(1)
    
    public void dec(String key) { // Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
        if (keyCountMap.containsKey(key)) {
            int count = keyCountMap.get(key);
            if (count == 1) {
                keyCountMap.remove(key);
                removeKeyFromBucket(countBucketMap.get(count), key);
            } else {
                changeKey(key, -1);
            }
        }
    }
    // Time complexity: O(1)
    
    public String getMaxKey() { // Returns one of the keys with maximal value.
        return tail.pre == head ? "" : (String) tail.pre.keySet.iterator().next();
    }
    // Time complexity: O(1)
    
    public String getMinKey() { // Returns one of the keys with Minimal value.
        return head.next == tail ? "" : (String) head.next.keySet.iterator().next();        
    }
    // Time complexity: O(1)

    private void changeKey(String key, int offset) { // make change on given key according to offset
        int count = keyCountMap.get(key);
        keyCountMap.put(key, count + offset);
        Bucket curBucket = countBucketMap.get(count);
        Bucket newBucket;
        if (countBucketMap.containsKey(count + offset)) {
            // target Bucket already exists
            newBucket = countBucketMap.get(count + offset);
        } else {
            // add new Bucket
            newBucket = new Bucket(count + offset);
            countBucketMap.put(count + offset, newBucket);
            addBucketAfter(newBucket, offset == 1 ? curBucket : curBucket.pre);
        }
        newBucket.keySet.add(key);
        removeKeyFromBucket(curBucket, key);
    }
    
    private void removeKeyFromBucket(Bucket bucket, String key) {
        bucket.keySet.remove(key);
        if (bucket.keySet.size() == 0) {
            removeBucketFromList(bucket);
            countBucketMap.remove(bucket.count);
        }
    }
    
    private void removeBucketFromList(Bucket bucket) {
        bucket.pre.next = bucket.next;
        bucket.next.pre = bucket.pre;
        bucket.next = null;
        bucket.pre = null;
    }
    
    private void addBucketAfter(Bucket newBucket, Bucket preBucket) { // add newBucket after preBucket
        newBucket.pre = preBucket;
        newBucket.next = preBucket.next;
        preBucket.next.pre = newBucket;
        preBucket.next = newBucket;
    }

}