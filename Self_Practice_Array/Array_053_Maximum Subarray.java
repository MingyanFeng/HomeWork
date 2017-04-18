/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.


More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

public class Solution {
// Version 1: Iterative 
//自己想到了假设nums[0]先是最大合，需要通过遍历来更新最大合；但是没有想明白如何抛弃前面副作用的部分，所以看了答案。
// 答案的思路很清晰: 发掘规律，遍历数组，把遍历过的数字加合，每当当前数字前面的数字之和小于0时，都应从当前数字重新开始计算，因为前面的部分只能起到副作用。
    public int maxSubArray(int[] nums) {
        // Corner case
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        /*
        Assume the maxSum is nums[0];
        Build an int variable called currSum, set currSum = 0;
        
        Traverse nums from 0 index, and let currSum += nums[i] each time;
        If currSum > maxSum, update maxSum;
        If currSum < 0, reset currSum to 0, because once currSum < 0, it only has negative contribution to maxSum;
        
        In this way, we can find maxSum after traversal;
        */
        
        // Assume the maxSum is nums[0];
        int maxSum = nums[0];
        // Build an int variable called currSum, set currSum = 0;
        int currSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if (currSum > maxSum) {
                maxSum = currSum;
            }
            // we have to firstly judge if currSum > maxSum, because if there're only negative numbers in the array, if we firstly reset currSum to 0, currSum will always > maxSum, but the maxSum should be an negative number in this case
            if (currSum < 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complxity: O(1)
    */

// 我也看了用DP的方法，感觉能看懂，但估计我自己背着答案应该写不出来。但是我想到了要建一个长度为nums.length的Array
// 这个是我参考的答案的链接：https://simpleandstupid.com/2014/10/28/maximum-subarray-leetcode-%E8%A7%A3%E9%A2%98%E7%AC%94%E8%AE%B0/
// 这道题还需要用Devide & Conquer的方法，我没看懂答案，觉得有些难
}