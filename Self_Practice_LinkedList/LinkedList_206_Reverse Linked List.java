/*
Reverse a singly linked list.

A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
// Version 1: Iterative
    public ListNode reverseList(ListNode head) {
        // Corner case
        if (head == null || head.next == null) {
            return head;
        }
        
        // Scanner node: newHead;
        ListNode newHead = null;

/*
为什么while循环里面只判断head是否为空就可以了呢？如果写成while(head != null && head.next != null)一般是用于什么类的题型呢？

解答：如果这边写成while(head != null && head.next != null)，那么整个List Node的最后一个结点就会被抛弃
          while(head != null && head.next != null)一般用于你需要使用“待操作的Node的前一个”
          比如，一个list node长这样：1 -> 2 -> 3 -> 4 -> 5
          我要断开node 4和node 5的链接，我必须先得到node 4，所以head.next != null就不能省了
*/
        while (head != null) {
            // It's a "loop"
            // Reverse
            ListNode temp = head.next;
            head.next = newHead;
            // Update newHead and head, move them forward, prepare for the next iteration
            newHead = head;
            head = temp;
        }
        // return the tail of original LinkedList, which is newHead
        return newHead;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(1)
    */


// Version 2: Recursive
    public ListNode reverseList(ListNode head) {
        // Corner case/Base case
        if (head == null || head.next == null) {
            return head;
        }
        
        // Next layer. Assuming all following layers are handed
        ListNode newHead = reverseList(head.next);
        
        // Current layer
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(n);
    */
}