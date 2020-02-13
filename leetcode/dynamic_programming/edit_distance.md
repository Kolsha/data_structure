### 72. Edit Distance
https://leetcode.com/problems/edit-distance/

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

1. Insert a character
2. Delete a character
3. Replace a character
Example 1:
```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```
Example 2:
```
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```

Solution
[Method 1: Dynamic Programming](https://leetcode.com/problems/edit-distance/discuss/25849/Java-DP-solution-O(nm))

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int word1Count = word1.length();
        int word2Count = word2.length();

        int[][] cost = new int[word1Count + 1][word2Count + 1];
        for (int i = 0; i <= word1Count; i++) {
            cost[i][0] = i;
        }

        for (int i = 0; i <= word2Count; i++) {
            cost[0][i] = i;
        }

        for (int i = 0; i < word1Count; i++) {
            for (int j = 0; j < word2Count; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    cost[i + 1][j + 1] = cost[i][j];
                } else {
                    int insertCost = cost[i + 1][j];
                    int deleteCost = cost[i][j + 1];
                    int replaceCost = cost[i][j];

                    cost[i + 1][j + 1] = 1 + Math.min(insertCost, Math.min(deleteCost, replaceCost));
                }
            }
        }
        return cost[word1Count][word2Count];
    }
}
```

