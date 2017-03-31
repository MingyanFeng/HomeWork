/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        // corner case
        if (node == null) {
            return;
        }
        
        /*
        since we only have access to the target node,
        we let the value of the target node == (target node).next.val
        and then let (target node).next = (target node).next.next
        
        1 -> 2 -> 3(target) -> 4(original) -> null  =>  1 -> 2 -> 4(target) -> 4(original) -> null
        
        =>  1 -> 2 -> 4(target) -> null
        */
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
/*
Time Complexity: O(1);

Space Complexity: O(1);
*/