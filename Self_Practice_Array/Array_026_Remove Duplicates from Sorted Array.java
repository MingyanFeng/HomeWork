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
出现Runtime Error的原因是什么？
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

这个Expectd answer是一个int变量吗？

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