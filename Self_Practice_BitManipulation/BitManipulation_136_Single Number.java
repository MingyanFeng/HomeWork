/*
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

public class Solution {
    public int singleNumber(int[] nums) {
        /*
        HashSet will use extra memory, we can use XOR to find the single number;
        */
        
        // Corner case
        if (nums == null || nums.length == 0) {
            return nums.length;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        
        int result = 0;
        
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Compleixty: O(1)
    */
}