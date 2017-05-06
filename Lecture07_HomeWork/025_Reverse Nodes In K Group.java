/*
25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list. 
k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
You may not alter the values in the nodes, only nodes itself may be changed.
Only constant memory is allowed.
For example,
Given this linked list: 1->2->3->4->5 
For k = 2, you should return: 2->1->4->3->5 
For k = 3, you should return: 3->2->1->4->5 
*/

public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode fakeHead = new ListNode(Integer.MIN_VALUE);
        fakeHead.next = head;
        int numOfReverse = getNumOfNodes(fakeHead) / k; // find # of nodes, and get # of groups that should be reversed
        ListNode temp = fakeHead;
        for (int i = 0; i < numOfReverse; i++) {
            temp = reverse(temp, k); // reverse each group
        }
        return fakeHead.next;
    }
    /*
    Time complexity: O(n);
    Space complexity: O(1);
    */
    
    private int getNumOfNodes(ListNode head) { // find # of nodes
        int result = 0;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            result++;
        }
        return result;
    }
    
    private ListNode reverse(ListNode head, int k) { // reverse a group
        ListNode tail = head.next;
        for (int i = 0; i < k - 1; i++) {
            ListNode temp = tail.next;
            tail.next = tail.next.next;
            temp.next = head.next;
            head.next = temp;
        }
        return tail;
    }

    public static void main(String[] args) {
        
    }
}