### 1299. Replace Elements with Greatest Element on Right Side

https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/

Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.

 

Example 1:
```
Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]
``` 

Constraints:

- 1 <= arr.length <= 10^4
- 1 <= arr[i] <= 10^5


Solution:

Optimal Method:

In Java
```java
class Solution {
    public int[] replaceElements(int[] arr) {
        int[] newresult = new int[arr.length];
        int max = -1;
        for(int i = arr.length - 1; i > -1; i--){
            newresult[i] = max;
            if(arr[i] > max)
                max = arr[i];
        }
        return newresult;
    }
}
```

Method 1: 

In Java
```java
class Solution {
    public int[] replaceElements(int[] arr) {
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            int max = -1;
            for(int j = i+ 1; j < arr.length; j++) {
                max = Math.max(max, arr[j]);
            }
            res[i] = max;
        }
        return res;
    }
}
```