/*
287. Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one. 
Note:
    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        /*
        This problem is similar to linked list cycle. 
        Value of elements can be the next index.
        When a cycle is found, the duplicate number must be in the cycle.
        */
        int slow = nums[0];
        int fast = nums[slow];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0; // when a cycle is found, fast is reset to be 0
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
    /*
    Time complexity: O(n);
    Space complexity: O(1);
    */

    public static void main(String[] args) {
        int[] nums = {5, 4, 2, 3, 2, 1};
    }
}