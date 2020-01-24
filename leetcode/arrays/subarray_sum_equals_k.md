### 560. Subarray Sum Equals K
https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
```
Input:nums = [1,1,1], k = 2
Output: 2
```
Note:
1. The length of the array is in range [1, 20,000].
2. The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

Solution
```java
class Solution {
    // Sliding window -- No, contains negative number
    // hashmap + preSum
    /*
     * 1. Hashmap<sum[0,i - 1], frequency> 2. sum[i, j] = sum[0, j] - sum[0, i - 1]
     * --> sum[0, i - 1] = sum[0, j] - sum[i, j] k sum hashmap-key --> hashmap-key =
     * sum - k 3. now, we have k and sum. As long as we can find a sum[0, i - 1], we
     * then get a valid subarray which is as long as we have the hashmap-key, we
     * then get a valid subarray 4. Why don't map.put(sum[0, i - 1], 1) every time ?
     * if all numbers are positive, this is fine if there exists negative number,
     * there could be preSum frequency > 1
     */
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
```

Method 2
```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
```


Method 2
```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
```