### 29. Divide Two Integers

https://leetcode.com/problems/divide-two-integers/

Given two integers `dividend` and `divisor`, divide two integers without using multiplication, division, and mod operator.

Return the quotient after `dividing` dividend by `divisor`.

The integer division should truncate toward zero, which means losing its fractional part. For example, `truncate(8.345) = 8` and `truncate(-2.7335) = -2`.

Note:

- Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: `[−2^31,  2^31 − 1]`. 
- For this problem, assume that your function returns 2^31 − 1 when the division result overflows.
 

Example 1:
```
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.
```
Example 2:
```
Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.
```
Example 3:
```
Input: dividend = 0, divisor = 1
Output: 0
```
Example 4:
```
Input: dividend = 1, divisor = 1
Output: 1
``` 

Constraints:

- -2^31 <= dividend, divisor <= 2^31 - 1
- divisor != 0

##### Approach 1: Repeated Subtraction

##### Complexity analysis
- Time complexity: O(n)
  Consider the worst case where the divisor is 1. For any dividend n, we'll need to subtract 1 a total of n times to get to 0.
- Space complexity: O(1)
```java
public int divide(int dividend, int divisor) {

    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    /* Count how many times the divisor has to be added
     * to get the dividend. This is the quotient. */
    int quotient = 0;
    while (dividend - divisor <= 0) {
        quotient--;
        dividend -= divisor;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        quotient = -quotient;
    }
    return quotient;
}
```

##### Approach 3: Adding Powers of Two
##### Intuition

In the previous approach, we did repeated exponential searches for the largest value that would fit into the current dividend.

However, notice that each time we do a search, we repeatedly go through the same doubles to find the largest. For example, consider the first and second step of our previous example: divide(93706, 157).

On the first step we did this:
```bash
157
314
628
1256
2512
5024
10048
20096
40192
80384
160768 # Too big
```
This left us with a difference of `93706 - 80384 = 13322`.

On the second step we repeated this process again with 13322:
```bash
157
314
628
1256
2512
5024
10048
20096 # Too big
```
Notice that we've just recomputed the first seven terms of the doubles again!

Instead of doing this, we should find a way so that we can compute the sequence just once and then use the results from this to compute our quotient.

In order to do this, we need to notice one more property about the difference. That property is that the difference will always be less than the previous doubling of the divisor that fits into it. Why? Well, if it were equal, or bigger, than the largest doubling, then we must've stopped doubling too soon. So, the difference is always less than the biggest doubling.

So to use these properties, we'll put all the "doubles" of 157 into a List. Then we'll iterate backwards over the list taking all the numbers that will fit into the dividend. Here's an animation of the algorithm.

##### Complexity analysis
- Time complexity: O(logN)
  We take O(logN) time in the first loop to create our list of doubles (and powers of two). For the second loop, because there's O(logn) items in the list of doubles, it only takes O(logn)time for this loop as well.
  Combined, our total time complexity is just O(logN + logN) = O(logN).

- Space complexity: O(logN)

```java
private static int HALF_INT_MIN = -1073741824;// -2**30;

public int divide(int dividend, int divisor) {

    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    ArrayList<Integer> doubles = new ArrayList<>();
    ArrayList<Integer> powersOfTwo = new ArrayList<>();

    /* Nothing too exciting here, we're just making a list of doubles of 1 and
     * the divisor. This is pretty much the same as Approach 2, except we're
     * actually storing the values this time. */
    int powerOfTwo = -1;
    while (divisor >= dividend) {
        doubles.add(divisor);
        powersOfTwo.add(powerOfTwo);
        // Prevent needless overflows from occurring...
        if (divisor < HALF_INT_MIN) {
            break;
        }
        divisor += divisor;
        powerOfTwo += powerOfTwo;
    }

    int quotient = 0;
    /* Go from largest double to smallest, checking if the current double fits.
     * into the remainder of the dividend. */
    for (int i = doubles.size() - 1; i >= 0; i--) {
        if (doubles.get(i) >= dividend) {
            // If it does fit, add the current powerOfTwo to the quotient.
            quotient += powersOfTwo.get(i);
            // Update dividend to take into account the bit we've now removed.
            dividend -= doubles.get(i);
        }
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        return -quotient;
    }
    return quotient;
}
```

##### Approach 4: Adding Powers of Two with Bit-Shifting
##### Intuition

In Approach 3 we put doubles of the divisor, and powers of two into lists. This was so that we could easily refer back to them.

However, we don't need to save them—we can simply find the largest double, along with it's corresponding power of two, and then generate the rest by dividing by two repeatedly. But we can't divide by two, that breaks the rules..., you might be thinking. The solution is to use the right-shift bitwise operator!
```java
int a = 1020;
a = a >> 1;
System.out.println(a);
// Prints 510.
```

One potential pitfall with the right-shift operator is using it on negative odd numbers. Two's complement makes the result one-off what you would expect/ probably wanted. This happens in all the programming languages we've checked, although there could be a few that behave differently.

```java
int a = -1020;
a = a >> 1;
System.out.println(a);
// Prints -510. Great!
int b = -1021;
b = b >> 1;
System.out.println(b);
// Prints -511. Ugghh.
```

The solution is to add 1 before doing the bit-shift on a negative number. This way, it'll be "correct" regardless of whether the number was odd or even.

```java
int a = -1020;
a = (a + 1) >> 1;
System.out.println(a);
// Prints -510. Great!
int b = -1021;
b = (b + 1) >> 1;
System.out.println(b);
// Prints -510. Yay!
```

The reason we brought this up is because it's a pitfall you might encounter with your own code, and potentially be driven crazy by, if you have limited experience working with bitwise operators. It turns out we can completely ignore the issue for the algorithm we've got here, as we know the numbers we're right shifting happen to always be even. This is because of the way they were generated.

Here is the algorithm, again using only positive numbers (like before, check the next section to see the actual implementations).

##### Complexity analysis
- Time complexity: O(logN)
- Space complexity: O(1)

```java
private static int HALF_INT_MIN = -1073741824;

public int divide(int dividend, int divisor) {

    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    /* In the first loop, we simply find the largest double of divisor
     * that fits into the dividend.
     * The >= is because we're working in negatives. In essence, that
     * piece of code is checking that we're still nearer to 0 than we
     * are to INT_MIN. */
    int highestDouble = divisor;
    int highestPowerOfTwo = -1;
    while (highestDouble >= HALF_INT_MIN && dividend <= highestDouble + highestDouble) {
        highestPowerOfTwo += highestPowerOfTwo;
        highestDouble += highestDouble;
    }

    /* In the second loop, we work out which powers of two fit in, by
     * halving highestDouble and highestPowerOfTwo repeatedly.
     * We can do this using bit shifting so that we don't break the
     * rules of the question :-) */
    int quotient = 0;
    while (dividend <= divisor) {
        if (dividend <= highestDouble) {
            quotient += highestPowerOfTwo;
            dividend -= highestDouble;
        }
        /* We know that these are always even, so no need to worry about the
         * annoying "bit-shift-odd-negative-number" case. */
        highestPowerOfTwo >>= 1;
        highestDouble >>= 1;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        return -quotient;
    }
    return quotient;
}
```