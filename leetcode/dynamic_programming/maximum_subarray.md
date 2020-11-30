### 53. Maximum Subarray
https://leetcode.com/problems/maximum-subarray/

Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
```
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
```
Solution


Method 1: Dynamic Programming

1. Find Subproblem
Assume <b>dp<sub>i</sub></b> which is the subarray ending with the index `i` has the greatest value:
<b>dp<sub>i</sub> = max {dp<sub>i-1</sub> + nums[i], nums[i]}</b>

2. Base cases
   dp<sub>0</sub> = nums[0]


Complexity analysis:
- Time complexity: O(n)
- Space complexity: O(n)


In Java
```java
class Solution {
    public int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++){
            dp[i] = Math.max(A[i] + dp[i - 1] , A[i]);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}
```

In Python
```python
class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        dp = [0]*len(nums)
        dp[0] = nums[0]
        res = nums[0]
        for i in range(1, len(nums)):
            if dp[i - 1] > 0:
                dp[i] = dp[i - 1] + nums[i]
            else:
                dp[i] = nums[i]
            res = max(res, dp[i])
        return res
```

Method 2: Divide and conquer
// todo figure this out

Time complexity: O(nlogn)

Space complexity: O(n)? Recursive?

```java
public class Solution {
    public int maxSubArray(int[] nums) {
        /*
         * Divide-and-conquer method. The maximum summation of subarray can only exists
         * under following conditions: 1. the maximum summation of subarray exists in
         * left half. 2. the maximum summation of subarray exists in right half. 3. the
         * maximum summation of subarray exists crossing the midpoints to left and
         * right. 1 and 2 can be reached by using recursive calls to left half and right
         * half of the subarraies. Condition 3 can be found starting from the middle
         * point to the left, then starting from the middle point to the right. Then
         * adds up these two parts and return.
         * 
         * T(n) = 2*T(n/2) + O(n) this program runs in O(nlogn) time
         */

        int maxsum = subArray(nums, 0, nums.length - 1);
        return maxsum;
    }

    private int subArray(int[] A, int left, int right) {
        if (left == right) {
            // base case
            return A[left];
        }
        int mid = left + (right - left) / 2;
        int leftsum = subArray(A, left, mid); // left part of the subarray sum, condition 1
        int rightsum = subArray(A, mid + 1, right); // right part of the subarray sum, condition 2
        int middlesum = midSubArray(A, left, mid, right); // cross part of the subarray sum, condition 3
        // System.out.println(leftsum);
        // System.out.println(rightsum);
        // System.out.println(middlesum);

        // following if condition will return middlesum if lost the "=" under the
        // conditon of leftsum = rightsum, which may be problematic if leftsum =
        // rightsum < middlesum.
        // for example: [-1, -1, -2, -2]
        if (leftsum >= rightsum && leftsum >= middlesum) {
            return leftsum;
        }
        if (rightsum >= leftsum && rightsum >= middlesum) {
            return rightsum;
        }
        return middlesum;
    }

    private int midSubArray(int[] A, int left, int mid, int right) {
        int leftsum = Integer.MIN_VALUE;
        int rightsum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += A[i];
            if (leftsum < sum) {
                leftsum = sum;
            }
        }

        sum = 0;
        for (int j = mid + 1; j <= right; j++) {
            sum += A[j];
            if (rightsum < sum) {
                rightsum = sum;
            }
        }

        return leftsum + rightsum;
    }
}
```

Approach 1: Divide and Conquer
Intuition

The problem is a classical example of divide and conquer approach, and can be solved with the algorithm similar with the merge sort.

Let's follow here a solution template for the divide and conquer problems :

Define the base case(s).

Split the problem into subproblems and solve them recursively.

Merge the solutions for the subproblems to obtain the solution for the original problem.

Algorithm

maxSubArray for array with n numbers:

If n == 1 : return this single element.

left_sum = maxSubArray for the left subarray, i.e. for the first n/2 numbers (middle element at index (left + right) / 2 always belongs to the left subarray).

right_sum = maxSubArray for the right subarray, i.e. for the last n/2 numbers.

cross_sum = maximum sum of the subarray containing elements from both left and right subarrays and hence crossing the middle element at index (left + right) / 2.

Merge the subproblems solutions, i.e. return max(left_sum, right_sum, cross_sum).

![](https://leetcode.com/problems/maximum-subarray/Figures/53/dc.png)

```java
class Solution {
  public int crossSum(int[] nums, int left, int right, int p) {
    if (left == right) return nums[left];

    int leftSubsum = Integer.MIN_VALUE;
    int currSum = 0;
    for(int i = p; i > left - 1; --i) {
      currSum += nums[i];
      leftSubsum = Math.max(leftSubsum, currSum);
    }

    int rightSubsum = Integer.MIN_VALUE;
    currSum = 0;
    for(int i = p + 1; i < right + 1; ++i) {
      currSum += nums[i];
      rightSubsum = Math.max(rightSubsum, currSum);
    }

    return leftSubsum + rightSubsum;
  }

  public int helper(int[] nums, int left, int right) {
    if (left == right) return nums[left];

    int p = (left + right) / 2;

    int leftSum = helper(nums, left, p);
    int rightSum = helper(nums, p + 1, right);
    int crossSum = crossSum(nums, left, right, p);

    return Math.max(Math.max(leftSum, rightSum), crossSum);
  }

  public int maxSubArray(int[] nums) {
    return helper(nums, 0, nums.length - 1);
  }
}
```

Complexity Analysis

Time complexity : \mathcal{O}(N \log N)O(NlogN). Let's compute the solution with the help of master theorem T(N) = aT\left(\frac{b}{N}\right) + \Theta(N^d)T(N)=aT( 
N
b
​	
 )+Θ(N 
d
 ). The equation represents dividing the problem up into aa subproblems of size \frac{N}{b} 
b
N
​	
  in \Theta(N^d)Θ(N 
d
 ) time. Here one divides the problem in two subproblemes a = 2, the size of each subproblem (to compute left and right subtree) is a half of initial problem b = 2, and all this happens in a \mathcal{O}(N)O(N) time d = 1. That means that \log_b(a) = dlog 
b
​	
 (a)=d and hence we're dealing with case 2 that means \mathcal{O}(N^{\log_b(a)} \log N) = \mathcal{O}(N \log N)O(N 
log 
b
​	
 (a)
 logN)=O(NlogN) time complexity.

Space complexity : \mathcal{O}(\log N)O(logN) to keep the recursion stack.


Approach 2: Greedy
Intuition

The problem to find maximum (or minimum) element (or sum) with a single array as the input is a good candidate to be solved by the greedy approach in linear time. One can find the examples of linear time greedy solutions in our articles of
Super Washing Machines, and Gas Problem.

Pick the locally optimal move at each step, and that will lead to the globally optimal solution.

The algorithm is general and straightforward: iterate over the array and update at each step the standard set for such problems:

current element

current local maximum sum (at this given point)

global maximum sum seen so far.

![](https://leetcode.com/problems/maximum-subarray/Figures/53/greedy.png)

```java
class Solution {
  public int maxSubArray(int[] nums) {
    int n = nums.length;
    int currSum = nums[0], maxSum = nums[0];

    for(int i = 1; i < n; ++i) {
      currSum = Math.max(nums[i], currSum + nums[i]);
      maxSum = Math.max(maxSum, currSum);
    }
    return maxSum;
  }
}
```
Complexity Analysis

Time complexity : \mathcal{O}(N)O(N) since it's one pass along the array.

Space complexity : \mathcal{O}(1)O(1), since it's a constant space solution.


Approach 3: Dynamic Programming (Kadane's algorithm)
Intuition

The problem to find sum or maximum or minimum in an entire array or in a fixed-size sliding window could be solved by the dynamic programming (DP) approach in linear time.

There are two standard DP approaches suitable for arrays:

Constant space one. Move along the array and modify the array itself.

Linear space one. First move in the direction left->right, then in the direction right->left. Combine the results. Here is an example.

Let's use here the first approach since one could modify the array to track the current local maximum sum at this given point.

Next step is to update the global maximum sum, knowing the local one.

![](https://leetcode.com/problems/maximum-subarray/Figures/53/dp.png)

```java
class Solution {
  public int maxSubArray(int[] nums) {
    int n = nums.length, maxSum = nums[0];
    for(int i = 1; i < n; ++i) {
      if (nums[i - 1] > 0) nums[i] += nums[i - 1];
      maxSum = Math.max(nums[i], maxSum);
    }
    return maxSum;
  }
}
```

Complexity Analysis

Time complexity : \mathcal{O}(N)O(N) since it's one pass along the array.

Space complexity : \mathcal{O}(1)O(1), since it's a constant space solution.

