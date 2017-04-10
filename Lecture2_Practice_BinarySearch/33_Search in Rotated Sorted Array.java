public class Solution {
    public int search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // use binary search
        int start = 0;
        int end = nums.length - 1;
        int mid;
        
        while (start + 1 < end) {
            mid = (end - start) / 2 + start;
            // if nums[mid] == target, return mid
            if (nums[mid] == target) {
                return mid;
            }
            
            // for middle1 situation
            if (nums[mid] > nums[start]) {
                // middle1's situation 1
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                // middle1's situation 2
                } else {
                    start = mid;
                }
                
            // for middle2 situation
            } else {
                // middle2's situation 1
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid;
                // middle2's situation 2
                } else {
                    end = mid;
                }
            }
        }
        // double check
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}
    /*
    Time Complexity: O(logn), n == nums.length;
    Space Complexity: O(1), start + end == O(1) extra space;
    */