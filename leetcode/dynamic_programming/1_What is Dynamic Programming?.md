### What is Dynamic Programming?

Dynamic Programming (DP) is a programming paradigm that can systematically and efficiently explore all possible solutions to a problem. As such, it is capable of solving a wide variety of problems that often have the following characteristics:

1. The problem can be broken down into "overlapping subproblems" - smaller versions of the original problem that are re-used multiple times.

2. The problem has an "optimal substructure" - an optimal solution can be formed from optimal solutions to the overlapping subproblems of the original problem.
As a beginner, these theoretical definitions may be hard to wrap your head around. Don't worry though - at the end of this chapter, we'll talk about how to practically spot when DP is applicable. For now, let's look a little deeper at both characteristics.

The Fibonacci sequence is a classic example used to explain DP. For those who are unfamiliar with the Fibonacci sequence, it is a sequence of numbers that starts with 0, 1, and each subsequent number is obtained by adding the previous two numbers together.

If you wanted to find the n^{th}n 
th
  Fibonacci number F(n)F(n), you can break it down into smaller subproblems - find F(n - 1)F(n−1) and F(n - 2)F(n−2) instead. Then, adding the solutions to these subproblems together gives the answer to the original question, F(n - 1) + F(n - 2) = F(n)F(n−1)+F(n−2)=F(n), which means the problem has optimal substructure, since a solution F(n)F(n) to the original problem can be formed from the solutions to the subproblems. These subproblems are also overlapping - for example, we would need F(4)F(4) to calculate both F(5)F(5) and F(6)F(6).

These attributes may seem familiar to you. Greedy problems have optimal substructure, but not overlapping subproblems. Divide and conquer algorithms break a problem into subproblems, but these subproblems are not overlapping (which is why DP and divide and conquer are commonly mistaken for one another).

Dynamic programming is a powerful tool because it can break a complex problem into manageable subproblems, avoid unnecessary recalculation of overlapping subproblems, and use the results of those subproblems to solve the initial complex problem. DP not only aids us in solving complex problems, but it also greatly improves the time complexity compared to brute force solutions. For example, the brute force solution for calculating the Fibonacci sequence has exponential time complexity, while the dynamic programming solution will have linear time complexity. Throughout this explore card, you will gain a better understanding of what makes DP so powerful. In the next section, we'll discuss the two main methods of implementing a DP algorithm.


### Top-down and Bottom-up

There are two ways to implement a DP algorithm:

1. Bottom-up, also known as tabulation.

2. Top-down, also known as memoization.

Let's take a quick look at each method.



### Bottom-up (Tabulation)

Bottom-up is implemented with iteration and starts at the base cases. Let's use the Fibonacci sequence as an example again. The base cases for the Fibonacci sequence are F(0) = 0F(0)=0 and F(1) = 1F(1)=1. With bottom-up, we would use these base cases to calculate F(2)F(2), and then use that result to calculate F(3)F(3), and so on all the way up to F(n)F(n).

```java
// Pseudocode example for bottom-up

F = array of length (n + 1)
F[0] = 0
F[1] = 1
for i from 2 to n:
    F[i] = F[i - 1] + F[i - 2]
```


### Top-down (Memoization)
Top-down is implemented with recursion and made efficient with memoization. If we wanted to find the n^{th}n 
th
  Fibonacci number F(n)F(n), we try to compute this by finding F(n - 1)F(n−1) and F(n - 2)F(n−2). This defines a recursive pattern that will continue on until we reach the base cases F(0) = F(1) = 1F(0)=F(1)=1. The problem with just implementing it recursively is that there is a ton of unnecessary repeated computation. Take a look at the recursion tree if we were to find F(5)F(5):
  
  ![image](https://user-images.githubusercontent.com/5952279/153697830-974dc33f-af65-42c9-8220-a0a40c1438d2.png)


Notice that we need to calculate F(2)F(2) three times. This might not seem like a big deal, but if we were to calculate F(6)F(6), this entire image would be only one child of the root. Imagine if we wanted to find F(100)F(100) - the amount of computation is exponential and will quickly explode. The solution to this is to memoize results.

```
memoizing a result means to store the result of a function call, usually in a hashmap or an array, so that when the same function call is made again, we can simply return the memoized result instead of recalculating the result.

```

After we calculate F(2)F(2), let's store it somewhere (typically in a hashmap), so in the future, whenever we need to find F(2)F(2), we can just refer to the value we already calculated instead of having to go through the entire tree again. Below is an example of what the recursion tree for finding F(6)F(6) looks like with and without memoization:
