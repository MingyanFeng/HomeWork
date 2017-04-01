public class Solution {
    public void rotate(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        // 3 step reverse
        k %= nums.length;
        // step 1: reverse all
        reverse(nums, 0, nums.length - 1);
        // step 2: reverse first k
        reverse(nums, 0, k - 1);
        // step 3: reverse last (n - k)
        reverse(nums, k, nums.length - 1);
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
/*
Time Complexity: O(n); n == nums.length;
Space Complexity: O(1);
*/