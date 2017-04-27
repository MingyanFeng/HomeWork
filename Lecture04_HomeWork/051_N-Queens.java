/*

51. N-Queens

Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

*/

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new LinkedList<>();
        /*
        chessboard is used to check the availability of each position
        0 represents available
        positive represents not available (has a Queen or has a conflict with other Queens)
        */ 
        int[][] chessboard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = 0;
            }
        }
        // use dfs
        nQueensHelper(0, chessboard, result, new LinkedList<String>());
        return result;
    }
    /*
    Time complexity: O(n * m), n is the parameter, m is the # of solutions
    Space complexity: O(n^2), n is the parameter
    */
    
    private void nQueensHelper(int level, // represents which row we are trying to place Queen
                                int[][] chessboard, 
                                List<List<String>> result, 
                                LinkedList<String> oneSol) { // represents one potential solution
        /*
        if level reaches the end of the matrix, the potential solution is a true solution,
        so add it into result
        */
        if (level == chessboard.length) {
            result.add(new LinkedList<String>(oneSol));
            return;
        }
        
        // check each position in a row
        for (int i = 0; i < chessboard[level].length; i++) {
            // skip unavailable postions
            if (chessboard[level][i] != 0) {
                continue;
            }
            
            setBarrier(chessboard, level, i); // set unavailable postion
            addOneLevel(i, chessboard[level].length, oneSol); // add a string row in the potential solution
            nQueensHelper(level + 1, chessboard, result, oneSol); // dfs next row
            
            removeBarrier(chessboard, level, i); // restore unavailable position
            oneSol.removeLast(); // remove last row in the potential solution for next possible placement
        }
    }
    
    private void setBarrier(int[][] chessboard, int i, int j) { // set unavailable postion
        for (int k = 1; i + k < chessboard.length; k++) {
            chessboard[i + k][j]++;
        }
        
        for (int k = 1; i + k < chessboard.length && j - k >= 0; k++) {
            chessboard[i + k][j - k]++;
        }
        
        for (int k = 1; i + k < chessboard.length && j + k < chessboard.length; k++) {
            chessboard[i + k][j + k]++;
        }
    }
    
    private void addOneLevel(int index, int n, List<String> oneSol) { // add a string row in the potential solution
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == index) {
                sb.append('Q');
            } else {
                sb.append('.');
            }
        }
        oneSol.add(sb.toString());
    }
    
    private void removeBarrier(int[][] chessboard, int i, int j) { // restore unavailable position
        for (int k = 1; i + k < chessboard.length; k++) {
            chessboard[i + k][j]--;
        }
        
        for (int k = 1; i + k < chessboard.length && j - k >= 0; k++) {
            chessboard[i + k][j - k]--;
        }
        
        for (int k = 1; i + k < chessboard.length && j + k < chessboard.length; k++) {
            chessboard[i + k][j + k]--;
        }
    }

    public static void main(String[] args) {

    }
}