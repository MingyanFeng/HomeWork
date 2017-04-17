	/*
	Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
	find all unique combinations in C where the candidate numbers sums to T.

	The same repeated number may be chosen from C unlimited number of times.

	Note:
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.

	For example, given candidate set [2, 3, 6, 7] and target 7, 
	A solution set is: 
	[
	  [7],
	  [2, 2, 3]
	]
	*/

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // initialize results
        List<List<Integer>> results = new ArrayList<>();
        // corner case
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        // remove duplicates, and sort input at the same time.
        int[] nums = removeDuplicates(candidates);
        
        // use a dfsHelper to find all possible combination sum
        dfsHelper(nums, 0, target, new ArrayList<Integer>(), results);
        
        // return results
        return results;
    }
    
    // 1. definition, next layer
    // Find all unique combinations in candidate that sum of candidate is remainTarget
    // Add each valid conbination into results
    // 从nums中的startIndex开始，挑选一些数，放到combination中，且他们的和为remainTarget
    private void dfsHelper(int[] nums, 
                           int startIndex, 
                           int remainTarget, 
                           List<Integer> combination, 
                           List<List<Integer>> results) {
        // 3. exit, base case
        if (remainTarget == 0) {
            // deep copy
            results.add(new ArrayList<Integer>(combination));
            return;
        }
        // 2. split, current layer
        for (int i = startIndex; i < nums.length; i++) {
            // !!! NOTE: remainTarget - nums[i] cannot be negative integer
            // 意异常处理：remainTarget- nums[i]不能为负数
            if (nums[i] > remainTarget) {
                break;
            }
            // [] -> [2] 
            combination.add(nums[i]);

            //combination的和受限制，所以递归的时候，不能再传remainTarget形参，而应传remainTarget - nums[i].
            //因为一个数可以选很多次，所以每次搜索时，都要从startIndex开始，而不是startIndex + 1.
            // 把所有[2]开头的 剩余的和为remainTarget - nums[i] 的集合，都找到，丢到results里面
            // !!! 注意for loop中，i = startIndex, 不是0, 也不是i + 1 !!!
            dfsHelper(nums, i, remainTarget - nums[i], combination, results);

            // BackTracking, [2] -> []
            combination.remove(combination.size() - 1);
        }
    }
    
    private int[] removeDuplicates(int[] candidates) {
        // sort the input
        Arrays.sort(candidates);
        // remove duplicates using two pointers
        // index is the slower pointer, i is the faster pointer
        // index only moves forward when candidates[index] != candidates[i]
        // 遇到下一个与当前点 不同的值 时， 用不同的值 替换++index处的值
        int index = 0;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] != candidates[index]) {
                candidates[++index] = candidates[i];
            }
        }
        // we only return distinct elements, and we have total index + 1 distinct elements
        // nums只取前index + 1个不同的元素
        int[] nums = new int[index + 1];
        for (int i = 0; i < index + 1; i++) {
            nums[i] = candidates[i];
        }
        return nums;
    }
}
	/*
	搜索的时间复杂度：O(答案总数 * 构造每个答案的时间), 可以用一个代数式O(s)来代表答案总数
	Time Complexity: O(s * n), s == # of solutions, n == candidates.length;
	Space Complexity: O(n), combination is O(n)
	*/