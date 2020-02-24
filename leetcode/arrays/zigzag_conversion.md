### 6. ZigZag Conversion
https://leetcode.com/problems/zigzag-conversion/

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
```
P   A   H   N
A P L S I I G
Y   I   R
```
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:
```
string convert(string s, int numRows);
```
Example 1:
```
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
```
Example 2:
```
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
```

Solution

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        int rowCount = Math.min(s.length(), numRows);
        ArrayList<StringBuilder> temp = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            temp.add(new StringBuilder());
        }

        char[] charArray = s.toCharArray();
        boolean goDown = true;
        int index = 0;
        for (int i = 0; i < charArray.length; i++) {
            char curr = charArray[i];
            temp.get(index).append(curr);
            index = goDown ? index + 1 : index - 1;
            if (index == 0 || index == numRows - 1) {
                goDown = !goDown;
            }
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : temp) {
            res.append(sb);
        }
        return res.toString();
    }
}
```