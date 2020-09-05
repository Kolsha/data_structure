### 17. Letter Combinations of a Phone Number

https://leetcode.com/problems/letter-combinations-of-a-phone-number/

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
![](http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png)

Example:
```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

Solution

Approach 1: Backtracking

Complexity analysis:
- Time complexity: <code>O(3<sup>N</sup> ×4<sup>M</sup>)</code> where N is the number of digits in the input that maps to 3 letters (e.g. 2, 3, 4, 5, 6, 8) and M is the number of digits in the input that maps to 4 letters (e.g. 7, 9), and `N + M` is the total number digits in the input.
- Space complexity: <code>O(3<sup>N</sup> × 4<sup>M</sup>)</code> since one has to keep <code>3<sup>N</sup> × 4<sup>M</sup></code> solutions.

```java
class Solution {
    private final static char[][] mapping = new char[][] {
        {}, // 0
        {}, // 1
        {'a', 'b', 'c'}, // 2
        {'d', 'e', 'f'}, // 3
        {'g', 'h', 'i'}, // 4
        {'j', 'k', 'l'}, // 5
        {'m', 'n', 'o'}, // 6
        {'p', 'q', 'r', 's'}, // 7
        {'t', 'u', 'v'}, // 8
        {'w', 'x', 'y', 'z'} // 9
    };
    
    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) {
            return res;
        }
        recursiveHelper(digits, 0, new StringBuilder(), res);
        return res;
    }
    
    private void recursiveHelper(String digits, int index, StringBuilder sb, ArrayList<String> res) {
        if(sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        
        int num = digits.charAt(index) - '0';
        char[] charArray = mapping[num];
        for(int i = 0; i < charArray.length; i++) {
            sb.append(charArray[i]);
            recursiveHelper(digits, index + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```
// todo optimized one
