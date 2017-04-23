/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

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
    public ListNode detectCycle(ListNode head) {
        /*
        Use two pointers, fast and slow, fast moves two steps each time, slow moves one step each time.
        
        If there's a circle, fast and slow will meet each other. At this time, need another pointer called slow2, move slow and slow2 one steps each time, until slow meets slow2, we return slow.
        Otherwise, there's no circle, return null.
        */
        
        // Corner case
        if (head == null || head.next == null) {
            return null;
        } 
        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 详细讲解在Self_Practice_LinkedList_Diagram那页笔记中
            if (fast == slow) {
                ListNode slow2 = head;
                // !!! NOTE: here should use WHILE LOOP, not if(), cause slow and slow2 need constantly move on if they don't meet the condition
                while (slow != slow2) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(1), fast + slow2 == extra O(1) space;
    */
}
/*
Method: 快慢指针 + 判断快慢指针是否相遇 + 通过距离差求得环的起点
*/