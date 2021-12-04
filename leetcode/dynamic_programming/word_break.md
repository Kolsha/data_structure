### [139. Word Break](https://leetcode.com/problems/word-break/)

Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

**Note** that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:
```
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
```
Example 2:
```
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
```
Example 3:
```
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
``` 

Constraints:

- 1 <= s.length <= 300
- 1 <= wordDict.length <= 1000
- 1 <= wordDict[i].length <= 20
- `s` and `wordDict[i]` consist of only lowercase English letters.
- All the strings of `wordDict` are **unique**.

##### Solution

##### Approach 1: Dynamic Programming + HashSet

The for loop is looking at ranges or substrings. If we know that the current substring is in the dictionary AND the substring before it is also in the dictionary then we know that the str.substring(0, i) is true. J == 0 because the first substring has nothing before it (dp[j- 1] does not exist).

```
------- left substring ---- | substring (j, i + 1) | -------right substring ------ |

      DP[j - 1] || j = 0              DP[i]

```

If we know that str.substring(j,i+1) is in the dictionary, we can only mark it true if the left substring is also in the dictionary.
Then, for the right substring, it is str.substring(i + 1, end). You can only mark dp[end] is true if you know the substring up to i are in dictionary.
This is what makes this a dynamic programming solution.


##### Complexity analysis
- Time complexity: $O(n^2)$
- Space complexity: $O(n)$

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String val : wordDict) {
            set.add(val);
        }

        // index 0 ~ length-1
        boolean[] dp = new boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                String subStr = s.substring(j, i + 1);
                if (set.contains(subStr) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length() - 1];
    }
}
```