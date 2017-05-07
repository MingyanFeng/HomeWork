/*
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.
*/

public class Solution {
    public int getSum(int a, int b) {
        /*
        Use XOR, when there's a carry, we need to let (a & b) << 1 because we can only get the carry by &.
        
        用位运算，分为两个步骤： 
        1. 输入 a，b 
        2. 按照位把ab相加，不考虑进位，结果是 a xor b，即1+1 =0 0+0 = 0 1+0=1，进位的请看下面 
        3. 计算ab的进位的话，只有二者同为1才进位，因此进位可以标示为 (a and b) << 1 ，注意因为是进位，所以需要向左移动1位 
        4. 于是a+b可以看成 （a xor b）+ （(a and b) << 1），这时候如果 (a and b) << 1 不为0，就递归调用这个方式吧，因为（a xor b）+ （(a and b) << 1） 也有可能进位，所以我们需要不断的处理进位。
        */
        
        int result = a ^ b;
        int carry = (a & b) << 1;
        
        if (carry != 0) {
            return getSum(result, carry);
        }
        
        return result;
    }
/*
Time Complexity: O(1);
Space Conplexity: O(1);

解答：本题的时间复杂度和空间复杂度均为O(1)，因为没有任何循环，也没有新建任何随形参大小变化而变化的变量
*/
}