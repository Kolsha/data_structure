### Factorial Trailing Zeroes
https://leetcode.com/problems/factorial-trailing-zeroes/

Given an integer n, return the number of trailing zeroes in n!.

Follow up: Could you write a solution that works in logarithmic time complexity?

 

Example 1:
```
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.
```
Example 2:
```
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.
```
Example 3:
```
Input: n = 0
Output: 0
```

Solution

##### Approach 1: Compute the Factorial

Complexity analysis:
- Time complexity: O(n^2)
- Space complexity: O(log n!) = O(nlogn)

```java
import java.math.BigInteger;

public int trailingZeroes(int n) {
    
    // Calculate n!
    BigInteger nFactorial = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
        nFactorial = nFactorial.multiply(BigInteger.valueOf(i));
    }
                    
    // Count how many 0's are on the end.
    int zeroCount = 0;
    
    while (nFactorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
        nFactorial = nFactorial.divide(BigInteger.TEN);
        zeroCount++;
    }
    
    return zeroCount;
}
```

##### Approach 2: Counting Factors of 5

##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

```java
public int trailingZeroes(int n) {
        
    int zeroCount = 0;
    for (int i = 5; i <= n; i += 5) {
        int currentFactor = i;
        while (currentFactor % 5 == 0) {
            zeroCount++;
            currentFactor /= 5;
        }
    }
    return zeroCount;
}
```

##### Approach 3: Counting Factors of 5 Efficiently
For example with n = 12345 we get:
fives = 12345/ 5 + 12345/ 25 + 12345/ 125 + 12345/625 ...

Which is equal to:

fives = 2469 + 493 + 98 + 19 + 3 + 0 + 0 + ... = 3082

In code, we can do this by looping over each power of 55, calculating how many times it divides into nn, and then adding that to a running fives count. Once we have a power of 55 that's bigger than nn, we stop and return the final value of fives.

~~~
fives = 0
power_of_5 = 5
while n >= power_of_5:
    fives += n / power_of_5
    power_of_5 *= 5

tens = fives
~~~

##### Complexity anlaysis
- Time complexity: O(logn)
- Space complexity: O(1)

```java
public int trailingZeroes(int n) {
    int zeroCount = 0;
    // We need to use long because currentMultiple can potentially become
    // larger than an int.
    long currentMultiple = 5;
    while (n >= currentMultiple) {
        zeroCount += (n / currentMultiple);
        currentMultiple *= 5;
    }
    return zeroCount;
}
```

```java
public int trailingZeroes(int n) {
    int zeroCount = 0;
    long currentMultiple = 5;
    while (n > 0) {
        n /= 5;
        zeroCount += n;
    }
    return zeroCount;
}
```