### 771. Jewels and Stones
https://leetcode.com/problems/jewels-and-stones/

You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
Note:

Solution

S and J will consist of letters and have length at most 50.
The characters in J are distinct.

Approach #1: Brute Force [Accepted]
Intuition and Algorithm

For each stone, check whether it matches any of the jewels. We can check with a linear scan.

```java
class Solution {
    public int numJewelsInStones(String J, String S) {
        int ans = 0;
        for (char s: S.toCharArray()) // For each stone...
            for (char j: J.toCharArray()) // For each jewel...
                if (j == s) {  // If the stone is a jewel...
                    ans++;
                    break; // Stop searching whether this stone 's' is a jewel
                }
        return ans;
    }
}
```
Complexity Analysis

Time Complexity: O(J\text{.length} * S\text{.length}))O(J.length∗S.length)).

Space Complexity: O(1)O(1) additional space complexity in Python. In Java, this can be O(J\text{.length} * S\text{.length}))O(J.length∗S.length)) because of the creation of new arrays.

Approach #2: Hash Set [Accepted]
Intuition and Algorithm

For each stone, check whether it matches any of the jewels. We can check efficiently with a Hash Set.

```java
class Solution {
    public int numJewelsInStones(String J, String S) {
        Set<Character> Jset = new HashSet();
        for (char j: J.toCharArray())
            Jset.add(j);

        int ans = 0;
        for (char s: S.toCharArray())
            if (Jset.contains(s))
                ans++;
        return ans;
    }
}
```
Complexity Analysis

Time Complexity: O(J\text{.length} + S\text{.length})O(J.length+S.length). The O(J\text{.length})O(J.length) part comes from creating J. The O(S\text{.length})O(S.length) part comes from searching S.

Space Complexity: O(J\text{.length})O(J.length).