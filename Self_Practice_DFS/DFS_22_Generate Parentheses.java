/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/

public class Solution {
    public List<String> generateParenthesis(int n) {
        // Initialize result
        List<String> results = new ArrayList<>();
      
        // Corner case
        if (n <= 0) {
            return results;
        }
        
        // dfsHelper
        dfsHelper(n, "", 0, 0, results);
      
        return results;
    }
  
    // Use a dfsHelper
    private void dfsHelper(int n, String sublist, int left, int right, List<String> results) {
        // Finish all possible combination
        if (sublist.length() == n * 2) {
            results.add(sublist);
            return;
        }
      
        // Since we need valid results, that means:
        // 1. # of left should be smaller than n;
        if (left < n) {
            dfsHelper(n, sublist + "(", left + 1, right, results);
        }
        // 2. the # of left should be larger than # of right;  
        if (right < left && right < n) {
            dfsHelper(n, sublist + ")", left, right + 1, results);
        }
    }
    /*
	Time Complexity: O(s * n), s == # of solutions, n == input argument
	Space Complexity: O(n)
    */
}