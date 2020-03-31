### 378. Kth Smallest Element in a Sorted Matrix

https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
```
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
```
Note:
You may assume k is always valid, 1 ≤ k ≤ n<sup>2</sup>.

Solution

Method 1: Priority Queue

Time complexity: O(nlogn)

Space complexity: O(N)

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        Comparator<Node> comp = new Comparator<>() {
            @Override
            public int compare(Node a, Node b) {
                return matrix[a.row][a.col] - matrix[b.row][b.col];
            }
        };
        
        PriorityQueue<Node> queue = new PriorityQueue<>(comp);
        
        for(int i = 0; i < matrix.length; i++) {
            queue.offer(new Node(i, 0));
        }
        
        int result = 0;
        while(!queue.isEmpty()) {
            // Get the smallest in the queue
            Node node = queue.poll();
            result = matrix[node.row][node.col];
            if(--k == 0) {
                break;
            }
            node.col++;
            if(node.col <matrix[0].length) {
                queue.offer(node);
            }
        }
        
        return result;
    }
    
    private static class Node {
        int row;
        int col;

        Node(int ir, int ic) {
            row = ir;
            col = ic;
        }
    }
}
```