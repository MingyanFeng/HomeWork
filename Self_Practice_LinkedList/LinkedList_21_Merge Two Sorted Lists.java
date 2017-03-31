/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       // corner cases
       if (l1 == null && l2 == null) {
           return null;
       }
       if (l1 == null) {
           return l2;
       }
       if (l2 == null) {
           return l1;
       }
       
       ListNode dummy = new ListNode(-1);
       ListNode curr = dummy;
       // merge while iterating, put the smaller node first
       while (l1 != null && l2 != null) {
           if (l1.val <= l2.val) {
               curr.next = l1;
               // move l1 forward
               l1 = l1.next;
           } else {
               curr.next = l2;
               // move l2 forward
               l2 = l2.next;
           }
           // NOTE: do NOT forget to move curr forward
           curr = curr.next;
       }
       // check remaining nodes, no need to move forward l1 or l2
       if (l1 != null) {
           curr.next = l1;
           // l1 = l1.next <- UNNECESSARY, this would skip nodes in l1, only get the end node of l1
       }
       if (l2 != null) {
           curr.next = l2;
           // l2 = l2.next <- UNNECESSARY, this would skip nodes in l2, only get the end node of l2
       }
       return dummy.next;
    }
}
/* 
Time Complexity: O(n); n == # of nodes

Space Complexity: O(1); curr = O(1) extra space
*/