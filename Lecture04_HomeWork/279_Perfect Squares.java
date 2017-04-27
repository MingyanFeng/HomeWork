/*

279. Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n. 
For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9. 

*/

public class PerfectSquares {
    public int numSquares(int n) {
        /*
        use dynamic programming
        each element represents the least number of perfect square numbers
        */
        int[] results = new int[n + 1];
        // set up initial value;
        for (int i = 0; i <= n; i++) {
            results[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; i + j * j <= n; j++) {
                /*
                from index i to n, find all the integers that can be represented by sum of perfect square numbers,
                and choose the smaller number
                */
                results[i + j * j] = Math.min(results[i + j * j], results[i] + 1);
            }
        }
        return results[n];
    }
    /*
    Time complexity: O(n * sqrt(n)), n is the parameter
    Space complexity: O(n)
    */

    public static void main(String[] args) {
        PerfectSquares test = new PerfectSquares();
        System.out.println(test.numSquares(100));
    }
}