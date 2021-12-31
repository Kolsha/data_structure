### [204. Count Primes](https://leetcode.com/problems/count-primes/)


Count the number of prime numbers less than a non-negative number, n.

Example:
```
Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
```

##### Solution

##### [Approach 1: ](https://leetcode.com/problems/count-primes/discuss/57588/My-simple-Java-solution)

![](https://leetcode.com/static/images/solutions/Sieve_of_Eratosthenes_animation.gif)

##### Complexity analysis
https://leetcode.com/problems/count-primes/discuss/473021/Time-Complexity-O(log(log(n)

- Time complexity: O(nlog(log(n)))
- Space complexity: O(N)


```java
public class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        
        return count;
    }
}
```

```java
class Solution {
    public int countPrimes(int n) {
        if(n < 2) {
            return 0;
        } 

        int res = 0;
        boolean[] notPrime = new boolean[n];
        for(int i = 2; i < n; i++) {
            if(notPrime[i]) {
                continue;
            }
            res++;
            for(int j = i; j < n; j=j+i) {
                notPrime[j] = true;
            }
        }
        return res;
    }
}
```