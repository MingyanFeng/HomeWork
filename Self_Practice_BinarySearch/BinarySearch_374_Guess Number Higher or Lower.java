/*
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example:
n = 10, I pick 6.

Return 6.
*/

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        /*
        Use Binary Search to find the number
        */
        /*
        解答：这个API的输入就是一个数，输出就是你输入的数跟API内部的基准数的大小关系
         如果输出为0，你输入的数（也就是形参）刚好等于API内部的基准数
         如果输出为-1，你输入的数（也就是形参）太大了！注意！是太大了！
         如果输出为1，你输入的数（也就是形参）太小了！注意！是太小了！
         这个my number确实有迷惑性，my number其实指的是API内部的基准数.
        */
        
        int start = 1;
        int end = n;
        
        while(start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                end = mid;
            } else {
                start = mid;
            }
        }
        // Double check
        if (guess(start) == 0) {
            return start;
        }
        if (guess(end) == 0) {
            return end;
        }
        return n;
    }
    /*
    Time Complexity: O(logn), n == input n;
    Space Complexity: O(1), start + end + mid == O(1) extra space;
    */
}