	/*
	Given a set of distinct integers, return all possible subsets.

	Notice
	Elements in a subset must be in non-descending order.
	The solution set must not contain duplicate subsets.

	Example 
	If S = [1,2,3], a solution is:
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
	*/

class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        /*
        We can use Depth-First Search (DFS) algorithm to solve this problem, 
            and we need to use Recurssion to do the DFS.
        */
        
        /*
        Initialize the results
        */
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        /*
        Before we write the next code, we need to consider some corner cases.
        */
        if(nums == null || nums.length == 0) {
            return results;
        }
        
        /*
        选代表
        [1,2,3]和[1,3,2]中，由于[1,2,3]排序了、有序，所以选[1,2,3]
        
        为保证结果按照升序挑选，需要给nums排序，但排序不是必须的步骤，因为dfsHelper是根据startIndex从0开始递增，一直到nums.length - 1的
        
        startIndex表示下一次从哪个下标开始搜索
        
        results存储每次DFS搜索结束时的结果
        */
        // Arrays.sort(nums);  
        /*
        这里的dfsHelper的定义
        把所有 空集 开头的集合，都丢到results里，其实就是把所有子集都丢到results

        Here, we need a method called dfsHlper to help us:
        1. Search subsets that beginign with nums[i], [] included; 
		2. Add all subsets to results.
        */
        dfsHelper(nums, 0, new ArrayList<Integer>(), results);

        /*
		When we finish searching, return results.
        */
        return results;
    }

    /*
    1. recursion definition
    把所有以 当前subset参数 开头的集合，都丢到results里（他要做这件事情）

    So, in dfsHelper method, we need to:
    1. Search subsets that beginign with nums[i], [] included; 
	2. Add all subsets to results.
    */
    private void dfsHelper(int[] nums,
                           int startIndex,
                           ArrayList<Integer> subset,
                           ArrayList<ArrayList<Integer>> results) {
        /*
        初始阶段：
        nums = [1,2,3]
        startIndex = 0;
        subset = null; subset此时是空集[]
        results = null;
        */
        
        /*
        2. Recurssion 拆解
        空集[]也是子集，所以要先把空集丢到results里
        
        给 当前的subset 拍照定格；
        之后无论形参中的subset怎么变化，都与此处results中加入的subset无关
		
		First, we need to add null set into results. 
        !!! Deep Copy! We cannot add subset's reference in the results.
        */
        results.add(new ArrayList<Integer>(subset));

        /*
		Then, we need to search subsets that beginign with int[i]
        */
        for (int i = startIndex; i < nums.length; i++) {
            // i = 0, subset = num[0] = [1]; 此时，当前subset为[1]， 先把[1]丢到results里
            // We add subset that only contains [1] into results
            subset.add(nums[i]); 
            // 再把所有[1]开头的集合，都丢到results里
            // Then, we need to search all subsets that begining with 1 into results
            dfsHelper(nums, i + 1, subset, results);
            // 已经把所有[1]开头的集合，都丢到results里了，现在要把[1]开头的集合清空，这样才能为[2]和[3]开头的集合腾出空间，继续进行DFS
            // After adding all subsets that start with 1, we need to backtrack, then, we can find all other subsets
            subset.remove(subset.size() - 1);
        }
        /*
        3. Recurssion 出口
        */
    }
}
	/*
	n == # of elements in nums

	Time Complexity: O(2^n)
	Because each element has 2 possibilities, in or not in the subset.

	搜索的时间复杂度：O(答案总数 * 构造每个答案的时间)
	举例：Subsets问题，求所有的子集。
	子集个数一共 2^n，每个集合的平均长度是 O(n) 的，所以时间复杂度为 O(n * 2^n)，
	同理 Permutations 问题的时间复杂度为：O(n * n!)

	Space Complexity: O(n)
	subset is O(n)
	*/