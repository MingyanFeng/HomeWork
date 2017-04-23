/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

public class Solution {
// Version 1: 我参考了答案，把答案看懂了，看着答案写的，需要自己再重新思考并写一遍
    public String addBinary(String a, String b) {
        /*
        Use StringBuilder to append the result form end to start;
        Use a boolean variable called hasCarry to see if there's a carry;
        
        There're 3 different situations:
        Situation 1: sum == 0, hasCarry = false, sb.append(0);
        0 + 0 = 0
        1 + 0 = 0 
        
        Situation 2: sum == 1, hasCarry = false, sb.append(1);
        0 + 1 = 1
        
        Situation 3: sum == 10, hasCarry = true, sb.append(1);
        1 + 1 = 10
        
        Upon these 3 situation, we can find out that:
        1. If hasCarry = ture, sum / 2(10) == sum / 10(2) == 10(2) / 10(2) == 1;
           Otherwise, sum / 2(10) == 1(2) / 10(2) == 0, or sum / 2(10) == 0(2) / 10(2) == 0;
           Hence, we can use sum / 2 to represent hasCarry;
        
        2. Also, we can let sb just appends (sum % 2), because (sum % 2) can represent each bit's add up result;
        */
        
        // Corner case
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        
        // Initialize a StringBuilder
        StringBuilder sb = new StringBuilder();
        // We need to add a and b from end to start, so we need to know the length of a and b
        int i = a.length() - 1;
        int j = b.length() - 1;
        // Initialize carry, set it to 0
        int carry = 0;
        
        while (i >= 0 || j >= 0) {
            // !!! NOTE: sum should be initialized to carry, not 0 !!!
            int sum = carry;
            
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
/*
这里为什么可以直接写十进制的2,而不写二进制的10呢？

解答：因为这边数据类型都是int，你写10的话Java直接判定这是十，不是二进制10
*/
            sb.append(sum % 2);
            carry = sum / 2;
        }
        // Need to check if there's still a carry after adding all bits
        if (carry != 0) {
            sb.append(carry);
        }
        // Neet to reverse the sb, then convert it to String
        return sb.reverse().toString();
    }
    /*
    Time Complexity: O(n + m), n == a.length(), m == b.length();
    Space Complexity: O(1), i + j + carry + sum = O(1) extra space;
    */
/*
请问这种 % 和 / 搭配的解法具有普适性吗？

解答：用int搞二进制加减，%和/是有普适性的
*/
}