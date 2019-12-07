### 829. Consecutive Numbers Sum

https://leetcode.com/problems/consecutive-numbers-sum/

Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:
```
Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3
```
Example 2:
```
Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
```
Example 3:
```
Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
```
Note:<br/>
 1 <= N <= 10 ^ 9

 **Solution:**
 <br/>from: https://leetcode.com/problems/consecutive-numbers-sum/discuss/129015/5-lines-C%2B%2B-solution-with-detailed-mathematical-explanation.

The thought process goes like this- Given a number N, we can possibly write it as a sum of 2 numbers, 3 numbers, 4 numbers and so on. Let's assume the first number in this series be x. Hence, we should have: <br/>
`x + (x+1) + (x+2) +...+ (x + k - 1) = N` 
<br/>&rarr; `k * (x + (x + k-1))/2 = N` <br/>
&rarr; 2kx + k<sup>2</sup>-k = 2N<br/>
&rarr; 2kx = 2N + k - k<sup>2</sup><br/> &rarr; `that's what we need here`

So, we can calculate the RHS(right-hand side) for every value of `k` and if it is a `multiple of k` then we can construct a sum of `N` using `k` terms starting from x.
Now, the question arises, till what value of k should we loop for? That's easy. In the worst case, RHS should be greater than 0. That is
`N - k*(k-1)/2 > 0` which implies
`k*(k-1) < 2N` which can be approximated to:<br/>
`(k-1)*(k-1) < 2N`
<br/>&rarr;
`k < sqrt(2N) + 1`<br/>
Hence the overall complexity of the algorithm is O(sqrt(N))

PS: OJ expects the answer to be 1 greater than the number of possible ways because it considers N as being written as N itself. It's confusing since they ask for sum of consecutive integers which implies atleast 2 numbers. But to please OJ, we should start count from 1.
 ```java
class Solution {
    public int consecutiveNumbersSum(int N) {
        int count = 1;
        // (k-1)*(k-1) < 2N
        for (int i = 2; i < Math.sqrt(2 * N) + 1; i++) {
            // 2kx = 2N - K^2 + K, x must great than 0
            int x = (2 * N + i - i * i) / (2 * i);
            int r = (2 * N + i - i * i) % (2 * i);
            if (x > 0 && r == 0) {
                count++;
            }
        }
        return count;
    }
}
 ```