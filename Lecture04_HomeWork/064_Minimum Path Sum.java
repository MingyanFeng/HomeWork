/*

64. Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

*/

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        // corner cases
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        /*
        use dynamic programming
        each index represents each position in the grid,
        and each element represents minimum sum to get the position from [0, 0]
        */
        int[][] results = new int[grid.length][grid[0].length];
        results[0][0] = grid[0][0];
        // set up initial value
        for (int i = 1; i < grid.length; i++) {
            results[i][0] = grid[i][0] + results[i - 1][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            results[0][j] = grid[0][j] + results[0][j - 1];
        }
        /*
        to get position[i, j], you can go from up or from left,
        and choose smaller sum from these two directions.
        */
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                results[i][j] = grid[i][j] + Math.min(results[i - 1][j], results[i][j - 1]);
            }
        }
        return results[grid.length - 1][grid[0].length - 1];
    }
    /*
    Time complexity: O(m * n), m and n are number of rows and columns respectively in the grid.
    Space complexity: O(m * n), m and n are number of rows and columns respectively in the grid.
    */

    public static void main(String[] args) {
        
    }
}