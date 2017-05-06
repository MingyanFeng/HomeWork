/*
289. Game of Life 

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970." 
Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article): 

    1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    2. Any live cell with two or three live neighbors lives on to the next generation.
    3. Any live cell with more than three live neighbors dies, as if by over-population..
    4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
    Could you solve it in-place? 
        Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
    In this question, we represent the board using a 2D array. 
        In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. 
        How would you address these problems?
*/

public class GameOfLife {
    public void gameOfLife(int[][] board) {
        /*
            -1: dead -> live
            0: dead
            1: live
            2: live -> dead
        */
        
        if (board == null) {
            return;
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int numOfNeighbor = getNeighbor(board, i, j);
                // ready to be dead
                if (board[i][j] == 1 && (numOfNeighbor < 2 || numOfNeighbor > 3)) {
                    board[i][j] = 2;
                    continue;
                }
                // ready to be live
                if (board[i][j] == 0 && numOfNeighbor == 3) {
                    board[i][j] = -1;
                    continue;
                }
            }
        }
        
        // finalize the matrix
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                    continue;
                }
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                    continue;
                }
            }
        }
    }
    /*
    Time complexity: O(m * n), m and n are # of rows and columns of the matrix.
    Space complexity: O(1);
    */
    
    private int getNeighbor(int[][] board, int i, int j) { // get # of live neighbors
        int numOfNeighbor = 0;
        if (i - 1 >= 0) {
            if (board[i - 1][j] > 0) {
                numOfNeighbor++;
            }
            if (j - 1 >= 0 && board[i - 1][j - 1] > 0) {
                numOfNeighbor++;
            }
            if (j + 1 < board[i - 1].length && board[i - 1][j + 1] > 0) {
                numOfNeighbor++;
            }
        }
        if (i + 1 < board.length) {
            if (board[i + 1][j] > 0) {
                numOfNeighbor++;
            }
            if (j - 1 >= 0 && board[i + 1][j - 1] > 0) {
                numOfNeighbor++;
            }
            if (j + 1 < board[i + 1].length && board[i + 1][j + 1] > 0) {
                numOfNeighbor++;
            }
        }
        if (j - 1 >= 0 && board[i][j - 1] > 0) {
            numOfNeighbor++;
        }
        if (j + 1 < board[i].length && board[i][j + 1] > 0) {
            numOfNeighbor++;
        }
        return numOfNeighbor;
    }

    public static void main(String[] args) {
        
    }
}