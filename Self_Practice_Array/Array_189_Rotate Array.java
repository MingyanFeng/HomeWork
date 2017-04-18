/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]
Hint:
Could you do it in-place with O(1) extra space?

Related problem: Reverse Words in a String II
*/

public class Solution {
// Version 1: 3 Step Reverse
    public void rotate(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
// 3步翻转法的基本思路我懂，但是下面这句 k %= nums.length; 我不太明白这句话的作用

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
    /*
    Time Complexity: O(n); n == nums.length;
    Space Complexity: O(1);
    */
}
