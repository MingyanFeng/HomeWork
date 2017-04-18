/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.
*/

// Version 1: 错误版本，Gee...不能再错第三次了！！！
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // corner case
        if (nums1 == null || nums1.length == 0) {
            return;
        }
        if (nums2 == null || nums2.length == 0) {
            return;
        }
        
        // i is for nums1's traversal
        int i = 0;
        // j is for nums2's traversal
        int j = 0;
        // index represents the nums1's new index-elements mapping after merge
        int index = 0;
        
        // traverse nums1 and nums2, keep the merged array sorted 
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                nums1[index++] = nums1[i++];
            } else {
                nums1[index++] = nums2[j++];
            }
        }
        
        // check remaining elements in nums1 and nums2
        while (i < m) {
            nums1[index++] = nums1[i++];
        }
        
        while (j < n) {
            nums1[index++] = nums2[j++];
        }
    }
    /*
    Time Complexity: O(n + m), n == # of elements in nums1, m == # of elements in nums2;

    Space Complexity: O(1), i + j + index == O(1) extra constant space；
    */
}

/*
上面的正向merge是错误的，如果input是[2,0], 1, [1], 1
output是[1,1], 而正确的答案应该是[1,2]

错因：没有考虑到正向merge时，nums1前面的所有元素都需要往后位移.
    ！！！！！！！ Merge sorted ARRAY 和 Merge sorted LINKEDLIST 不一样， Linkedlist可以通过.next来改变指向，但是Array做不到把单个element拆分再合并，Array只能把现有元素不断往后移。！！！！！！！
      nums1的大小足以装两个数组，所以自然想到把两个数组都合并到nums1中，
      但是nums1前面都是有用的信息，如果直接从前面加，我们得将后面所有的数都位移。
      但是如果我们从后往前，合并到第一个数组的最后，则不用位移。
改正：从后往前merge, 反向思维
*/

public class Solution { 
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // corner case
        if (nums1 == null || nums1.length == 0) {
            return;
        }
        if (nums2 == null || nums2.length == 0) {
            return;
        }
        
        // i is for nums1's traversal
        int i = m - 1;
        // j is for nums2's traversal
        int j = n - 1;
        // index represents the nums1's new index-elements mapping after merge
        int index = m + n - 1;
        
        // traverse nums1 and nums2 FROM END TO START, keep the merged array sorted 
        while (i >= 0 && j >= 0) {
            // ！！！！！！！这里注意，因为是从后往前merge，所以谁大，先放谁 ！！！！！！！
            if (nums1[i] > nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }
        
        // check remaining elements in nums1 and nums2
        while (i >= 0) {
            nums1[index--] = nums1[i--];
        }
        
        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }
    /*
    Time Complexity: O(n + m), n == # of elements in nums1, m == # of elements in nums2;

    Space Complexity: O(1), i + j + index == O(1) extra constant space;
    */
}

/*
启发： 
1. 分清Array和Linkedlist，以及其他线性数据结构的各自特点，Array是不能把单独的元素拆分出来再合并的，要不然还要LinkedList干嘛啊！想一下为什么Array查快改慢，为什么Linkedlist查慢改快
2. 逆向思维很重要，同时要注意相同算法，在进行逆向思维时，细节部分可能需要改变，比如从后往前merge的时候，就不能谁小谁先上了，应该是谁大谁先上。
*/  