### 293. Flip Game

https://leetcode.com/problems/flip-game/

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: `+` and `-`, you and your friend take turns to flip two consecutive `"++"` into `"--"`. The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

Example:
```
Input: s = "++++"
Output: 
[
  "--++",
  "+--+",
  "++--"
]
```
Note: If there is no valid move, return an empty list `[]`.

Solution

```java
class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        ArrayList<String> res = new ArrayList<>();
        char[] tmp = s.toCharArray(); // this function takes O(n) time complexity
        for(int i = 0; i+1 < s.length(); i++) {
            if(s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                tmp[i] = '-';
                tmp[i+1] = '-';
                res.add(String.valueOf(tmp));
                tmp[i] = '+'; // reset the value back
                tmp[i+1] = '+'; 
            }
        }
        return res;
    }
}
```