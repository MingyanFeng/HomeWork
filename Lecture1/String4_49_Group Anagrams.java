// 参考了答案
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // corner case
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }
        
        // the key is an element(string) from strs, the value is the element(string)'s anagram words
        Map<String, List<String>> map = new HashMap<>();
        // sort the input strs
        Arrays.sort(strs);
        for (String s : strs) {
            // sort each element(string) char by char
            char[] c = s.toCharArray();
            Arrays.sort(c);
            // return the string representation of sorted element, as keyStr
            String keyStr = String.valueOf(c);
            // if map does not contains keyStr, initalize a new ArrayList as its value
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<String>());
            // otherwise, if map contains the same sequence of sorted element(keyStr), we add the element ITSELF to the keyStr's anagram words list
            } else {
                map.get(keyStr).add(s);
            }
        }
        //map.values(): returns a Collection view of the values contained in this map
        return new ArrayList<List<String>>(map.values());
    }
}
/*
Time Complexity: O(nlogn + nmlogm); n == strs.length, m == max element's length

Space Complexity: O(m + n); n == strs.length, m == max element's length; HashMap will cost extra k*n space
*/