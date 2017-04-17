/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/

public class Solution {
    public int numIslands(char[][] grid) {
        // Corner case
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        // Initialize a int variable called num to count # of islands
        int num = 0;
        
        // Traverse the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // Control condition
                if (grid[i][j] == '0') {
                    continue;
                }
                
                // Use a dfsHelper to change '1' to '0', which disable an island
                dfsHelper(grid, i, j);
                // After disabling an island, let num++
                num++;
            }
        }
        // return num
        return num;
    }
    
    private void dfsHelper(char[][] grid, int x, int y) {
        // Base case
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') { // grid[x][y] == '0' is control condition
            return;
        }
        
        // Disable an island
        grid[x][y] = '0';
        
        dfsHelper(grid, x + 1, y);
        dfsHelper(grid, x - 1, y);
        dfsHelper(grid, x, y + 1);
        dfsHelper(grid, x, y - 1);
        
        return;
    }
    /*
    Time Complexity: O(n), n == grid.length;
    Space Complexity: O(n)
    */
}