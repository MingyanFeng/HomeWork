/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

public class Solution {
// Version 1: 自己想的，运行比较慢
    public boolean isValid(String s) {
        /*
        Convert s into charArray, put s char by char into a stack;
        If charS[i] == '(' or '[' or '{', keep iterating the charS;
        If charS[i] == ')' or ']' or '}', pop stack, and compare the poped element with charS[i],
            if stack is empty, reutrn false;
            otherwise,
                if they're not a valid pair, return false;
                otherwise, if they're a valid pair, continue;
        Check if the stack is empty after finishing the traversal;
            if it's empty, return ture; 
            otherwise, return false;
        */
        
        // Corner case
        if (s == null || s.length() < 2) {
            return false;
        }
        
        // Convert s into charArray
        char[] charS = s.toCharArray();
        
        // Initialize a stack
        Deque<Character> stack = new ArrayDeque<>();
        
        // Iterate the input s
        for (int i = 0; i < charS.length; i++) {
            if (charS[i] == '(' || charS[i] == '[' || charS[i] == '{') {
                stack.push(charS[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char temp = stack.pop();
                    if (temp == '(' && charS[i] == ')' || 
                        temp == '[' && charS[i] == ']' ||
                        temp == '{' && charS[i] == '}') {
                        continue;        
                    } else {
                        return false;
                    }
                }
            }
            
        }
        // Finish iterating the charS, check if the stack is empty
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
    Time Complexity: O(n), n == s.length();
    Space Complexity: O(n), charS == O(n) extra space, stack's worst case == O(n) extra space, i == O(1) extra space, O(n) + O(n) + O(1) ~ O(n);
    */
}