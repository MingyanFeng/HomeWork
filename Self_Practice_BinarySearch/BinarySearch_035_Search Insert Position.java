/*
Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

public class Solution {
    public int searchInsert(int[] nums, int target) {
        // Corner case
        if (nums == null || nums.length == 0 || nums[0] > target) {
            return 0;
        }
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        // target在nums里的情况，满足nums[start] <= target <= nums[end]
        while (start + 1 < end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // target不存在nums里的情况，满足nums[start] < target < nums[end] && start + 1 == end, 所以只需double check nums[start]是否等于target, 如相等，则返回start, 否则返回end
        if (nums[start] == target) {
            return start;
        }
        return end;
    }
    /*
    Time Complexity: O(logn), n == nums.length;
    Space Complexity: O(1);
    */
}