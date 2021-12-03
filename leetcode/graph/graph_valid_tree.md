### [261. Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/)


Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.


Solution


// todo
Time Complexity:
Space Complexity:

```java
class Solution {
    public boolean validTree(int n, int[][] edges) {
        ArrayList<List<Integer>> adjList = new ArrayList<>();
        // init adjacency list
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        // init adjacency list with edges info
        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int des = edges[i][1];
            adjList.get(src).add(des);
            adjList.get(des).add(src);
        }

        boolean[] visited = new boolean[n];
        // check if the graph has cycle
        if (hasCycle(adjList, visited, 0, -1)) {
            return false;
        }

        // check if all visited
        for (boolean ver : visited) {
            if (!ver) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(List<List<Integer>> adjList, boolean[] visited, int currNode, int prevNode) {
        visited[currNode] = true;

        // Go thro connected vertices
        int connCount = adjList.get(currNode).size();
        for (int i = 0; i < connCount; i++) {
            int nextNode = adjList.get(currNode).get(i);
            // if next node is visited and prev node != next node, means it has a cycle (You
            // can draw diagram for better understanding)
            // if next node is not visited, and go check recursively if this graph has cycle
            if (visited[nextNode] && prevNode != nextNode
                    || !visited[nextNode] && hasCycle(adjList, visited, nextNode, currNode)) {
                return true;
            }
        }

        return false;
    }
}
```