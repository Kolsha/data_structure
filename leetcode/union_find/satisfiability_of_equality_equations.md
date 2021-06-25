### [990. Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/)


Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.



Example 1:
```
Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
```
Example 2:
```
Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
```
Example 3:
```
Input: ["a==b","b==c","a==c"]
Output: true
```
Example 4:
```
Input: ["a==b","b!=c","c==a"]
Output: false
```
Example 5:
```
Input: ["c==c","b==d","x!=z"]
Output: true
``` 

Note:

- 1 <= equations.length <= 500
- equations[i].length == 4
- equations[i][0] and equations[i][3] are lowercase letters
- equations[i][1] is either '=' or '!'
- equations[i][2] is '='

###　Solution

#### Approach 1: Union find
#### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

```java
class Solution {
    public boolean equationsPossible(String[] equations) {
        DisjointSet set = new DisjointSet();
        for(String equation: equations) {
            char v1 = equation.charAt(0);
            char v2 = equation.charAt(3);
            char sign = equation.charAt(1);

            if('=' == sign) {
                set.union(v1-'a', v2-'a');
            }
        }

        for(String equation: equations) {
            char v1 = equation.charAt(0);
            char v2 = equation.charAt(3);
            char sign = equation.charAt(1);
            
            if('!' == sign &&  set.find(v1-'a') == set.find(v2 - 'a')) {
                return false;
            }
        }
        return true;
    }
    
    public static class DisjointSet {
        private int[] parent = null;
        DisjointSet() {
            // init parent and root values
            parent = new int[26];
            for(int i = 0; i < 26; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if(parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
        
    }
}
```