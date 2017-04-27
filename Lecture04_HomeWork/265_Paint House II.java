/*

265. Paint House II

There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color. 
The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... 
Find the minimum cost to paint all houses. 

Note:
    All costs are positive integers.

Follow up:
    Could you solve it in O(nk) runtime?

*/

public class PaintHouseII {
    public int minCostII(int[][] costs) {
        // corner cases
        if (costs == null || 
            costs.length == 0 || 
            costs[0].length == 0 ||
            (costs.length > 1 && costs[0].length == 1)) {
            return 0;
        }
        if (costs.length == 1 && costs[0].length == 1) {
            return costs[0][0];
        }
        
        /*
        use dynamic programming
        index of column represents index of house
        index of row represents index of color
        element in [i, j] of "results" represents the minimum cost so far when house j paints with color i 
        */
        int[][] results = new int[costs.length][costs[0].length];
        // set up initial value;
        for (int j = 0; j < costs[0].length; j++) {
            results[0][j] = costs[0][j];
        }
        int minIndex = -1; // represents the index of color which results in the minimum cost so far
        int min2Index = -1; // represents the index of color which results in the second minimum cost so far
        // set up value of minIndex and min2Index
        if (results[0][0] < results[0][1]) {
            minIndex = 0;
            min2Index = 1;
        } else {
            minIndex = 1;
            min2Index = 0;
        }

        for (int j = 2; j < results[0].length; j++) {
            if (results[0][j] < results[0][minIndex]) {
                min2Index = minIndex;
                minIndex = j;
            } else if (results[0][j] < results[0][min2Index]) {
                min2Index = j;
            }
        }
        
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < costs[0].length; j++) {
                // if the chosen color is the minIndex of the last house, choose min2Index; otherwise choose minIndex
                results[i][j] = costs[i][j] + (j == minIndex ? results[i - 1][min2Index] : results[i - 1][minIndex]);
            }
            
            if (results[i][0] < results[i][1]) {
                minIndex = 0;
                min2Index = 1;
            } else {
                minIndex = 1;
                min2Index = 0;
            }
            // find new minIndex and min2Index which results in the minimum (or second minimum) cost so far
            for (int j = 2; j < results[i].length; j++) {
                if (results[i][j] < results[i][minIndex]) {
                    min2Index = minIndex;
                    minIndex = j;
                } else if (results[i][j] < results[i][min2Index]) {
                    min2Index = j;
                }
            }
        }
        
        return results[costs.length - 1][minIndex];
    }
    /*
    Time complexity: O(n * k), n and k are number of rows and columns respectively in the matrix.
    Space complexity: O(n * k),  n and k are number of rows and columns respectively in the matrix.
    */

    public static void main(String[] args) {
        
    }
} 