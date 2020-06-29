### 658. Find K Closest Elements

https://leetcode.com/problems/find-k-closest-elements/

Given a `sorted array arr`, two integers `k` and `x`, find the `k` closest elements to `x` in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

 

Example 1:
```
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
```
Example 2:
```
Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
``` 

Constraints:

- 1 <= k <= arr.length
- 1 <= arr.length <= 10^4
- Absolute value of elements in the array and x will not exceed 10<sup>4</sup>


Solution

Approach 1: Binary Search and Two Pointers
Algorithm

The original array has been sorted so we can take this advantage by the following steps.

1. If the target `x` is less or equal than the first element in the sorted array, the first `k` elements are the result.

2. Similarly, if the target `x` is more or equal than the last element in the sorted array, the last `k` elements are the result.

3. Otherwise, we can use binary search to find the `index` of the element, which is equal (when this list has `x`) or a little bit larger than `x` (when this list does not have it). Then set `low` to its left `k-1` position, and high to the right `k-1` position of this `index` as a start. The desired k numbers must in this rang [index-k-1, index+k-1]. So we can shrink this range to get the result using the following rules.
   - If `low` reaches the lowest index `0` or the `low` element is closer to `x` than the `high` element, decrease the `high` index.

   - If `high` reaches to the highest index `arr.size()-1` or it is nearer to `x` than the `low` element, increase the `low` index.

   - The looping ends when there are exactly k elements in [low, high], the subList of which is the result.

##### Complexity Analysis

<p>Time complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>log</mi><mo>⁡</mo><mi>n</mi><mo>+</mo><mi>k</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(\log n +k)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mop">lo<span style="margin-right:0.01389em;">g</span></span><span class="mspace" style="margin-right:0.16666666666666666em;"></span><span class="mord mathdefault">n</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span><span class="mbin">+</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span></span><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.03148em;">k</span><span class="mclose">)</span></span></span></span>. <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>log</mi><mo>⁡</mo><mi>n</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(\log n)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mop">lo<span style="margin-right:0.01389em;">g</span></span><span class="mspace" style="margin-right:0.16666666666666666em;"></span><span class="mord mathdefault">n</span><span class="mclose">)</span></span></span></span> is for the time of binary search, while <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>k</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(k)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.03148em;">k</span><span class="mclose">)</span></span></span></span> is for shrinking the index range to k elements.</p>

<p>Space complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>k</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(k)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.03148em;">k</span><span class="mclose">)</span></span></span></span>. It is to generate the required sublist.</p>

```java
public class Solution {
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
		int n = arr.size();
		if (x <= arr.get(0)) {
			return arr.subList(0, k);
		} else if (arr.get(n - 1) <= x) {
			return arr.subList(n - k, n);
		} else {
			int index = Collections.binarySearch(arr, x);
			if (index < 0)
				index = -index - 1;
			int low = Math.max(0, index - k - 1), high = Math.min(arr.size() - 1, index + k - 1);

			while (high - low > k - 1) {
				if (low < 0 || (x - arr.get(low)) <= (arr.get(high) - x))
					high--;
				else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x))
					low++;
				else
					System.out.println("unhandled case: " + low + " " + high);
			}
			return arr.subList(low, high + 1);
		}
	}
}
```

Approach 2: PriorityQueue / [heap sort](https://brilliant.org/wiki/heap-sort/#complexity-of-heapsort)

Time Complexity: O(NlogN)

Space Complexity: O(N)
```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // "If there is a tie, the smaller elements are always preferred."
        Comparator<Integer> cmp = (num1, num2) ->  Math.abs(num1 - x) != Math.abs(num2 - x)? Math.abs(num1 - x) - Math.abs(num2 - x) : num1 - num2;
        PriorityQueue<Integer> heap = new PriorityQueue<>(cmp);
        
        for(int num: arr) {
            heap.offer(num);
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        while(k-- > 0) {
            res.add(heap.poll());
        }
        // "The result should also be sorted in ascending order."
        Collections.sort(res);
        return res;
    }
}
```