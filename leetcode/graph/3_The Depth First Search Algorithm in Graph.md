[Overview of Depth-First Search Algorithm](https://leetcode.com/explore/learn/card/graph/619/depth-first-search-in-graph/3882/)

Previously, we learned how to check the connectivity between two vertices with the “disjoint set” data structure. Now, let's switch gears and consider: Given a graph, how can we find all of its vertices, and how can we find all paths between two vertices?

The depth-first search algorithm is ideal in solving these kinds of problems because it can explore all paths from the start vertex to all other vertices. Let's start by considering an example. In Figure 7, there are five vertices [A, C, D, B, E]. Given two vertices A and B, there are two paths between them. One path is [A, C, D, B], and the other is [A, E, B].

![image](https://user-images.githubusercontent.com/5952279/153625609-7c91208e-9be9-4f8e-83f6-d3ae30b95581.png)


In Graph theory, the depth-first search algorithm (abbreviated as DFS) is mainly used to:

1. Traverse all vertices in a “graph”;

2. Traverse all paths between any two vertices in a “graph”.



### [Traversing all Vertices – Depth-First Search Algorithm](https://leetcode.com/explore/learn/card/graph/619/depth-first-search-in-graph/3847/)

### Complexity Analysis
Time Complexity: O(V + E). Here, VV represents the number of vertices, and EE represents the number of edges. We need to check every vertex and traverse through every edge in the graph.

Space Complexity: O(V). Either the manually created stack or the recursive call stack can store up to V vertices.



### [Traversing all paths between two vertices – Depth-First Search Algorithm](https://leetcode.com/explore/learn/card/graph/619/depth-first-search-in-graph/3848/)

Complexity Analysis
Time Complexity: O((V - 1)!)O((V−1)!) The above example is for an undirected graph. The worst-case scenario, when trying to find all paths, is a complete graph. A complete graph is a graph where every vertex is connected to every other vertex.
In a complete graph, there will be V - 1V−1 unique paths of length one that start at the source vertex; one of these paths will go to the target and end. Each of the remaining paths will have V - 2V−2 unique paths that extend from it (since none of them will go back to the source vertex which was already visited). This process will continue and lead to approximately (V - 1)!(V−1)! total paths. Remember, once a path reaches the target vertex, it ends, so the total number of paths will be less than (V - 1)!(V−1)!.

The precise total number of paths in the worst-case scenario is equivalent to the [Number of Arrangements](https://oeis.org/wiki/Number_of_arrangements) of the subset of vertices excluding the source and target node, which equals e \cdot (V - 2)!e⋅(V−2)!.

While finding all paths, at each iteration, we add all valid paths from the current vertex to the stack, as shown in the video. Each time we add a path to the stack requires O(V)O(V) time to create a copy of the current path, append a vertex to it, and push it onto the stack. Since the path grows by one vertex each time, a path of length VV must have been copied and pushed onto the stack VV times before reaching its current length. Therefore, it is intuitive to think that each path should require O(V^2)O(V 
2
 ) time in total. However, there is a flaw in our logic. Consider the example above; at 2:50 we add ADE to the stack. Then at 3:20, we add ADEC, ADEB, and ADEF to the stack. ADE is a subpath of ADEC, ADEB, and ADEF, but ADE was only created once. So the time required for each path to create ADE can be thought of as O(V)O(V) divided by the number of paths that stem from ADE. With this in mind, the time spent to create a path is VV plus V - 1V−1 divided by the number of paths that stem from this subpath plus V - 2V−2 times... For a complete graph with many nodes, this averages out to O(2 \cdot V) = O(V)O(2⋅V)=O(V) time per path.

Thus, the time complexity to find all paths in an undirected graph in the worst-case scenario is equal to the number of paths found O((V - 2)!)O((V−2)!) times the average time to find a path O(V)O(V) which simplifies to O((V - 1)!)O((V−1)!).

- Space Complexity: O(V^3). The space used is by the stack which will contain:
    - (V−1) paths after adding first V - 1V−1 paths to the stack.
    - (V−1)−1+(V−2) paths after popping one path and adding second set of paths.
    - (V−1)−1+(V−2)−1+(V−3)−1+...
    - ≈ V⋅(V−1)/2+1 paths will be at most in the stack, and each path added to the stack will take O(V)O(V) space.

Therefore, in total, this solution will require O(V \cdot (V - 1) / 2 + 1) \cdot V = O(V^3)O(V⋅(V−1)/2+1)⋅V=O(V 
3
 ) space. Note that the space used to store the result does not count towards the space complexity.

Example:
1. Find if Path Exists in Graph
2. All Paths From Source to Target
3. Reconstruct Itinerary
4. All paths from source lead to destination


















