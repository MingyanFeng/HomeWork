/*
99. Recover Binary Search Tree

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/

// Tags: Tree Depth-first Search

/*
Analysis:

对于这道题目，我们不妨先看看，如果将一个有序数组中的两个元素进行交换了，如何找出这两个数？（找到后还原是简单的）

如对于数组1,2,7,4,5,6,3,8,9，如何判断是哪两个元素发生了交换呢？

不难发现，新的数组中存在两对逆序并相邻的数字，即7,4和6,3，造成这出现的原因，正是发生了一次交换，由于一定是较小的数换到了较大数的位置（向后），较大的数换到了较小数的位置（向前）。所以在这两对中，我们可以简单的判断出：是前一对的较大数和后一对的较小数发生了交换。

当然存在一些特殊情况，最简单的就是1,2,4,3,5,6，只存在一对逆序，这是因为交换的两个数本身是相邻的，对于这种情况，我们进行分类讨论与判断即可。
*/
// 用于存储当前找到的发生交换的数
int first = -1;
int second;
// 从前往后依次寻找逆序并相邻的数字对
for (int i = 0; i < n; i++) {
    // 存在逆序
    if (i > 0 && val[i] < val[i - 1]) {
        // 如果当前一对都没有找到
        if (first == -1) {
            // 不放先假设只有一对
            first = i - 1; 
            second = i;
        }
        else {
            // 存在两对，只需要修改second
            second = i;
        }
    }
}
// Swap(val[first], val[second])
/*
Analysis:

现在，数组上的问题得到了解决，那么如何在二叉搜索树上解决类似的问题呢？

由于二叉搜索树的中序遍历实际上就是按照从小到大的顺序依次枚举每个元素，所以只需要使用一次深度优先搜索。

我们就可以像数组一样的访问二叉搜索树的每一个元素，即
*/
void dfs(root) {
    dfs(root -> left)
    // 仿照数组的算法处理root -> val与prev
    prev = root -> val 
    dfs(root -> right)
}
/*
Analysis:
这样一来，我们就可以将数组上的算法套用在二叉搜索树上，找到发生交换的两个位置，最后再将它们交换回来就可以。
*/


// Version 1: Brutal Force
	/*
	因为是BST，所以如果按照中序遍历的方法遍历一遍得到的序列一定是有序的，那么遍历一遍序列就知道了哪两个Node是错误的。

	但是空间复杂度为O(n)，并不符合题目要求。 
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

    List<TreeNode> nodes;

    public void recoverTree(TreeNode root) {

        nodes = new ArrayList<>();

        inorder(root);

        int size = nodes.size();

        TreeNode first=null;
        TreeNode second=null;

        for (int i = 0;i<size-1;i++) {
        	TreeNode prev = nodes.get(i);
            TreeNode next = nodes.get(i+1);

            if (prev.val>next.val) {
                if (first == null) {
                    first = prev;
                    second = next;                    
                } else {
                    second = next;
                }
            }
        }

        int val = first.val;
        first.val = second.val;
        second.val = val;
    }

    private void inorder(TreeNode root){
    	// Corner case
        if (root==null) {
            return;
        }

        inorder(root.left);
        nodes.add(root);
        inorder(root.right);
    }
}


// Version 2: Recursion
	/*
	看到了一种仿第一种做法的递归版本。
	只是这里需要维护一个prev节点，该节点就是中序遍历时的前一个节点。
	然后将prev节点与当前结点比较，如果prev节点大于当前结点，说明存在问题，但并不肯定是这两个节点发生了互换。
	等全部节点遍历一遍之后，如果有两组发生错误的地方，那么就将第一组的第一个错误点和第二组的第二个错误点交换。
	如果只有一组发生错误，那就两个节点互换即可。 

	空间复杂度为O(logN)，理论上也不符合要求。但是更进一步的O(1)的算法已经看不懂了。。。
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

    TreeNode prev = null;
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        inOrder(root);

        int val = first.val;
        first.val = second.val;
        second.val = val;
    }

    private void inOrder(TreeNode root){
        if (root==null) {
            return;
        }

        inOrder(root.left);

        if (prev!=null && prev.val>root.val) {		// Find the wrong element
            if (first == null) {
                first = prev;
                second = root;
            } else {
                second = root;
            }
        }

        prev = root;
        inOrder(root.right);
    }
}