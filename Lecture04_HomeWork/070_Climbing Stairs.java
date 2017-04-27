/*

70. Climbing Stairs

You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top? 
Note: Given n will be a positive integer. 

*/

public class ClimbingStairs {
    public int climbStairs(int n) {
        // corner case
        if (n == 1) {
            return 1;
        }
        /*
        use dynamic programming
        each index represents # of stairs
        and each element represents # of distinct ways
        */
        int[] results = new int[n + 1];
        // set up initial value
        results[1] = 1;
        results[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            // you can climb 1 step from (i)th step, or climb 2 steps from (i - 1)th step
            results[i] = results[i - 1] + results[i - 2];
        }
        return results[n];
    }
    /*
    Time complexity: O(n), n is the parameter
    Space complexity: O(n), n is the parameter
    */

    public static void main(String[] args) {
        ClimbingStairs test = new ClimbingStairs();
        System.out.println(test.climbStairs(10));
    }
}