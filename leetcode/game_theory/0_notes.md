From: https://web.stanford.edu/class/cs97si/05-combinatorial-games.pdf

[Combinatorial Games](https://www.geeksforgeeks.org/introduction-to-combinatorial-game-theory/)
===
1. <a class="nav-link" href="#simple_games">Simple Games</a>
2. <a class="nav-link" href="#mini_algo">Minimax Algorithm</a>
3. <a class="nav-link" href="#nim_game">Nim Game</a>
4. <a class="nav-link" href="#nimbers">Grundy Numbers(Nimbers)</a>

<h4 id="simple_games">Simple Games</h4>

- Turn-based competitive multi-player games.
- Can be a simple win-or-lose game, or can involve points.
- Everyone has perfect information.
- Each turn, the player changes the current “state” using a valid “move”.
- At some states, there are no valid moves , then the current player immediately loses at these states.

#### Combinatorial Game Example
- Settings: There are n stones in a pile. Two players take turns and remove 1 or 3 stones at a time. The one who takes the last stone wins. Find out the winner if both players play perfectly.
- State space: Each state can be represented by the number of remaining stones in the pile.
- Valid moves from state x: x → (x − 1) or x → (x − 3), as long as the resulting number is nonnegative.
- State 0 is the losing state.
- No cycles in the state transitions &rarr; Can solve the problem bottom-up (DP).
- A player wins if there is a way to force the opponent to lose &rarr; Conversely, we lose if there is no such a way
- State x is a winning state (W) if:
1. (x − 1) is a losing state,
2. OR (x − 3) is a losing state
- Otherwise, state x is a losing state (L)

- DP table for small values of n:<br/>

| n  | 0  | 1  | 2  | 3  | 4  | 5  | 6  | 7  |   
|---|---|---|---|---|---|---|---|---|---|
|  W / L | L  | W  | L  | W  | L  | W  | L  | W  | 

- See a pattern?

- Let's prove our conjecture:<br/>
  `If n is odd, the first player wins. If n is even, the
second player wins.`
- Holds true for the base case n = 0
- In general,

    1. If n is odd, we can remove one stone and give the opponent an event number of stones.
    2. If n is even, no matter what we choose, we have to give an odd number of sones to the opponent. 
#### Similiar Problem:
[Stone Game](./stone_game.md), [Divisor Game](./divisor_game.md)
<h4 id="mini_algo">Minimax Algorithm</h4>

- Settings: a competitive zero-sum two-player game
- Zero-sum: if the first player’s score is x, then the other player gets −x
- Each player tries to maximize his/her own score
- Both players play perfectly
- Can be solved using a minimax algorithm

#### [Minimax Algorithm](https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/)
- Recursive algorithm that decides the best move for the current player at a given state
- Define f(S) as the optimal score of the current player who
starts at state S
- Let T<sub>1</sub>, T<sub>2</sub>, . . . , T<sub>m</sub> be states can be reached from S using a
single move
- Let T be the state that minimizes f(T<sub>i</sub>)
- Then, f(S) = −f(T) &rarr; Intuition: minimizing the opponent’s score maximizes my score

#### [Memoization](https://www.geeksforgeeks.org/memoization-1d-2d-and-3d/)
- A technique used to avoid repeated calculations in recursive
functions
- High-level idea: take a note (memo) of the return value of a function call. When the function is called with the same argument again, return the stored result
- Each subproblem is solved at most once &rarr; Some may not be solved at all!
### Memoization using std::map
```c++
map<int, int> memo;
int fib(int n)
{
    if(memo.count(n)) return memo[n];
    if(n <= 1) return n;
    return memo[n] = fib(n - 1) + fib(n - 2);
}
```

#### [Minimax Algorithm Pseudocode](https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/)

- Given state S, want to compute f(S)
- If we know f(S) already, return it
- Set return value x ← −∞
- For each valid next state T:
- Update return value x ← max{x, −f(T)}
- Write a memo f(S) = x and return x

#### Possible Extensions
- The game is not zero-sum:
    1.  Each player wants to maximize his own score
    2.  Each player wants to maximize the difference between his score and the opponent’s
- There are more than two players
- All of above can be solved using a similar idea

<h4 id="nim_game">Nim Game</h4>

- Settings: There are n piles of stones. Two players take turns. Each player chooses a pile, and removes any number of stones from the pile. The one who takes the last stone wins. Find out the winner if both players play perfectly
- Can’t really use DP if there are many piles, because the state space is huge
- Starts with heaps of 3, 4, 5 stones &rarr; We will call them heap A, heap B, and heap C
- Alice takes 2 stones from A: (1, 4, 5)
- Bob takes 4 from C: (1, 4, 1)
- Alice takes 4 from B: (1, 0, 1)
- Bob takes 1 from A: (0, 0, 1)
- Alice takes 1 from C and wins: (0, 0, 0)

#### Solution to Nim
- Given heaps of size n1, n2, . . . , nm
- The first player wins if and only if the nim-sum
n1 ⊕ n2 ⊕ · · · ⊕ nm is nonzero (⊕ is bitwise XOR operator)
- Why?
    - If the nim-sum is zero, then whatever the current player does, the nim-sum of the next state is nonzero
    - If the nim-sum is nonzero, it is possible to force it to become zero (not obvious, but true)

<h4 id="nimbers"><a href="https://www.geeksforgeeks.org/combinatorial-game-theory-set-3-grundy-numbersnimbers-and-mex/">Grundy Numbers</a></h4>

Similar Problem:

[Game of Nim](https://www.geeksforgeeks.org/combinatorial-game-theory-set-2-game-nim/), 
[Nim Game](./nim_game.md)
#### Playing Multiple Games at Once
- Suppose that multiple games are played at the same time. At each turn, the player chooses a game and make a move. You lose if there is no possible move. We want to determine the winner.

#### Grundy Numbers (Nimbers)
- For each game, we compute its Grundy number
- The first player wins if and only if the XOR of all the Grundy numbers is nonzero
- For example, the Grundy number of a one-pile version of the nim game is equal to the number of stones in the pile (we will see this again later)
- Let’s see how to compute the Grundy numbers for general games

- Let S be a state, and T1, T2, . . . , Tm be states can be reached
from S using a single move
- The Grundy number g(S) of S is the smallest nonnegative integer that doesn’t appear in {g(T<sub>1</sub>), g(T<sub>2</sub>), . . . , g(T<sub>m</sub>)}
    - Note: the Grundy number of a losing state is 0
    - Note: I made up the notation g(·). Don’t use it in other places

#### Grundy Numbers Example

- Consider a one-pile nim game
- g(0) = 0, because it is a losing state
- State 0 is the only state reachable from state 1, so g(1) is the
smallest nonnegative integer not appearing in {g(0)} = {0}.
Thus, g(1) = 1
- Similarly, g(2) = 2, g(3) = 3, and so on
- Grundy numbers for this game is then g(n) = n &rarr; That’s how we got the nim-sum solution

#### Tips for Solving Game Problems
- If the state space is small, use memoization
- If not, print out the result of the game for small test data and look for a pattern &rarr; `This actually works really well!`
- Try to convert the game into some nim-variant
- If multiple games are played at once, use Grundy numbers

Similar Problems:

