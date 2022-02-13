### Common Patterns in DP

###   Iteration in the recurrence relation

In all the problems we have looked at so far, the recurrence relation is a static equation - it never changes. Recall Min Cost Climbing Stairs. The recurrence relation was:

![image](https://user-images.githubusercontent.com/5952279/153746366-41e89f0b-6078-495f-9b6a-b44bc6ad2737.png)

because we are only allowed to climb 1 or 2 steps at a time. What if the question was rephrased so that we could take up to \text{k}k steps at a time? The recurrence relation would become dynamic - it would be:

![image](https://user-images.githubusercontent.com/5952279/153746375-8dcdadf0-8893-4730-be8e-3da9c2bdacb2.png)

We would need iteration in our recurrence relation.

This is a common pattern in DP problems, and in this chapter, we're going to take a look at some problems using the framework where this pattern is applicable. While iteration usually increases the difficulty of a DP problem, particularly with bottom-up implementations, the idea isn't too complicated. Instead of choosing from a static number of options, we usually add a for-loop to iterate through a dynamic number of options and choose the best one.

Example:
1. Minimum Difficulty of a Job Schedule
2. Word break
3. Longest Increasing Subsequence

### State Transition by Inaction

This is a small pattern that occasionally shows up in DP problems. Here, "doing nothing" refers to two different states having the same value. We're calling it "doing nothing" because often the way we arrive at a new state with the same value as the previous state is by "doing nothing" (we'll look at some examples soon). Of course, a decision making process needs to coexist with this pattern, because if we just had all states having the same value, the problem wouldn't really make sense (\text{dp(i) = dp(i - 1)} ?dp(i) = dp(i - 1)?) It is just that if we are trying to maximize or minimize a score for example, sometimes the best option is to "do nothing", which leads to two states having the same value. The actual recurrence relation would look something like \text{dp(i, j) = max(dp(i - 1, j), ...)}dp(i, j) = max(dp(i - 1, j), ...).

Usually when we "do nothing", it is by moving to the next element in some input array (which we usually use \text{i}i as a state variable for). As mentioned above, this will be part of a decision making process due to some restriction in the problem. For example, think back to House Robber: we could choose to rob or not rob each house we were at. Sometimes, not robbing the house is the best decision (because we aren't allowed to rob adjacent houses), then \text{dp(i) = dp(i - 1)}dp(i) = dp(i - 1).

In the next article, we'll use the framework to solve a problem with this pattern.

Example:

1. Best Time to Buy and Sell Stock IV
2. Best Time to Buy and Sell Stock with Cooldown

### State Reduction
In an earlier chapter when we used the framework to solve [Maximum Score from Performing Multiplication Operations](https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4100/), we mentioned that we could use 2 state variables instead of 3 because we could derive the information the 3rd one would have given us from the other 2. By doing this, we greatly reduced the number of states (as we learned earlier, the number of states is the product of the number of values each state variable can take). In most cases, reducing the number of states will reduce the time and space complexity of the algorithm.

This is called state reduction, and it is applicable for many DP problems, including a few that we have already looked at. State reduction usually comes from a clever trick or observation. Sometimes, as is in the case of Maximum Score from Performing Multiplication Operations, state reduction can result in lower time and space complexity. Other times, only the space complexity will be improved while the time complexity remains the same.

State reduction can also be achieved in the recurrence relation. Recall when we looked at House Robber. Only one state variable was used, \text{i}i, which indicates what house we are currently at. An alternative way to solve the problem would be adding an extra boolean state variable \text{prev}prev that indicates if we robbed the previous house or not, and that would look something like this:

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        @cache
        def dp(i, prev):
            if i < 0:
                return 0
            ans = dp(i - 1, False)
            if not prev:
                ans = max(ans, dp(i - 1, True) + nums[i])
                
            return ans
        
        return dp(len(nums) - 1, False)
```

However, we mentioned in the House Robber article: "We certainly could include this state variable, but we can develop our recurrence relation in a way that makes it unnecessary.". By using a clever recurrence relation and base case, we avoided the need for the extra state variable which reduces the number of states by a factor of 2.

Note: state reductions for space complexity usually only apply to bottom-up implementations, while improving time complexity by reducing the number of state variables applies to both implementations.

When it comes to reducing state variables, it's hard to give any general advice or blueprint. The best advice is to try and think if any of the state variables are related to each other, and if an equation can be created among them. If a problem does not require iteration, there is usually some form of state reduction possible.

Another common scenario where we can improve space complexity is when the recurrence relation is static (no iteration) along one dimension. Let's look back at where we started - Fibonacci. Recall that the i^{th}i 
th
  Fibonacci number can be calculated with the recurrence relation:

![image](https://user-images.githubusercontent.com/5952279/153746572-e44d6389-d428-4ee9-b12a-6cdc1149905c.png)


Because this recurrence relation is static, to calculate the i^{th}i 
th
  Fibonacci number, we only ever care about the previous two numbers. That means if we are using a bottom-up approach to find the n^{th}n 
th
  Fibonacci number and start from the base cases, we don't actually need to use an array and remember every single Fibonacci number.

Let's say we wanted F(100)F(100). Starting from the base cases, we need to calculate every Fibonacci number from F(2)F(2) to F(99)F(99), but at the time of the actual calculation for F(100)F(100), we only care about F(98)F(98) and F(99)F(99). The other 90+ Fibonacci numbers aren't needed, so storing all of them is a waste of space.

![dp](https://user-images.githubusercontent.com/5952279/153746633-c843d39e-60d1-47de-a95f-4cd22c52c907.gif)

Using only two variables instead, we can improve space complexity to O(1)O(1) from O(n)O(n) using an array. The time complexity remains the same.

```java
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        int one_back = 1;
        int two_back = 0;
        
        for (int i = 2; i <= n; i++) {
            int temp = one_back;
            one_back += two_back;
            two_back = temp;
        }
        
        return one_back;
    }
}
```

```python
class Solution:
    def fib(self, n: int) -> int:
        if n <= 1: return n
        one_back = 1
        two_back = 0
        for i in range(2, n + 1):
            temp = one_back
            one_back += two_back
            two_back = temp

        return one_back
```


```
Whenever you notice that values calculated by a DP algorithm are only reused a few times and then never used again, try to see if you can save on space by replacing an array with some variables. A good first step for this is to look at the recurrence relation to see what previous states are used. For example, in Fibonacci, we only refer to the previous two states, so all results before \text{n - 2}n - 2 can be discarded.
```


### Up Next
The next problem, Min Cost Climbing Stairs, was already given as a practice problem in an earlier chapter. This time, try to solve it in O(n)O(n) time with O(1)O(1) space.

Example
1. Min Cost Climbing Stairs

### Counting DP

Most of the problems we have looked at in earlier chapters ask for either the maximum, minimum, or longest of something. However, it is also very common for a DP problem to ask for the number of distinct ways to do something. In fact, one of the first examples we looked at did this - recall that Climbing Stairs asked us to find the number of ways to climb to the top of the stairs.


```
Another term used to describe this class of problems is "counting DP".

```

What are the differences with counting DP? With the maximum/minimum problems, the recurrence relation typically involves a \text{max()}max() or \text{min()}min() function. This is true for all types of problems we have looked at - iteration, multi-dimensional, etc. With counting DP, the recurrence relation typically just sums the results of multiple states together. For example, in Climbing Stairs, the recurrence relation was \text{dp(i) = dp(i - 1) + dp(i - 2)}dp(i) = dp(i - 1) + dp(i - 2). There is no \text{max()}max() or \text{min()}min(), just addition.

Another difference is in the base cases. In most of the problems we have looked at, if the state goes out of bounds, the base case equals \text{0}0. For example, in the Best Time to Buy and Sell Stock questions, when we ran out of transactions or ran out of days to trade, we returned \text{0}0 because we can't make any more profit. In Longest Common Subsequence, when we run out of characters for either string, we return \text{0}0 because the longest common subsequence of any string and an empty string is \text{0}0. With counting DP, the base cases are often not set to \text{0}0. This is because the recurrence relation usually only involves addition terms with other states, so if the base case was set to \text{0}0 then you would only ever add \text{0}0 to itself. Finding these base cases involves some logical thinking - for example, when we looked at Climbing Stairs - we reasoned that there is \text{1}1 way to climb to the first step and \text{2}2 ways to climb to the second step.

These next 3 problems are very good practice problems that ask for the number of distinct ways to do something. Try them on your own, but if you get stuck, come back here for hints:

Example:
276. Paint Fence

![image](https://user-images.githubusercontent.com/5952279/153746773-6de8f21d-4fff-4625-adcf-6f1e2579a00d.png)

518. Coin Change 2

![image](https://user-images.githubusercontent.com/5952279/153746795-bdaac4b3-eb93-4af8-ad50-7e0b99fa7c88.png)


91. Decode Ways

![image](https://user-images.githubusercontent.com/5952279/153746816-34574100-550a-4aae-bd0f-2d1692a446a6.png)


### Kadane's Algorithm

Kadane's Algorithm is an algorithm that can find the maximum sum subarray given an array of numbers in O(n)O(n) time and O(1)O(1) space. Its implementation is a very simple example of dynamic programming, and the efficiency of the algorithm allows it to be a powerful tool in some DP algorithms. If you haven't already solved Maximum Subarray, take a quick look at the problem before continuing with this article - Kadane's Algorithm specifically solves this problem.

Kadane's Algorithm involves iterating through the array using an integer variable \text{current}current, and at each index \text{i}i, determines if elements before index \text{i}i are "worth" keeping, or if they should be "discarded". The algorithm is only useful when the array can contain negative numbers. If \text{current}current becomes negative, it is reset, and we start considering a new subarray starting at the current index.

Pseudocode for the algorithm is below:
```java
// Given an input array of numbers "nums",
1. best = negative infinity
2. current = 0
3. for num in nums:
    3.1. current = Max(current + num, num)
    3.2. best = Max(best, current)

4. return best
```

Line 3.1 of the pseudocode is where the magic happens. If \text{current}current has become less than 0 from including too many or too large negative numbers, the algorithm "throws it away" and resets.

![dp](https://user-images.githubusercontent.com/5952279/153746911-9dc0898e-423b-4336-96cd-a6e8cef9d261.gif)

While usage of Kadane's Algorithm is a niche, variations of Kadane's Algorithm can be used to develop extremely efficient DP algorithms. Try the next two practice problems with this in mind. No framework hints are provided here as implementations of Kadane's Algorithm do not typically follow the framework intuitively, although they are still technically dynamic programming (Kadane's Algorithm utilizes optimal sub-structures - it keeps the maximum subarray ending at the previous position in 

Example
1. Best Time to Buy and Sell Stock
2. Maximum Sum Circular Subarray



















