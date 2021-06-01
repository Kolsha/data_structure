### [313. Super Ugly Number](https://leetcode.com/problems/super-ugly-number/)

A super ugly number is a positive integer whose prime factors are in the array `primes`.

Given an integer `n` and an array of integers primes, return the <code>$n^{th}$</code> **super ugly number**.

The <code>$n^{th}$</code> super ugly number is guaranteed to fit in a **32-bit** signed integer.

 

Example 1:
```
Input: n = 12, primes = [2,7,13,19]
Output: 32
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
```
Example 2:
```
Input: n = 1, primes = [2,3,5]
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].
``` 

Constraints:

- $1 <= n <= 10^6$
- 1 <= primes.length <= 100
- 2 <= primes[i] <= 1000
- primes[i] is guaranteed to be a prime number.
- All the values of primes are unique and sorted in ascending order.

### Solution

#### Approach 1: Priority Queue

```java
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add(1L);
        
       
        long ans = -1L;
        for(int i = 0; i < n; i++) {
            long curr = pq.poll();
            while(!pq.isEmpty() && curr == pq.peek()) {
                pq.poll();
            }
            
            for(int prime: primes) {
                pq.add(curr * prime);
            }
            ans = curr;
        }
        
        
        return (int) ans;
    }
}
```