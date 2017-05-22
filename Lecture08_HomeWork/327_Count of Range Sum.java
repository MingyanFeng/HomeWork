/*
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
*/

// Tags:  Divide and Conquer Binary Search Tree

public class Solution {
    
        long[] counts;
        int lower;
        int upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        /*
        思路：首先想到的还是暴力搜索，时间复杂度为O(n^2)，
        n^2的复杂度再降就是nlogn了，而是就是二分的思想，把大数据转化为求一堆小数据。
        想法很像归并排序，当然不是排序了。 

        时间复杂度分析：对问题研究为T(n) = 2*T(n/2) + (n/2)^2，这个公式计算之后仍然了O（n^2）的复杂度。不过已经比暴力搜索快得多了。
        */
        
        int length = nums.length;
        this.lower = lower;this.upper = upper;
        if(length <= 0)
            return 0;
        counts = new long[nums.length];
        counts[0] = nums[0];
        for(int i = 1;i<nums.length;i++){
            counts[i] = counts[i-1]+nums[i];
        }

        return countNum(nums,0,length-1);
    }
    private int countNum(int[] nums,int left,int right){
        if(left == right){
            if(nums[left] >=lower && nums[right] <= upper)
                return 1;
            return 0;
        }
        int mid = (left+right)/2;
        int total = 0;
        for(int i = left;i<=mid;i++){
            for(int j = mid+1;j<=right;j++){
                long tmpNum = counts[j] - counts[i] + nums[i];
                if(tmpNum >= lower && tmpNum <= upper)
                    ++total;
            }
        }
        //采用二分法
        return total + countNum(nums,left,mid) + countNum(nums,mid+1,right);
    }
}