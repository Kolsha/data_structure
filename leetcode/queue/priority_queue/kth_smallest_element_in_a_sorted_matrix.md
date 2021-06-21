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

### Solution

#### Approach 1: Priority Queue

##### Complexity analysis:
- Time complexity: O(nlogn)

- Space complexity: O(N)

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


#### Approach 2: Binary Search

##### Complexity analysis:
- Time complexity: O(N∗log(max−min))

- Space complexity: O(1)
```java
class Solution {
  
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n - 1][n - 1];
    
        while (start < end) {
            int mid = start + (end - start) / 2;
      
            // first number is the smallest and the second number is the largest
            int[] smallLargePair = { matrix[0][0], matrix[n - 1][n - 1] };
            int count = countLessEqual(matrix, mid, smallLargePair);
            if (count == k) {
                return smallLargePair[0];
            }
      
            if (count < k) {
                // search higher
                start = smallLargePair[1]; 
            } else {
                // search lower
                end = smallLargePair[0];
            }
        }
        return start;
    }

    private static int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length, row = n - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }
        return count;
    }
}
```