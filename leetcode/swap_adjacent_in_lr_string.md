### 777. Swap Adjacent in LR String


https://leetcode.com/problems/swap-adjacent-in-lr-string/

In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

 

Example 1:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: true
Explanation: We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Example 2:

Input: start = "X", end = "L"
Output: false
Example 3:

Input: start = "LLR", end = "RRL"
Output: false
Example 4:

Input: start = "XL", end = "LX"
Output: true
Example 5:

Input: start = "XLLR", end = "LXLX"
Output: false
 

Constraints:

1 <= start.length <= 104
start.length == end.length
Both start and end will only consist of characters in 'L', 'R', and 'X'.


Solution

Approach 1: Invariant
Intuition

Let's think of 'L' and 'R' as people facing left and right standing on one line, and 'X' as an empty space on that line.

We can ask: what invariants (conditions that remain true after making any move) there are. This is natural for any question that involves transforming some state and asking whether a final state is possible.

Algorithm

One invariant is that 'L' and 'R' characters in the string can never cross each other - people walking on the line cannot pass through each other. That means the starting and target strings must be the same when reading only the 'L' and 'R's. With respect to some starting string, let's call such a target string "solid" (since the people don't pass through each other).

Additionally, the n-th 'L' can never go to the right of it's original position, and similarly the n-th 'R' can never go to the left of it's original position. We'll call this "accessibility". Formally, if (i_1, i_2, \cdots )(i 
1
​	
 ,i 
2
​	
 ,⋯) and (i^{'}_1, i^{'}_2, \cdots )(i 
1
′
 
​	
 ,i 
2
′
 
​	
 ,⋯) are the positions of each 'L' character in the source and target string, and similarly for (j_i \cdots ), (j^{'}_1 \cdots )(j 
i
​	
 ⋯),(j 
1
′
 
​	
 ⋯) and positions of 'R' characters, then we will say a target string is accessible if i_k \geq i^{'}_ki 
k
​	
 ≥i 
k
′
 
​	
  and j_k \leq j^{'}_kj 
k
​	
 ≤j 
k
′
 
​	
 .

This shows being solid and accessible are necessary conditions. With an induction on the number of people, we can show that they are sufficient conditions. The basic idea of the proof is this: A person on the end either walks away from the others, or walks towards. If they walk away, then let them walk first and it is true; if they walk towards, then let them walk last and it is true (by virtue of the target being solid).

With these ideas in hand, we'll investigate the two properties individually. If they are both true, then the answer is True, otherwise it is False.

```java
class Solution {
    public boolean canTransform(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", "")))
            return false;

        int t = 0;
        for (int i = 0; i < start.length(); ++i)
            if (start.charAt(i) == 'L') {
                while (end.charAt(t) != 'L') t++;
                if (i < t++) return false;
            }

        t = 0;
        for (int i = 0; i < start.length(); ++i)
            if (start.charAt(i) == 'R') {
                while (end.charAt(t) != 'R') t++;
                if (i > t++) return false;
            }

        return true;
    }
}
```

Complexity Analysis

Time Complexity: O(N)O(N), where NN is the length of start and end.

Space Complexity: O(N)O(N). The replacement operation is O(N)O(N), while the remaining operations use O(1)O(1) additional space. We could amend the replace part of our algorithm to use pointers so as to reduce the total complexity to O(1)O(1).

Approach 2: Two Pointers
Intuition and Algorithm

Following the explanation in Approach #1, the target string must be solid and accessible.

We use two pointers to solve it. Each pointer i, j points to an index of start, end with start[i] != 'X', end[j] != 'X'.

Then, if start[i] != end[j], the target string isn't solid. Also, if start[i] == 'L' and i < j, (or start[i] == 'R' and i > j), the string is not accessible.

In our Python implementation, we use generators to handle moving i, j to the next index where start[i] != 'X', end[j] != 'X'.

```java
class Solution {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        
        // count X in start and end --> should be the same
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (start.charAt(i) == 'X') count++;
            if (end.charAt(i) == 'X') count--;
        }
        if (count != 0) return false;

        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && end.charAt(j) == 'X') j++;

            // i and j are the indices representing the next
            // occurrences of non-X characters
            if (i == n || j == n)
                return i == n && j == n;

            if (start.charAt(i) != end.charAt(j)) return false;
            if (start.charAt(i) == 'L' && i < j) return false;
            if (start.charAt(i) == 'R' && i > j) return false;
            
            i++;
            j++;
        }
        
        return true;
    }
}
```

Complexity Analysis

Time Complexity: O(N)O(N), where NN is the length of start and end.

Space Complexity: O(1)O(1).