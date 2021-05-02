### [Decode XORed Array](https://leetcode.com/problems/decode-xored-array/)

There is a hidden integer array arr that consists of n non-negative integers.

It was encoded into another integer array encoded of length n - 1, such that encoded[i] = arr[i] XOR arr[i + 1]. For example, if arr = [1,0,2,1], then encoded = [1,2,3].

You are given the encoded array. You are also given an integer first, that is the first element of arr, i.e. arr[0].

Return the original array arr. It can be proved that the answer exists and is unique.

 

Example 1:
```
Input: encoded = [1,2,3], first = 1
Output: [1,0,2,1]
Explanation: If arr = [1,0,2,1], then first = 1 and encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
```
Example 2:
```
Input: encoded = [6,2,7,3], first = 4
Output: [4,2,0,7,4]
``` 

Constraints:

- 2 <= n <= $10^4$
- encoded.length == n - 1
- 0 <= encoded[i] <= $10^5$
- 0 <= first <= $10^5$

#### Solution

#### Approach: Bit manipulation
ref: https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/Bitwise_XOR

Given: encoded[i] = arr[i] ^ arr[i + 1]
&rarr; encoded[i] ^ <font color='blue'>encoded[i]</font> ^ <font color='blue'>arr[i + 1]</font> = arr[i] ^ arr[i + 1] ^ <font color='blue'>encoded[i]</font> ^ <font color='blue'>arr[i + 1]</font>
&rarr; arr[i + 1] = arr[i] ^ encoded[i]

#### Implementation
Java
```java
class Solution {
    public int[] decode(int[] encoded, int first) {
        
        int[] res = new int[encoded.length+1];
        res[0] = first;
        for(int i = 0; i < encoded.length; i++) {
            res[i+1] = res[i] ^ encoded[i];
        }
        return res;
    }
}
```

Python
```python
class Solution:
    # arr[i + 1] = arr[i] ^ encoded[i]
    def decode(self, encoded: List[int], first: int) -> List[int]:
        res = [first]
        for code in encoded:
            res.append(res[-1] ^ code)
        return res
```

#### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(n)