/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
// Version 1: 想到了用%搭配/的方法, 但是程序我参看了答案，还是需要强化思路转成代码的能力
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        (l1.val + l2.val) % 10 == add up value;
        (l1.val + l2.val) / 10 == carry value;
        */
        
        // Corner Case
        
        // Initialize a newHead
        ListNode newHead = new ListNode(-1);
        // Need another scanner node for newHead
        ListNode temp = newHead;
        // Need another two scanner to scan l1 and l2 digit by digit 
        ListNode d1 = l1;
        ListNode d2 = l2;
        
        int sum = 0;
        
        while (d1 != null || d2 != null) {
            // Renew sum by dividing 10
            sum /= 10;
            
            // Get new sum
            if (d1 != null) {
                sum += d1.val;
                d1 = d1.next;
            }
            
            if (d2 != null) {
                sum += d2.val;
                d2 = d2.next;
            }
            
            // Build temp.next and update temp
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }
        // If there's still a carry after we add all digits, we let temp.next = new ListNode(1)
        if (sum / 10 == 1) {
            temp.next = new ListNode(1);
        }
        
        return newHead.next;
    }
    /*
    Time Complexity: O(n + m), n == # of nodes in l1, m == # of nodes in l2;
    Space Complexity: O(1), temp + d1 + d2 + sum == O(1) extra space;
    */
}
/*
Method: 求和进位问题，% 与 / 搭配
*/