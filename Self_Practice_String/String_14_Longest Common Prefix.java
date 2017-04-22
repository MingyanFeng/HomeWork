/*
Write a function to find the longest common prefix string amongst an array of strings.

Tags: String
*/

public class Solution {
// Version 1: 看了之前提交的答案，背着答案自己写了一遍，之前没有理解透彻的substring在这次有了新的理解
    public String longestCommonPrefix(String[] strs) {
        /*
        Assume the longest common prefix is strs[0];
        Then, we traverse the strs from index 1 to index strs.length() - 1, and compare each element in strs with strs[0];
        On each comparison, update the longest common prefix, by cutting the strs[0], or adding the strs[0]. => a new index + substing() method;
        Note that: 
            1. After each comparison, if one of the element is "", a null string, the new index shoude == 0, and we return "" as longest common prefix;
            2. The length substring
                 public String substring(int beginIndex, int endIndex)
                 Returns a new string that is a substring of this string. The substring begins at the specified beginIndex and extends to the character at index endIndex - 1. Thus the length of the substring is endIndex-beginIndex;
        */
        
        // corner case
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Assume the longest common prefix is strs[0]
        String prefix = strs[0];
        
        // Traverse the strs from index 1 to strs.length() - 1, compare each element with prefix, update prefix
        for (int i = 1; i < strs.length; i++) {
            // int j is used to compare prefix and each element char by char
            int j = 0;
            while (j < strs[i].length() && j < prefix.length() && prefix.charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            if (j == 0) {
                return "";
            }
            prefix = prefix.substring(0, j);
        }
        return prefix;
    }
    /*
    Time Complexity: O(n); n == strs.length()
    这里分析有误：n 为所有string的总长度
    
    Space Complexity: O(1); i + j == O(1) extra constant space
    */

//
}
/*
Assume the First element is the result, then update it while doing traversal.
*/