### 282. Expression Add Operators

https://leetcode.com/problems/expression-add-operators/

Given a string that contains only digits `0-9` and a target value, return all possibilities to add **binary** operators (not unary) `+`, `-`, or `*` between the digits so they evaluate to the target value.

Example 1:
```
Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
```
Example 2:
```
Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
```
Example 3:
```
Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
```
Example 4:
```
Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
```
Example 5:
```
Input: num = "3456237490", target = 9191
Output: []
``` 

Constraints:

- 0 <= num.length <= 10
- num only contain digits.

Solution

```java
class Solution {
    /**
     * When we use dfs to do this question, the most tricky part is that how to deal
     * with multiplication. For every addition and subtraction, we just directly
     * adding or subtracting the new number. However, for multiplication, we should
     * multiply current number and previous number firstly, and then add previous
     * previous number. So we can use a variable preNum to record every previous
     * number in each recursion step. If current recursive call is trying
     * multiplication, we should use previous calculation value subtract previous
     * number, and then adding multiplication result between previous number and
     * current number.
     */
    public List<String> addOperators(String num, int target) {
        if (num.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        dfs(result, num, target, "", 0, 0, 0);
        return result;
    }

    /**
     * @param result:  result list to store valid expressions
     * @param num:     input num candidates
     * @param target:  input target number
     * @param expr:    current expression string
     * @param calcVal: current calculation value
     * @param preNum:  previous number, in order to multiply current number if we
     *                 want to put * between preNum and curNum
     * @param pos:     current index in the input num array
     */
    public void dfs(List<String> result, String num, int target, String expr, long calcVal, long preNum, int pos) {
        if (pos == num.length()) {
            if (calcVal == target) {
                result.add(expr);
            }
            return;
        }

        // start from first index of current position in num string, try all possible
        // length of nums
        for (int i = pos; i < num.length(); i++) {
            // corner case: if current position is 0, we can only use it as a single digit
            // number, should be 0
            // if it is not a single digit number with leading 0, it should be considered as
            // an invalid number
            if (i > pos && num.charAt(pos) == '0') {
                break;
            }
            long curNum = Long.parseLong(num.substring(pos, i + 1));

            // position 0 should be considered individually, since it does not have any
            // operand character before curNum
            if (pos == 0) {
                dfs(result, num, target, expr + curNum, curNum, curNum, i + 1);
            } else {
                dfs(result, num, target, expr + "+" + curNum, calcVal + curNum, curNum, i + 1);
                dfs(result, num, target, expr + "-" + curNum, calcVal - curNum, -curNum, i + 1);
                // trick part: to calculate multiplication, we should subtract previous number,
                // and then add current
                // multiplication result to the subtraction result
                dfs(result, num, target, expr + "*" + curNum, calcVal - preNum + preNum * curNum, preNum * curNum, i + 1);
            }
        }
    }
}
```