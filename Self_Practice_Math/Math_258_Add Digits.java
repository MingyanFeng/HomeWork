/*
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
*/

public class Solution {
// 错误Version
    public int addDigits(int num) {
        /*
        错因：想使用递归，但是出现stack overflow的现象。
        
        分析：
        1. 这道题其实用while循环不断更新每次运算后的结果就可以。
        2，而且，num / 10 是否等于0不是判断是否需要再次运算的准则，而是应该判断每次运行后，结果是否 >9 即可。
        */
        // Base case
        if (num == 0) {
            return num;
        }
        
        int result = 0;
        
        while (num != 0) {
            result += num % 10;
            num = num / 10;
        }
        
        return addDigits(result);
        
    }

// Loop Version
    public int addDigits(int num) {
        
        int sum = num;
        
        while (num > 9) {       //当结果只有一位数时，退出循环
            sum = 0;
            while (num > 9) {       //将传入的num(除了最高位)的各位相加
                sum += num % 10;
                num /= 10; 
            }
            sum += num;     //加上最高位
            num = sum;
        }
        
        return sum;
    }
/*
本题的时空复杂度怎么分析？？？
Time Complexity: O(?)
Space Complexity: O(1)

解答：时间复杂度的确难分析，姑且就等于O(n)，n为形参的位数。
        设为n是因为感觉n位数加起来，大小会大大缩减，后面几次运算加起来也不太会超过n
*/

// Follow up: 
	/*
	其实是一道数学题，回想一下Nim Game。
	因为一旦num > 9，势必要再次进行计算，所以突破口是if num % 9 == 0, if yes, num就是9; otherwise, return num % 9 

	假如有这么一个数ABCDE，有ABCDE=A*10000+B*1000+C*100+D*10+E=(A+B+C+D+E)+(A*9999+B*999+C*99+D*9+E)，那么ABCDE%9=(A+B+C+D+E)%9，
	这就很好的实现了ABCDE%9的结果为一位数，但是这里还需要注意一点，若 (A+B+C+D+E) = 9，则结果为9。
	*/ 

    public int addDigits(int num) {

        return num == 0 ? 0: (num % 9 == 0 ? 9 : (num % 9));

    }

    /*
	用分开的if-else写一下，就是
    */

    public int addDigits(int num) {
    	if (num == 0) {
    		return num;
    	}

    	if (num % 9 == 0) {
    		return 9;
    	}

    	return num % 9;
    }
/*
Time Complexity: O(1);
Space Complexity: O(1);
*/
}