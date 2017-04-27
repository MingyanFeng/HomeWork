/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

public class Solution {
// Version 1: XOR, 我看了答案，然后自己写了一遍，思路部分我是复制的答案
    public int missingNumber(int[] nums) {
        /*
        The basic idea is to use XOR operation. 
        We all know that a^b^b =a, which means two xor operations with the same number will eliminate the number and reveal the original number.
        In this solution, I apply XOR operation to both the index and value of the array. 
        In a complete array with no missing numbers, the index and value should be perfectly corresponding( nums[index] = index);
        So in a missing array, what left finally is the missing number.
        */
        
        // Corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int result = 0;
        int i = 0;
        
        for (i = 0; i < nums.length; i++) {
            result = result ^ i ^ nums[i]; 
        }
/*
对于下面这句有疑问，我之前写的是return result; 没有AC
为什么要return result ^ i 呢？

另外一个答案里，
public int missingNumber(int[] nums) { //xor
    int res = nums.length;
    for(int i=0; i<nums.length; i++){
        res ^= i;
        res ^= nums[i];
    }
    return res;
}
res分了两步，第一步先^i, 第二步再^nums[i], 然后就直接return res;了
不太明白为什么分两步的时候，就可以直接return res; 但是如果直接result = result ^ i ^ nums[i]; 却要返回result ^ i;
*/       
        return result ^ i;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Compleixty: O(1), i == O(1) extra space;
    */
}