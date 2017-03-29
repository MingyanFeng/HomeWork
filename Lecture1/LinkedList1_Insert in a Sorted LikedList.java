class ListNode {     
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    /**
     * @param head: The head of linked list.
     * @param val: an integer
     * @return: The head of new linked list
     */
    public ListNode insertNode(ListNode head, int val) {
        // corner case
        if (head == null) {
            return new ListNode(val);
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;

        // keep curr moving forward until we find the insert position
        while (curr.next != null && curr.next.val <= val) {
            curr = curr.next;
        }

        // insert the target node
        ListNode temp = new ListNode(val);
        temp.next = curr.next;
        curr.next = temp;
        
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3; 
        node3.next = node4;

        Solution test = new Solution();
        ListNode result1 = test.insertNode(node1, 5);
        ListNode result2 = test.insertNode(node1, 0);

        while (result1 != null) {
            System.out.print(result1.val + " ");
            result1 = result1.next;
        }

        System.out.println();

        while (result2 != null) {
            System.out.print(result2.val + " ");
            result2 = result2.next;
        }
    }
}
/*
Time Complexity: O(n); n == # of nodes in the list

Space Complexity: O(1); curr == extra constant space consumption
*/