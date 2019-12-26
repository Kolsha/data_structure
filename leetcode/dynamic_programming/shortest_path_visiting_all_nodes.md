### 847. Shortest Path Visiting All Nodes
https://leetcode.com/problems/shortest-path-visiting-all-nodes/

An undirected, connected graph of N nodes (labeled `0, 1, 2, ..., N-1`) is given as `graph`.

`graph.length = N`, and `j != i` is in the list `graph[i]` exactly once, if and only if nodes `i` and `j` are connected.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

 

Example 1:
```
Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
```
Example 2:
```
Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]
 ```

Note:
1. 1 <= graph.length <= 12
2. 0 <= graph[i].length < graph.length

Solution:
Please ref [Dynamic Programming Notes ~ Subset](./0_summary.md) if you can't understand the code.
```java
class Solution {
    public int shortestPathLength(int[][] graph) {
        int[][] dp = new int[graph.length][1 << graph.length];
        ArrayList<State> list = new ArrayList<>();
        // iterate through each point
        for (int i = 0; i < graph.length; i++) {
            // init ith row default value for dp solution
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            // if the start point and the end point are the same, the distance is zero.
            dp[i][1 << i] = 0;
            // save bitmask<-> point mapping info for the point
            list.add(new State(1 << i, i));
        }

        while (!list.isEmpty()) {
            State state = list.remove(0);
            // Go through connected points for each point
            for (int next : graph[state.source]) {
                // state.mask represents the point, e.g. point 0 -> 0001, point 1 -> 0010...
                // 1 << next represents the visited connected point
                // nextMask will be the set containing both points,
                // e.g. the set containing point 0 and point 1 will be 0011
                int nextMask = state.mask | (1 << next);
                // firstly, the base case is the point connected to itself, the distance will be
                // 0;
                // e.g. dp[0][0001] = 0, dp[1][0010] = 0, dp[2][0100] = 0, dp[3][1000] = 0
                // then, we go through each connected points for the point and update the
                // relationship
                // e.g. since connected points is [1, 2, 3] for point 0, update for point 0:
                // dp[1 (next)][0011 (nextMask for 0, 1)] = dp[0 (source)][0001 (stat.mask)] + 1
                // = 0 + 1;
                // dp[2 (next)][0101 (nextMask for 0, 2)] = dp[0 (source)][0001 (stat.mask)] + 1
                // = 0 + 1;
                // dp[3 (next)][1001 (nextMask for 0, 3)] = dp[0 (source)][0001 (stat.mask)] + 1
                // = 0 + 1;
                if (dp[next][nextMask] > dp[state.source][state.mask] + 1) {
                    dp[next][nextMask] = dp[state.source][state.mask] + 1;
                    // Add the set to the queue
                    list.add(new State(nextMask, next));
                }
            }
        }

        int res = Integer.MAX_VALUE;
        // iterate through each point on the graph
        for (int i = 0; i < graph.length; i++) {
            // find the minimum value for visited all graph points
            // e.g. the value start point 0 to visit all points will be dp[0][1111]
            res = Math.min(res, dp[i][(1 << graph.length) - 1]);
        }
        return res;
    }

    class State {
        public int mask, source;

        public State(int m, int s) {
            mask = m;
            source = s;
        }
    }
}
```