/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

/*
    拓扑排序
    复杂度
    时间 O(n) 空间 O(n)
    
    思路：对于这题来说，我们首先要初始化所有节点（即字母），一个是该字母指向的字母的集合（被指向的字母在字母表中处于较后的位置），一个是该字母的计数器。然后我们根据字典开始建图，但是字典中并没有显示给出边的情况，如何根据字典建图呢？其实边都暗藏在相邻两个词之间，比如abc和abd，我们比较两个词的每一位，直到第一个不一样的字母c和d，因为abd这个词在后面，所以d在字母表中应该是在c的后面。所以每两个相邻的词都能蕴含一条边的信息。在建图的同时，实际上我们也可以计数了，对于每条边，将较后的字母的计数器加1。计数时需要注意的是，我们不能将同样一条边计数两次，所以要用一个集合来排除已经计数过的边。最后，我们开始拓扑排序，从计数器为0的字母开始广度优先搜索。为了找到这些计数器为0的字母，我们还需要先遍历一遍所有的计数器。
    
    最后，根据结果的字母个数和图中所有字母的个数，判断时候有环即可。无环直接返回结果。
    
    注意！！！
    要先对字典里所有存在的字母初始化入度为0，否则之后建图可能会漏掉一些没有入度的字母。
    'a'+'b'+""和'a'+""+'b'是不一样的，前者先算数字和，后者则是字符串拼接。
    因为字典里有重复的边，所有要先判断，已经添加过的边不要重复添加。
*/

public class Solution {
    public String alienOrder(String[] words) {
        // 节点构成的图
        Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
        // 节点的计数器
        Map<Character, Integer> indegree = new HashMap<Character, Integer>();
        // 结果存在这个里面
        StringBuilder order = new StringBuilder();
        // 初始化图和计数器
        initialize(words, graph, indegree);
        // 建图并计数
        buildGraphAndGetIndegree(words, graph, indegree);
        // 拓扑排序的最后一步，根据计数器值广度优先搜索
        topologicalSort(order, graph, indegree);
        // 如果大小相等说明无环
        return order.length() == indegree.size() ? order.toString() : "";
    }
    
    private void initialize(String[] words, Map<Character, Set<Character>> graph, Map<Character, Integer> indegree){
        for(String word : words){
            for(int i = 0; i < word.length(); i++){
                char curr = word.charAt(i);
                // 对每个单词的每个字母初始化计数器和图节点
                if(graph.get(curr) == null){
                    graph.put(curr, new HashSet<Character>());
                }
                if(indegree.get(curr) == null){
                    indegree.put(curr, 0);
                }
            }
        }
    }
    
    private void buildGraphAndGetIndegree(String[] words, Map<Character, Set<Character>> graph, Map<Character, Integer> indegree){
        Set<String> edges = new HashSet<String>();
        for(int i = 0; i < words.length - 1; i++){
        // 每两个相邻的词进行比较
            String word1 = words[i];
            String word2 = words[i + 1];
            for(int j = 0; j < word1.length() && j < word2.length(); j++){
                char from = word1.charAt(j);
                char to = word2.charAt(j);
                // 如果相同则继续，找到两个单词第一个不相同的字母
                if(from == to) continue;
                // 如果这两个字母构成的边还没有使用过，则
                if(!edges.contains(from+""+to)){
                    Set<Character> set = graph.get(from);
                    set.add(to);
                    // 将后面的字母加入前面字母的Set中
                    graph.put(from, set);
                    Integer toin = indegree.get(to);
                    toin++;
                    // 更新后面字母的计数器，+1
                    indegree.put(to, toin);
                    // 记录这条边已经处理过了
                    edges.add(from+""+to);
                    break;
                }
            }
        }
    }
    
    private void topologicalSort(StringBuilder order, Map<Character, Set<Character>> graph, Map<Character, Integer> indegree){
        // 广度优先搜索的队列
        Queue<Character> queue = new LinkedList<Character>();
        // 将有向图的根，即计数器为0的节点加入队列中
        for(Character key : indegree.keySet()){
            if(indegree.get(key) == 0){
                queue.offer(key);
            }
        }
        // 搜索
        while(!queue.isEmpty()){
            Character curr = queue.poll();
            // 将队头节点加入结果中
            order.append(curr);
            Set<Character> set = graph.get(curr);
            if(set != null){
                // 对所有该节点指向的节点，更新其计数器，-1
                for(Character c : set){
                    Integer val = indegree.get(c);
                    val--;
                    // 如果计数器归零，则加入队列中待处理
                    if(val == 0){
                        queue.offer(c);
                    }
                    indegree.put(c, val);
                }
            }
        }
    }
}