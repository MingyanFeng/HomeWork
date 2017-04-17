/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // Initialize result
        List<Integer> result = new LinkedList<>();
        
        // Corner case
        if (root == null) {
            return result;
        }
        
        // Use BFS to traverse each level of the tree, and let queue poll out each node of the level, at the same time, check each node's left and right child, update the next level.
        // When we reach the end of one level, the TreeNode temp will be updated to the right side node of each level.
        
        // Build a Queue to do BFS
        Queue<TreeNode> queue = new LinkedList<>();
        // Offer root node into queue
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            // get the # of nodes of each level, assume is m
            int size = queue.size();
            // Initialize a temp node, which can be updated duing each level's poll out
            TreeNode temp = null;
            // poll out each node of this level
            for (int i = 0; i < size; i++) {
                temp = queue.poll();
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            // After finish each level's traversal, add the right side node's VALUE into result
            result.add(temp.val);
        }
        return result;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(m), m == the largest # of nodes in a level, worst case: balanced tree, m = 2 / n
    */
}