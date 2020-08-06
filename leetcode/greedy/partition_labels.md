### 763. Partition Labels

https://leetcode.com/problems/partition-labels/

A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

 

Example 1:
```
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
``` 

Note:

- `S` will have length in range `[1, 500]`.
- `S` will consist of lowercase English letters (`'a'` to `'z'`) only.

Solution

Approach 1: Greedy

Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

```java
class Solution {
    public List<Integer> partitionLabels(String S) {
        // find the last char's index
        int[] last = new int[26];
        for(int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        
        // sliding window to find (anchor, j)
        int start = 0, end = 0;
        for(int i = 0; i < S.length(); i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            
            if(i == end) {
                res.add(end - start + 1);
                start = i + 1;
            }
        }
        return res;

    }
}
```