	/*
	Given a collection of distinct numbers, return all possible permutations.

	For example,
	[1,2,3] have the following permutations:
	[
	  [1,2,3],
	  [1,3,2],
	  [2,1,3],
	  [2,3,1],
	  [3,1,2],
	  [3,2,1]
	]
	*/

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // initialize results
        List<List<Integer>> results = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) {
            return results;
        }
        // dfsHelper
        dfsHelper(nums, new boolean[nums.length], new ArrayList<Integer>(), results);
        // return results
        return results;
    }
    
    private void dfsHelper(int[] nums, boolean[] isVisited, List<Integer> path, List<List<Integer>> results) {
        // base case
        if (path.size() == nums.length) {
            // !!! DEEP COPY !!!
            results.add(new ArrayList<Integer>(path));
            return;
        }
        
        // current layer
        for (int i = 0; i < nums.length; i++) {
            // we need to know whether current element has been visited
            // boolean type's default value is false
            if (isVisited[i]) {
                continue;
            }
            path.add(nums[i]);
            isVisited[i] = true;
            // next layer
            dfsHelper(nums, isVisited, path, results);
            // back tracking
            isVisited[i] = false;
            path.remove(path.size() - 1);
        }
    }
/*
Time Complexity: O(n!), n == nums.length;
Space Complexity: O(n)
*/
}