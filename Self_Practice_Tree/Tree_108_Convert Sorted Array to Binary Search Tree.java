/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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
        /*
        错因：直接假设了最后一层的TreeNode数量为0.5 * nums.length，但这个不是height balanced tree的特性。
        
        分析：各种Tree的结构和特性掌握不牢固。
        
        1. A Perfect Tree (AKA: A full binary tree, proper binary tree, 2-tree):
        is a tree in which every node other than the leaves has two children. 
                         O  
                       /   \
                      O     O    
                     / \   / \ 
                    O   O O   O 
        !!!NOTE: A perfect binary tree is a complete binary tree in which the LAST level is FULL.
                                               
        
        2. A complete binary tree:
        is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
                        O
                      /   \
                     O      O
                   /  \    / \
                  O    O  O   O
                 / \
                O   O
        !!!NOTE: 
        a complete binary tree can have an incomplete LAST level, as long as all the leaves in it are pushed across to the left.
        An almost complete binary tree is a complete but not perfect binary tree. So the upper example is also almost complete.
        An almost complete binary tree is also complete.
        
        http://web.cecs.pdx.edu/~sheard/course/Cs163/Doc/FullvsComplete.html
        http://stackoverflow.com/questions/26327125/difference-between-complete-and-almost-complete-binary-tree
        
        3. Binary Search Tree （二叉查找树），也称有序二叉树（ordered binary tree）,排序二叉树（sorted binary tree），是指一棵空树或者具有下列性质的二叉树：

        1). 若任意节点的左子树不空，则左子树上所有结点的值均小于等于它的根结点的值；
        2). 任意节点的右子树不空，则右子树上所有结点的值均大于等于它的根结点的值；
        3). 任意节点的左、右子树也分别为二叉查找树。

        4. A Balanced Tree:
        A tree is only balanced if:
		1). The left and right subtrees' heights differ by at most one, AND
		2). The left subtree is balanced, AND
		3). The right subtree is balanced
		According to this, the next tree is balanced:

			     A
			   /   \
			  B     C  
			 /     / \  
			D     E   F  
			     /  
			    G  

		The next one is not balanced because the subtrees of C differ by 2 in their height:

			     A
			   /   \
			  B     C   <-- difference = 2
			 /     /
			D     E  
			     /  
			    G 

        5. A Height-balanced Binary Tree （平衡二叉树）（引自GeekforGeek）:

        An empty tree is height-balanced. A non-empty binary tree T is balanced if:
        1) Left subtree of T is balanced
        2) Right subtree of T is balanced
        3) The difference between heights of left subtree and right subtree is not more than 1.  
        
        http://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
        
        答案思路：选中点构造根节点，然后递归的构造左子树和右子树。
        突破口是如何构造根节点，由于input array is sorted in ascending order, 所以想到array的中点即是Tree的root。
        后续思想类似于Merge Sort中的Divide部分，分别找到下层各个subtree的root, 即一切二，二切四，四切八......
        Firstly, find the mid point of nums, as the root of the tree;
        Then, find the mid point of the left subtree, and the mid point of the right subtree;
        And so on, find the mid point of the left subtree's left subtree, and the mid point of the right subtree's right subtree;
        */
        
    public TreeNode sortedArrayToBST(int[] nums) {
        // Corner case
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        TreeNode root = helper(nums, 0, nums.length - 1);
        
        return root;
    }
    
    // 1. 定义
    private TreeNode helper(int[] nums, int start, int end) {
        // 3. 出口，特殊情况
        // Base case
        if (start > end) {
            return null;
        }
        
        // 2. 拆解，current layer 
        // Find mid point
        int mid = (start + end) >>> 1;
        // node is the root of the current tree
        TreeNode node = new TreeNode(nums[mid]);

        // 2. 拆解，next layer
        // Then, find the root of the current tree's left subtree and right subtree
        node.left = helper(nums, start, mid - 1);
        node.right = helper(nums, mid + 1, end);
        
        // 2. 拆解，当前层返回值
        // Return the root of current tree
        return node;
    }
    /*
    Time Complexity: O(n), n == nums.length;
    Space Compleixty: O(h), h == height of the height balanced BST, h == logn;
    */
}