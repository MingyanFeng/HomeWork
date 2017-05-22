/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

// Tags:  Depth-first Search Breadth-first Search Union Find Graph

public class Solution {
    public int countComponents(int n, int[][] edges) {
        /*
        Use DFS to scan every node;
        Use a int variable, result, to add total connected components;
        If current node has not been visited, counter++; At the same time, set nodes connected to current node as visited;
        Otherwise, keep scanning;
        Use a HashSet to judge if the current node is visited;
        */
        
        // Corner case
        if (n <= 1) {
            return n;
        }
        
        // Store the graph in a HashMap
        Map<Integer, List<Integer>> hm = new HashMap<>();
        // Initialize HashMap
        for (int i = 0; i <n; i++) {
            hm.put(i, new ArrayList<>());
        }
        // Since it's an undirected graph, we need to consider both side of an edge when doing the storage
        for (int[] edge: edges) {
            hm.get(edge[0]).add(edge[1]);
            hm.get(edge[1]).add(edge[0]);
        }
        
        // Use to HashSet to remove duplicates
        Set<Integer> isVisited = new HashSet<>();
        // Set result to 0
        int result = 0;
        // Use a dfsHelper to traverse the graph, and count the connnected components
        for (int i = 0; i < n; i++) {
            if (isVisited.add(i)) {
                dfsHelper(i, hm, isVisited);
                result++;
            }
        }
        return result;
    }
    
    private void dfsHelper(int i, Map<Integer, List<Integer>> hm, Set<Integer> isVisited) {
        for (int j : hm.get(i)) {
            if (isVisited.add(j)) {
                dfsHelper(j, hm, isVisited);
            }
        }
    }
}
/*
Time Complexity: O(v + e), v == # of vertices, e == # of edges;
Space Complexity: O(v);
*/