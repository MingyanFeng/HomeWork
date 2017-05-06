/*
442. Find All Duplicates in an Array

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements that appear twice in this array.
Could you do it without extra space and in O(n) runtime?

Example:
    Input: [4,3,2,7,8,2,3,1]
    Output: [2,3]
*/

public class FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new LinkedList<>();
        if (nums == null || nums.length < 2) {
            return result;
        }
        /*
        For each element, if an element is first found, make it negative.
        If an element is negative, it is the element that appears twice.
        */
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(index + 1);
            } else {
                nums[index] = - nums[index];
            }
        }
        return result;
    }
    /*
    Time complexity: O(n);
    Space complexity: O(1);
    */

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        FindAllDuplicatesInAnArray test = new FindAllDuplicatesInAnArray();
        List<Integer> result = test.findDuplicates(nums);
        while (!result.isEmpty()) {
            System.out.print(result.poll() + " ");
        }
        System.out.println();
    }
}