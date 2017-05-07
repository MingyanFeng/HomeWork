/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        /*
        Find the mid point of the current List;
        Then, to find the mid point of the current List's left half part and right half part;
        And so on.
        
        错因：困在了不知道怎么找LinkedList的中点。
        
        分析：麻烦牢记一下快慢指针是找LinkedList中点的方法；快慢指针的套路还需要多练习；希望下次遇到相似思路的题目，可以一下子就想到用快慢指针来找中点这个突破口。
        
        答案思路：
        Use two pointers, fast and slow, fast and slow all start from head;
        Fast moves forward 2 steps each time, slow moves forward 1 step;
        When fast get the last position of the linkedlist, slow is on the middle position of the linkedlist;
        So we find the mid point of the current list (head, null);
        next, we do the same steps above of two separate lists, one is (head, slow), the other one is (slow.next, null)
        */
        
        // Corner case
        if (head == null) {
            return null;
        }
        
        return helper(head, null);
    }
   
    // 1. 定义
    private TreeNode helper(ListNode head, ListNode tail) {
        // 3. 出口，特殊情况，Corner case
        if (head == tail) {
            return null;
        }
        
        // 2. 拆解，current layer
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode tempRoot = new TreeNode(slow.val);
        
        // 2. 拆解，next layer
        tempRoot.left = helper(head, slow);
        tempRoot.right = helper(slow.next, tail);
        
        // 2. 拆解，当前层返回值
        return tempRoot;
    }
    /*
    Time Complexity: O(n), n == # of ListNode;
    Space Complexity: O(h), h == height of the tree, h == logN;

    解答：这里的时间复杂度应该是f(n) = O(n) + 2f(n/2)，根据master method，结果应该是O(nlogn)
    */
}