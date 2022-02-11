### [Overview of Breadth-First Search Algorithm](https://leetcode.com/explore/learn/card/graph/620/breadth-first-search-in-graph/3883/)

Previously, we discussed the “depth-first search” algorithm. This section will talk about a closely related and equally popular algorithm called “breadth-first search”. Similarly, the “breadth-first search” algorithm can traverse all vertices of a “graph” and traverse all paths between two vertices. However, the most advantageous use case of “breadth-first search” is to efficiently find the shortest path between two vertices in a “graph” where all edges have equal and positive weights.

Although the “depth-first search” algorithm can find the shortest path between two vertices in a “graph” with equal and positive weights, it must traverse all paths between two vertices before finding the shortest one. The “breadth-first search” algorithm, in most cases, can find the shortest path without traversing all paths. This is because when using "breadth-first search", as soon as a path between the source vertex and target vertex is found, it is guaranteed to be the shortest path between the two nodes.

In Figure 8, the vertices are [A, C, D, B, E]. Given vertices A and B, there are two paths between them. One path is [A, C, D, B], and the other is [A, E, B]. Obviously, [A, E, B] is the shortest path between A and B.

![image](https://user-images.githubusercontent.com/5952279/153629044-3691f699-edc3-4bcd-aa85-19c032489e9f.png)

In Graph theory, the primary use cases of the “breadth-first search” (“BFS”) algorithm are:

1. Traversing all vertices in the “graph”;

2. Finding the shortest path between two vertices in a graph where all edges have equal and positive weights.

### [Traversing all Vertices - Breadth-First Search](https://leetcode.com/explore/learn/card/graph/620/breadth-first-search-in-graph/3851/)

### Complexity Analysis
- Time Complexity: O(V + E)O(V+E). Here, VV represents the number of vertices, and EE represents the number of edges. We need to check every vertex and traverse through every edge in the graph. The time complexity is the same as it was for the DFS approach.

- Space Complexity: O(V)O(V). Generally, we will check if a vertex has been visited before adding it to the queue, so the queue will use at most O(V)O(V) space. Keeping track of which vertices have been visited will also require O(V)O(V) space.

### [Shortest Path Between Two Vertices - Breadth-First Search](https://leetcode.com/explore/learn/card/graph/620/breadth-first-search-in-graph/3852/)

### Complexity Analysis
- Time Complexity: O(V + E)O(V+E). Here, VV represents the number of vertices, and EE represents the number of edges. In the worst case, when the distance between the starting vertex and the target vertex is the maximum possible, we need to check every vertex and traverse through every edge in the graph.

- Space Complexity: O(V)O(V). The queue will take up to O(V)O(V) space to store all the graph vertices in the worst-case scenario. We must also use O(V)O(V) space to keep track of which vertices have been visited.

Example:
1. Find if Path Exists in Graph
```c++
class Solution {
public:
    bool validPath(int n, vector<vector<int>>& edges, int start, int end) {
        vector<vector<int>> adjacency_list(n);
        for (vector<int> edge : edges) {
            adjacency_list[edge[0]].push_back(edge[1]);
            adjacency_list[edge[1]].push_back(edge[0]);
        }
        
        queue<int> q;
        q.push(start);
        vector<bool> seen(n);
        seen[start] = true;
        
        while (!q.empty()) {
            // Get the current node.
            int node = q.front();
            q.pop();
            
            // Check if we have reached the target node.
            if (node == end) {
                return true;
            }
            
            // Add all neighbors to the stack.
            for (int neighbor : adjacency_list[node]) {
                // Check if neighbor has been added to the queue before.
                if (!seen[neighbor]) {
                    seen[neighbor] = true;
                    q.push(neighbor);
                }
            }
        }
        
        return false;
    }
};
```

```java
class Solution {
    public boolean validPath(int n, int[][] edges, int start, int end) {
        List<List<Integer>> adjacency_list = new ArrayList<>();        
        for (int i = 0; i < n; i++) {
            adjacency_list.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adjacency_list.get(edge[0]).add(edge[1]);
            adjacency_list.get(edge[1]).add(edge[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean seen[] = new boolean[n];
        Arrays.fill(seen, false);
        seen[start] = true;
        
        while (!queue.isEmpty()) {
            // Get the current node.
            int node = queue.poll();
            
            // Check if we have reached the target node.
            if (node == end) {
                return true;
            }
            
            // Add all neighbors to the stack.
            for (int neighbor : adjacency_list.get(node)) {
                // Check if neighbor has been added to the queue before.
                if (!seen[neighbor]) {
                    seen[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        
        return false;
    }
}
```

```python
class Solution:
    def validPath(self, n: int, edges: List[List[int]], start: int, end: int) -> bool:
        
        adjacency_list = [[] for _ in range(n)]
        for a, b in edges:
            adjacency_list[a].append(b)
            adjacency_list[b].append(a)
        
        queue = collections.deque([start])
        seen = set([start])
        
        while queue:
            # Get the current node.
            node = queue.popleft()
            
            # Check if we have reached the target node.
            if node == end:
                return True
            
            # Add all neighbors to the queue.
            for neighbor in adjacency_list[node]:
                # Check if neighbor has been added to the queue before.
                if neighbor not in seen:
                    seen.add(neighbor)
                    queue.append(neighbor)
        
        # Our queue is empty and we did not reach the end node.
        return False
```

Complexity Analysis
Time Complexity: O(V + E)O(V+E). Here, VV represents the number of vertices and EE represents the number of edges.

To create the adjacency list, we must iterate over each of the EE edges.
In the while loop, at most we will visit vertex once.
The for loop inside the while loop will have a cumulative sum of at most EE iterations since it will iterate over all of the node's neighbors for each node.
Space Complexity: O(V + E)O(V+E).

The adjacency list, will contain O(V + E)O(V+E) elements.
The queue will also contain O(V)O(V) elements.
The seen set will use O(V)O(V) space to store the visited nodes.


3. All paths From Source to Target
```c++
class Solution {
public:
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        vector<vector<int>> paths;
        if (graph.size() == 0) {
            return paths;
        }

        vector<int> path;
        queue<vector<int>> q;
        path.push_back(0);
        q.push(path);

        while (!q.empty()) {
            vector<int> currentPath = q.front();
            q.pop();
            int node = currentPath.back();
            for (int nextNode : graph[node]) {
                vector<int> tmpPath(currentPath.begin(), currentPath.end());
                tmpPath.push_back(nextNode);
                if (nextNode == graph.size() - 1) {
                    paths.push_back(tmpPath);
                } else {
                    q.push(tmpPath);
                }
            }
        }
        return paths;
    }
};
```

```java
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return paths;
        }

        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        queue.add(path);

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int node = currentPath.get(currentPath.size() - 1);
            for (int nextNode: graph[node]) {
                List<Integer> tmpPath = new ArrayList<>(currentPath);
                tmpPath.add(nextNode);
                if (nextNode == graph.length - 1) {
                    paths.add(new ArrayList<>(tmpPath));
                } else {
                    queue.add(new ArrayList<>(tmpPath));
                } 
            }
        }
        return paths;
    }
}
```

```python
class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        paths = []
        if not graph or len(graph) == 0:
            return paths

        queue = deque()
        path = [0]
        queue.append(path)

        while queue:
            current_path = queue.popleft()
            node = current_path[-1]
            for next_node in graph[node]:
                temp_path = current_path.copy()
                temp_path.append(next_node)

                if next_node == len(graph) - 1:
                    paths.append(temp_path)
                else:
                    queue.append(temp_path)
        return paths
```

Complexity Analysis
Time Complexity: O(2^V \cdot V)O(2 
V
 ⋅V). Here, VV represents the number of vertices.

For a graph with VV vertices, there could be at most 2^{V - 1} - 12 
V−1
 −1 possible paths to go from the starting vertex to the target vertex. We need O(V)O(V) time to build each such path.
Therefore, a loose upper bound on the time complexity would be (2^{V - 1} - 1) \cdot O(V) = O(2^V \cdot V)(2 
V−1
 −1)⋅O(V)=O(2 
V
 ⋅V).
Since we have overlapping between the paths, the actual time spent on the traversal will be lower to some extent.
Space Complexity: O(2^V \cdot V)O(2 
V
 ⋅V). The queue can contain O(2^V)O(2 
V
 ) paths and each path will take O(V)O(V) space. Therefore, the overall space complexity is O(2^V \cdot V)O(2 
V
 ⋅V).
 
4. Populating Next Right Pointers in Each Node
5. Shortest Path in Binary Matrix
6. N-ary Tree Level Order Traversal
7. Rotting Oranges



