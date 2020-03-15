### 269. Alien Dictionary

https://leetcode.com/problems/alien-dictionary/

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
```
Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
```
Example 2:
```
Input:
[
  "z",
  "x"
]

Output: "zx"
```
Example 3:
```
Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".
```
Note:

1. You may assume all letters are in lowercase.
2. You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
3. If the order is invalid, return an empty string.
4. There may be multiple valid order of letters, return any one of them is fine.

Solution

Method 1: Intuitive - Topological sort
```java
class Solution {
    public String alienOrder(String[] words) {
        // build graph
        int[][] matrix = new int[26][26];
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);

        buildGraph(words, matrix, indegree);

        // start topological sort
        // find the vertice which indegree == 0
        Queue<Character> queue = new LinkedList<>();
        int rightSize = 0;
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] >= 0) {
                rightSize++;
                if (indegree[i] == 0) {
                    char temp = (char) ('a' + i);
                    queue.offer(temp);
                }
            }
        }
        String res = "";
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            res = res + curr;
            int index = curr - 'a';
            for (int i = 0; i < 26; i++) {
                if (matrix[index][i] == 1) {
                    if (--indegree[i] == 0) {
                        char temp = (char) (i + 'a');
                        queue.offer(temp);
                    }
                }
            }

        }
        return (res.length() != rightSize) ? "" : res;
    }

    private void buildGraph(String[] words, int[][] matrix, int[] indegree) {
        // find max length
        int maxLength = 0;
        for (int i = 0; i < words.length; i++) {
            maxLength = Math.max(maxLength, words[i].length());
            for (int j = 0; j < words[i].length(); j++) {
                char temp = words[i].charAt(j);
                indegree[temp - 'a'] = 0;
            }
        }

        // init matrix and indegree for graph
        for (int j = 0; j < maxLength; j++) {
            for (int i = 0; i < words.length; i++) {
                String currStr = words[i];
                String nextStr = (i + 1 < words.length) ? words[i + 1] : "";
                char lastCharCurrStr = (j - 1 >= 0 && j - 1 < currStr.length()) ? currStr.charAt(j - 1) : 0;
                char lastCharNextStr = (j - 1 >= 0 && j - 1 < nextStr.length()) ? nextStr.charAt(j - 1) : 0;
                char currCharCurrStr = (j < currStr.length()) ? currStr.charAt(j) : 0;
                char currCharNextStr = (j < nextStr.length()) ? nextStr.charAt(j) : 0;

                if (lastCharCurrStr == lastCharNextStr && currCharCurrStr != 0 && currCharNextStr != 0
                        && currCharCurrStr != currCharNextStr) {
                    if (matrix[currCharCurrStr - 'a'][currCharNextStr - 'a'] == 0) {
                        indegree[currCharNextStr - 'a']++;
                    }
                    matrix[currCharCurrStr - 'a'][currCharNextStr - 'a'] = 1;
                }
            }
        }
    }
}
```