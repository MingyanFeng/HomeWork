public class Solution {
    public boolean isPalindrome(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return true;
        }
        
        // preprocess of the input, ignoring whitespace, ignoring cases
        s = s.trim();
        s = s.toLowerCase();
        
        // we can use two pointers, left and right, compare each pointers's char, if one of the left != right, return false
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // since we only consider alphanumeric characters, let left++ if the char is not alphanumeric character 
            while (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                // if we cannot find alphanumeric character after the traversal of the input, return true
                if (left == s.length()) {
                    return true;
                }
            }
            // since we only consider alphanumeric characters, let right-- if the char is not alphanumeric character
            while (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // as long as there is a left-right pair that does not equals to each other, return false
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            // ohterwise, keep traversing, left++, right--
            } else {
                left++;
                right--;
            }
        }
        // after doing the traversal of the input, that means each left-right pair is equals to each other, is valid palindrome
        return true;
    }
}
/*
Time Complexity: O(n); n == s.length();

Space Complexity: O(1); left + right == extra O(1) space
*/