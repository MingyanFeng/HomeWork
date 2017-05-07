/*
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
*/

public class Solution {
// Version 1: Recursive Solution
    public boolean isPowerOfThree(int n) {
        /*
        答案思路：
        首先，n必须大于0;
        其次，n == 1的情况需要特殊考虑；
        当n % 3 != 0时，先更新n为n / 3，然后将n / 3作为新的形参，传入isPowerOfThree方法；
        */
        return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));
    }
	/*
	Time Complexity: O(m), m == log3 n, 以3为底，n就是输入, n是3的m次方，时间复杂度就是那个“m”;
	Space Complexity: O(m);
	*/


// Version 2: Iterative Solution
    public boolean isPowerOfThree(int n) {
        /*
        n == 1的情况特殊考虑；
        当n > 1时，只要n % 3 == 0, 就不断对3取余
        */
        if (n == 1) {
            return true;
        }
        
        if (n > 1) {
            while (n % 3 == 0) {    // 当n % 3 != 0 时，只需判断跳出while loop后被更新的n是否为1即可
                n /= 3;
            }
            return n == 1;
        }
        
        return false;
    }
	/*
	Time Complexity: O(m), m == log3 n, 以3为底，n就是输入, n是3的m次方，时间复杂度就是那个“m”;
	Space Complexity: O(1);
	*/


// Follow up: do it without using any loop / recursion
    public boolean isPowerOfThree(int n) {
        /*
        数学题
        Since 3^20 is bigger than int, and 3^19 is 1162261467
        3^19 / 3^m == 3^(19 - m)
        3^(19 - m) % 3 == 0
        */
        return (n > 0 && 1162261467 % n == 0);
    }
	/*
	Time Complexity: O(m), m == log3 n, 以3为底，n就是输入, n是3的m次方，时间复杂度就是那个“m”;
	Space Complexity: O(1);

	解答：这里的时间复杂度应该是O(1)，因为只有一次运算，没有任何循环或递归
	*/
}