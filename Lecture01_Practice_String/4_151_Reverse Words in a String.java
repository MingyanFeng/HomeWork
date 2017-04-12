public class Solution {
    // use 3 step reverse
    public String reverseWords(String s) {
        // corner case
        if (s == null) {
            return null;
        }
        
        // preprocess, skip the whitespace on the beginning and end of s
        s = s.trim();
        // if s is "", return itself
        if (s.length() == 0) {
            return s;
        }
        
        // convert the input Sting into charArray
        char[] charS = s.toCharArray();
        // swap all chars
        swap(charS, 0, charS.length - 1);
        int startIndex = 0;
        for (int i = 0; i < charS.length; i++) {
            // reverse according to whitespace, each whitespace means then end of a word
            if (charS[i] == ' ') {
                swap(charS, startIndex, i - 1);
                // update next word's begining point
                while (charS[i] == ' ') {
                    i++;
                }
                startIndex = i;
            }
        }
        // covert charS into String
        return new String(charS);
    }
    // swap helper
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

/*
This way keeps all spaces between words.
E,g: Input: "   a    b  "
     Output: "b    a"

But what if the expected output is "b a" (only keep one space between each words)
*/