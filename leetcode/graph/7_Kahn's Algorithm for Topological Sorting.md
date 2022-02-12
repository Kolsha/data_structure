### Overview of Kahn's Algorithm

When selecting courses for the next semester in college, you might have noticed that some advanced courses have prerequisites that require you to take some introductory courses first. In Figure 12, for example, to take Course C, you need to complete Course B first, and to take Course B, you need to complete Course A first. There are many courses that you must complete for an academic degree. You do not want to find out in the last semester that you have not completed some prerequisite courses for an advanced course. So, how can we arrange the order of the courses adequately while considering these prerequisite relationships between them?

![image](https://user-images.githubusercontent.com/5952279/153696881-73be8410-5b98-4e0b-aa88-338e5ca98e9f.png)

“Topological sorting” helps solve the problem. It provides a linear sorting based on the required ordering between vertices in directed acyclic graphs. To be specific, given vertices u and v, to reach vertex v, we must have reached vertex u first. In “topological sorting”, u has to appear before v in the ordering. The most popular algorithm for “topological sorting” is Kahn’s algorithm.



### Video Explanation
In this video, we'll cover how the Kahn's Algorithm can be used for topological sorting.

Note, for simplicity while introducing Kahn's algorithm, we iterated over all of the courses and reduced the in-degree of those for which the current course is a prerequisite. This requires us to iterate over all EE prerequisites for all VV courses resulting in O(V \cdot E)O(V⋅E) time complexity at the cost of O(V)O(V) space to store the in degree for each vertex.

However, this step can be performed more efficiently by creating an adjacency list where adjacencyList[course] contains a list of courses that depend on course. Then when each course is taken, we will only iterate over the courses that have the current course as a prerequisite. This will reduce the total time complexity to O(V + E)O(V+E) at the cost of an additional O(E)O(E) space to store the adjacency list.



### Limitation of the Algorithm
- “Topological sorting” only works with graphs that are directed and acyclic.

- There must be at least one vertex in the “graph” with an “in-degree” of 0. If all vertices in the “graph” have a non-zero “in-degree”, then all vertices need at least one vertex as a predecessor. In this case, no vertex can serve as the starting vertex.

### Complexity Analysis
VV represents the number of vertices, and EE represents the number of edges.


- Time Complexity: O(V + E)O(V+E).

  - First, we will build an adjacency list. This allows us to efficiently check which courses depend on each prerequisite course. Building the adjacency list will take O(E)O(E) time, as we must iterate over all edges.
  
  - Next, we will repeatedly visit each course (vertex) with an in-degree of zero and decrement the in-degree of all courses that have this course as a prerequisite (outgoing edges). In the worst-case scenario, we will visit every vertex and decrement every outgoing edge once. Thus, this part will take O(V + E)O(V+E) time.
  
  - Therefore, the total time complexity is O(E) + O(V + E) = O(V + E)O(E)+O(V+E)=O(V+E).

- Space Complexity: O(V+E)O(V+E).

  - The adjacency list uses O(E)O(E) space.

  - Storing the in-degree for each vertex requires O(V)O(V) space.

  - The queue can contain at most VV nodes, so the queue also requires O(V)O(V) space.


Example:
1. Course Schedule II
```c++
class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<int> result(numCourses);
        if (numCourses == 0) {
            return result;
        }

        if (prerequisites.empty()) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        vector<int> indegree(numCourses);
        queue<int> zeroDegree;
        for (vector<int>& pre : prerequisites) {
            indegree[pre[0]]++;
        }
        for (int i = 0; i < indegree.size(); i++) {
            if (indegree[i] == 0) {
                zeroDegree.push(i);
            }
        }
        if (zeroDegree.empty()) {
            return vector<int>();
        }

        int index = 0;
        while (!zeroDegree.empty()) {
            int course = zeroDegree.front();
            zeroDegree.pop();
            result[index] = course;
            index++;
            for (vector<int>& pre : prerequisites) {
                if (pre[1] == course) {
                    indegree[pre[0]]--;
                    if (indegree[pre[0]] == 0) {
                        zeroDegree.push(pre[0]);
                    }
                }
            }
        }

        for (int in : indegree) {
            if (in != 0) {
                return vector<int>();
            }
        }

        return result;
    }
};
```

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        if (numCourses == 0) {
            return result;
        }
        
        if (prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }
        
        int[] indegree = new int[numCourses];
        Queue<Integer> zeroDegree = new LinkedList<>();
        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0){
                zeroDegree.add(i);
            }
        }
        if (zeroDegree.isEmpty()) {
            return new int[0];
        }
        int index = 0;
        while (!zeroDegree.isEmpty()) {
            int course = zeroDegree.poll();
            result[index] = course;
            index++;
            for (int[] pre : prerequisites) {
                if (pre[1] == course) {
                    indegree[pre[0]]--;
                    if (indegree[pre[0]] == 0) {
                        zeroDegree.add(pre[0]);
                    }
                }
            }
        }
        
        for (int in : indegree) {
            if (in != 0) {
                return new int[0];
            }
        }

        return result;
    }
}
```


```python
class Solution:
    def findOrder(self, num_courses: int, prerequisites: List[List[int]]) -> List[int]:
        result = [0] * num_courses
        if num_courses == 0:
            return result

        if not prerequisites:
            result = [i for i in range(num_courses)]
            return result

        indegree = [0] * num_courses
        zero_degree = deque()
        for pre in prerequisites:
            indegree[pre[0]] += 1
        for i in range(len(indegree)):
            if indegree[i] == 0:
                zero_degree.append(i)
        if not zero_degree:
            return []

        index = 0
        while zero_degree:
            course = zero_degree.popleft()
            result[index] = course
            index += 1
            for pre in prerequisites:
                if pre[1] == course:
                    indegree[pre[0]] -= 1
                    if indegree[pre[0]] == 0:
                        zero_degree.append(pre[0])

        if any(i for i in indegree): 
            return []
            
        return result
```

2. Alien Dictionary

3. Minimum Height Trees

4. Parallel Courses







