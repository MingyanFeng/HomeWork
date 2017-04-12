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
        // corner case
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head;
        
        // check each node by iterating the list
        while (curr.next != null) {
            // since the list is sorted, if there're duplicate elements, they must be neighborhoods
            if (curr.val == curr.next.val) {
                // delete one of the duplicate elements
                curr.next = curr.next.next;
            // otherwise, keep iterating and checking until curr reaches the end of the list
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
/*
Time Complexity: O(n); n == # of nodes in the list

Space Complexity: O(1); curr == extra constant space consumption
*/