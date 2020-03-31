### 373. Find K Pairs with Smallest Sums

https://leetcode.com/problems/find-k-pairs-with-smallest-sums/

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
```
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
```
Example 2:
```
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
```
Example 3:
```
Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
```

Solution

Method 1: Priority Queue w/ 2 for loops

Time complexity: O(n<sup>2</sup>)

Space complexity: O(n)

```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
        Set<String> set = new HashSet<>();
        Comparator<List<Integer>> comp = new Comparator<>() {
            @Override
            public int compare(List<Integer> a, List<Integer> b) {
                return (a.get(0) + a.get(1)) - (b.get(0) + b.get(1));
            }
        };
        
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(comp);
        
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(nums1[i]);
                temp.add(nums2[j]);
                queue.offer(temp);
            }
        }
        
        ArrayList<List<Integer>> res = new ArrayList<>();
        while(k-- > 0 && !queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }
}
```