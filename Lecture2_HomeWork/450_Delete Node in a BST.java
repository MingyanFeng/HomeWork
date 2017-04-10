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
    public TreeNode deleteNode(TreeNode root, int key) {
        // corner case
        if (root == null) {
            return null;
        }
        // if the root is the node we want to delete
        if (root.val == key) {
            return deleteRoot(root);
            // deleteRoot method is used for deleting the node with the given key in the BST 
            // and returning the root node reference (possibly updated) of the BST
        }
        TreeNode tempRoot = root;
        while (tempRoot != null) {
            // if tempRoot.left is the node we want to delete
            if (tempRoot.left != null && tempRoot.left.val == key) {
                tempRoot.left = deleteRoot(tempRoot.left);
                break;
            }
            // if tempRoot.right is the root we want to delete
            if (tempRoot.right != null && tempRoot.right.val == key) {
                tempRoot.right = deleteRoot(tempRoot.right);
                break;
            }
            // update tempRoot
            if (tempRoot.val < key) {
                tempRoot = tempRoot.right;
            } else {
                tempRoot = tempRoot.left;
            }
        }
        return root;
    }
    // need draw a diagram to show different situations
    private TreeNode deleteRoot(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        if (root.right.left == null) {
            TreeNode newRoot = root.right;
            newRoot.left = root.left;
            return newRoot;
        }
        TreeNode temp = root.right;
        while(temp.left.left != null) {
            temp = temp.left;
        }
        TreeNode newRoot = temp.left;
        temp.left = newRoot.right;
        newRoot.left = root.left;
        newRoot.right = root.right;
        return newRoot;
    }
}
    /*
    Time Complexity: O(n), n == # of total node's in the tree
    Space Complexity: O(1)