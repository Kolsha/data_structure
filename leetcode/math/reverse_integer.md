### 7. Reverse Integer

https://leetcode.com/problems/reverse-integer/

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:
```
Input: 123
Output: 321
```
Example 2:
```
Input: -123
Output: -321
```
Example 3:
```
Input: 120
Output: 21
```
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: `[−2 ^ 31,  2 ^ 31 − 1]`. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Solution


Approach 1: push-pop mathematicaly
```java
//pop operation:
pop = x % 10;
x /= 10;

//push operation:
temp = rev * 10 + pop;
rev = temp;
```

Complexity Analysis
- Time complexity: O(log(N)) : O(log<sub>10</sub> N &rarr; digit counts)

- Space complexity: O(1)

```java
    public int reverse(int x) {
        int res = 0;
        while(x!=0) {
            int pop = x % 10;
            x = x / 10;
            // prevent overflow
            // Integer.MAX_VALUE = 2^31 = 2147483647
            if(res > Integer.MAX_VALUE / 10  || (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            
            // Integer.MIN_VALUE = -2^31 = -2147483648
            if(res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE/ 10 && pop < -8)) {
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }
}
```

Approach 1: Pop and Push Digits & Check before Overflow
Intuition

We can build up the reverse integer one digit at a time. While doing so, we can check beforehand whether or not appending another digit would cause overflow.

Algorithm

Reversing an integer can be done similarly to reversing a string.

We want to repeatedly "pop" the last digit off of xx and "push" it to the back of the \text{rev}rev. In the end, \text{rev}rev will be the reverse of the xx.

To "pop" and "push" digits without the help of some auxiliary stack/array, we can use math.

//pop operation:
pop = x % 10;
x /= 10;

//push operation:
temp = rev * 10 + pop;
rev = temp;
However, this approach is dangerous, because the statement \text{temp} = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop can cause overflow.

Luckily, it is easy to check beforehand whether or this statement would cause an overflow.

To explain, lets assume that \text{rev}rev is positive.

If temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop causes overflow, then it must be that \text{rev} \geq \frac{INTMAX}{10}rev≥ 
10
INTMAX
​	
 
If \text{rev} > \frac{INTMAX}{10}rev> 
10
INTMAX
​	
 , then temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop is guaranteed to overflow.
If \text{rev} == \frac{INTMAX}{10}rev== 
10
INTMAX
​	
 , then temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop will overflow if and only if \text{pop} > 7pop>7
Similar logic can be applied when \text{rev}rev is negative.

```java
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
```
Complexity Analysis

Time Complexity: O(\log(x))O(log(x)). There are roughly \log_{10}(x)log 
10
​	
 (x) digits in xx.
Space Complexity: O(1)O(1).