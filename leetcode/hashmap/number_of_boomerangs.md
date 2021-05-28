### [447. Number of Boomerangs](https://leetcode.com/problems/number-of-boomerangs/)

You are given n points in the plane that are all distinct, where points[i] = [xi, yi]. A boomerang is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Return the number of boomerangs.

 

Example 1:
```
Input: points = [[0,0],[1,0],[2,0]]
Output: 2
Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].
```
Example 2:
```
Input: points = [[1,1],[2,2],[3,3]]
Output: 2
```
Example 3:
```
Input: points = [[1,1]]
Output: 0
``` 

Constraints:

- n == points.length
- 1 <= n <= 500
- points[i].length == 2
- $-10^4 <= xi, yi <= 10^4$
- All the points are unique.

### Solution

#### Approach 1: HashMap

#### Complexity analysis
- Time complexity: O(n^2)
- Space complexity: O(n)
```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int sum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < points.length; i++) {
            for(int j = 0; j < points.length; j++) {
                if(i == j) {
                    continue;
                }
                
                int dist = getDistance(points[i], points[j]);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
            for(int val:map.values()) {
                sum += (val) * (val - 1);
            }
            map.clear();
        }
        

        return sum;
    }

    private int getDistance(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }
}
```

[Why are we doing the val * (val-1) ? Permutation](https://zhuanlan.zhihu.com/p/41855459)