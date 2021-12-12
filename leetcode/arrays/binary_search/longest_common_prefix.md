### [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string `""`.

**Example 1:**
```
Input: ["flower","flow","flight"]
Output: "fl"
```
**Example 2:**
```
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```
Note:

All given inputs are in lowercase letters `a-z`.

##### Solution
##### Approach 1: For loop, comparing string in pairs

##### Complexity analysis
- Time complexity:
- Space complexity:


```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }

        String commonPrefix = strs[0];
        for(String str: strs) {
            commonPrefix = getCommonPrefix(commonPrefix, str);
        }
        return commonPrefix;
    }
    
    private String getCommonPrefix(String firstStr, String secondStr) {
        int minLength = Math.min(firstStr.length(), secondStr.length());
        int i = -1;
        StringBuilder sb = new StringBuilder();
        while (++i < minLength) {
            char charFromFirst = firstStr.charAt(i);
            char charFromSecond = secondStr.charAt(i);
            if(charFromFirst == charFromSecond) {
                sb.append(charFromFirst);
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
```

##### Approach 2: Sort the String

##### Complexity analysis
- Time complexity: 
- Space complexity:

```java
class Solution {

    public String longestCommonPrefix(String[] strs) {
        // Argument checks
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        
        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        for (int i = 0, j = 0; i < first.length && j < last.length; i++, j++) {
            if (first[i] != last[j]) break;
            sb.append(first[i]);
        }
        return sb.toString();
    }
}
```

##### Approach 3: [Binary Search](https://www.geeksforgeeks.org/longest-common-prefix-using-binary-search/)

##### Complexity analysis
- Time complexity:
- Space complexity:

```java
```

