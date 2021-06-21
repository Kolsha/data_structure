### [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/)

There are `n` cities. Some of them are connected, while some are not. If city `a` is connected directly with city `b`, and city `b` is connected directly with city `c`, then city `a` is connected indirectly with city `c`.

A **province** is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an `n x n` matrix `isConnected` where `isConnected[i][j] = 1` if the ith city and the jth city are directly connected, and `isConnected[i][j] = 0` otherwise.

Return the total number of **provinces**.

 

Example 1:
![](https://assets.leetcode.com/uploads/2020/12/24/graph1.jpg)
```
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
```
Example 2:

![](https://assets.leetcode.com/uploads/2020/12/24/graph2.jpg)
```
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
``` 

Constraints:

- 1 <= n <= 200
- n == isConnected.length
- n == isConnected[i].length
- isConnected[i][j] is 1 or 0.
- isConnected[i][i] == 1
- isConnected[i][j] == isConnected[j][i]


### Solution

#### Approach 1: Union - find

### Complexity analysis:
- Time complexity: O(n^2)
- Space complexity: O(n)

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        DisjointSet set = new DisjointSet(isConnected);
        int row = isConnected.length;
        int col = isConnected[0].length;
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(isConnected[i][j] == 1 && i!=j) {
                    set.union(i, j);
                }
            }
        }
        return set.getGroupCount();
    }
    
    private static class DisjointSet {
        private int[] parent = null;
        private int count = 0; 
        DisjointSet(int[][] isConnected) {
            count = isConnected.length;
            parent = new int[count];
            // init roots of disjoint sets
            for(int i = 0; i < count; i++) {
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
            if(rootX!=rootY) {
                parent[rootX] = rootY;
                this.count--;
            }
        }
        
        public int getGroupCount() {
            return this.count;
        }
    }
}
```