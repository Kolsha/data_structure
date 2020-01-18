### 821. Shortest Distance to a Character
Given a string `S` and a character `C`, return an array of integers representing the shortest distance from the character C in the string.

Example 1:
```
Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
``` 

Note:

1. S string length is in [1, 10000].
2. C is a single character, and guaranteed to be in string S.
3. All letters in S and C are lowercase.

Solution:
Method 1: Straight forward
```java
class Solution {
    public int[] shortestToChar(String S, char C) {
        ArrayList<Integer> posHolder = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            char curr = S.charAt(i);
            if (curr == C) {
                posHolder.add(i);
            }
        }

        int[] res = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            res[i] = getNearest(i, posHolder);
        }
        return res;
    }

    private int getNearest(int pos, ArrayList<Integer> list) {
        int dist = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            dist = Math.min(dist, Math.abs(list.get(i) - pos));
        }
        return dist;
    }
}
```

Method 2: // todo