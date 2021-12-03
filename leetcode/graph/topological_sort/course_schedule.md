### [207. Course Schedule](https://leetcode.com/problems/course-schedule/)


There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:
```
Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
```
Example 2:
```
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
```
Note:

1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
2. You may assume that there are no duplicate edges in the input prerequisites.

Solution

According to the Wiki about what Topological sorting is (https://en.wikipedia.org/wiki/Topological_sorting)
and the Kahn's algorithm as shown below:

![](https://upload.wikimedia.org/wikipedia/commons/thumb/0/03/Directed_acyclic_graph_2.svg/360px-Directed_acyclic_graph_2.svg.png)

L ← Empty list that will contain the sorted elements

S ← Set of all nodes with no incoming edges
```
while S is non-empty do

    remove a node n from S
    add n to tail of L
    for each node m with an edge e from n to m do
        remove edge e from the graph
        if m has no other incoming edges then
            insert m into S
if graph has edges then
    return error (graph has at least one cycle)
else
    return L (a topologically sorted order)
```
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        // init indegree for each course, so we can find course indegree == 0
        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int course = prerequisites[i][0];
            if (matrix[pre][course] == 0) {
                indegree[course]++;
            }
            matrix[pre][course] = 1;
        }

        // start topology sorting
        LinkedList<Integer> list = new LinkedList<>();
        // find course which indegree == 0 and add it to the queue
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                list.offer(i);
            }
        }

        int count = 0;
        while (!list.isEmpty()) {
            int course = list.poll();
            count++;

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[course][i] != 0) {
                    // find the unvisited courses and add to the queue
                    if (--indegree[i] == 0) {
                        list.offer(i);
                    }
                }
            }
        }
        return numCourses == count;
    }
}
```