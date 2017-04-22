/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/

public class Solution {
// Version 1: 自己想的
    public int lengthOfLastWord(String s) {
        // Corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // If the input is "  A   b      ", we should return 1 because b is the last word in the string
        s = s.trim();
        // Initialize the length to 0
        int length = 0;
        
        // Traverse the input string, update the length while doing the traverse
        for (int i = 0; i < s.length(); i++) {
            // Use temp to represent EACH WORD's length, temp should be reseted to 0 after it counted ONE WORD;
            int temp = 0;
            // Count each word's length;
            while (i < s.length() && Character.isLetter(s.charAt(i))) {
                temp++;
                i++;
            }
            // Update the value of length;
            length = temp;
        }
        return length;
    }
    /*
    Time Complexity: O(n), n == s.length();
    Space Complexity: O(1), i == O(1) extra space;
    */
}