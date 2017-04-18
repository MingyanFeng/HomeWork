/*
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
*/

public class Solution {
    public int removeElement(int[] nums, int val) {
        // Corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        /*
        Use two pointers, slow and fast, slow start from 0 index, fast start from 0 index;
        Since the order of elements can be changed, we let the elements that not equals to val to replace elements that equals to val;
        
        If nums[fast] != val, let nums[slow] = nums[fast], and move both slow and fast forward;
        If nums[fast] == val, only move forward fast;
        
         s            s              s              s            s  
        [3,2,2,3] -> [3,2,2,3] -> [2,2,2,3] -> [2,2,2,3] -> [2,2,2,3]
         f              f              f              f
        
        Keep this way until fast == nums.length - 1
        */
        int slow = 0;
        
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: O(1);
    */
}