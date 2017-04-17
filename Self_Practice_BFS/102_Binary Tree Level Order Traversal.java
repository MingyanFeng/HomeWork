	/*
	Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

	For example:
	Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its level order traversal as:
	[
	  [3],
	  [9,20],
	  [15,7]
	]
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
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        /* 
        My thinking is to use Breadth-First Search algorithm 
          to traverse each level of the binary tree. 
        At the same time, use a queue to store each level's TreeNodes firstly.
        Second, transfer the storage of each level's TreeNodes' value 
          to an ArrayList of Integer.
        Then, we can use an ArrayList of ArrayList to sotre each level's results.
        */
        
		/*
		No need to talk about The advantage and disadvantage
		Because this is the best solution.
		*/

        /*
        Since The function return value type is an ArrayList containing an ArrayList,
          we can construct an empty return value called results.
        */
        
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        
        /*
        Before we write the next code, we need to consider some corner cases.
        If the root is null, there's no tree, we can should return null.
        */
        
        if (root == null) {
            return results;
        }
        
        /*
        After considering corner cases, let's use Breadth-First Search algorithm 
          to traverse each level of the binary tree.
        We can use a Queue to do the Breadth-First Search.
        */
        
        Queue<TreeNode> queue = new LinkedList<>();
        // We should offer the root into queue.
        queue.offer(root);
        
        /*
        while queue is not empty, 
          we new an ArrayList of Integer, to restore each level's TreeNodes' value.
        And we need to set an int varable named "size" to determine 
          whether we reach the end of each level. 
        */
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                /*
                Here we new a TreeNode named "head" to represent 
                  the first element in queue.
                */ 
                TreeNode head = queue.poll();
                level.add(head.val);
                /*
                After adding head's value, we need to consider
                  wether the head has left child and right child.
                If it has, we need to offer its child into queue,
                  before we adding the rest of TreeNodes' value into level.
                */
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            /*
            After each level's traversal, 
              we add each level's TreeNodes' value into results.
            */
            results.add(level);
        }
        // Finally, we return the results.
        return results;
    }
}
		/*
		The time complexity is O(n + (n - 1)) = O(2n - 1) = O(n), n == # of nodes;
		The space complexity is O(m), m == the largest # of nodes in one level; 
			Worst case: complete tree, m == number of leaves, which equals to n/2 (n is the number of nodes in a complete tree)
		*/