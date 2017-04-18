/*
Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.
*/

public class Solution {
    public int[] plusOne(int[] digits) {
// 想到了999这种情况，但是对于普通的情况，如何判断某一位是否为9，或是否小于9，没能想出来怎么做，所以就看了答案，然后自己写了一遍
        
        /*
        Travers the digits !!! BACKWARDS !!!
        If digits[i] < 9, let digits[i] + 1, return digits;
        Otherwise, which means digits[i] == 9, let digits[i] == 0, keep i move forward until find a digit[i] < 9;
        
        If all digitis[i] == 9, we need to constract a new int[] result, and its length should be digits.length + 1, and set result[0] = 1;
        */
        
        // Traverse the digits from end to start, in BACKWARDS way
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        
        // Corner case: each digit in digits is 9, like 999
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
    /*
    Time Compleixty: O(n), n == digits.length;
    Space Complexity: O(1), i == O(1) extra space;
    */
}