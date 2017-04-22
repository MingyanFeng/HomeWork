/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
*/

public class Solution {
// Version 1: 自己想的
    public String reverseVowels(String s) {
        // Corner case
        if (s == null || s.length() == 0) {
            return s;
        }
        
        // Convert s into charArray
        char[] charS = s.toCharArray();
        
        // Two Pointers
        int left = 0;
        int right = s.length() - 1;
        
        // reverseHelper: reverse vowels in charS
        reverseHelper(charS, left, right);
        
        // return a new String
        return new String(charS);
    }
    
    private void reverseHelper(char[] charS, int left, int right) {
        if (charS == null || charS.length == 0) {
            return;
        }
        
        while (left < right) {
            boolean isLeftVowel = isVowel(charS[left]);
            boolean isRightVowel = isVowel(charS[right]);
            
            if (!isLeftVowel && isRightVowel) {
                left++;
            } else if (isLeftVowel && !isRightVowel) {
                right--;
            } else if (isLeftVowel && isRightVowel) {
                char temp = charS[left];
                charS[left] = charS[right];
                charS[right] = temp;
                left++;
                right--;
            } else {
                left++;
                right--;
            }
        }
    }
    
    private boolean isVowel(char i) {
        return i == 'a' || i == 'A' || i == 'e' || i == 'E' || i == 'i' || i == 'I' || i == 'o' || i == 'O' || i == 'u' || i == 'U';
    }
    /*
    Time Complexity: O(n), n == s.length();
    Space Compelxity: O(1), left + right == O(1) extra space;
    */

    /* 
	Two Pointers, 面对面异向相遇问题
    */

// Version 2： 参考了答案，自己背着答案写了一遍，思路相同，利用了String现有的方法，并且用while循环替代了if-else判断，使代码更加简洁
	public String reverseVowels(String s) {
        // Corner case
        if (s == null || s.length() < 2) {
            return s;
        }
        
        // Construct a String called vowels, contains all vowels in both lower case and upper case
        String vowels = "aeiouAEIOU";
        
        // Convert s into charArray
        char[] charS = s.toCharArray();
        
        // Two pointers
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Move left forward if char[left] is not a vowel
            // !!! NOTE: (left < right) condition should add to nested while loop!!!
            // !!! NOTE: vowels.contains(CharSequence s), charS[left] or charS[right] is char, char cannot be converted to CharSequencenot CharSequence
                // while (left < right && !vowels.contains(charS[left])) { // !!! WRONG
            while (left < right && !vowels.contains(charS[left] + "")) { // RIGHT
                left++;
            }
            // Move right backward if char[right] is not a vowel
            while (left < right && !vowels.contains(charS[right] + "")) {
                right--;
            }
            
            // reverse vowels
            char temp = charS[left];
            charS[left] = charS[right];
            charS[right] = temp;
            
            // move left and right
            left++;
            right--;
        }
        return new String(charS);
    }
    /*
    Time Complexity: O(n), n == s.length();
    Space Complexity: O(1), left + right == O(1) extra space;
    */

    /* 
	Two Pointers, 面对面异向相遇问题
    */
}