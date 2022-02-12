### Single Source Shortest Path Algorithm


Previously, we used the “breadth-first search” algorithm to find the “shortest path” between two vertices. However, the “breadth-first search” algorithm can only solve the “shortest path” problem in “unweighted graphs”. But in real life, we often need to find the “shortest path” in a “weighted graph”.

For example, there may be many routes from your home to a target location, such as a bus station, and the time needed for each route may be different. The route with the shortest distance may not be the one that requires the least amount of time because of the speed limit and traffic jams. So, if we want to find the route that takes the least time from home to a certain bus station, then the weights should be time instead of distance. With that in mind, how can we solve the “shortest path” problem given two vertices in a “weighted graph”?

The main focus of this chapter is to solve such “single source shortest path” problems. Given the starting vertex, find the “shortest path” to any of the vertices in a weighted graph. Once we solve this, we can easily acquire the shortest paths between the starting vertex and a given target vertex.

### Edge Relaxation
In the following video, we'll talk about an Edge Relaxation operation that is a key element in solving the “single-source shortest path” problem.

An alternative way to understand why this process is called ‘relaxation’ is to imagine that each path is a rubber band of length 1. The original path from A to D is of length 3, so the rubber band was stretched to 3 times its original length. When we relax the path to length 2, by visiting C first, the rubber band is now only stretched to twice its length, so you can imagine the rubber band being relaxed, hence the term edge relaxation.



In this chapter, we will learn two “single source shortest path” algorithms:

1. Dijkstra’s algorithm
2. Bellman-Ford algorithm

“Dijkstra's algorithm” can only be used to solve the “single source shortest path” problem in a weighted directed graph with non-negative weights.

“Bellman-Ford algorithm”, on the other hand, can solve the “single-source shortest path” in a weighted directed graph with any weights, including, of course, negative weights. 

### Dijkstra's Algorithm
“Dijkstra’s algorithm” solves the “single-source shortest path” problem in a weighted directed graph with non-negative weights.

### The Main Idea
We take the starting point u as the center and gradually expand outward while updating the “shortest path” to reach other vertices.

“Dijkstra's Algorithm” uses a “greedy approach”. Each step selects the “minimum weight” from the currently reached vertices to find the “shortest path” to other vertices.



### Proof of the Algorithm
Now let's prove that Dijkstra's Algorithm actually leads to the correct answer. We'll do that in the next video.

The “greedy approach” only guarantees that, at each step, it takes the optimal choice in the current state. It does not guarantee that the final result is optimal. So, how does “Dijkstra’s Algorithm” ensure that its final result is optimal?


### Limitation of the Algorithm
“Dijkstra’s Algorithm” can only be used on graphs that satisfy the following condition:

- Weights of all edges are non-negative.

We'll discuss the aforementioned limitation of the Dijkstra's Algorithm in the following video.

### Complexity Analysis
VV represents the number of vertices, and EE represents the number of edges.

- Time Complexity: O(E + V \log V)O(E+VlogV) when a Fibonacci heap is used, or O(V + E \log V)O(V+ElogV) for a Binary heap.
    - If you use a Fibonacci heap to implement the “min-heap”, extracting minimum element will take O(\log V)O(logV) time while key decreasing operation will take amortized O(1)O(1) time, therefore, the total time complexity would be O(E + V \log V)O(E+VlogV).
    - If you use a Binary heap, then the time complexity would be O(V + E \log V)O(V+ElogV).

- Space Complexity: O(V)O(V). We need to store VV vertices in our data structure.

Example:
1. Network Delay Time

### Bellman Ford Algorithm
As discussed previously, the “Dijkstra algorithm” is restricted to solving the “single source shortest path” problem in graphs without negative weights. So, how could we solve the “single source shortest path” problem in graphs with negative weights? In this chapter, we will introduce the Bellman-Ford algorithm.

### Basic Theorem

Theorem 1: In a “graph with no negative-weight cycles” with N vertices, the shortest path between any two vertices has at most N-1 edges.


Theorem 2: In a “graph with negative weight cycles”, there is no shortest path.
In the next video, we'll show why there is no solution can be found when we have a “graph with negative weight cycles”.

### Using Dynamic Programming to Find the Shortest Path
Now we'll use Dynamic Programming technique to find the shortest path between the vertices in the given graph.


### Complexity Analysis
VV represents the number of vertices in the graph, and EE represents the number of edges.

- Time Complexity: O(V \cdot E)O(V⋅E). In the worst-case scenario, when all the vertices are connected with each other, we need to check every path from every vertex; this results in O(V \cdot E)O(V⋅E) time complexity.

- Space Complexity: O(V^2)O(V 
2
 ). We need to store a 2-dimensional DP matrix with the size of V \cdot VV⋅V.


### Explanation of the Bellman-Ford Algorithm:
We will solve the same problem in the following video, but now we'll use the Bellman-Ford Algorithm.

### Optimizing the Bellman-Ford Algorithm
In the following video, we'll further optimize our algorithm.


### Comparing the Two Bellman-Ford Algorithm Variations
Now let's check the differences between the two versions of the Bellman-Ford Algorithm that we explained earlier.

### Limitation of the algorithm
“Bellman-Ford algorithm” is only applicable to “graphs” with no “negative weight cycles”.



### How does the Bellman-Ford algorithm detect “negative weight cycles”?
Although the “Bellman-Ford algorithm” cannot find the shortest path in a graph with “negative weight cycles”, it can detect whether there exists a “negative weight cycle” in the “graph”.

Detection method: After relaxing each edge N-1 times, perform the Nth relaxation. According to the “Bellman-Ford algorithm”, all distances must be the shortest after relaxing each edge N-1 times. However, after the Nth relaxation, if there exists distances[u] + weight(u, v) < distances(v) for any edge(u, v), it means there is a shorter path . At this point, we can conclude that there exists a “negative weight cycle”.



### Complexity Analysis
VV represents the number of vertices, and EE represents the number of edges.

- Time Complexity: we iterate through all the vertices, and in each iteration, we'll perform a relaxation operation for each appropriate edge. Therefore, the time complexity would be O(V \cdot E)O(V⋅E).

- Space Complexity: O(V)O(V). We use two arrays of length VV. One to store the shortest distance from the source vertex using at most k-1 edges. The other is to store the shortest distance from the source vertex using at most k edges.


### Improved Bellman-Ford Algorithm with Queue — SPFA Algorithm


Previously, we introduced the “Bellman-Ford Algorithm” along with an improvement. The improvement is that for a graph without negative cycles, after relaxing each edge N-1 times, we can get the minimum distance from the starting vertex to all other vertices. However, there could be unnecessary computation when relaxing all edges N-1 times, resulting in suboptimal time complexity in some cases.



### Limitations of the Bellman-Ford Algorithm
In the following video, you'll learn more about the limitations of the “Bellman-Ford Algorithm”.


### SPFA algorithm
To address the limitations, we introduce an improved variation of the Bellman-Ford algorithm by using a queue. This improvement is known as “the Shortest Path Faster Algorithm” (SPFA algorithm).

Instead of choosing among any untraversed edges, as one does by using the “Bellman-Ford” algorithm, the “SPFA” Algorithm uses a “queue” to maintain the next starting vertex of the edge to be traversed. Only when the shortest distance of a vertex is relaxed and that the vertex is not in the “queue”, we add the vertex to the queue. We iterate the process until the queue is empty. At this point, we have calculated the minimum distance from the given vertex to any vertices.



### Video explanation of the SPFA algorithm
Now it's time to look at “the Shortest Path Faster Algorithm” (SPFA) in action.

### Complexity Analysis
VV represents the number of vertices, and EE represents the number of edges.

- Time Complexity: we iterate through all the vertices, and in each iteration, we'll perform a relaxation operation for each appropriate edge. Therefore, the time complexity would be O(V \cdot E)O(V⋅E).

- Space Complexity: O(V)O(V). We need to store VV vertices.

Note that the time complexity of the improved version of the algorithm for the worst-case scenario is the same as of the standard version of the Bellman-Ford Algorithm. However, on average, the SPFA tends to be faster.

Example:
1. Cheapest Flights Within K Stops

```c++
class Solution {
public:
    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {
        if (src == dst) {
            return 0;
        }

        vector<int> previous(n, INT_MAX);
        vector<int> current(n, INT_MAX);
        previous[src] = 0;
        for (int i = 1; i < k + 2; i++) {
            current[src] = 0;
            for (vector<int>& flight : flights) {
                int previous_flight = flight[0];
                int current_flight = flight[1];
                int cost = flight[2];

                if (previous[previous_flight] < INT_MAX) {
                    current[current_flight] = min(current[current_flight],
                                                  previous[previous_flight] + cost);
                }
            }
            previous.assign(current.begin(), current.end());
        }
        return current[dst] == INT_MAX ? -1 : current[dst];
    }
};
```

```java
class Solution {
    // Bellman Ford Algorithm
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (src == dst) {
            return 0;
        }

        int[] previous = new int[n];
        int[] current = new int[n];
        for (int i = 0; i < n; i++) {
            previous[i] = Integer.MAX_VALUE;
            current[i] = Integer.MAX_VALUE;
        }
        previous[src] = 0;
        for (int i = 1; i < k + 2; i++) {
            current[src] = 0;
            for (int[] flight : flights) {
                int previous_flight = flight[0];
                int current_flight = flight[1];
                int cost = flight[2];

                if (previous[previous_flight] < Integer.MAX_VALUE) {
                    current[current_flight] = Math.min(current[current_flight],
                                                       previous[previous_flight] + cost);
                }
            }
            previous = current.clone();
        }
        return current[dst] == Integer.MAX_VALUE ? -1 : current[dst];
    }
}
```

```python
class Solution:
    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dst: int, k: int) -> int:
        if src == dst:
            return 0
        
        INF = sys.maxsize
        previous = [INF] * n
        current = [INF] * n
        previous[src] = 0
        
        for i in range(1, k + 2):
            current[src] = 0
            for flight in flights:
                previous_flight, current_flight, cost = flight

                if previous[previous_flight] < INF:
                    current[current_flight] = min(current[current_flight],
                                                  previous[previous_flight] + cost)
                    
            previous = current.copy()
            
        return -1 if current[dst] == INF else current[dst]
```



2. Path With Minimum Effort





















