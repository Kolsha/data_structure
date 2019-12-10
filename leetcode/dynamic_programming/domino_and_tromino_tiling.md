### 790. Domino and Tromino Tiling
https://leetcode.com/problems/domino-and-tromino-tiling/

We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.<br/>

⬛⬛ &rarr; domino

⬛<br/>
⬛⬛ &rarr; tromino

Given N, how many ways are there to tile a 2 x N board? `Return your answer modulo 10^9 + 7`.

(In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)
Example:
```
Input: 3
Output: 5
```
Note:<br/> N  will be in range [1, 1000].



**Solution:**<br/>
Define subproblems
可以发现所求总方法数 A(N)   
`2 x N` tiles: <br>
⬛...⬛⬛⬛<br>
⬛...⬛⬛⬛ <br>
是以下几种最终状态的总和<br/>
A(N-1): 最后放的是一块直立的 Domino。<br/> 
🔲...🔲🔲⬛<br>
🔲...🔲🔲⬛<br>

A(N-2): 最后放的是两块横著的 Domino。<br/>
🔲...🔲⬛⬛<br>
🔲...🔲⬜⬜<br>

B(N-1): 最后放的是缺口在朝左上的 Tromino。<br/>
🔲...🔲🔲⬛<br>
🔲...🔲⬛⬛<br>

C(N-1): 最后放的是缺口朝左下的 Tromino。<br/>
🔲...🔲⬛⬛<br>
🔲...🔲🔲⬛<br>

Reccurences will be:<br/>
A(N) = A(N-1) + A(N-2) + B(N - 1) + C(N - 1)<br/>
因为 B(N) 和 C(N) 是对称的，所以方法数相等，B(N) = C(N)<br>
&rarr; A(N) = A(N-1) + A(N-2) + 2 * B(N-1)

Base case:<br>
A(0) = 1, A(1) = 1, A(2) = 2<br/>
B(1) = 0, B(1) = 0, B(2) = 1<br/>

Implementation:
```java
class Solution {
    public int numTilings(int N) {
        if (N < 3) {
            return N;
        }

        int[] A = new int[N + 1];
        int[] B = new int[N + 1];
        int k = 1000000007;
        A[0] = 1;
        A[1] = 1;
        A[2] = 2;
        B[1] = 0;
        B[2] = 1;
        for (int i = 3; i < N + 1; i++) {
            A[i] = (int) ((A[i - 1] + A[i - 2] + 2L * B[i - 1]) % k); // 2L part is very tricky here
            B[i] = (A[i - 2] + B[i - 1]) % k;
        }
        return A[N];
    }
}
```