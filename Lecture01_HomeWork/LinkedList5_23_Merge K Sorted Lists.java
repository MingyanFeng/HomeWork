/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 参考了答案，图示思路见oneNote笔记，记得导出一份pdf


// version 1.1: use minHeap, need a private Comparator
public class Solution {

    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode left, ListNode right) {
            return left.val - right.val;
        }
    };
    // NOTE: add ; on the end of the Comparator
    
    public ListNode mergeKLists(ListNode[] lists) {

        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // build a heap
        Queue<ListNode> queue = new PriorityQueue<>(lists.length, ListNodeComparator);
        // add each element(list)'s head node into heap
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        // poll out the min node, keep insert the following nodes in k lists into heap
        while(!queue.isEmpty()) {
            ListNode head = queue.poll();
            tail.next = head;
            tail = head;
            if (head.next != null) {
                queue.add(head.next);
            }
        }
        return dummy.next;
    }
}
/*
Time Complexity: O(mlogn), m == # of all nodes in the lists, n == lists.length

Space Complexity: O(n), n == lists.length
*/


// version 1.2: use minHeap
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // build a minHeap
        Queue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode left, ListNode right) {
                if (left.val < right.val) {
                    return -1;
                } else if (left.val == right.val) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        // NOTE: Override Comparator in the arguments, and add ; at the end
        
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

       // add each element(list)'s head node into heap
       for (ListNode node : lists) {
           if (node != null) {
               queue.add(node);
           }
       }

       // poll out the min node, keep insert the following nodes in k lists into heap
       while (!queue.isEmpty()) {
           tail.next = queue.poll();
           tail = tail.next;
           if (tail.next != null) {
               queue.add(tail.next);
           }
       }
       return dummy.next;
    }
}
/*
Time Complexity: O(mlogn), m == # of all nodes in the lists, n == lists.length

Space Complexity: O(n), n == lists.length
*/


