/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

public class Solution {
// version 1: HashMap 自己想的
    public int[] twoSum(int[] nums, int target) {
        // Initialize result
        int[] result = new int[2];
        
        // Corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // Store all elements of nums[] into a HashMap
        Map<Integer, Integer> index = new HashMap<>();
        
        // When stroe elements, let Key of the HashMap to be nums[i], Value of the HashMap to be nums[i]'s index
        for (int i = 0; i < nums.length; i++) {
            index.put(nums[i], i);
        }
        
        // Look up in the HashMap, to see if the HashMap containsKey target - nums[i]
        for (int i = 0; i < nums.length; i++) {
            // If the HashMap containsKey target - nums[i], and HashMap.get(target - nums[i]) != i
            // Return i and HashMap.get(target - nums[i]), NOTE sequence of the output
            if (index.containsKey(target - nums[i]) && i != index.get(target - nums[i])) {
                result[0] = Math.min(i, index.get(target - nums[i]));
                result[1] = Math.max(i, index.get(target - nums[i]));
                // At the same time, since we've found the result, return result
                return result;
            }
            // If the HashMap doesn't containsKey target - nums[i], move i forward until i == nums.length - 1
        }
        
        // If we didn't find result after looking up the HashMap, return null;
        return null;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: ideal is O(n), but real space complexity is bigger than O(n) due to the property of HashTable (with separate chains)
    */
}