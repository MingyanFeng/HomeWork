/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

// version 1: use boolean variable
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Initialize results
        List<List<Integer>> results = new LinkedList<>();
        
        // Corner case
        if (root == null) {
            return results;
        }
        
        // Use BFS to traverse the tree level by level
        // At the same time, use a boolean variable to help us decide if this level is even level, if is even level, we need to add nodes in a backwards order.
        // We can use LinkedList Class to initilaize the level, so that we can use addFirst() method to help us add nodes in a backwards order while traversing a level.
        
        // Build a queue to help us do the BFS
        Queue<TreeNode> queue = new LinkedList<>();
        // Offer the root into queue
        queue.offer(root);
        
        // Initialize a boolean variable called isEvenLevel
        boolean isEvenLevel = false;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            // !!! Use LinkedList Class to initialize the level, in order to use addFirst() method !!!
            LinkedList<Integer> level = new LinkedList<>();
            
            // Traverse each level
            for (int i = 0; i < size; i++) {
                // poll out the first node
                TreeNode head = queue.poll();
                
                // Judge if this level is even level in order to decide what order we need to add nodes.val
                if (isEvenLevel) {
                    level.addFirst(head.val);
                } else {
                    level.addLast(head.val);
                }
                
                // Check if head.left and head.right is null
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            
            }
            // Add each level's nodes into results
            results.add(level);
            // !!! Inverse the boolean variable isEvenLevel !!!
            isEvenLevel = !isEvenLevel;
        }
        // After doing ALL level's traversal, return results
        return results;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(m), m == the largest # of nodes in one level; worst case is the tree is a balanced tree, m == n / 2
    */
}

// version 2: use int variable counter
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Initialize results
        List<List<Integer>> results = new LinkedList<>();
        
        // Corner case
        if (root == null) {
            return results;
        }
        
        // Use BFS to traverse the tree level by level
        // At the same time, use a int variable counter, initialized to 1, after each level's traversal, we let counter++. if (counter % 2 == 1), we say is odd level, if (counter % 2 == 0), we say is even level
        // We can use LinkedList Class to initilaize the level, so that we can use addFirst() method to help us add nodes in a backwards order while traversing a level.
        
        // Build a queue to help us do the BFS
        Queue<TreeNode> queue = new LinkedList<>();
        // Offer the root into queue
        queue.offer(root);
        
        // Initialize a int variable called counter
        int counter = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            // !!! Use LinkedList Class to initialize the level, in order to use addFirst() method !!!
            LinkedList<Integer> level = new LinkedList<>();
            
            // Traverse each level
            for (int i = 0; i < size; i++) {
                // poll out the first node
                TreeNode head = queue.poll();
                
                // Judge if this level is even level in order to decide what order we need to add nodes.val
                if (counter % 2 == 0) { // even level
                    level.addFirst(head.val);
                } else { // odd level
                    level.addLast(head.val);
                }
                
                // Check if head.left and head.right is null
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            // Add each level's nodes into results
            results.add(level);
            // !!! Let counter++ !!!
            counter++;
        }
        // After doing ALL level's traversal, return results
        return results;
    }
    /*
    Time Complexity: O(n), n == # of nodes;
    Space Complexity: O(m), m == the largest # of nodes in one level; worst case is the tree is a balanced tree, m == n / 2
    */
}