### [78. Subsets](https://leetcode.com/problems/subsets/)

Given a set of **distinct** integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:
```
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

Solution

Approach 1: Backtracking

Complexity analysis:
- Time complexity:
- Space complexity:

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, ArrayList<Integer>tmp, int[] nums, int start) {
        res.add(new ArrayList<>(tmp));
        for(int i = start; i < nums.length; i++) {
            // start backtracking
            tmp.add(nums[i]);
            backtrack(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
```