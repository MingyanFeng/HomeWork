public class Solution {
    public int removeElement(int[] nums, int val) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // since the order of elements can be changed
        // we let the elements that not equals to val to replace elements that equals to val
        // at the same time, we use a pointer to count the number of elements that not equals to val
        int pointer = 0;
        for (int i = 0; i < nums.length; i++) {
            // when nums[i] != val, we first replace nums[pointer] with nums[i], then let pointer++
            if (nums[i] != val) {
                nums[pointer++] = nums[i];
            }
        }
        return pointer;
    }
}
/*
Time Complexity: O(n); n == # of elements in nums

Space Complexity: O(1); i == extra O(1) constant space
*/