/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
// Version 1: 自己想的，但是没能AC
/*
Submission Result: Time Limit Exceeded

Last executed input:
Intersected at '3': [3]
[2,3]
*/
/*
想问一下，这个自己构造一个环，返回结果前再把环解开的思路，正确吗？
程序错在哪里了呢？
*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*
        Let the last node in A.next = headB;
        Then, this question is equivalent to #142;
        Do not forget to separate the last node in A with headB;
        */
        
        // Corner case
        if (headA == null || headB == null) {
            return null;
        }
        
        // Build a cycle
        ListNode temp = headA;
        
        while (temp != null && temp.next != null) {
            temp = temp.next;
        }
        temp.next = headB;
        
        ListNode fast = headB;
        ListNode slow = headA;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode slow2 = headA;
                while(slow != slow2) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                // Break the cycle
                temp.next = null;
                // return slow
                return slow;
            }
        }
        temp.next = null;
        return null;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(1), temp + fast + slow2 == O(1) extra space;
    */
}
/*
Method: 自己构造一个环 + 快慢指针
*/