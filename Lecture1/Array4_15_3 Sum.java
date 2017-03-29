public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) {
            return results;
        }
        // sort the input array
        Arrays.sort(nums);
        // 
        for (int i = 0; i + 2 < nums.length; i++) {
            // incase of duplicate result, we need to check if the neighboorhoods are the same element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // we let target number becomes 0 - nums[i]
            // so the question can be coverted into a "2 sum", and target is 0 - nums[i]
            int low = i + 1;
            int high = nums.length - 1;
            int target = 0 - nums[i];
            // find two elements whose sum is target by traversing the nums
            while (low < high) {
                if (nums[low] + nums[high] == target) {
                    results.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    low++;
                    high--;
                    // incase of duplicate result, we need to check if the neighboorhoods are the same element
                    while (low < high && nums[low] == nums[low - 1]) {
                        low++;
                    }
                    while (low < high && nums[high] == nums[high + 1]) {
                        high--;
                    }
                } else if (nums[low] + nums[high] < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return results;
    }
}
/*
Time Complexity: O(n); n == nums.length;

Space Complexity: O(1); low + high + target + i == extra O(1) constant space
*/