/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
*/

// Tag:  Binary Search Dynamic Programming Queue

public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        // 2D Kadane's algorithm + 1D maxSum problem with sum limit k  
        // 2D subarray sum solution  
          
        // Boundary check  
        if(matrix.length == 0) return 0;  
          
        int m = matrix.length, n = matrix[0].length;  
        int result = Integer.MIN_VALUE;  
          
        // Outer loop should use smaller axis  
        // Now we assume we have more rows than cols, therefore outer loop will be based on cols   
        for(int left = 0; left < n; left++){  
            // Array that accumulate sums for each row from left to right   
            int[] sums = new int[m];  
            for(int right = left; right < n; right++){  
                // Update sums[] to include values in curr right col  
                for(int i = 0; i < m; i++){  
                    sums[i] += matrix[i][right];  
                }  
                  
                // We use TreeSet to help us find the rectangle with maxSum <= k with O(logN) time  
                TreeSet<Integer> set = new TreeSet<Integer>();  
                // Add 0 to cover the single row case  
                set.add(0);  
                int currSum = 0;  
                  
                for(int sum : sums){  
                    currSum += sum;  
                    // We use sum subtraction (curSum - sum) to get the subarray with sum <= k  
                    // Therefore we need to look for the smallest sum >= currSum - k  
                    Integer num = set.ceiling(currSum - k);  
                    if(num != null) result = Math.max( result, currSum - num );  
                    set.add(currSum);  
                }  
            }  
        }  
          
        return result;  
    }
}