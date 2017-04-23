/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
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
// Version 1: 自己想的， Iterative
    public ListNode removeElements(ListNode head, int val) {
        /*
        Use a dummy node, dummy.next = head;
        First, consider head.
        If head.val = val, we need skip the current head, but dummy.next = head, if we still return dummy.next, this may return wrong answer.
            So we need another ListNode curr = dummy.
        
        Hence, what we need consider is dummy.next.
        
        If dummy.next.val == val, dummy.next = dummy.next.next;
        Otherwise, just move dummy forward;
        
        return curr.next;
        */
        
        // Corner case
        if (head == null) {
            return head;
        }
        
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode curr = dummy;
        
        while (dummy.next != null) {
            if (dummy.next.val == val) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
        }
        
        return curr.next;
       
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(1);
    */
/*
我想的这个方法可行吗？就是可不可以就这么直接让另外一个curr = dummy, 然后不考虑head, 直接考虑dummy.next
因为这道题里面，head有可能是被删除的，所以不可以直接返回dummy.next
*/


// Version 2: 看了Discuss，Recursive
    public ListNode removeElements(ListNode head, int val) {
        // Corner case
        if (head == null) {
            return head;
        }
        
        head.next = removeElements(head.next, val);
         
        return (head.val == val)? head.next : head;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(n)
    */
}