/*

139. Word Break

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. 
You may assume the dictionary does not contain duplicate words. 
For example, given
    s = "leetcode",
    dict = ["leet", "code"]. 
Return true because "leetcode" can be segmented as "leet code". 

*/

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        /*
        use dynamic programming
        each index represents each position in the string
        each element represents whether the substring from the beginning to the current index can be segmented
        */
        boolean[] results = new boolean[s.length() + 1];
        // set up initial value
        results[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // try every possible segments in the substring (0, i)
                if (results[j] && wordDict.contains(s.substring(j, i))) {
                    results[i] = true;
                    break;
                }
            }
        }
        return results[s.length()];
    }
    /*
    Time complexity: O(n^2), n == s.length();
    Space complexity: O(n), n == s.length();
    */

    public static void main(String[] args) {
        
    }
}