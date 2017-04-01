public class Solution {
    public String reverseString(String s) {
        // corner case
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        // convert input string to charArray
        char[] charS = s.toCharArray();
        // swap the charArray's start and end, until finish iterating
        swap(charS, 0, charS.length - 1);
        return new String(charS);
    }
    
    private void swap(char[] ch, int start, int end) {
        while (start < end) {
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
    }
}
/*
Time Complexity: O(n); n == s.length();
Space Complexity: O(1); 
*/