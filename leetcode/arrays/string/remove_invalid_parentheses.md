### 301. Remove Invalid Parentheses

https://leetcode.com/problems/remove-invalid-parentheses/

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:
```
Input: "()())()"
Output: ["()()()", "(())()"]
```
Example 2:
```
Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
```
Example 3:
```
Input: ")("
Output: [""]
```

Solution

Method 1: BFS

Time complexity: O(n * 2 ^ n)

Regarding the time complexity, I think one way we can think about the search space is as a power subset of the original string. So it includes all possible substrings from 0 character to N(number of chars in the input string) characters. So the possibilities are 2^n. (we either pick a character or don't pick it) For each subset we check if it is a valid string so it becomes n*(2^n)


Space complexity: O(2 ^ n)
Worst case, going throu all substrings

In Java
```java
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        ArrayList<String> res = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(s);
        visited.add(s);

        // if longer string is found, then we will stop find the answer from shorter
        // strings
        boolean found = false;
        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (isValid(str)) {
                res.add(str);
                found = true;
                continue;
            }

            if (found) {
                continue;
            }

            // generate all possible solutions
            StringBuilder sb = null;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != '(' && str.charAt(i) != ')') {
                    continue;
                }
                sb = new StringBuilder(str);
                String nextStr = sb.deleteCharAt(i).toString();
                if (visited.add(nextStr)) {
                    queue.offer(nextStr);
                }
            }
        }
        return res;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            if (s.charAt(i) == ')') {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }
        return count == 0;
    }
}
```

In Python
```python
class Solution(object):
    def isValid(s):
        """
        check if String s is a vilid parentheses string
        """
        count = 0
        for i in range(0, len(s)):
            if s[i] == '(':
                count += 1
            elif s[i] == ')':
                if count == 0:
                    return False
                count -= 1
        return count == 0

    def removeInvalidParentheses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        def isValid(s):
            """
            check if String s is a vilid parentheses string
            """
            count = 0
            for i in range(0, len(s)):
                if s[i] == '(':
                    count += 1
                elif s[i] == ')':
                    if count == 0:
                        return False
                    count -= 1
            return count == 0

        res = []

        visited = set()  # set for visited
        queue = []      # list for bfs queue

        queue.append(s)
        visited.add(s)
        found = False
        while len(queue) != 0:
            str = queue.pop(0)
            if isValid(str):
                res.append(str)
                found = True

            if found:
                continue

            for i in range(0, len(str)):
                if str[i] != '(' and str[i] != ')':
                    continue

                nextStr = str[0:i] + str[i+1:]
                if nextStr not in visited:
                    queue.append(nextStr)
                    visited.add(nextStr)
        return res
```
// todo
https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution

Method 2: DFS