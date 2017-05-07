/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

public class Solution {
    public String convertToTitle(int n) {
        /*
        我的思路：
        n % 26 的值可以确定最末尾对应的字母
        n / 26 > 1 的次数可以确定 一共有字母的位数
        
        答案思路：
        本质就是将10进制数转换成26进制数，使用A-Z字母表示；
        仿照十进制的做法，但有几点细节需要注意：
        1. 为什么①处是(n - 1) % 26 + 'A'，而不是 n % 26 + 64呢？这是因为 n = 26 时，求余的结果为0，就无法表示出 'Z'
        2. 为什么②处是(n - 1) / 26，而不是 n / 26 呢？这是因为如果最后 n 刚好为26时，那么就不能接着运算了。
        这个数字转化为字母的过程与逆向字母转化为数字过程存在不同，所以还得考虑到细节处：
        
        错因：
        26进制的转折点是25，十进制的转折点是9；
        
        分析：
        1. 数学是不是白学了。。。
        2. 你注意到返回的String在构建的时候，应该是先填后面的字母，逐步向前的吗？
        */
        
        // Initialize the result
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            // Get the character from end to start
            sb.insert(0, (char)((n - 1) % 26 + 'A')); // ①
            // Update n
            n = (n - 1) / 26; // ②
        }
        
        return sb.toString();
    }
	/*
	Time Complexity: O(m), m == n / 26;
	Space Complexity: O(1);
	*/
}
/*
Method: % 和 / 结合，
这类% 和 / 结合目前遇到的题目有：
1. 取给定整数的每一位 (涉及到每一个数位的操作)
2. 258_Add Digits (数位之间的运算)
3. 326_Power of Three (是否为 倍数/指数/次方 的判断)
4. 168_Excel Sheet Column Title, 171_Excel Sheet Column Number (数与其他数据类型之间的相互转换，通常会有一定的规律，比如以某个数为限，有规律地重复)
*/