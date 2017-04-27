/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution and you may not use the same element twice.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        /*
        Use two pointers, left and right, left start at 0 index, right start at number.length - 1 index;
        If numbers[left] + numbers[right] > target, right--;
        Else if numbers[left] + numbers[right] < target, left++;
        Else, return left + 1, right + 1, !!! AND BREAK THE WHILE LOOP !!!
        */
        
        // Initialize result
        int[] result = new int[2];
        
        // Corner case
        if (numbers == null || numbers.length < 2) {
            return result;
        }
        
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            if (numbers[left] + numbers[right] < target) {
                left++;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                result[0] = left + 1;
                result[1] = right + 1;
                // !!! BREAK THE WHILE LOOP !!!
                break;
            }
        }
        
        return result;
    }
    /*
    Time Complexity: O(n), n == numbers.length;
    Space Complexity: O(1), left + right == O(1) extra space;
    */
}
/*
Method: 前后指针，面对面相遇问题；注意当找到答案的时候，break while loop.
*/