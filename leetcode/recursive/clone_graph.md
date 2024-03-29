### [133. Clone Graph](https://leetcode.com/problems/clone-graph/)

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
```java
class Node {
    public int val;
    public List<Node> neighbors;
}
``` 

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 

Example 1:
![](https://assets.leetcode.com/uploads/2019/11/04/133_clone_graph_question.png)
```
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
```
Example 2:

![](https://assets.leetcode.com/uploads/2020/01/07/graph.png)
```
Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
```
Example 3:
```
Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
```
Example 4:

![](https://assets.leetcode.com/uploads/2020/01/07/graph-1.png)
```
Input: adjList = [[2],[1]]
Output: [[2],[1]]
``` 

Constraints:

- 1 <= Node.val <= 100
- Node.val is unique for each node.
- Number of Nodes will not exceed 100.
- There is no repeated edges and no self-loops in the graph.
- The Graph is connected and all nodes can be visited starting from the given node.

Solution

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class Solution {
    HashMap<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        return clone(node);
    }

    private Node clone(Node node) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }
        Node temp = new Node(node.val);
        map.put(temp.val, temp);
        for (Node neighbor : node.neighbors) {
            temp.neighbors.add(clone(neighbor));
        }
        return temp;
    }
}
```


#### Approach 2: BFS

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        
        Node res = new Node(node.val);
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        HashMap<Integer, Node> map = new HashMap<>();
        map.put(node.val, res);
        
        while(!q.isEmpty()) {
            Node tmp = q.poll();
            
            for(Node neighbor: tmp.neighbors) {
                if(!map.containsKey(neighbor.val)) {
                    q.offer(neighbor);
                    map.put(neighbor.val, new Node(neighbor.val));
                }
                map.get(tmp.val).neighbors.add(map.get(neighbor.val));
            }
        }
        return res;
    }
}
```