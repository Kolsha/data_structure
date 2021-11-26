### [89. Gray Code](https://leetcode.com/problems/gray-code/)

An **n-bit gray code sequence** is a sequence of <code>$2^n$</code> integers where:

Every integer is in the inclusive range <code>$[0, 2^n - 1]$</code>,
The first integer is `0`,
An integer appears **no more than once** in the sequence,
The binary representation of every pair of **adjacent** integers differs by **exactly one bit**, and
The binary representation of the **first** and **last** integers differs by **exactly one bit**.
Given an integer `n`, return any valid **n-bit gray code sequence**.

 

Example 1:
```
Input: n = 2
Output: [0,1,3,2]
Explanation:
The binary representation of [0,1,3,2] is [00,01,11,10].
- 00 and 01 differ by one bit
- 01 and 11 differ by one bit
- 11 and 10 differ by one bit
- 10 and 00 differ by one bit
[0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
- 00 and 10 differ by one bit
- 10 and 11 differ by one bit
- 11 and 01 differ by one bit
- 01 and 00 differ by one bit
```
Example 2:
```
Input: n = 1
Output: [0,1]
``` 

Constraints:

- 1 <= n <= 16

##### Solution

##### Approach 1: Bit Manupulation
Generate the sequence iteratively. For example, when n = 3, we can get the result based on n = 2.
00,01,11,10 -> (000,001,011,010 ) (110,111,101,100). 

The middle two numbers only differ at their highest bit, while the rest numbers of part two are exactly symmetric of part one.
Say the example input is 3.
```
0: 000
1: 001
3: 011
2: 010

6: 110
7: 111
5: 101
4: 100
```
For the pair of (2, 6), (3, 7), (1, 5) and (0, 4), the last 2 bits are the same. The only difference is 6,7,5 and 4 set the first bit on.

##### Complexity analysis
- Time complexity: $O(n^2)$
- Space complexity: $O(n^2)$

```java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> rs = new ArrayList<Integer>();
        rs.add(0);
         
        for(int i = 0; i < n; i++) {
            int size = rs.size();
            for(int k = size - 1; k >= 0; k--) {
                rs.add(rs.get(k) | 1 << i);
            }
        }
        return rs;
    }
}
```