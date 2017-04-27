/*

291. Word Pattern II

Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
Examples:
    pattern = "abab", str = "redblueredblue" should return true.
    pattern = "aaaa", str = "asdasdasdasd" should return true.
    pattern = "aabb", str = "xyzabcxzyabc" should return false.

Notes:
You may assume both pattern and str contains only lowercase letters. 

*/

public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {

        Map<Character, String> hm = new HashMap<>(); // used to store relationship between pattern letter and str words
        Set<String> hs = new HashSet<>(); // used to store str words
        // use dfs
        return wordPatternMatch(pattern, 0, str, 0, hm, hs);
    }
    
    private boolean wordPatternMatch(String pattern, int patternIndex, // represents current position of pattern
                                    String str, int strIndex, // represents curretn position of pattern
                                    Map<Character, String> hm,
                                    Set<String> hs) {
        // corner cases
        if (patternIndex == pattern.length() && strIndex == str.length()) {
            return true;
        }
        
        if (patternIndex == pattern.length() || strIndex == str.length()) {
            return false;
        }
        
        for (int i = strIndex + 1; i <= str.length(); i++) {
            char tempC = pattern.charAt(patternIndex);
            String tempS = str.substring(strIndex, i);
            
            // if the str word extracted from hm does not match the word in str, skip and try next relationship
            if (hm.containsKey(tempC) && !hm.get(tempC).equals(tempS)) {
                if (hm.get(tempC).length() > tempS.length()) {
                    continue;
                } else {
                    return false;
                }
            }
            
            // if pattern letter is new but str word has already appeared, skip and try next relationship
            if (!hm.containsKey(tempC) && hs.contains(tempS)) {
                continue;
            }
            
            // if both pattern letter and str word are new
            if (!hm.containsKey(tempC) && !hs.contains(tempS)) {
                hs.add(tempS);
                hm.put(tempC, tempS);
                if (wordPatternMatch(pattern, patternIndex + 1, str, i, hm, hs)) {
                    return true;
                } else { // if this relationship fails, remove previous trace and try new relationship
                    hs.remove(tempS);
                    hm.remove(tempC);
                    continue;
                }
            }
            
            if (wordPatternMatch(pattern, patternIndex + 1, str, i, hm, hs)) {
                return true;
            } else {
                return false;
            }
        }
        
        return false;
    }
    /*
    Time complexity: exponential
    Space complecity: O(m + n), m is the size of the HashMap and n is the size of the HashSet
    */

    public static void main(String[] args) {
        String pattern = "abab";
        String str = "redblueredblue";
        WordPatternII test = new WordPatternII();
        System.out.println(test.wordPatternMatch(pattern, str));
    }
}
