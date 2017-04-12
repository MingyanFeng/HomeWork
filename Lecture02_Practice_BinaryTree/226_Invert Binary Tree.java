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
    // version 1: DFS
    public TreeNode invertTree(TreeNode root) {
        // corner case
        if (root == null) {
            return root;
        }
        // swap using recursive DFS. Bottom up, go down to the left node of the tree, and then, go up.
        // !!! NOTE: we need to call invertTree method when we swap
        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);
        return root;
    }
    /*
    Time Complexity: O(n), n == # of nodes in the tree;
    Space Complexity: O(h), h == height of the tree;
    */

// version 2: BFS
     public TreeNode invertTree(TreeNode root) {
        // corner case
        if (root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
    /*
    Time Complexity: O(n), n == # of nodes in the tree;
    Space Complexity: O(m), m == # of the nodes in the max level that contains max # of tree nodes
    */
}
