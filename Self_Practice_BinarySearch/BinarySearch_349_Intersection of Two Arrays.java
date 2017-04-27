/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
*/

public class Solution {
// Version 1.1: Sort + Two Pointers + HashSet, 看了这道题的tag里面有Two Pointers和sort，自己想的
    public int[] intersection(int[] nums1, int[] nums2) {
        /*
        Sort + Two Pointers
        */
        
        // Initialize result
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        // Corner case
        if (nums1 == null || nums2 == null) {
            return result;
        }
        // Sort two input array
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int i = 0; // i is the scanner for nums1
        int j = 0; // j is the scanner for nums2
        int index = 0; // index is used for result
        Set<Integer> hash = new HashSet<>(); // remove duplicates
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j] && hash.add(nums1[i])) {
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
    Space Complexity: O(n + min(n, m)), Sort的空间复杂度是O(1), hash == O(n), result = O(min(n, m));
    */

/*
这道题是在Binary Search的Tag归类里的，我没想出来怎么用BinarySearch做这道题，参考了答案。
答案用于Binary Search的思路是：把nums2排序，将nums1中的每个元素在排好序的nums2中进行Binary Search；
如果nums1中的元素能够在排好序的nums2中找到，把这个元素加到一个HashSet中；
最后把HashSet中的元素放到一个新建的数组中，数组的长度为HashSet.size();
*/
// Version 2: Binary Search, 参考了答案
 public int[] intersection(int[] nums1, int[] nums2) {
        /*
        Sort + Binary Search + HashSet;
        Sort nums2;
        Find nums1's elements in nums2, use Binary Search in nums2;
        If we can find nums1's element in nums2, add this element into a HashSet;
        Make a new int[] to store elements in HashSet;
        */
        
        // Corner case
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        
        Arrays.sort(nums2);
        
        Set<Integer> common = new HashSet<>();
        // ！！！不需要Integer，int就行了，add方法会自动给int封装的 ！！！
        for (int num: nums1) {
            if (binarySearch(num, nums2)) {
                common.add(num);
            }
        }
        
        int[] result = new int[common.size()];
        int i = 0;
        // ！！！不需要Integer，int就行了，add方法会自动给int封装的 ！！！
        /*
        ！！！但是，不可以这么写：
        for (int i = 0; i < result.length; i++) {
            result[i] = common.get(i);
        }
        return result;
        
        Set Interface 和 HashSet Class 均没有.get()这个方法，不许瞎胡造！！！
        */ 
        for (int num: common) {
            result[i++] = num;
        }
        return result;
    }
    
    private boolean binarySearch(int target, int[] nums) {
        // Corner case
        // ！！！注意这里的Corner case不可少，如不过滤Corner case，那么Double Check将出现java.lang.ArrayIndexOutOfBoundsException: 0 的错误 ！！！
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // Double check
        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        
        return false;
    }
    /*
    Time Complexity: O(nlogm + mlogm), n == nums1.length, m == nums2.length; nlogm = nums1's elements' Binary Search in nums2, mlogm = sort nums2;
    Space Complexity: O(n), common == O(n) extra space, sort nums2's extra space == O(1), but real space complexity would be larger than O(n) due to the property of HashTable;
    */

// Version 3: Use Two HashSet, 参考了答案
	/*
	Use two HashSet, set and intersect;
	Add nums1's elements into set;
	Traverse nums2, if set.contains(nums2[i]), add nums2[i] into intersect;
	Put the interset's elements into a new array;
	*/
	public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }

        int[] result = new int[intersect.size()];
        int i = 0;

        for (Integer num : intersect) {
            result[i++] = num;
        }

        return result;
    }
    /*
	Time Complexity: O(n + m), n == nums1.length; m == nums2.length;
	Space Complexity: Ideal is O(n + m), but the real space complexity will be larger than O(n + m) due to the property of HashTable;
    */
}
/*
我觉得这道题的最优解是Version3, 即用两个Hashset.
但如果面试官不允许用HashSet这个数据结构的话，Version1是不是也不用呢？因为我在Version1中，用了HashSet去重。还能用其他方法去重吗？
Version2我看了答案才明白如何用Binary Search, 思路可以get到，但是九章的模板在这里好像不可行，写代码的时候岂不是又要懵。。。

解答：这道题的最优解就是version 3
         如果不允许用HashSet的话，思路可以用version 1的，稍微改一下，每次发现一样的元素，先和已经确定是intersection的list里的最后一个数比较，如果相同，就不要加入，否则就更新intersection的list
         九章的二分法模板针对的是二分法过程本身。你可以看看你新建的binarySearch这个方法，不就用到模板了吗？
         解题方法有很多，不要局限于一种方法，而且这里明显version 3就比version 2好得多，而且如果不让用HashSet，version 2也是不能用的
*/	