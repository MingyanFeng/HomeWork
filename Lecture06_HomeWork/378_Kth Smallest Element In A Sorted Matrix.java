/*
378. Kth Smallest Element in a Sorted Matrix

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element. 

Example: 
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

Note: 
You may assume k is always valid, 1 ≤ k ≤ n^2.
*/

public class KthSmallestElementInASortedMatrix {
    private class Point implements Comparable<Point> { // used to store the position and value of a element in a matrix
        public int i;
        public int j;
        public int value;
        public Point (int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
        
        @Override
        public int compareTo (Point that) {
            return this.value - that.value;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        /*
        A min-heap is used to store elements.
        When k - 1 small elements are extracted from the min heap, the kth element will be the head of the min heap. 
        */
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int j = 0; j < matrix[0].length; j++) {
            pq.add(new Point(0, j, matrix[0][j]));
        }
        for (int i = 1; i < k; i++) {
            Point temp = pq.poll();
            if (temp.i + 1 < matrix.length) {
                pq.add(new Point(temp.i + 1, temp.j, matrix[temp.i + 1][temp.j]));
            }
        }
        return pq.poll().value;
    }
    /*
    Time complexity: O(klogm), k is the parameter, m is # of columns
    Space complexity: O(m), m is # of columns
    */

    public static void main(String[] args) {
        
    }
} 