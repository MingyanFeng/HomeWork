/*
Given an array of integers and an integer k, 
find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
*/

public class Solution {
// HasnMap错误版本
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        /*
        Use HashMap to sotre each element's value as its Key, to store each element's index as its Value;
        Look up the HashMap, to see if nums[i] == nums[j] && Math.abs(HashMap.get(nums[i]) - HashMap.get(nums[j])) <= k
        */
        
        // Corner case
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return false;
        }
        
        // Build a HashMap
        Map<Integer, Integer> index = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
        // ！！！注意这里，如果有两个相同的elements，那么这个element的index会被更新为后出现的那个element的index ！！！
            index.put(nums[i], i);
        }
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && Math.abs(index.get(nums[i]) - index.get(nums[j])) <= k) {
                    return true;    
                }
            }
        }
        return false;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: Ideal is O(n), but real space complexity is bigger than O(n) due to the property of HashTable;
    */

// HashMap正确版本
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        /*
        Use HashMap to sotre each element's value as its Key, to store each element's index as its Value;
        Look up the HashMap, 
        If (!containsKey(nums[i])), we put nums[i] and its index in the HashMap;
        Otherwise, we check if (i - HashMap.get(nums[i]) > k), if yes, we update the index of nums[i]; 
                            otherwise, we've found target pair.
        */
        
        // Corner case
        if (nums == null || nums.length < 2) {
            return false;
        }
        
        // Build a HashMap
        Map<Integer, Integer> index = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (!index.containsKey(nums[i])) {
                index.put(nums[i], i);
            } else {
                if (i - index.get(nums[i]) > k) {
                    index.put(nums[i], i);
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: Ideal is O(n), but real space complexity is bigger than O(n) due to the property of HashTable;
    */
}