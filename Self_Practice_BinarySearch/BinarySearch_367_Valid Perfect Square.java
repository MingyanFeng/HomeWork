/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
*/

public class Solution {
    public boolean isPerfectSquare(int num) {
        /*
        Use Binary Search;
        If mid * mid == num, we say mid is a perfect square;
        */
        
        int low = 1;
        int high = num;
        
        while (low + 1 < high) {
            // Set mid to long incase of overflow
            long mid = (low + high) >>> 1;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                // 由于low和high均是int, 需要把long强制转换回int
                low = (int)mid;
            } else {
                high = (int)mid;
            }
        }
        
        if (low * low == num) {
            return true;
        }
        if (high * high == num) {
            return true;
        }
        
        return false;
    }
    /*
    Time Complexity: O(logn), n == input num;
    Space Complexity: O(1), low + high + mid == O(1) extra space;
    */
}
/*
参考了答案，请问这种把mid设成long，然后再把mid强制转换成int的方法是不是太tricky了？有普适性吗？
*/