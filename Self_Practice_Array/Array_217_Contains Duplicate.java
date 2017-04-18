/*
Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
*/

public class Solution {
// Version 1: HashSet 自己想的
    public boolean containsDuplicate(int[] nums) {
       // Corner case
       if (nums == null || nums.length == 0) {
           return false;
       }
       
       // Use a HashSet to see if there're duplicate elements
       Set<Integer> hash = new HashSet<>();
       
       // Traverse nums
       for (int i = 0; i < nums.length; i++) {
           if (hash.contains(nums[i])) {
               return true;
           } else {
               hash.add(nums[i]);
           }
       }
       // If no duplicate elements after traversal, return false
       return false;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Complexity: O(n);
    */


// Version 2: Sort 上次提交的记录没有清楚，就看到sort这个方法了，只是看到了sort，代码是自己写的
    public boolean containsDuplicate(int[] nums) {
       // Corner case
       if (nums == null || nums.length == 0) {
           return false;
       }
       
       // Sort nums, if there're duplicated elements, they must be neighboors
       Arrays.sort(nums);
       
       for (int i = 1; i < nums.length; i++) {
           if (nums[i] == nums[i - 1]) {
               return true;
           }
       }
       // If no duplicates is found after traversal, return false;
       return false;
    }
    /*
    Time Complexity: O(nlogn), n == nums.length;
    Space Complexity: O(1);
    */
}