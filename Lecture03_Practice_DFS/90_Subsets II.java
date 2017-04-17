	/*
	Given a collection of integers that might contain duplicates, nums, return all possible subsets.

	Note: The solution set must not contain duplicate subsets.

	For example,
	If nums = [1,2,2], a solution is:

	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	]
	*/

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        // after soring, duplicates elements are neighboors
        Arrays.sort(nums);
        
        dfsHelper(nums, 0, new ArrayList<Integer>(), results);
        
        return results;
    }
    
    private void dfsHelper(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> results) {
        // deep copy subset & add it to results
        results.add(new ArrayList<Integer>(subset));
        
        for (int i = startIndex; i < nums.length; i++) {
            // remove duplicate, if i > startIndex, and nums[i] == nums[i - 1], means nums[i] and nums[i - 1] are same elements. 
            // And we need to make sure that we are in bound, so add a condition: i != 0, which makes sure that nums[i - 1] is valid.
            /*
			   We can turn if(i != 0 && nums[i] != nums[i - 1] && i > startIndex)
			   into        if(i != startIndex && nums[i] != nums[i - 1])
            */
            //if (i != 0 && nums[i] == nums[i - 1] && i > startIndex) {
			if (i != startIndex && nums[i] != nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            dfsHelper(nums, i + 1, subset, results);
            subset.remove(subset.size() - 1);
        }
    }
}
/*
Time Complexity: O(2^n), n == nums.length;
Space Complexity: O(n)
*/