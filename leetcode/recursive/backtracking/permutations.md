### [46. Permutations](https://leetcode.com/problems/permutations/)


Given a collection of distinct integers, return all possible permutations.

Example:
```
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```



Solution

Approach 1: Backtracking

[Backtracking problem general approach](https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning))

Complexity analysis:
- Time complexity: O(n * n!)
- Space complexity:

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, ArrayList<Integer> tmp, int[] nums) {
        if(tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(tmp.contains(nums[i])) {
                continue;
            }
            // start backtracking
            tmp.add(nums[i]);
            backtrack(res, tmp, nums);
            tmp.remove(tmp.size() - 1);
        }
    }
}
```