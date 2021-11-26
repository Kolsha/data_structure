### [39. Combination Sum](https://leetcode.com/problems/combination-sum/)


Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

- All numbers (including target) will be positive integers.
- The solution set must not contain duplicate combinations.

Example 1:
```
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
```
Example 2:
```
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

### Solution

[Method 1: Backtracking questions general approaches](https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning))

```java
class Solution {
    ArrayList<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, 0, target, new ArrayList<Integer>(), 0);
        return res;
    }

    private void backtrack(int[] candidates, int cur, int target, List<Integer> temp, int start) {
        if (cur >= target) {
            if (cur == target) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            backtrack(candidates, cur + candidates[i], target, temp, i);
            temp.remove(temp.size() - 1);
        }
    }
}
```