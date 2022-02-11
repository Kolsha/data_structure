### Overview of Minimum Spanning Tree


You might wonder: what is a spanning tree? A spanning tree is a connected subgraph in an undirected graph where all vertices are connected with the minimum number of edges. In Figure 9, all pink edges [(A, B), (A, C), (A, D), (A, E)] form a tree, which is a spanning tree of this undirected graph. Note that [(A, E), (A, B), (B, C), (C, D)] is also a spanning tree of the undirected graph. Thus, an “undirected graph” can have multiple spanning trees.

![image](https://user-images.githubusercontent.com/5952279/153630693-b3681f84-03bc-4dd8-985b-b9aa82bd1eb2.png)

After learning what a spanning tree is, you might have another question: what is a minimum spanning tree? A minimum spanning tree is a spanning tree with the minimum possible total edge weight in a “weighted undirected graph”. In Figure 10, a spanning tree formed by green edges [(A, E), (A, B), (B, C), (C, D)] is one of the minimum spanning trees in this weighted undirected graph. Actually, [(A, E), (E, D), (A, B), (B, C)] forms another minimum spanning tree of the weighted undirected graph. Thus, a “weighted undirected graph” can have multiple minimum spanning trees.

![image](https://user-images.githubusercontent.com/5952279/153630805-f943a646-95de-415d-bb29-4c357e6fea8d.png)

In this chapter, we will learn about the “cut property and two algorithms for constructing a “minimum spanning tree”:

- Kruskal’s Algorithm

- Prim’s algorithm

### [Cut Property](https://leetcode.com/explore/learn/card/graph/621/algorithms-to-construct-minimum-spanning-tree/3855/)


What is a “cut”? Although many theorems are named after people’s names, “cut” is not one of them. To understand the “cut property”, we need to understand two basic concepts.

- First, in Graph theory, a “cut” is a partition of vertices in a “graph” into two disjoint subsets. Figure 11 illustrates a “cut”, where (B, A, E) forms one subset, and (C, D) forms the other subset.

- Second, a crossing edge is an edge that connects a vertex in one set with a vertex in the other set. In Figure 11, (B, C), (A, C), (A, D), (E, D) are all “crossing edges”.

![image](https://user-images.githubusercontent.com/5952279/153631255-e8312e50-2e00-45b7-b797-c01248c26828.png)

After knowing the basics of a graph cut, let’s delve into the “cut property”. The cut property provides theoretical support for Kruskal’s algorithm and Prim’s algorithm. So, what is the “cut property”? According to Wikipedia, the “cut property” refers to:

For any cut C of the graph, if the weight of an edge E in the cut-set of C is strictly smaller than the weights of all other edges of the cut-set of C, then this edge belongs to all MSTs of the graph.

### [Kruskal’s Algorithm](https://leetcode.com/explore/learn/card/graph/621/algorithms-to-construct-minimum-spanning-tree/3856/)


“Kruskal’s algorithm” is an algorithm to construct a “minimum spanning tree” of a “weighted undirected graph”.

### Visual Example
![](https://leetcode.com/explore/learn/card/Figures/Graph_Explore/KruskalDemo.gif)

The above animation shows how Kruskal's algorithm grows the minimum spanning tree by adding edges. In this example, the distance between two vertices is the edge weight. We try adding each edge, one at a time, from the lowest weight edge up to the highest weight edge. If either of the edges' vertices is not already part of the MST, then the edge is added to the MST.

### Why does Kruskal’s Algorithm only choose N-1 edges?
In the following video, we'll prove that we need to choose exactly N-1N−1 edges of the graph with NN edges in total to construct a “minimum spanning tree” of that graph.

Please note that in the video above, all the graphs are linked lists. However, it's not necessary for a “minimum spanning tree” to be a linked list only. In other words, we can form an MST without forming a linked list. In any case, only N-1 edges are needed.

### Why can we apply the “greedy strategy”?
In the subsequent video, you'll see why the greedy approach does work in accomplishing our task.

### Complexity Analysis
- Time Complexity: O(E \cdot \log E)O(E⋅logE). Here, EE represents the number of edges.

    - At first, we need to sort all the edges of the graph in ascending order. Sorting will take O(E \log E)O(ElogE) time.

    - Next, we can start building our minimum spanning tree by selecting which edges should be included. For each edge, we will look at whether both of the vertices of the edge belong to the same connected component; which is an O(\alpha(V))O(α(V)) operation, where \alphaα refers to the Inverse Ackermann function. In the worst case, the tree will not be complete until we reach the very last edge (the edge with the largest weight), so this process will take O(E \alpha(V))O(Eα(V)) time.
    
    - Therefore, in total, the time complexity is O(E \log E + E \alpha(V)) = O(E \log E)O(ElogE+Eα(V))=O(ElogE).

- Space Complexity: O(V)O(V). VV represents the number of vertices. Keeping track of the root of every vertex in the union-find data structure requires O(V)O(V) space. However, depending on the sorting algorithm used, different amounts of auxiliary space will be required to sort the list of edges in place. For instance, Timsort (used by default in python) requires O(E)O(E) space in the worst-case scenario, while Java uses a variant of quicksort whose space complexity is O(\log E)O(logE).


Example:
1. Kruskal's Algorithm
2.   Min Cost to Connect All Points

### [Prim’s Algorithm](https://leetcode.com/explore/learn/card/graph/621/algorithms-to-construct-minimum-spanning-tree/3859/)

### Visual Example
![](https://leetcode.com/explore/learn/card/Figures/Graph_Explore/PrimAlgDemo.gif)
The above illustration demonstrates how Prim's algorithm works by adding vertices. In this example, the distance between two vertices is the edge weight. Starting from an arbitrary vertex, Prim's algorithm grows the minimum spanning tree by adding one vertex at a time to the tree. The choice of a vertex is based on the greedy strategy, i.e., the addition of the new vertex incurs the minimum cost.

### Proof of the Prim's Algorithm

### The difference between the “Kruskal’s algorithm” and the “Prim’s algorithm”
“Kruskal’s algorithm” expands the “minimum spanning tree” by adding edges. Whereas “Prim’s algorithm” expands the “minimum spanning tree” by adding vertices.

### Complexity Analysis
VV represents the number of vertices, and EE represents the number of edges.

- Time Complexity: O(E \cdot \log V)O(E⋅logV) for Binary heap, and O(E+V \cdot \log V)O(E+V⋅logV) for Fibonacci heap.

    - For a Binary heap:
        - We need O(V + E)O(V+E) time to traverse all the vertices of the graph, and we store in the heap all the vertices that are not yet included in our minimum spanning tree.
        - Extracting minimum element and key decreasing operations cost O(\log V)O(logV) time.
        - Therefore, the overall time complexity is O(V + E) \cdot O(\log V) = O(E \cdot \log V)O(V+E)⋅O(logV)=O(E⋅logV).
    - For a Fibonacci heap:
        - Extracting minimum element will take O(\log V)O(logV) time while key decreasing operation will take amortized O(1)O(1) time, therefore, the total time complexity would be O(E + V \cdot \log V)O(E+V⋅logV).

- Space Complexity: O(V)O(V). We need to store VV vertices in our data structure.





