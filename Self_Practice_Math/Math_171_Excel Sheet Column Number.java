/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
*/

public class Solution {
    public int titleToNumber(String s) {
        /*
        答案思路：
        循环读取数字，从左向右读取，每次该位字符减去A加上一即为该为数字代表的数值，然后每次循环前将之前的结果乘以26.表示该表示为26进制。
        
        分析：
        1. 想过读取数字时候是应该从左往右，还是从右往左了吗？
        2. 每次更新result都要*26，想过为什么吗？
        */
        
        // Corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int i = 0;
        int result = 0;
        
        while (i < s.length()) {
            result *= 26;
            result += s.charAt(i) - 'A' + 1;
            i++;
        }
        
        return result;
    }
	/*
	Time Complexity: O(n), n == s.length();
	Space Complexity: O(1);
	*/
}