### 1588. Sum of All Odd Length Subarrays

https://leetcode.com/problems/sum-of-all-odd-length-subarrays/

Given an array of positive integers `arr`, calculate the sum of all possible odd-length subarrays.

A subarray is a contiguous subsequence of the array.

Return the sum of all odd-length subarrays of `arr`.

Example 1:
```
Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
```
Example 2:
```
Input: arr = [1,2]
Output: 3
Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
```
Example 3:
```
Input: arr = [10,11,12]
Output: 66
```


Constraints:

- 1 <= arr.length <= 100
- 1 <= arr[i] <= 1000

#### Solution

Approach 1: Brute Force
Enumerate all possible odd length of subarray.

##### Complexity analysis
- Time complexity: O(n^3)
- Space complexity: O(1)

```python
    def sumOddLengthSubarrays(self, A):
        n = len(A)
        res = 0
        for l in xrange(1, n + 1, 2):
            for i in xrange(n - l + 1):
                res += sum(A[i:i + l])
        return res
```

#### Approach 2: Consider the contribution of A[i]

Consider the subarray that contains `A[i]`,
we can take 0,1,2..,i elements on the left,
from `A[0]` to `A[i]`,
we have `i + 1` choices.

we can take 0,1,2..,n-1-i elements on the right,
from `A[i]` to `A[n-1]`,
we have `n - i` choices.

In total, there are `k = (i + 1) * (n - i)` subarrays, that contains `A[i]`.
And there are `(k + 1) / 2` subarrays with odd length, that contains `A[i]`.
And there are `k / 2` subarrays with even length, that contains `A[i]`.

`A[i]` will be counted `((i + 1) * (n - i) + 1) / 2` times for our question.


**Example of array [1,2,3,4,5]**
1 2 3 4 5 subarray length 1
1 2 X X X subarray length 2
X 2 3 X X subarray length 2
X X 3 4 X subarray length 2
X X X 4 5 subarray length 2
1 2 3 X X subarray length 3
X 2 3 4 X subarray length 3
X X 3 4 5 subarray length 3
1 2 3 4 X subarray length 4
X 2 3 4 5 subarray length 4
1 2 3 4 5 subarray length 5

5 8 9 8 5 total times each index was added, `k = (i + 1) * (n - i)`
3 4 5 4 3 total times in odd length array with `(k + 1) / 2`
2 4 4 4 2 total times in even length array with `k / 2`s


#### Complexity analysis
- Time complexity: O(N)
- Space complexity: O(1)


```java
    public int sumOddLengthSubarrays(int[] A) {
        int res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            res += ((i + 1) * (n - i) + 1) / 2 * A[i];
        }
        return res;
    }
```

```python
    def sumOddLengthSubarrays(self, A):
        res, n = 0, len(A)
        for i, a in enumerate(A):
            res += ((i + 1) * (n - i) + 1) / 2 * a
        return res

    def sumOddLengthSubarrays(self, A):
        return sum(((i + 1) * (len(A) - i) + 1) / 2 * a for i, a in enumerate(A))
```

