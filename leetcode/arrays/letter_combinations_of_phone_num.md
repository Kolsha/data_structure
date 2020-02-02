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
Method 1: Not optimized Approach
```java
class Solution {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        char[][] dial = new char[10][];
        dial[2] = new char[] { 'a', 'b', 'c' };
        dial[3] = new char[] { 'd', 'e', 'f' };
        dial[4] = new char[] { 'g', 'h', 'i' };
        dial[5] = new char[] { 'j', 'k', 'l' };
        dial[6] = new char[] { 'm', 'n', 'o' };
        dial[7] = new char[] { 'p', 'q', 'r', 's' };
        dial[8] = new char[] { 't', 'u', 'v' };
        dial[9] = new char[] { 'w', 'x', 'y', 'z' };

        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            int numChar = digits.charAt(i) - '0';
            ArrayList<String> temp = getPerm(dial[numChar], result);
            result.clear();
            result.addAll(temp);
        }

        return result;
    }

    private ArrayList<String> getPerm(char[] dial, List<String> holder) {
        ArrayList<String> result = new ArrayList<>();
        for (String str : holder) {
            for (char ele : dial) {
                String value = str + ele;
                result.add(value);
            }
        }
        return result;
    }
}
```

// todo optimized one
