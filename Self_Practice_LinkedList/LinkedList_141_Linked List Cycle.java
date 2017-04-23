/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        /*
        Use two pointers, fast and slow, fast moves two steps each time, slow moves one step each time.
        
        If there's a circle, fast and slow will meet each other, which means they have the same value.
        Otherwise, there's no circle.
        */
        
        // Corner case
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // !!! NOTE: need to judge if slow and fast are null FIRST, then to compare their val !!!
            if (slow != null && fast != null && slow.val == fast.val) {
                return true;
            }
        }
        return false;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(1), slow + fast == O(1) extra space;
    */
}
/*
Method: 快慢指针 + 判断快慢指针是否相遇
*/