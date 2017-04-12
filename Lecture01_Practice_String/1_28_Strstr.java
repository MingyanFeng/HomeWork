public class Strstr {
    /*
    version 1 : two pointers
    */
    public int strStr(String haystack, String needle) {
        // corner case
        if (haystack == null || needle == null) {
            return -1;
        }
        
        // use two pointers
        // !!! NOTE: i < haystack.length() - needle.length() + 1, draw a diagram!
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j = 0;
            // if haystack.charAt(i + j) != needle.charAt(j), let i++;
            // until we find a char in haystack == char in needle, we begin to compare char by char.
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            // when we finished the nested for loop, means we might find the target
            // we need double check whether j == needle.length();
            // if j == needle.length(), return i(i is the start point of the needle)
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
    /*
    Time Complexity: O(mn); m == haystack.length(), n == needle.length();
    Space Complexity: O(1);
    */
}