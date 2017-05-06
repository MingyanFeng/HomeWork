/*
324. Wiggle Sort II

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3].... 
Example:
    (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
    (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2]. 
Note:
    You may assume all input has valid answer. 
Follow Up:
    Can you do it in O(n) time and/or in-place with O(1) extra space? 
*/

public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        // corner cases
        if (nums == null || nums.length == 0) {
            return;
        }
        // first sort the array
        Arrays.sort(nums);
        // tempResult is used to store the sorted element temporarily
        int[] tempResult = Arrays.copyOf(nums, nums.length);
        int mid = (nums.length - 1) / 2;
        int index = 0;
        /* 
        The sorted array are divided into two parts.
        The new array should be rearranged like below:
        [1, 1, 1, 4, 5, 6] -> [1, 1, 1], [4, 5, 6] -> [1, 1, 1], [6, 5, 4] -> [1, 6, 1, 5, 1, 4]
        */
        for (int i = 0; i <= mid; i++) {
            nums[index] = tempResult[mid - i];
            if (index + 1 < nums.length) {
                nums[index + 1] = tempResult[nums.length - i - 1];
            }
            index += 2;
        }
    }
    /*
    Time complexity: O(nlogn);
    Space complexity: O(n)
    */

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        WiggleSortII test = new WiggleSortII();
        test.wiggleSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}