/*
When there could be duplicates in the array, the worst case is O(n).

To explain why, consider this sorted array 1111115, which is rotated to 1151111.

Assume left = 0 and mid = 3, and the target we want to search for is 5. 
Therefore, the condition A[left] == A[mid] holds true, which leaves us with only two possibilities:

	1. All numbers between A[left] and A[right] are all 1's.
	2. Different numbers (including our target) may exist between A[left] and A[right].

As we cannot determine which of the above is true, the best we can do is to move left one step to the right and repeat the process again. 

Just think when nums[start] == nums[mid] == nums[end], where should we go next? left or right or stay?

Therefore, we are able to construct a worst case input which runs in O(n), for example: the input 11111111...115.
*/ 

public class Solution {
    public boolean search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }
}
    /*
    Time Complexity: O(n), n == # of elements in nums
    Space Complexity: O(1)
    */
