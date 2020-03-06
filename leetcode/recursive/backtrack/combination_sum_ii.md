### 40. Combination Sum II

https://leetcode.com/problems/combination-sum-ii/

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:
```
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```
Example 2:
```
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
```

Solution

```java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, res, target, 0, 0, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int[] nums, List<List<Integer>> res, int target, int curr, int start,
            ArrayList<Integer> temp) {
        if (curr >= target) {
            if (curr == target) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            backtrack(nums, res, target, curr + nums[i], i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
```