### 528. Random Pick with Weight

https://leetcode.com/problems/random-pick-with-weight/

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

- 1 <= w.length <= 10000
- 1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.

Example 1:
```
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
```
Example 2:
```
Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
```

Solution

`pickIndex which randomly picks an index in proportion to its weight` &rarr;
In case someone still not understand it here is another example:

For the array of {1, 3, 4, 6}
the pickIndex() call will have

1/(1+3+4+6) = 1/14 = ~7% chance of picking index 0;

3/14 = 21% change of picking index 1;

4/14 = 29% change of picking index 2;

and 6/14 = 43% of picking index 3.

```java
class Solution {
    private Random rnd = new Random();
    private int max;
    private int[] arr;
    public Solution(int[] w) {
        arr = new int[w.length];
        arr[0] = w[0];
        max = w[0];
        for(int i = 1; i < w.length; i++) {
            arr[i] = arr[i-1] + w[i];
            max+=w[i];
        }
        
    }
    
    public int pickIndex() {
        int target = rnd.nextInt(max) + 1;
        int ret = Arrays.binarySearch(arr, target);
        if(ret < 0) {
            ret = -ret - 1;
        }
        return ret;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
```
#facebook