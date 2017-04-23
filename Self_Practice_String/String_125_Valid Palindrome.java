/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

public class Solution {
// Version 1: 自己想的，有重复代码，需要改进
    public boolean isPalindrome(String s) {
        /*
        Use two pointers, start and end;
        */
        
        // Corner case
        if (s == null || s.length() == 0) {
            return true;
        }
        
        s = s.trim();
        s = s.toLowerCase();
        
        int start = 0;
        int end = s.length() - 1;
        
        while(start < end) {
            if (Character.isLetterOrDigit(s.charAt(start)) && Character.isLetterOrDigit(s.charAt(end))) {
                if (s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            } else if (!Character.isLetterOrDigit(s.charAt(start)) && Character.isLetterOrDigit(s.charAt(end))) {
                start++;
            } else if (Character.isLetterOrDigit(s.charAt(start)) && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }
    /*
    Time Complexity: O(n), n == s.length();
    Space Complexity: O(1), start + end == O(1) extra space;
    */


// Version 2: 参考了答案
    public boolean isPalindrome(String s) {
        // Corner case
        if (s == null || s.length() == 0) {
            return true;
        }
        
        s = s.trim();
        s = s.toLowerCase();
        
        int start = 0;
        int end = s.length() - 1;
        
        while (start <= end) {
        	// 对于start，只需要判断start即可，无需再判断end
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            // 对于end，只需要判断end即可，无需再判断start
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            // 走到这里说明，s.charAt(start)和s.charAt(end)肯定都是letter或者digit了，否则过不了上面两个for循环
            // 所以只需要判断s.charAt(start)和s.charAt(end)是否相等，并更新，即可
            } else {
                if(s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }
    /*
    Time Complexity: O(n), n == s.length();
    Space Complexity: O(1), start + end == O(1) extra space;
    */
}