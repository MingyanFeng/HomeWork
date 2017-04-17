	/*
	Given a collection of candidate numbers (C) and a target number (T), 
	find all unique combinations in C where the candidate numbers sums to T.

	Each number in C may only be used once in the combination.

	Note:
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
	A solution set is: 
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]
	*/
public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // initialize results
        List<List<Integer>> results = new ArrayList<>();
        
        // corner case
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        
        // sort the candidates, after sorting, the duplicate elements are neighboors
        Arrays.sort(candidates);
        
        // use bfsHelper to find all possible combinations, add the combinations into results
        dfsHelper(candidates, 0, target, new ArrayList<Integer>(), results);
        // return results
        return results;
    }
    
    // dfsHelper
    // find all possible combinations, add the combinations into results
    // next layer
    private void dfsHelper(int[] nums, 
                           int startIndex, 
                           int remainTarget, 
                           List<Integer> combination, 
                           List<List<Integer>> results) {
       // base case 
       if (remainTarget == 0) {
           results.add(new ArrayList<Integer>(combination));
           return;
       }
       
       // current layer
       for (int i = startIndex; i < nums.length; i++) {
            // Remove duplicate
            if (i != startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            // remainTarget shoud be bigger than nums[i]
            if (nums[i] > remainTarget) {
                break;
            }
            combination.add(nums[i]);
            // !!! NOTE: here, we should let startIndex == i + 1 !!!
            // Each number in C may only be used once in the combination, we can not start from StarIndex again
            dfsHelper(nums, i + 1, remainTarget - nums[i], combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}

    /*
	搜索的时间复杂度：O(答案总数 * 构造每个答案的时间), 可以用一个代数式O(s)来代表答案总数
	Time Complexity: O(s * n), s == # of solutions, n == candidates.length;
	Space Complexity: O(n), combination is O(n)
	*/