public class Modify {
	/*
	1. Given a singly LinkedList, how to add a node to it?

	Consider:
	1. What if the head is null;
	2. Insert to the end
	*/
	public ListNode addNode(ListNode head, int val) {
		// corner case
		if (head == null) {
			return new ListNode(val);
		}
		// add node in the end of the LinkedLikst
		ListNode curr = head;
		// iterate LinkedList
		while (head.next != null) {
			head = head.next;
		}
		head.next = new ListNode(val);
		return curr;
	}
	/*
	Time Complexity: O(n); n == # of nodes
	Space Complexity: O(1);
	*/


	/*
	2. Delete a node in LinkedList

	Consider:
	1. Node to be deleted is head;
	2. Node to be deleted is some other node excpet head

	Related: LeetCode 237 Delete Node in a Linked List
	*/
	public ListNode deleteNode(ListNode head, int val) {
		// corner case
		if (head == null) {
			return null;
		}
		// if the head is the node we want to delete
		if (head.val == val) {
			head = head.next;
			return head;
		}
		// delete the node(except head)
		ListNode curr = head;
		while (head.next != null) {
			if (head.next.val == val) {
				head.next = head.next.next;
				return curr;
			}
			head = head.next;
		}
		return curr;
	}
	/*
	Time Complexity: O(n); n == # of nodes
	Space Complexity: O(1);
	*/


	/*
	3. Reverse LinkedList
	*/
	// version 1: Iterative
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

	// version 2: Recursive
	// the definition of the recursion
	public ListNode reverseListRecursive(ListNode head) {
		// base case, the exit
		if (head == null || head.next == null) {
			return head;
		}
		// the definition of the recursion
		ListNode newHead = reverseListRecursive(head.next);

		// reverse the LinkedList recursively, the split of the recursion
		head.next.next = head;
		head.next = null;

		return newHead;
	}
	/* 
	Time Complexity: O(n); n == # of nodes
	Space Complexity: O(n);
	*/


	/*
	4. Merge Two Sorted Lists

	Given two sorted lists, merge them in ascending order
	*/
	public ListNode mergeSortedLists(ListNode l1, ListNode l2) {
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
	/* 
	Time Complexity: O(n); n == # of nodes
	Space Complexity: O(1); curr = O(1) extra space
	*/
}