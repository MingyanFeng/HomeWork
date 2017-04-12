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
    public int kthSmallest(TreeNode root, int k) {
        // corner case 
        if (root == null) {
            return root.val;
        }
        // need a variable to count left subtree's total nodes
        int leftNodes = countNodes(root.left);
        // if k <= leftNodes, we just find kth smallest element in the left subtree
        if (k <= leftNodes) {
            return kthSmallest(root.left, k);
        // if k > leftNodes + 1, we need to find kth smallest element in the right subtree
        // NOTE: 1 is counted as current node, we need to let k - 1 -leftNodes as a new k
        } else if (k > leftNodes + 1) {
            return kthSmallest(root.right, k - 1 - leftNodes);
        // otherwise, k == leftNodes + 1, kth smallest element is the root
        } else {
            return root.val;
        }
    }
    
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
    /*
    Time Complexity: O(h^2); h == height of the BST
    Space Complexity: O(h); h == height of the BST
    */