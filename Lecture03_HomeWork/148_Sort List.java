/*
Sort a linked list in O(n log n) time using constant space complexity.
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
    public ListNode sortList(ListNode head) {
        // Corner case
        if (head == null || head.next == null) {
            return head;
        }
        
        /*
        Use Merge sort, see diagram
        */
        
        // Step 1: Cut the input list into two halves; current layer
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        
        // Find the mid point of the list
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Break the list
        prev.next = null;
        
        // Step 2: sort each half; next layer
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        
        // Step 3: merge l1 and l2
        return merge(l1, l2);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            // DON'T forget to update temp
            temp = temp.next;
        }
        
        // Double check the remaining ListNodes
        if (l1 != null) {
            temp.next = l1;
        }
        
        if (l2 != null) {
            temp.next = l2;
        }
        return dummy.next;
    }
    /*
    Time Complexity: O(nlogn), n = # of total ListNodes
    Space Complexity: O(logn) + O(1) == O(logn), Step 2 is O(logn), Step 3 is O(1);  
    */
}