	/*
	Given a collection of numbers that might contain duplicates, return all possible unique permutations.

	For example,
	[1,1,2] have the following unique permutations:
	[
	  [1,1,2],
	  [1,2,1],
	  [2,1,1]
	]
	*/

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // initialize results
        List<List<Integer>> results = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) {
            return results;
        }
        // sort input, in order to remove duplicates
        Arrays.sort(nums);
        // dfsHelper
        dfsHelper(nums, new boolean[nums.length], new ArrayList<Integer>(), results);
        // return results
        return results;
    }
    
    private void dfsHelper(int[] nums, boolean[] isVisited, List<Integer> path, List<List<Integer>> results) {
        // base case
        if (path.size() == nums.length) {
            results.add(new ArrayList<Integer>(path));
            return;
        }
        // current layer
        for (int i = 0; i < nums.length; i++) {
            // remove duplicates
            if (isVisited[i] || i != 0 && nums[i] == nums[i - 1] && !isVisited[i - 1]) {
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