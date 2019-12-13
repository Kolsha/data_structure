from:<br/>https://web.stanford.edu/class/cs97si/04-dynamic-programming.pdf

Dynamic Programming
===
#### Dynamic Programming
1. <a class="nav-link" href="#dp">Dynamic Programming</a>
2. <a class="nav-link" href="#1_dimen_dp">1-dimensional DP</a>
3. <a class="nav-link" href="#2_dimen_dp">2-dimensional DP</a>
4. <a class="nav-link" href="#interval_dp">Interval DP</a>
5. <a class="nav-link" href="#tree_dp">Tree DP</a>
6. <a class="nav-link" href="#subset_dp">Subset DP</a>

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
			DP[i] = DP[i - 1] + DP[i - 3] 
					+ DP[i - 4]; 
        }
		return DP[n]; 
	} 
} 
```
Similar problem: [Climbing Stairs](./climbing_stairs.md)<br/>

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
<h4 id="tree_dp">Tree DP</h4>
<h4 id="subset_dp">Subset DP</h4>
