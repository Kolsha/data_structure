### [140. Word Break II](https://leetcode.com/problems/word-break-ii/)

Given a string `s` and a dictionary of strings `wordDict`, add spaces in `s` to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in **any order**.

**Note** that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:
```
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
```
Example 2:
```
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
```
Example 3:
```
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
``` 

Constraints:

- 1 <= s.length <= 20
- 1 <= wordDict.length <= 1000
- 1 <= wordDict[i].length <= 10
- `s` and `wordDict[i]` consist of only lowercase English letters.
- All the strings of wordDict are unique.

##### Solution

##### Approach 1: Recursive + HashMap Memorization
```
Input String s

             index i
|--------------|-----------------|
  SubStr(0, i) +     SubStr(i)
```
##### Complexity analysis
- Time complexity:
- Space complexity:

```java
class Solution {
    private HashMap<String, List<String>> map = new HashMap<>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        ArrayList<String> res = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return res;
        }
        
        if(map.containsKey(s)) {
            return map.get(s);
        }
        
        HashSet<String> set = new HashSet<>();
        for(String word: wordDict) {
            set.add(word);
        }

        if(set.contains(s)) {
            res.add(s);
        }
        
        for(int i = 1; i < s.length(); i++) {
            String aft = s.substring(i);
            if(set.contains(aft)) {
                List<String> temp = wordBreak(s.substring(0, i), wordDict);
                for(String wd: temp) {
                    res.add(wd + " " + aft);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}
```