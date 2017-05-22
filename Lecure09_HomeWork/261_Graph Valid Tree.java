/*
	Given n nodes labeled from 0 to n - 1 
	and a list of undirected edges (each edge is a pair of nodes), 
	write a function to check whether these edges make up a valid tree.	

	! Notice
	You can assume that no duplicate edges will appear in edges. 
	Since all edges are undirected, 
	[0, 1] is the same as [1, 0] and thus will not appear together in edges.

	Example 
	Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
*/

public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        /*
        My thinking is:
        If these edges make up a valid tree, 
          they should meet 2 conditions:
        The first condition is the number of edges should equals to n - 1.
        
        The second condition is the graph must be connected. 
          That is, if we start from any point, we can access all the points.
          And we can use Breadth-First Search algorithm to traverse 
          each level of the binary tree, to see if the graph is connected.
        */
        
        /*
        The advantage of this method is:
        The disadvantage of this method is:
        No need talk about them, because we cannot think of other solutions
        */

        /*
        Before we write the next code, we need to consider some corner cases.
        If n equals to zero, it means there's no tree, we should return false.
        */
        if (n == 0) {
            return false;
        }
        
        /*
        For the first condition, 
          Since edges are entered as a two-dimensional array,
          the first condition should be edges.length == n - 1.
        if edges.length DOESN'T equal to n - 1, 
          it means there's no valid tree.
        */
        if (edges.length != n - 1) {
            return false;
        }
        
        /*
        For the second condition, 
          we can use a HashMap to initiallize an Adjacency List,
          which shows all pairs of nodes of each edge.
        The key of the HashMap is the current node.
        The value of the HashMap is the current node's neighbors.
        */
        
        /*
        Incase of duplicate input, we use a Set as the HashMap's value.
        And we can use a method called initializedGraph 
          to do the initialization here, 
          I will write the validTree method first, and then fill this method.
        */
        
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        
        /*
        After initializing the graph, we can use Breadth-First Search algorithm
          to see if the graph is connected.
        We can use a Queue to do the Breadth-First Search.
        And we need a HashSet to prevent duplicate neighbors of current node.
        */
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();
        
        queue.offer(0);
        hash.add(0);
        /*
        We also need a int varable named "visited" to see 
          if all nodes has been visited from a node in the graph.
        We should set visited to zero.
        */
        int visited = 0;
        
        while (!queue.isEmpty()) {
            /*
            When queue poll a TreeNode, we let visited add 1.
            */
            int node = queue.poll();
            visited++;
            /*
            Then we need to add current node's neighbor into hash and queue,
            to continue checking the connectivity of the graph.
            */
            for (int neighbor : graph.get(node)) {
                /* If hash contains neighbor, we continue.*/
                if (hash.contains(neighbor)) {
                    continue;
                }
                /* Otherwise, we add neighbor into hash and queue*/
                hash.add(neighbor);
                queue.offer(neighbor);
            }
        }
        /* 
        Then, we return if visited equals to n.
        That is, if the second condition is true.
        */
        return (visited == n);
    }
    
    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        // Traverse all nodes
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        // Find all pair of nodes of each edge
        for (int i = 0; i < edges.length; i++) {
            // u means i th edge's one side end
            // v means i th edge's the other side end
            int u = edges[i][0];
            int v = edges[i][1];
            /*
            We need to store neighbors in the graph
            Since we have undirected edges, 
            we should consider both end nodes
            */  
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // We return graph when we finish the initialization
        return graph;
    }
}
		/*
        The Time Complexity: O(n + m). Initialization is O(n + m), BFS is O(n + m), O(2n + 2m) == O(n + m)
        n is # of vertices, m is edges.length
        
        The Space Complexity:
        graph: O(2m) = O(m)
        queue: O(n)
        hash: O(n)
        Total Space Complexity: O(m) + O(n) + O(n) = O(n + m)
        */