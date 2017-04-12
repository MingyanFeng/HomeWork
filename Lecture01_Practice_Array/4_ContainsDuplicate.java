public class Solution {
    /*
    version 1: HashSet

    use HashSet to store dictinct element in the array.
    If the element not found in the HashSet, I store it in the HashSet.
    If the element is found in the HashSet, return true;
    */ 
    public boolean containsDuplicate(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        // use HashSet to judge if there're dupolicated elements 
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return true;
            }
            set.add(i);
        }
        return false;
    }
    /*
    Time Complexity: O(n); n == nums.length
    Space Complexity: O(n);

    The advantage of this method is that it is fast.
    The time complexity is O(n).
    The disadvantage of this method is that it is not in-place.
    The ideal space complexity is O(n),
    but the real space complexity is much larger due to the property of Hash Table
    */


    /*
    version 2: Sorting

    Sort the arrays first.
    If any duplicates exist, they must be neighbors after sorting.
    So we just view every element in the array.
    If any duplicates appears, return true.
    */
    public boolean containsDuplicate(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return false;
        }
        
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
    /*
    The advantage of this methos is that it is in-place.
    The disadvantage is that it is slow,
    and the time complexity is O(nlogn).
    */
}