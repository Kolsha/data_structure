### [275. H-Index II](https://leetcode.com/problems/h-index-ii/)


Given an array of integers `citations` where `citations[i]` is the number of citations a researcher received for their `ith` paper and `citations` is sorted in an **ascending order**, return compute the researcher's **h-index**.

According to the [definition of h-index on Wikipedia](https://en.wikipedia.org/wiki/H-index): A scientist has an index `h` if `h` of their `n` papers have at least `h` citations each, and the other `n − h` papers have no more than `h` citations each.

If there are several possible values for `h`, the maximum one is taken as the h-index.

You must write an algorithm that runs in logarithmic time.

 

Example 1:
```
Input: citations = [0,1,3,5,6]
Output: 3
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had received 0, 1, 3, 5, 6 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
```
Example 2:
```
Input: citations = [1,2,100]
Output: 2
``` 

Constraints:

- n == citations.length
- $1 <= n <= 10^5$
- 0 <= citations[i] <= 1000
- `citations` is sorted in **ascending order**.

##### Solution

##### Approach 1: Binary Search
binary search, each time check citations[mid]
- case 1: citations[mid] == len-mid, then it means there are citations[mid] papers that have at least citations[mid] citations.<br/><br/>

- case 2: citations[mid] > len-mid, then it means there are citations[mid] papers that have moret than citations[mid] citations, so we should continue searching in the left half.<br/><br/>

- case 3: citations[mid] < len-mid, we should continue searching in the right side.<br/><br/>

After iteration, it is guaranteed that right+1 is the one we need to find (i.e. len-(right+1) papars have at least len-(righ+1) citations)
##### Complexity analysis
- Time complexity:
- Space complexity:

```java
class Solution {

    public int hIndex(int[] citations) {
        int len = citations.length;
	    int lo = 0, hi = len - 1;
	    while (lo <= hi) {
		    int med = (hi + lo) / 2;
		    if (citations[med] == len - med) {
			    return len - med;
		    } else if (citations[med] < len - med) {
			    lo = med + 1;
		    } else { 
			    //(citations[med] > len-med), med qualified as a hIndex,
		        // but we have to continue to search for a higher one.
			    hi = med - 1;
		    }
	    }
	    return len - lo;
    }
}
```