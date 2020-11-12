### 171. Excel Sheet Column Number

https://leetcode.com/problems/excel-sheet-column-number/

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:
```
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
```
Example 1:
```
Input: "A"
Output: 1
```
Example 2:
```
Input: "AB"
Output: 28
```
Example 3:
```
Input: "ZY"
Output: 701
``` 

Constraints:

- 1 <= s.length <= 7
- s consists only of uppercase English letters.
- s is between "A" and "FXSHRXW".

Solution

Approach 1: Right to left

Let's say we want to get the value of title **AZZC**. This can be broken down as 'A*\*\*' + 'Z**' + 'Z*' + 'C'. Here, the *s represent smaller blocks. * means a block of 1-character titles. ** means a block of 2-character titles. There are 261 titles in a block of 1-character titles. There are 262 titles in a block of 2-character titles.

Scanning AZZC from right to left while accumulating results:

1. First, ask the question, what the value of 'C' is:
    - 'C' = 3 x 260 = 3 x 1 = 3
    - result = 0 + 3 = 3

2. Then, ask the question, what the value of 'Z*' is:
    - 'Z*' = 26 x 261 = 26 x 26 = 676
    - result = 3 + 676 = 679

3. Then, ask the question, what the value of      'Z**' is:
    - 'Z**' = 26 x 262 = 26 x 676 = 17576
    - result = 679 + 17576 = 18255
4. Finally, ask the question, what the value of 'A***' is:
    - 'A***' = 1 x 263 = 1 x 17576 = 17576
    - result = 18255 + 17576 = 35831


Complexity analysis:
- Time complexity: O(N)
- Space complexity: O(1)

```java
class Solution {
    public int titleToNumber(String s) {
        int result = 0;

        Map < Character, Integer > alpha_map = new HashMap < > ();
        for (int i = 0; i < 26; i++) {
            int c = i + 65; // Decimal 65 in ASCII corresponds to char 'A'
            alpha_map.put((char) c, i + 1);
        }

        int n = s.length();
        for (int i = 0; i < n; i++) {
            char cur_char = s.charAt(n - 1 - i);
            result += (alpha_map.get(cur_char) * (Math.pow(26, i)));
        }
        return result;
    }
}
```