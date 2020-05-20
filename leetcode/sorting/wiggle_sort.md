### 280. Wiggle Sort

https://leetcode.com/problems/wiggle-sort/

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:
```
Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
```

Thanks for the explanation! I rephrase it with more concise expression.

The rule is:

- If i is even, nums[i] <= nums[i+1]
- If i is odd, nums[i] >= nums[i+1]
Traverse the array, and fix the ordering by swapping unmatched pairs.

Proof:
Suppose sequence [1, 2, ..., i] follows the rule.
If i is odd, we need to prove by swapping, i+1 will follow the rule as well.

- If nums[i] >= nums[i+1], it follows the rule.
- If nums[i] < nums[i+1], swap nums[i] and nums[i+1]
  ```
    Before         After
    e  o  e        e   o  e
   i-1 i i+1      i-1 i+1 i
  ```
Because sequence [1, 2, ..., i] follows the rule, nums[i-1] <= num[i], and nums[i] < nums[i+1]. Therefore, nums[i-1] < nums[i+1]. After swapping, the rule sustains.

The proof of i being even is similar. Q.E.D.

```java
public void wiggleSort(int[] nums) {
  if (nums == null || nums.length < 2) { return; }
  for (int i = 0; i <= nums.length - 2; i++) {
    if ((i % 2 == 0 && nums[i] > nums[i+1]) || (i % 2 != 0 && nums[i] < nums[i+1])) {
      int tmp = nums[i];
      nums[i] = nums[i+1];
      nums[i+1] = tmp;
    }
  }
}
```

#facebook