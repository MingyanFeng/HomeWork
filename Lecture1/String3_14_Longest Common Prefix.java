public class Solution {
    public String longestCommonPrefix(String[] strs) {
        // corner case
        if (strs == null || strs.length == 0) {
            return "";
        }
        // we can start from the first element(string) in strs, compare its prefix with next elements(strings), until the end of the strs
        String prefix = strs[0];
        // NOTE: i should starts form 1
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < strs[i].length() && j < prefix.length() && strs[i].charAt(j) == prefix.charAt(j)) {
                j++;
            }
            // if j == 0 after each compare, return ""
            if (j == 0) {
                return "";
            }
            // update the prefix after each comparation, NOTE that string's index starts form 0, hence we can use substring()
            /*
            public String substring(int beginIndex, int endIndex)
            Returns a new string that is a substring of this string. The substring begins at the specified beginIndex and extends to the character at index endIndex - 1. Thus the length of the substring is endIndex-beginIndex.
            */
            prefix = prefix.substring(0, j);
        }
        return prefix;   
    }
}
/*
Time Complexity: O(n); n == strs.length()

Space Complexity: O(1); i + j == O(1) extra constant space
*/