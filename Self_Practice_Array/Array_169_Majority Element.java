/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
*/

public class Solution {
// Version 1: HashMap 自己想的
    public int majorityElement(int[] nums) {
        // Corner case
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        /*
        Use a HashMap to store all elements of nums, the Key is nums[i], the Value is nums[i]'s appearance time.
        Initialize the HashMap, if HashMap containsKey(num[i]), let nums[i]'s appearance time + 1
        Find the element with appearance time > n/2 by looking up in the initialized HashMap
        */
        
        // Build a HashMap
        Map<Integer, Integer> map = new HashMap<>();
        
        // Initilaize the HashMap
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        
        // Finde the majority element
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) > (nums.length / 2)) {
                // Return the majority element
                return nums[i];
            }
        }
        // Return Integer.MIN_VALUE if not found
        return Integer.MIN_VALUE;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: Ideal is O(n), but real space complexity is bigger than O(n) due to the property of Hash Table
    */


// Version 2: One Time Traversal 看了disscuss后又写了一遍，感觉这个更好
    public int majorityElement(int[] nums) {
        // Assume that the majority element is nums[0], and the nums[0]'s apperance is 1
        int major = nums[0];
        int count = 1;
        
        /*
        Traverse the nums.
        If nums[i] == major, let count++;
        If nums[i] != major, let count--;
        If count == 0, just assume that majority element is nums[i], and let count++;
        */
        // !!! NOTE: i start from 1, not 0 !!!
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        // return major
        return major;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: O(1);
    */
}
/*
Version 2: Assume the First element is the result, then update it while doing traversal.
*/