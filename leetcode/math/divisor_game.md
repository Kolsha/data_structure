### 1025. Divisor Game

https://leetcode.com/problems/divisor-game/

Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number `N` on the chalkboard.  On each player's turn, that player makes a move consisting of:

Choosing any `x` with `0 < x < N` and `N % x == 0`.
Replacing the number N on the chalkboard with N - x.
Also, if a player cannot make a move, they lose the game.

Return True if and only if Alice wins the game, assuming both players play optimally.


**Example 1:**
```
Input: 2
Output: true
Explanation: Alice chooses 1, and Bob has no more moves.
```
**Example 2:**
```
Input: 3
Output: false
Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
``` 

Note:

1 <= N <= 1000

**Solution**

from:

https://leetcode.com/problems/divisor-game/discuss/274606/JavaC%2B%2BPython-return-N-2-0

Conclusion

If `N` is even, can win.<br/>
If `N` is odd, will lose.

Recursive Prove （Top-down)

1. If `N` is even.<br/>
We can choose `x = 1`.
The opponent will get `N - 1`, which is a odd.
Reduce to the case odd and he will lose.<br/>
2. If `N` is odd,
   <br/>2.1 If N = 1, lose directly.
   <br/>2.2 We have to choose an odd x.
The opponent will get N - x, which is a even.
Reduce to the case even and he will win.

So the N will change odd and even alternatively until N = 1.

Mathematical Induction Prove （Bottom-up)
N = 1, lose directly
N = 2, will win choosing x = 1.
N = 3, must lose choosing x = 1.
N = 4, will win choosing x = 1.
....

For N <= n, we have find that:
If N is even, can win.
If N is odd, will lose.

For the case N = n + 1
If N is even, we can win choosing x = 1,
give the opponent an odd number N - 1 = n,
force him to lose,
because we have found that all odd N <= n will lose.

If N is odd, there is no even x that N % x == 0.
As a result, we give the opponent a even number N - x,
and the opponent can win,
because we have found that all even N <= n can win.

Now we prove that, for all N <= n,
If N is even, can win.
If N is odd, will lose.

```java
class Solution {
    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }
}
```
