/*
202. Happy Number

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

public class Solution {
// Version 1: Loop 
    public boolean isHappy(int n) {
        /*
        The idea is to use one hash set to record sum of every digit square of every number occurred. 
        Once the current sum cannot be added to set, return false; Because  it loops endlessly in a cycle which does not include 1
        Once the current sum equals 1, return true;
        */
        
        // 使用一个HashSet用于保存中间出现的结果，即每次更新的n值
        Set<Integer> inLoop = new HashSet<>();
        
        int squareSum;
        
        while (inLoop.add(n)) { // When inLoop can add n, we can calculate the squareSum, otherwise, return false. ！！！注意： 此时n不能为1，并且n的值不能重复出现，否则会死循环
            squareSum = 0;      // Reset squareSum to 0 at the begining of each loop
            while (n > 0) {
                int lastDigit = n % 10;
                squareSum += lastDigit * lastDigit;
                n = n / 10;     // Update the value of n
            }
            if (squareSum == 1) {
                return true;
            } else {
                n = squareSum;
            }
        }
        
        return false;
    }
	/*
	Time Complexity: O(m), m == time of calculating steps if n is a happy number;
	Space Complexity: O(1);
	*/
}
/*
Method: % 和 / 结合，
这类% 和 / 结合目前遇到的题目有：
1. 取给定整数的每一位 (涉及到每一个数位的操作)
2. 258_Add Digits, 202. Happy Number (数位之间的运算，并进行后续判断)
3. 326_Power of Three (是否为 倍数/指数/次方 的判断)
4. 168_Excel Sheet Column Title, 171_Excel Sheet Column Number (数与其他数据类型之间的相互转换，通常会有一定的规律，比如以某个数为限，有规律地重复)
*/