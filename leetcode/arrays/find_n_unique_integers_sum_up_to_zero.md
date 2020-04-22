### 1304. Find N Unique Integers Sum up to Zero

https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/

Given an integer n, return any array containing n unique integers such that they add up to 0.

 

Example 1:
```
Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
```
Example 2:
```
Input: n = 3
Output: [-1,0,1]
```
Example 3:
```
Input: n = 1
Output: [0]
``` 

Constraints:

- 1 <= n <= 1000

Solution

Optimal Method

```java
class Solution {
    public int[] sumZero(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n / 2; i += 1) {
            res[2*i] = (i+1);
            res[2*i+1] = -(i+1);
        }
        if (n % 2 == 1) res[n-1] = 0;
        return res;
    }
}
```


Intuitive Method:


```java
class Solution {
    public int[] sumZero(int n) {
        int[] res = new int[n];
        int lValue = - (n/2);
        int rValue = (n/2);
        for(int i = 0; i < n/2; i++) {
            res[i] = lValue++;
        }
        
        for(int r = n - 1; r >= n/2; r--) {
            if(r == n/2 && ((n & 1) != 0)) {
                res[r] = 0;
            } else {
                res[r] =rValue--;
            }
        }
        return res;
    }
}
```

