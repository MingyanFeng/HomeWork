/*
308. Range Sum Query 2D - Mutable

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

// Tags:  Binary Indexed Tree Segment Tree

// Version 1: Indexed Tree Solution
	/*
	Analysis:
	
	Based on the 1-D solution in problem Range Sum Query - Mutable, we can extend it to solve this 2-D problem.

	Initializing the binary indexed tree takes O(mn*logm*logn) time, both update() and getSum() take O(logm*logn) time. 
	The arr[][] is used to keep a backup of the matrix[][] so that we know the difference of the updated element and use that to update the binary indexed tree. 
	The idea of calculating sumRegion() is the same as in Range Sum Query 2D - Immutable.
	*/
public class NumMatrix {
	int m, n;
	int[][] arr;    // stores matrix[][]
	int[][] BITree; // 2-D binary indexed tree

	public NumMatrix(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
		    return;
		}

		m = matrix.length;
		n = matrix[0].length;

		arr = new int[m][n];
		BITree = new int[m + 1][n + 1];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
			update(i, j, matrix[i][j]); // init BITree[][]
			arr[i][j] = matrix[i][j];   // init arr[][]
			}
		}
	}

	public void update(int i, int j, int val) {
		int diff = val - arr[i][j];  // get the diff
		arr[i][j] = val;             // update arr[][]
	  
		i++; j++;

		while (i <= m) {
			int k = j;
			while (k <= n) {
				BITree[i][k] += diff; // update BITree[][]
		    	k += k & (-k); // update column index to that of parent
			}
			i += i & (-i);   // update row index to that of parent
		}
	}

	int getSum(int i, int j) {
	int sum = 0;

	i++; j++;
	while (i > 0) {
		int k = j;
		while (k > 0) {
	    	sum += BITree[i][k]; // accumulate the sum
	    	k -= k & (-k); // move column index to parent node
		}
		i -= i & (-i);   // move row index to parent node
	}
	return sum;
	}

	public int sumRegion(int i1, int j1, int i2, int j2) {

	return getSum(i2, j2) - getSum(i1-1, j2) - getSum(i2, j1-1) + getSum(i1-1, j1-1);
	
	}
}

// Version 2: 
	/*
	Analysis: 

	这道题让我们求一个二维区域和的检索，是Range Sum Query - Immutable 区域和检索的延伸。
	有了之前那道题的基础，我们知道这道题其实也是换汤不换药。
	还是要建立一个累计区域和的数组，然后根据边界值的加减法来快速求出给定区域之和。
	这里我们维护一个二维数组dp，其中dp[i][j]表示累计区间(0, 0)到(i, j)这个矩形区间所有的数字之和，
	那么此时如果我们想要快速求出(r1, c1)到(r2, c2)的矩形区间时，
	只需dp[r2][c2] - dp[r2][c1 - 1] - dp[r1 - 1][c2] + dp[r1 - 1][c1 - 1]即可。
	*/
public class NumMatrix {

	private int[][] colSums;
	private int[][] matrix;

	public NumMatrix(int[][] matrix) {
	    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
	        return;   
	     }
	     
	     this.matrix = matrix;
	     
	     int m = matrix.length;
	     int n = matrix[0].length;

	     colSums = new int[m + 1][n];

	     for (int i = 1; i <= m; i++) {
	         for(int j = 0; j < n; j++) {
	             colSums[i][j] = colSums[i - 1][j] + matrix[i - 1][j];
	         }
	     }
	}
	/*
	Time Complexity: the worst case scenario: O(m)
	*/

	public void update(int row, int col, int val) {
	    for (int i = row + 1; i < colSums.length; i++) {
	        colSums[i][col] = colSums[i][col] - matrix[row][col] + val;
	    }
	    
	    matrix[row][col] = val;
	}
	/*
	Time Complexity: the worst case scenario: O(n)
	*/

	public int sumRegion(int row1, int col1, int row2, int col2) {
	    int ret = 0;
	    
	    for(int j = col1; j <= col2; j++){
	        ret += colSums[row2 + 1][j] - colSums[row1][j];
	    }
	    
	    return ret;
	}
}