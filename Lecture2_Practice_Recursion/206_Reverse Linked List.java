/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

// version 1: Iteration
	public ListNode reverseListIterative(ListNode head) {
		// corner case
		if (head == null || head.next == null) {
			return head;
		}
		// Iterate the LinkedList, reverse node by node
		ListNode prev = null;
		ListNode next = null;
		// !!! NOTE: while's judgement is head != null, not head.next != null
		while (head != null) {
			next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
	/*
	Time Complexity: O(n); n == # of nodes
	Space Complexity: O(1);
	*/


// version2: Non-Tail Recursion
	public ListNode reverseListIterative(ListNode head) {
		// Step 1. base case
        if (head == null || head.next == null) {
            return head;
        }
        
        // Step 3. next layer, assuming that all following levels are already reversed;
        ListNode newHead = reverseList(head.next);
        
        // Step 2. current layer (core logic)
        head.next.next = head;
        head.next = null;
        
        // return the result
        return newHead;
	}
	/*
	Time Complexity: O(n); n == # of nodes
	Space Complexity: O(n);
	*/
}