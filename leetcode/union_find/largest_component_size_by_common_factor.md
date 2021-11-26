### [952. Largest Component Size by Common Factor](https://leetcode.com/problems/largest-component-size-by-common-factor/)

You are given an integer array of unique positive integers `nums`. Consider the following graph:

- There are `nums.length` nodes, labeled `nums[0]` to `nums[nums.length - 1]`,
There is an undirected edge between `nums[i]` and `nums[j]` if `nums[i]` and `nums[j]` share a common factor greater than `1`.

- Return the size of the largest connected component in the graph.

 

Example 1:
![](https://assets.leetcode.com/uploads/2018/12/01/ex1.png)
```
Input: nums = [4,6,15,35]
Output: 4
```
Example 2:

![](https://assets.leetcode.com/uploads/2018/12/01/ex2.png)
```
Input: nums = [20,50,9,63]
Output: 2
```
Example 3:
![](https://assets.leetcode.com/uploads/2018/12/01/ex3.png)
```
Input: nums = [2,3,6,7,4,12,21,39]
Output: 8
``` 

Constraints:

- $1 <= nums.length <= 2 * 10^4$
- $1 <= nums[i] <= 10^5$
- All the values of nums are unique.


##### Solution

##### Approach 1: Union- Find

##### Complexity analysis
- Time complexity:
- Space complexity:

```java
class Solution {
    class UF {
        int[] parent;
        int[] size;
        int max;
        public UF (int N) {
            parent = new int[N];
            size = new int[N];
            max = 1;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x) {
            if (x == parent[x]) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                max = Math.max(max, size[rootY]);
            }
        }
    }
    public int largestComponentSize(int[] A) {
        int N = A.length;
        
        // key is the factor, val is the node index
        Map<Integer, Integer> map = new HashMap<>();
        UF uf = new UF(N);
        for (int i = 0; i < N; i++) {
            int a = A[i];
            for (int j = 2; j * j <= a; j++) {
                if (a % j == 0) {
                    //this means that no index has claimed the factor yet
                    if (!map.containsKey(j)) {
                        map.put(j, i);
                    } else {
                        //this means that one index already claimed, so union that one with current
                        uf.union(i, map.get(j));
                    }

                    if (!map.containsKey(a/j)) {
                        map.put(a/j, i);
                    } else {
                        uf.union(i, map.get(a/j));
                    }
                }
            }

            //a could be factor too. Don't miss this
            if (!map.containsKey(a)) {
                map.put(a, i);
            } else {
                uf.union(i, map.get(a));
            }
        }
        return uf.max;
    }
}
```