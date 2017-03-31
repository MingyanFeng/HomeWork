public class Test {
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);

		node1.next = node2;
		node2.next = node3;

		ListNode node4 = new ListNode(6);
		ListNode node5 = new ListNode(7);
		ListNode node6 = new ListNode(8);
		ListNode node7 = new ListNode(9);

		node4.next = node5;
		node5.next = node6;
		node6.next = node7;

		/*
		when addNode method is static, we can type:
		Modify.addNode(node1, 4);

		but now addNode is not a static method, so we need to create a new object of Modify class to call addNode()
		*/ 
		Modify head = new Modify();

		// test add Node
		head.addNode(node1, 4);
		while (node1 != null) {
			System.out.print(node1.val + " ");
			node1 = node1.next;
		}
		
		// test delete node
		ListNode deleted = head.deleteNode(node1, 2);
		while (deleted != null) {
			System.out.print(deleted.val + " ");
			deleted = deleted.next;
		}
		

		// test reverse list
		ListNode reversed1 = head.reverseListIterative(node1);
		while (reversed != null) {
			System.out.print(reversed1.val + " ");
			reversed1 = reversed1.next;
		}

		ListNode reversed2 = head.reverseListRecursive(node1);
		while (reversed2 != null) {
			System.out.print(reversed2.val + " ");
			reversed2 = reversed2.next;
		}

		// test merge two sorted lists
		ListNode merged = head.mergeSortedLists(node1, node4);
		while (merged != null) {
			System.out.print(merged.val + " ");
			merged = merged.next;
		}
	}
}