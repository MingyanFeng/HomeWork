/*
211. Add and Search Word - Data structure design

Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z.
*/

// Tags:  Backtracking Trie Design

/*
Analysis:

The problem asks us to design a class that support "insert" and "search" operations, apparently it perfectly match the functionality of a prefix tree.
But for the search operation, we need to support search, '.' which represent any character from 'a' to 'z'.
How could we solve this prblem?
*/

// Version 1: Use Array

// Need another class TrieNode
class TrieNode { 
	// TrieNode has two attributes
    public TrieNode[] children;
    public boolean hasWord;
    
    public TrieNode() {
        children = new TrieNode[26];
        for (int i = 0; i < 26; ++i) {
            children[i] = null;
        }
        hasWord = false;
    }
}

public class WordDictionary {

	/** Initialize your data structure here. */
    private TrieNode root;
 
    public WordDictionary(){
        root = new TrieNode();
    }
 
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode now = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);

            if (now.children[c - 'a'] == null) {
                now.children[c - 'a'] = new TrieNode();
            }

            now = now.children[c - 'a'];
        }
        now.hasWord = true;
    }
    
    private boolean find(String word, int index, TrieNode now) {
        if (index == word.length()) {
            return now.hasWord;
        }
        
        Character c = word.charAt(index);

        if (c == '.') {
            for(int i = 0; i < 26; ++i)  {
            	if (now.children[i] != null) {
                	if (find(word, index+1, now.children[i])) {
                		return true;
                	}
            	}
            }
            return false;
        } else if (now.children[c - 'a'] != null) {
            return find(word, index+1, now.children[c - 'a']);  
        } else {
            return false;
        }
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return find(word, 0, root); // Need a helper function 
    }
}
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

// Version 2: Use HashMap
class TrieNode {

    public HashMap<Character, TrieNode> children;
    public boolean hasWord;

    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        hasWord = false;
    }
}


public class WordDictionary {
	/** Initialize your data structure here. */
    private TrieNode root;
 
    public WordDictionary(){
        root = new TrieNode();
    }
 
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode now = root;

        for(int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);

            if (!now.children.containsKey(c)) {
                now.children.put(c, new TrieNode());
            }

            now = now.children.get(c);
        }
        now.hasWord = true;
    }
    
    private boolean find(String word, int index, TrieNode now) {
        if (index == word.length()){
            if (now.children.size()==0) {
                return true; 
            }
            else {
                return false;
            }
        }
        
        Character c = word.charAt(index);

        if (now.children.containsKey(c)) {
            if (index == word.length()-1 && now.children.get(c).hasWord) {
                return true;
            }
            return find(word, index+1, now.children.get(c));  
        } else if (c == '.') {
            boolean result = false;
            for (Map.Entry<Character, TrieNode> child: now.children.entrySet()) {
                if (index == word.length()-1 && child.getValue().hasWord) {
                    return true;
                } 
 
                //if any path is true, set result to be true; 
                if(find(word, index+1, child.getValue())) {
                    result = true;
                }
            }
            return result;
        } else {
            return false;
        }
    }
    
/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return find(word, 0, root);
    }
}
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */