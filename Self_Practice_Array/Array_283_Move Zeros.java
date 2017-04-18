/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

public class Solution {
// Version 1 自己想的
    public void moveZeroes(int[] nums) {
        // Corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        /*
        Use two pointers, slow and fast, slow start from 0, fast start from 1.
        If nums[slow] == 0 && nums[fast] != 0, swap nums[slow] and nums[fast], and move slow and fast forward.
        If nums[slow] == 0 && nums[fast] == 0, only move fast forward.
        If nums[slow] != 0 && nums[fast] != 0, move both slow and fast forward.
        If nums[slow] != 0 && nums[fast] != 0, move both slow and fast forward.
        Hence, there's 3 different situations.
        */
        
        int slow = 0;
        int fast = 1;
        
        while (slow < nums.length && fast < nums.length && slow < fast) {
            if (nums[slow] == 0 && nums[fast] != 0) {
                nums[slow++] = nums[fast];
                nums[fast++] = 0;
            } else if (nums[slow] == 0 && nums[fast] == 0) {
                fast++;
            } else {
                slow++;
                fast++;
            }
        }
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: O(1);
    */


// Version 2 课上答案，感觉这个更好
    public void moveZeroes(int[] nums) {
        // Corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        /*
        Use two pointers, but each element only get a one-time replacement.
        Replace every 0 while traverse nums, after each replacement, move both slow and fast.
        After traversal, replace [ nums[slow] to nums[nums.length - 1] ] to 0.
        */
        
        // slow represents the index of results
        int slow = 0;
        
        for (int fast = 0; fast < nums.length; fast++) {
            // if nums[fast] != 0, let nums[slow] = nums[fast], and let slow++ (move slow forward)
			// otherwise, only keep fast++, the for loop will do this
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        // after fast has finished iterating, we change elements in [ nums[slow], nums[nums.length - 1] ] to 0
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: O(1);
    */
}