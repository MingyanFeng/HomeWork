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
     * @param head: the head of linked list.
     * @return: a middle node of the linked list
     */
    public ListNode middleNode(ListNode head) { 
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        // we need two pointers to help us find the mid point.
        // fast will go two nodes each time, slow will go one node each time.
        // when fast reaches the end, slow is the middle.
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
        ListNode result = test.middleNode(node1);

        System.out.println(result.val);
    }
}
/*
Time Complexity: O(n); n == # of nodes in the list

Space Complexity: O(1); fast == extra constant space consumption
*/