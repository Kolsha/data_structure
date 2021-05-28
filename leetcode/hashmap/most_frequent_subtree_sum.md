### [508. Most Frequent Subtree Sum](https://leetcode.com/problems/most-frequent-subtree-sum/)

Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.

The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).

 

Example 1:

![](https://assets.leetcode.com/uploads/2021/04/24/freq1-tree.jpg)
```
Input: root = [5,2,-3]
Output: [2,-3,4]
```
Example 2:
![](https://assets.leetcode.com/uploads/2021/04/24/freq2-tree.jpg)
```
Input: root = [5,2,-5]
Output: [2]
``` 

Constraints:

- The number of nodes in the tree is in the range $[1, 10^4]$.
- $-10^5$ <= Node.val <= $10^5$


### Solution

#### Approach 1: Recursive + HashMap

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        getAllTreeSum(map, root);
        
        int max = 0;
        ArrayList<Integer> res = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() > max) {
                max = entry.getValue();
                res.clear();
                res.add(entry.getKey());
                
            } else if (entry.getValue() == max) {
                res.add(entry.getKey());
            }
        }
        // https://stackoverflow.com/a/23688547/1145976
        return res.stream().mapToInt(i -> i).toArray();
    }
    
    private int getAllTreeSum(HashMap<Integer, Integer> map, TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int sum = root.val;
        if(root.left!=null) {
            sum += getAllTreeSum(map, root.left);
        }
        if(root.right!=null) {
            sum += getAllTreeSum(map, root.right);
        }
        
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
```

Optimized:

```java
Class Solution {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
    int maxCount = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> res = new ArrayList<>();
        for (int s : count.keySet()) {
            if (count.get(s) == maxCount)
                res.add(s);
        }
        return res.stream().mapToInt(i->i).toArray();
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int s = dfs(root.left) + dfs(root.right) + root.val;
        count.put(s, count.getOrDefault(s, 0) + 1);
        maxCount = Math.max(maxCount, count.get(s));
        return s;
    }
}
```
