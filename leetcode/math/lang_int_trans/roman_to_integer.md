### [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)


Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
```
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

- I can be placed before V (5) and X (10) to make 4 and 9. 
- X can be placed before L (50) and C (100) to make 40 and 90. 
- C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:
```
Input: "III"
Output: 3
```
Example 2:
```
Input: "IV"
Output: 4
```
Example 3:
```
Input: "IX"
Output: 9
```
Example 4:
```
Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
```
Example 5:
```
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

##### Solution

**Is there only one valid representation for each number?**

This is more relevant to the other question, [Integer to Roman](./integer_to_roman.md), however we'll still briefly look at it now.

Given that the representation for `3` is `III`, it could seem natural that the representation for `15` is `VVV`, because that would be `5 + 5 + 5`. However, it's actually `XV`, which is `10 + 5`. How are you even supposed to know which is correct?

The trick is to use the "biggest" symbols you can. Because `X` is bigger than `V`, we should use an `X` first and then make up the remainder with a single `V`, giving `XV`.

We'll talk more about this in the [Integer to Roman](./integer_to_roman.md) article. This question is a lot simpler because there's only one logical way of converting from a Roman Numeral to an Integer. This is also why this question is labelled as "easy", whereas the other is labelled as "medium".

**A few more examples**

If you're not very familiar with Roman Numerals, work through these examples and then have another go at writing your own algorithm before reading the rest of this solution article.

What is `CXVII` as an integer?

Recall that `C = 100, X = 10, V = 5, and I = 1.` Because the symbols are ordered from most significant to least, we can simply add the symbols, i.e. `C + X + V + I + I = 100 + 10 + 5 + 1 + 1 = 117`.

What is `DXCI` as an integer?

Recall that D = 500.

Now, notice that this time the symbols are not ordered from most significant to least—the X and C are out of numeric order. Because of this, we subtract the value of X (10) from the value of C (100) to get 90.

So, going from left to right, we have `D + (C - X) + I = 500 + 90 + 1 = 591`.

What is `CMXCIV` as an integer?

Recall that M = 1000.

The symbols barely look sorted at all here—from left-to-right we have 100, 1000, 10, 100, 1, 5. Do not panic though, we just need to look for each occurrence of a smaller symbols preceding a bigger symbol. The first, third, and fifth symbols are all smaller than their next symbol. Therefore they are all going to be subtracted from their next.

The first two symbols are CM. This is M - C = 1000 - 100 = 900
The second two symbols are XC. This is C - X = 100 - 10 = 90.
The final two symbols are IV. This is V - I = 5 - 1 = 4.
Like we did above, we add these together. (M - C) + (C - X) + (V - I) = 900 + 90 + 4 = 994.

##### Approach 1: HashMap

Complexity analysis:
- Time complexity : O(1)
As there is a finite set of roman numerals, the maximum number possible number can be 3999, which in roman numerals is MMMCMXCIX. As such the time complexity is O(constant).

- Space complexity: O(1)
Because only a constant number of single-value variables are used, the space complexity is O(1).

```java
class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        map.put('Z', Integer.MIN_VALUE); // for null value

        int res = 0, i = 0;
        while(i < s.length()) {
            char curChar = s.charAt(i);
            char nextChar = i + 1 < s.length()? s.charAt(i+1): 'Z';
            if(map.get(curChar) < map.get(nextChar)) {
                res += map.get(nextChar) - map.get(curChar);
                i = i + 2;
            } else {
                res += map.get(curChar);
                i = i + 1;
            }
        }
        return res;
    }
}
```