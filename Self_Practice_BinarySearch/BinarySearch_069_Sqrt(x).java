/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/

public class Solution {
    public int mySqrt(int x) {
        // Corner case
        if (x <= 0) {
            return x;
        }
        
        int low = 1;
        int high = x;
        
        while (low + 1 < high) {
            long mid = (low + high) >>> 1;
            if (mid * mid == x) {
                return (int)mid;
            } else if (mid * mid < x) {
                low = (int)mid;
            } else {
                high = (int)mid;
            }
        }
        
        if (high * high == x) {
            return high;
        }
       
        return low;
    }
    /*
    Time Complexity: O(logx), x == input x;
    Space Compleixty: O(1);
    */
}
/*
请问这种把mid设成long，然后再把mid强制转换成int的方法是不是太tricky了？有普适性吗？

解答：这里为了避免溢出先用long求值然后转换成int的方法还是比较常见的，具有普适性
*/