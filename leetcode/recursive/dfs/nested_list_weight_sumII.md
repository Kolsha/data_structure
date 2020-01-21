### 364. Nested List Weight Sum II
https://leetcode.com/problems/nested-list-weight-sum-ii/

Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
```
Input: [[1,1],2,[1,1]]
Output: 8 
Explanation: Four 1's at depth 1, one 2 at depth 2.
```
Example 2:
```
Input: [1,[4,[6]]]
Output: 17 
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
```

Solution
Method 1: DFS find depth, then DFS get sum
```java
/**
 * // This is the interface that allows for creating nested lists. // You should
 * not implement it, or speculate about its implementation public interface
 * NestedInteger { // Constructor initializes an empty nested list. public
 * NestedInteger();
 *
 * // Constructor initializes a single integer. public NestedInteger(int value);
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list. public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // Set this NestedInteger to hold a single integer. public void
 * setInteger(int value);
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to
 * it. public void add(NestedInteger ni);
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list // Return null if this NestedInteger holds a single integer
 * public List<NestedInteger> getList(); }
 */
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int[] depth = new int[1];
        depth[0] = 1;
        // dfs
        findDepth(nestedList, depth, 1);
        System.out.println(depth[0]);

        // get sum
        int[] sum = new int[1];
        getSum(nestedList, sum, depth[0]);

        return sum[0];
    }

    private void getSum(List<NestedInteger> nestedList, int[] sum, int depth) {
        if (nestedList == null || nestedList.isEmpty()) {
            return;
        }

        for (NestedInteger ele : nestedList) {
            if (ele.isInteger()) {
                sum[0] += depth * ele.getInteger();
            } else {
                getSum(ele.getList(), sum, depth - 1);
            }
        }
    }

    public void findDepth(List<NestedInteger> nestedList, int[] depth, int currLevel) {
        if (nestedList == null || nestedList.isEmpty()) {
            return;
        }
        depth[0] = Math.max(depth[0], currLevel);
        for (NestedInteger ele : nestedList) {
            if (ele.isInteger()) {
                continue;
            }
            findDepth(ele.getList(), depth, currLevel + 1);
        }
    }
}
```