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
2. All paths From Source to Target
3. Populating Next Right Pointers in Each Node
4. Shortest Path in Binary Matrix
5. N-ary Tree Level Order Traversal
6. Rotting Oranges



