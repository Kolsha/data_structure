### [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii/)

An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.

 

Example 1:
```
Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
```
Example 2:
```
Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
``` 

Constraints:

- 1 <= n <= 1690

### Solution

#### Approach 1: PriorityQueue

##### Complexity anlaysis
- Time complexity: O(NLogN)
- Space complexity: O(N)

```java
class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1L);
        for(int i = 1; i < n; i++) {
            long temp = queue.poll();
            // remove the same value in the sorted queue
            while(!queue.isEmpty() && queue.peek() == temp) {
                queue.poll();
            }
            
            queue.offer(temp * 2);
            queue.offer(temp * 3);
            queue.offer(temp * 5);
        }
        
        return queue.peek().intValue();
    }
}
```