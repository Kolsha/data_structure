### 1015. Smallest Integer Divisible by K

https://leetcode.com/problems/smallest-integer-divisible-by-k/

Given a positive integer K, you need find the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.

Return the length of N.  If there is no such N, return -1.


**Example 1:**
```
Input: 1
Output: 1
Explanation: The smallest answer is N = 1, which has length 1.
```
**Example 2:**
```
Input: 2
Output: -1
Explanation: There is no such positive integer N divisible by 2.
```
**Example 3:**
```
Input: 3
Output: 3
Explanation: The smallest answer is N = 111, which has length 3.
``` 

Note:

1 <= K <= 10^5

**Solution:**
from:
- https://leetcode.com/problems/smallest-integer-divisible-by-k/discuss/260852/JavaC%2B%2BPython-O(1)-Space-with-Proves-of-Pigeon-Holes
- https://leetcode.com/problems/smallest-integer-divisible-by-k/discuss/260875/Python-O(K)-with-Detailed-Explanations

- [Pigeonhole principle](https://en.wikipedia.org/wiki/Pigeonhole_principle)


我们根据 K 值计算出最小可被 K　整除的 N 有几位，1 % K, 11 % K, 111 % K, 11...1　% K
- 如果馀数为　０，则目前的数为最小可被 K 整除的数。
- 如果馀数皆不为 0，因除数为 K 时，馀数最多只有K - 1种值， 则馀数必有重复（根据 [Pigeonhole principle](https://en.wikipedia.org/wiki/Pigeonhole_principle)）而当馀数重复时，下一个馀数会进入循环的值，比如说 K　= 6　时，
- 1 % 6 = 1
- 11 % 6 = 5
- 111 % 6 = 3
- 1111 % 6 = 1
- 11111 % 6 = 5
- 111111 % 6 = 3

在此情况下， K 不能整除 11...1 因为馀数值会不断的循环，透过前一个馀数的值，可以预测出下个馀数的值，比如说在本题：`下个馀数的值 = ( 10 * 本次馀数的值 + 1 ) % K` 此时，馀数值永不为 0。

同时，直观上来说，因为 N（11...1） 必不能被 2 或 5 整除，因此能整除 N 的 K 也一定不能被 2 或 5 整除，因此可被 2 或 5 整除的值，直接返回 -1

***Solution:***
```java
class Solution {
    public int smallestRepunitDivByK(int K) {
        if (K % 2 == 0 || K % 5 == 0) {
            return -1;
        }
        // 从 N = 1, 11 ... 一直到 K 个 1, (10 * remainder + 1) 即为 N
        int remainder = 0;
        for (int digits = 1; digits <= K; digits++) {
            remainder = (10 * remainder + 1) % K;
            if (remainder == 0) {
                return digits;
            }
        }
        return -1;

    }
}
```