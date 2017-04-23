/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
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
// Version 1: 参考了答案
    public boolean isPalindrome(ListNode head) {
        /*
        没想出来怎么在找到linkedlist最后一个node之后，往回倒着走，于是看了答案；
        然后觉得答案里这个方法挺好的，既然linkedlist无法倒退着走，那就把需要倒着走的部分先反转过来，反转以后，正着走就相当于是在倒序遍历了
        答案思路如下：
        
        This can be solved by reversing the 2nd half and compare the two halves. Let's start with an example [1, 1, 2, 1].

        In the beginning, set two pointers fast and slow starting at the head.
        
        1 -> 1 -> 2 -> 1 -> null 
        sf
        
        (1) Move: fast pointer goes to the end, and slow goes to the middle.
        
        1 -> 1 -> 2 -> 1 -> null 
                  s          f
                  
        (2) Reverse: the right half is reversed, and slow pointer becomes the 2nd head.
        
        1 -> 1    null <- 2 <- 1           
        h                      s
        
        (3) Compare: run the two pointers head and slow together and compare.
        
        1 -> 1    null <- 2 <- 1             
             h            s
        
        */
        
        // Corner case
        if (head == null) {
            return true;
        }
        
        // Build two pointers, fast and slow
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // If there're totally odd nodes, let right half be smaller
        if (fast != null) {
            slow = slow.next;
        }
        
        // Reverse the right half, slow pointer becomes the head of right half
        slow = reverse(slow);
        // Let fast pointer become the head of left half
        fast = head;
        
        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        // Scanner node
        ListNode newHead = null;
        
        while (head != null) {
            // Reverse
            ListNode temp = head.next;
            head.next = newHead;
            
            // Prepare for next iteration
            newHead = head;
            head = temp;
        }
        return newHead;
    }
    /*
    Time Complexity: O(n + n) ~ O(n), n == # of nodes
    Space Complexity: O(1), fast + slow + temp == O(1) extra space
    */
}

/*
Method: 快慢指针 + 部分reverse
*/