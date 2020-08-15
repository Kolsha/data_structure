From:<br/>https://web.stanford.edu/class/cs97si/04-dynamic-programming.pdf<br/>
Useful info:<br/>https://www.geeksforgeeks.org/dynamic-programming/<br/>

https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/m2G1pAq0OO0

Dynamic Programming
===
1. <a class="nav-link" href="#dp">Dynamic Programming</a>
2. <a class="nav-link" href="#1_dimen_dp">1-dimensional DP</a>
3. <a class="nav-link" href="#2_dimen_dp">2-dimensional DP</a>
4. <a class="nav-link" href="#interval_dp">Interval DP</a>
5. <a class="nav-link" href="#tree_dp">Tree DP</a>
6. <a class="nav-link" href="#subset_dp">Subset DP</a>
7. <a class="nav-link" href="#kadane">Kadane’s Algorithm</a>

<h4 id="dp">Dynamic Programming</h4>

#### What is DP?
1. Wikipedia definition: “method for solving complex problems
by breaking them down into simpler subproblems”

2. This definition will make sense once we see some examples<br/>
– Actually, we’ll only see problem solving examples today.

#### Steps for Solving DP Problems
1. Define subproblems
   
2. Write down the recurrence that relates subproblems
   
3. Recognize and solve the base cases


<h4 id="1_dimen_dp">1-dimensional DP Example</h4>

**Problem 1:**<br/> given n, find the number of different ways to write n as the sum of 1, 3, 4.<br/>

Example: 
```
for n = 5, the answer is 6
5 = 1 + 1 + 1 + 1 + 1
  = 1 + 1 + 3
  = 1 + 3 + 1
  = 3 + 1 + 1
  = 1 + 4
  = 4 + 1
```
Let's solve this problem ~ Define subproblems
- Let D<sub>n</sub> be the number of ways to write n as the sum of 1, 3, 4

Find the recurrence
- Since n = bunch of 1s + bunch of 3s + bunch of 4s <br/>Therefore, <br/>DP(n) = DP(series that end with 1) +<br/> DP(series that end with 3) +<br/> DP(series that end with 4)

- Say we want to find num series end up with 1<br/>
&rarr; n = x<sub>1</sub> + x<sub>2</sub> ... + x <sub>m</sub> + 1<br/>
&rarr; n - 1 = x<sub>1</sub> + x<sub>2</sub> ... + x <sub>m</sub>
<br/>Thus, the number of sums that end with 1 is equal to D<sub>n-1</sub>
<br/>Same thing applies to 3s and 4s cases.

- Recurrence is then
D<sub>n</sub> = D<sub>n−1</sub> + D<sub>n−3</sub> + D<sub>n−4</sub>

Solve the base cases
- D<sub>0</sub> = 1
- D<sub>n</sub> = 0 for all negative n
- Alternatively, can set: D<sub>0</sub> = D<sub>1</sub> = D<sub>2</sub> = 1, and D<sub>3</sub> = 2
&rarr; We’re basically done!

Implementation from : https://www.geeksforgeeks.org/count-ofdifferent-ways-express-n-sum-1-3-4/
```java
class Solution { 

	static int countWays(int n) {
		int DP[] = new int[n + 1]; 

		// base cases 
		DP[0] = DP[1] = DP[2] = 1; 
		DP[3] = 2; 

		// iterate for all values from 4 to n 
		for (int i = 4; i <= n; i++) {
			DP[i] = DP[i - 1] + DP[i - 3] + DP[i - 4];
                }
		return DP[n]; 
	}
} 
```
Similar problem: 
1. [Climbing Stairs](./climbing_stairs.md)
2. [Maximum Subarray](./maximum_subarray.md)

**Problem 2:** [Tiling with Dominoes ( aka "Tri Tiling" )](https://www.geeksforgeeks.org/tiling-with-dominoes/)
<br/>Given a 3 x n board, find the number of ways to fill it with 2 x 1 dominoes.
<br/>**Example 1**<br/> Following are all the 3 possible ways to fill up a 3 x 2 board.
<br/>![](https://media.geeksforgeeks.org/wp-content/uploads/example1-3-300x113.png)<br/>
<br/>**Example 2**
Here is one possible way of filling a 3 x 8 board. You have to find all the possible ways to do so.<br/>
![](https://media.geeksforgeeks.org/wp-content/uploads/example_3x8-300x113.jpg)

Let's solve this problem ~ Define subproblems
<br/>At any point while filling the board, there are three possible states that the last column can be in:<br/>

- An =  No. of ways to completely fill a 3 x n board. (We need to find this)
- Bn =  No. of ways to fill a 3 x n board with top corner in last column not filled.
- Cn =  No. of ways to fill a 3 x n board with bottom corner in last column not filled.

![](https://media.geeksforgeeks.org/wp-content/uploads/possibleStates-1-1024x327.jpg)
![](https://media.geeksforgeeks.org/wp-content/uploads/impossibleStates-300x127.jpg)

Finding Reccurences<br/>
Note: Even though B<sub>n</sub> and C<sub>n</sub> are different states, they will be equal for same ‘n’. i.e B<sub>n</sub> = C<sub>n</sub>
Hence, we only need to calculate one of them.

Calculating A<sub>n</sub>:<br/>
A<sub>n</sub> = A<sub>n - 2</sub> + B<sub>n - 1</sub> + C<sub>n - 1</sub><br/>
= A<sub>n - 2</sub> + 2 * (B<sub>n - 1</sub>)

![](https://media.geeksforgeeks.org/wp-content/uploads/An-1024x186.jpg)

Calculating B<sub>n</sub> :<br/>
B<sub>n</sub> = A<sub>n - 1</sub> + B<sub>n - 2</sub>
![](https://media.geeksforgeeks.org/wp-content/uploads/Bn-1024x186.jpg)

Final Recursive Relations:
1. A<sub>n</sub> = A<sub>n - 2</sub> + 2 * (B<sub>n - 1</sub>)
2. B<sub>n</sub> = A<sub>n - 1</sub> + B<sub>n - 2</sub>

Implementation:
```java
class Solution {

	static int countWays(int n) {
		int[] A = new int[n+1];
		int[] B = new int[n+1];
		A[0] = 1;
        A[1] = 0;
		B[0] = 0;
        B[1] = 1;
		for (int i = 2; i <= n; i++) { 
			A[i] = A[i - 2] + 2 * B[i - 1]; 
			B[i] = A[i - 1] + B[i - 2]; 
		}
		return A[n]; 
	}
}
```
**Similar problem:** [Domino and tromino tiling](./domino_and_tromino_tiling.md) 
<h4 id="2_dimen_dp">2-dimensional DP</h4>
2-dimensional DP Example<br/>
Problem: given two strings x and y, find the longest common subsequence (LCS) and print its length. <br/>

Example:
```
– x: ABCBDAB
– y: BDCABC
– “BCAB” is the longest subsequence found in both sequences, so
the answer is 4
```
Solving the LCS Problem<br/>

Define subproblems<br/>
- Let D<sub>ij</sub> be the length of the LCS of x<sub>1</sub>...x<sub>i</sub> and y<sub>1</sub>...y<sub>j</sub>
- Find the recurrence
   - If x<sub>i</sub> = y<sub>j</sub> , they both contribute to the LCS<br/> &rarr; D<sub>ij</sub> = D<sub>i−1,j−1</sub> + 1
– Otherwise, either x<sub>i</sub> or y<sub>j</sub> does not contribute to the LCS, so one can be dropped<br/>
&rarr; D<sub>ij</sub> = max { D<sub>i−1, j</sub> ,    D<sub>i, j−1</sub> }
– Find and solve the base cases: D<sub>i0</sub> = D<sub>0j</sub> = 0

Implementation
```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] lcs = new int[text1.length() + 1][text2.length() + 1];
        int result = 0;
        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                if (i == 0 || j == 0) {
                    lcs[i][j] = 0;
                } else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    result = Math.max(lcs[i][j], result);
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        return result;
    }
}
```
Similar problem:<br/> 
[Leetcode:  Longest Common Subsequence](./longest_common_subsequence.md),<br/>
[GeeksForGeeks: Longest common substring](https://www.geeksforgeeks.org/longest-common-substring-dp-29/)
<h4 id="interval_dp">Interval DP</h4>
Interval DP Example<br/>

Problem: given a string x = x<sub>1...n</sub>, find the minimum number of characters that need to be inserted to make it a palindrome.

Example:
```
– x: Ab3bd
– Can get “dAb3bAd” or “Adb3bdA” by inserting 2 characters
(one ‘d’, one ‘A’)
```
Solution:<br/>
Define subproblems<br/>
- Let D<sub>ij</sub> be the minimum number of characters that need to be inserted to make x<sub>i...j</sub> into a palindrome.

Find the recurrence<br/>
- Consider a shortest palindrome y<sub>1...k</sub> containing x<sub>i...j</sub>
- Either y<sub>1</sub> = x<sub>i</sub> or y<sub>k</sub> = x<sub>j</sub> (why?)
- y<sub>2...k−1</sub> is then an optimal solution for x<sub>i+1...j</sub> or x<sub>i...j−1</sub> or
x<sub>i+1...j−1</sub> &rarr; Last case possible only if y<sub>1</sub> = y<sub>k</sub> = x<sub>i</sub> = x<sub>j</sub>
- D<sub>ij</sub> = 1 + min { D<sub>i+1 j</sub> , D<sub>i j-1</sub> }, when x<sub>i</sub> != x<sub>j</sub>;<br/>
D<sub>ij</sub> = D<sub>i+1 j-1</sub>, when x<sub>i</sub> == x<sub>j</sub>

- Find and solve the **base cases**: D<sub>ii</sub> = 0 = D<sub>i i-1</sub> for all i

Implementation
```java
class Solution {
    static int findMinInsertionsDP(char str[], int n) {
        // Create a table of size n*n. table[i][j]
        // will store minumum number of insertions
        // needed to convert str[i..j] to a palindrome.
        int table[][] = new int[n][n];
        int i, j, gap;

        // todo: 看不懂…再想想吧…
        // Fill the table…
        for (gap = 1; gap < n; ++gap) {
            for (i = 0, j = gap; j < n; ++i, ++j) {
                table[i][j] = (str[i] == str[j]) ? table[i + 1][j - 1]
                        : (Integer.min(table[i][j - 1], table[i + 1][j]) + 1);

            }
        }
        // Return minimum number of insertions
        // for str[0..n-1]
        return table[0][n - 1];
    }
}

```



<h4 id="tree_dp">Tree DP</h4>
ref: https://www.geeksforgeeks.org/graph-coloring-applications/

Problem: Given a tree, color nodes black as many as possible without coloring two adjacent nodes.<br/>

Subproblems:
- First, we arbitrarily decide the root node r
- Bv: the optimal solution for a subtree having v as the root,
where we color v black
- Wv: the optimal solution for a subtree having v as the root,
where we don’t color v
- Answer is max{Br, Wr}

Find the recurrence<br/>
- Crucial observation: once v’s color is determined, subtrees can be solved independently.
<br/>
- If v is colored, its children must not be colored. 
Bv = 1 + $\sum_{u ∈ children(v)}$Wu
<br/>
- If v is not colored, its children can have any color
Wv = 1 + $\sum_{u ∈ children(v)}$ max{Bu, Wu}

Base cases: leaf nodes

Similar problems:<br/>
[Ways to color a skewed tree such that parent and child have different colors](https://www.geeksforgeeks.org/ways-to-color-a-skewed-tree-such-that-parent-and-child-have-different-colors/)<br/>

[Color tree with minimum colors such that colors of edges incident to a vertex are different](https://www.geeksforgeeks.org/color-tree-with-minimum-colors-such-that-colors-of-edges-incident-to-a-vertex-are-different/)

[Unique Binary Search Trees](./unique_binary_search_trees.md)
<h4 id="subset_dp">Subset DP</h4>

Problem:<br/>
Given a weighted graph with `n` nodes, find the shortest path that visits every node exactly once (Traveling Salesman Problem) <br/>

Define subproblems
- D<sub>S,v</sub>: the length of the optimal path that visits every node in
the set S exactly once and ends at v
- There are approximately n * 2<sup>n</sup> subproblems. (todo: explain why? )
- Answer is min<sub>v∈V</sub> D<sub>V,v</sub>, where V is the given set of nodes.
  
Let’s solve the base cases first
- For each node v, D<sub>{v},v</sub> = 0

 Find the recurrence
- Consider a path that visits all nodes in S exactly once and ends at v
- Right before arriving v, the path comes from some u in
S − {v}
- And that subpath has to be the optimal one that covers
S − {v}, ending at u
- We just try all possible candidates for u:<br/>
D<sub>S,v</sub> = $\min$<sub>u∈S−{v}</sub>(D<sub>S - {v}, u</sub> + cost(u, v))

When working with subsets, it’s good to have a nice
representation of sets

Idea: Use an integer to represent a set
- Concise representation of subsets of small integers {0, 1, . . .}
- If the ith (least significant) digit is 1, i is in the set
- If the ith digit is 0, i is not in the set
- e.g., 19 = 010011<sub>(2)</sub> in binary represent a set {0, 1, 4} (// todo what???)

Using Bitmasks (//todo give more examples)
- Union of two sets x and y: x | y
- Intersection: x & y
- Symmetric difference: x ˆ y
- Singleton set {i}: 1 << i
- Membership test: x & (1 << i) != 0

[Shortest Path Visiting All Nodes](./shortest_path_visiting_all_nodes.md)<br/>
[Travelling Salesman Problem](https://www.geeksforgeeks.org/travelling-salesman-problem-set-1/)


<a href="https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d"><h4 id="kadane">Kadane’s Algorithm</h4></a>
// todo

from:
https://zh.wikipedia.org/wiki/%E6%9C%80%E5%A4%A7%E5%AD%90%E6%95%B0%E5%88%97%E9%97%AE%E9%A2%98

Kadane算法掃描一次整個數列的所有數值，在每一個掃描點計算以該點數值為結束點的子數列的最大和（正數和）。該子數列由兩部分組成：以前一個位置為結束點的最大子數列、該位置的數值。因為該算法用到了「最佳子結構」（以每個位置為終點的最大子數列都是基於其前一位置的最大子數列計算得出），該算法可看成動態規劃的一個例子。

算法可用如下Python代碼實現：
```python
def max_subarray(A):
    max_ending_here = max_so_far = A[0]
    for x in A[1:]:
        max_ending_here = max(x, max_ending_here + x)
        max_so_far = max(max_so_far, max_ending_here)
    return max_so_far
```
該問題的一個變種是：如果數列中含有負數元素，允許返回長度為零的子數列。該問題可用如下代碼解決：
```python
def max_subarray(A):
    max_ending_here = max_so_far = 0
    for x in A:
        max_ending_here = max(0, max_ending_here + x)
        max_so_far = max(max_so_far, max_ending_here)
    return max_so_far
```
ref:

[Kadane’s Algorithm Explained](https://hackernoon.com/kadanes-algorithm-explained-50316f4fd8a6)

[Why Kadane's algorithm works?](https://afshinm.name/2018/06/24/why-kadane-algorithm-works/)
[Kadane’s Algorithm — (Dynamic Programming) — How and Why does it Work?](https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d)

Similar Problem:

[Maximum Subarray](./../divide_and_conquer/max_subarray.md)

[Best Time to Buy and Sell Stock](./best_time_to_buy_and_sell_stock.md)