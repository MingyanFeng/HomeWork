/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // corner case
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // we need two pointers that keep their distance == n
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        // move fast, until it aheads of slow in the distance of n
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // move both fast and end, until fast reaches the end of the list
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // remove the target node
        slow.next = slow.next.next;
        return dummy.next;
    }
}
/*
Time Complexity: O(n); n == # of nodes in the list

Space Complexity: O(1); slow + fast == extra constant space consumption
*/