/*

213. House Robber II

Note: This is an extension of House Robber.
After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention.
This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. 
Meanwhile, the security system for these houses remain the same as for those in the previous street. 
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

*/

public class HouseRobberII {
    public int rob(int[] nums) {
        // corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        /*
        use dynamic programming twice
        First time: rob the first house and do not rob the last house
        Seconde time: do not rob the first house
        Choose the maximum value of these two methods.
        */
        int[] results1 = new int[nums.length];
        int[] results2 = new int[nums.length];
        // set up initial value
        results1[0] = nums[0];
        results1[1] = Math.max(nums[0], nums[1]);
        results2[0] = 0;
        results2[1] = nums[1];
        for (int i = 2; i < nums.length - 1; i++) {
            results1[i] = Math.max(nums[i] + results1[i - 2], results1[i - 1]);
            results2[i] = Math.max(nums[i] + results2[i - 2], results2[i - 1]);
        }
        if (nums.length > 2) {
            results1[nums.length - 1] = Math.max(results1[nums.length - 2], results1[nums.length - 3]);
            results2[nums.length - 1] = Math.max(nums[nums.length - 1] + results2[nums.length - 3], results2[nums.length - 2]);
        }
        return Math.max(results1[nums.length - 1], results2[nums.length - 1]);
    }
    /*
    Time complexity: O(n), n is nums.length;
    Space complexity: O(n), n is nums.length;
    */

    public static void main(String[] args) {
        int[] nums = {100, 3, 40, 50, 23, 70};
        HouseRobberII test = new HouseRobberII();
        System.out.println(test.rob(nums));
    }
}