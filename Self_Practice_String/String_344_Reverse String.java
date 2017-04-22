/*
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
*/

public class Solution {
// Version 1: 自己想的
    public String reverseString(String s) {
        // Corner case
        if (s == null || s.length() == 0) {
            return s;
        }
        
        /*
        Convert s into char array;
        And use two pointers, left and right, lefts starts from 0 index, right starts from s.length() - 1 index;
        While left < right, swap characters on left and right;
        return new String(char array);
        */
        
        char[] charS = s.toCharArray();
        
        int left = 0;
        int right = charS.length - 1;
        
        swap(charS, left, right);
 
        return new String(charS);
    }
    
    private void swap(char[] charS, int left, int right) {
        while (left < right) {
            char temp = charS[left];
            charS[left] = charS[right];
            charS[right] = temp;
            left++;
            right--;
        }
    }
    /*
    Time Complexity: O(n), n == s.length();
    Space Complexity: O(1), left + right == O(1) extra space;
    */
}
/*
Two Pointers 面对面相遇问题，反向相遇
*/