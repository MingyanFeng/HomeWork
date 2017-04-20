/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/

public class Solution {
    public String decodeString(String s) {
        // Corner case
        if (s == null || s.length() == 0) {
            return new String();
        }
        
        /*
        Use stack, see diagram
        */
        
        Deque<Integer> intStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        
        int count = 0;
        
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                count = count * 10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(count);
                strStack.push(cur);
                cur = new StringBuilder();
                count = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (count = intStack.pop(); count > 0; count--){
                    cur.append(tmp);
                } 
            } else cur.append(ch);
        }
        
        return cur.toString();
    }
    /*
    Time Complexity: O(n), n == s.length();
    Space Complexity: O(n)
    */
}