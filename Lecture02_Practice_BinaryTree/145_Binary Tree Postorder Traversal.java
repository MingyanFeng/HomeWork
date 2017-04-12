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
    // version 1: Iterative
/*
Need to draw a diagram to show the whole process
*/
    public List<Integer> postorderTraversal(TreeNode root) {
    	LinkedList<Integer> result = new LinkedList<>();
    	// corner case
    	if (root == null) {
    		return result;
    	}
        // !!! Better to use Deque to construct a stack, do not use Stack class !!!
    	Deque<TreeNode> stack = new ArrayDeque<>();        // !!! always add root node first !!!
    	// !!! always add root node first !!!
    	stack.push(root);

    	while (! stack.isEmpty()) {
    		TreeNode temp = stack.pop();
    		result.addFirst(temp.val);
    		if (temp.left != null) {
    			stack.push(temp.left);
    		}
    		if (temp.right != null) {
    			stack.push(temp.right);
    		}
    	}
    	return result;
    }
}
    /*
    Time Complexity: O(n); n == # of tree nodes;
    Space Complexity: O(h); h is the height of the tree;
    */