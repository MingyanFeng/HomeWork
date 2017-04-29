/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/

public class Solution {
// Version 1: HashMap + ArrayList, 参考了答案
    public int[] intersect(int[] nums1, int[] nums2) {

        // Corner case
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        
        Map<Integer, Integer> hm = new HashMap<>();
        List<Integer> tempResult = new ArrayList<>();
        
        // Put nums1's elements into HashMap
        for (int i = 0; i < nums1.length; i++) {
            if (hm.containsKey(nums1[i])) {
                hm.put(nums1[i], hm.get(nums1[i]) + 1);
            } else {
                hm.put(nums1[i], 1);
            }
        }
        
        // Check the intersect element by traversing nums2
        for (int i = 0; i < nums2.length; i++) {
            if (hm.containsKey(nums2[i]) && hm.get(nums2[i]) > 0) {
                tempResult.add(nums2[i]);
                hm.put(nums2[i], hm.get(nums2[i]) - 1);
            }
        }
        
        int[] result = new int[tempResult.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = tempResult.get(i);
        }
        
        return result;
    }
    /*
    Time Complexity: O(n + m), n == nums1.length, m == nums2.length;
    Space Complexity: Idealy is O(n), but real space complexity is larger than O(n) due to the property of HashTable;
    解答：这里空间复杂度应该是O(n)，n == nums1.length
    */


// Version 2: Sort + Two Pointers, 不去重了。自己写的，居然AC了。。。
    public int[] intersect(int[] nums1, int[] nums2) {
        /*
        Sort + Two Pointers
        */
        

        // Corner case
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        // Sort two input array
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        
        int i = 0; // i is the scanner for nums1
        int j = 0; // j is the scanner for nums2
        int index = 0; // index is used for result
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result[index++] = nums1[i++];
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        // We only need the intersection part of two arrays, this may be shoter than results
        int[] intersection = new int[index];
        for (int k = 0; k < index; k++) {
            intersection[k] = result[k];
        }
        
        return intersection;
    }
    /*
    Time Complexity: O(nlogn + mlogm), n == nums1.length, m == nums2.length;
    Space Complexity: O(min(n, m)), Sort的空间复杂度是O(1);
    */
}