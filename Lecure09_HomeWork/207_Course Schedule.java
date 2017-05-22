/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*/

// Tags:  Depth-first Search, Breadth-first Search, Graph, Topological Sort

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*
        NOTE: prerequisites的第二列是先修课
        
        Use BFS, need 3 steps:
        Step 1: Count each course's indegree;
        Step 2: Put 0 indegree courses into a queue; 
                as start nodes of topological order, using BFS algorithm;
        Step 3: Find advanced course (non 0 degree courses), store them in a List;
                they are start nodes' all neighbors,
                and minus advanced course's indegree by 1.
                Then, check if advanced course's indegree is 0, 
                if is 0, offer the advanced course with 0 indegree into queue;
        After three steps above, 
        if there exists a course with non-zero indegree, return false; 
        otherwise, return true;
        */
        
        /*
        二维数组的行数和列数表示：
            int[][] a;
            行数：a.length
            列数：a[i].length    i为指定某一行
        */
        
        // Corner case
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        if (numCourses == 0) {
            return false;
        }
        
        // Step 1: Count each course's indegree;
        
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            // prerequisites的第一列是后修课，遍历prerequisites时，给后修课的indegree先加1；
            degree[prerequisites[i][0]]++;
            // 然后，把每一门先修课（prerequisites[i][1]）对应的后修课加到edges里面去
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        // step 2: put all 0 indegree courses into a queue, as the start courses
        
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Step 3: find start courses' all advanced courses, and let advanced courses' indegree minus 1.
        /*
        int count 相当于拓扑排序那道题中的order, 只要count == numCourses,
            说明graph是连通的，即从一个点出发，能够访问到所有点
        */
        int count = 0;
        
        // BFS
        while (!queue.isEmpty()) {
            // Each time the queue poll a course out, count++
            // 注意强制转换！Forced conversion
            int course = (int)queue.poll();
            count++;
            
            /*
            分层遍历
            n表示edges中下标为course的课程的后修课数量
            */ 
            int n = edges[course].size();
            for (int i = 0; i < n; i++) {
                // minus non 0 courses' indegree by 1
                // pointer表示edges中下标为course的课程的 每一门后修课
                int pointer = (int)edges[course].get(i);
                // 每一门后修课的indegree - 1
                degree[pointer]--;
                
                // Check again, to see if the indegree of pointer is 0
                if (degree[pointer] == 0) {
                    queue.offer(pointer);
                }
            }
        }
        
        return count == numCourses;
    }
    /*
    Time Complexity:O(n + m), n == # of vertices, m == # of edges;
    Step 1 is O(n + m), Step 2 is O(n), Step 3 is O(n + m)
    
    Space Complexity:O(n + m)
    queue: O(n), degree: O(n), edges: O(m)
    */
}