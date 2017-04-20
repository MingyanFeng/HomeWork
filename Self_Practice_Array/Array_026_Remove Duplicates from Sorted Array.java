/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
It doesn't matter what you leave beyond the new length.
*/

public class Solution {
//错误version: Runtime Error, 自己想的
    public int removeDuplicates(int[] nums) {
        // Corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
         
        if (nums.length == 1) {
            return 1;
        }
        
        /*
        Use two pointers, slow and fast, slow starts from index 1, fast starts from index 1;
        Traverse nums;
        
        If nums[fast] == nums[slow], only move fast forward;
        If nums[fast] != nums[slow], nums[slow] = nums[fast], move both fast and slow forward;
        
        Return slow after fast pointer has finished traversal;
        */
        
        // Build two pointers, and traverse nums
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            // If nums[fast] != nums[slow], nums[slow] = nums[fast], move both fast and slow forward;
            if (nums[fast] != nums[slow]) {
                nums[slow++] = nums[fast];
            }
            // Otherwise, only move fast forward;
        }
        // Return slow after fast pointer has finished traversal;
        return slow;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: O(1), fast == extra O(1) space;
    */

/*
出现Runtime Error的原因是什么？ 解答请翻到最底下
*/


//正确version: 出现Runtime Error后看了答案，发现不同之处在于Corner case。 把Corner Case进行了修改之后，不再有Runtime Error了。
/*
那么nums == null || nums.length = 0的这个情况不能返回-1呢？
把corner case修改过之后，就可以跑过测试
Your input
[]

Your answer
[]

Expected answer
[]

这个Expectd answer是一个int变量吗？ 解答请翻到最底下

*/
	public int removeDuplicates(int[] nums) {
        // Corner case
        if (nums.length < 2) {
            return nums.length;
        }
/*
如果把Corner case写成
if (nums == null || nums.length == 0) {
    return nums.length;
}

if (nums.length == 1) {
    return 1;
}
也是可以通过测试的，但是我不明白nums.length对于一个空集输入来讲，是怎么得到一个int返回类型的，为什么不可以返回-1呢？
解答请翻到最底下
*/
        
        /*
        Use two pointers, slow and fast, slow starts from index 1, fast starts from index 1;
        Traverse nums;
        
        If nums[fast] == nums[slow], only move fast forward;
        If nums[fast] != nums[slow], nums[slow] = nums[fast], move both fast and slow forward;
        
        Return slow after fast pointer has finished traversal;
        */
        
        // Build two pointers, and traverse nums
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            // If nums[fast] != nums[slow], nums[slow] = nums[fast], move both fast and slow forward;
            if (nums[fast] != nums[slow]) {
                nums[slow++] = nums[fast];
            }
            // Otherwise, only move fast forward;
        }
        // Return slow after fast pointer has finished traversal;
        return slow;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: O(1), fast == extra O(1) space;
    */
}



/*
    解答：为什么会出现runtime error
    程序本身并没有错，corner cases返回值本身是合法的
    但是，corner cases返回值在遇到LeetCode自己给的测试程序时遇到了问题
    因为LeetCode的测试程序是会打印出从0开始到（你返回的值）个数的
    然后你的程序在corner cases的情况下返回了-1
    而第0个开始-1个数是非法的（个数不存在负数），所以测试程序会报错
    正确的返回值应该是0
    这样的话LeetCode测试会打印出第0个开始0个数，也就是没有数，也就是空
    这样的返回值对测试程序来说是合法的

    通过这个问题，可以总结出两个经验：
    1.一定要明确各种corner case需要返回的值，如果不明确，问面试官
    2.不要自己乱造返回值，不要随便返回-1值，除非题目明确要求，或者返回-1那步根本走不到
*/