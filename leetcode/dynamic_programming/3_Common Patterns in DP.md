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
