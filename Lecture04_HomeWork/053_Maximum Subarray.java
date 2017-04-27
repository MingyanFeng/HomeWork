/*

53. Maximum Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest sum. 
For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6. 

*/

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        // Kadane's Algorithm， similar to "Best Time to Buy and Sell Stock"
        int maxSoFar = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++){
            // calculate the maximum sum of adjacent elements
            // find a contiguous subarray with maximum sum
        	maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
        	maxSoFar = Math.max(maxSoFar, maxEndingHere);	
        }
        return maxSoFar;
    }
    /*
    Time complexity: O(n), n is nums.length;
    Space complecity: O(1)
    */

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaximumSubarray test = new MaximumSubarray();
        System.out.println(test.maxSubArray(nums));
    }
}