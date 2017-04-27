/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
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
    public ListNode deleteDuplicates(ListNode head) {
        // Corner case
        if (head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(1);
    */
}
/*
本题就需要考虑head和head.next是否同时为空，因为要比较head和head.next的value。
LinkedList的题目，while循环中的判断条件需要格外注意，比如，如果while循环中需要用到head.next，那么必须考虑head.next。
*/