### [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:
```
Input: "()"
Output: true
```
Example 2:
```
Input: "()[]{}"
Output: true
```
Example 3:
```
Input: "(]"
Output: false
```
Example 4:
```
Input: "([)]"
Output: false
```
Example 5:
```
Input: "{[]}"
Output: true
```

##### Solution

##### Approach 1: Stack
An interesting property about a valid parenthesis expression is that a sub-expression of a valid expression should also be a valid expression. (Not every sub-expression) e.g.
![](https://leetcode.com/problems/valid-parentheses/Figures/20/20-Valid-Parentheses-Recursive-Property.png)

Also, if you look at the above structure carefully, the color coded cells mark the opening and closing pairs of parenthesis. The entire expression is valid, but sub portions of it are also valid in themselves. This lends a sort of a recursive structure to the problem. For e.g. Consider the expression enclosed within the two green parenthesis in the diagram above. The opening bracket at index 1 and the corresponding closing bracket at index 6.

What if whenever we encounter a matching pair of parenthesis in the expression, we simply remove it from the expression?

Let's have a look at this idea below where remove the smaller expressions one at a time from the overall expression and since this is a valid expression, we would be left with an empty string in the end.

The stack data structure can come in handy here in representing this recursive structure of the problem. We can't really process this from the inside out because we don't have an idea about the overall structure. But, the stack can help us process this recursively i.e. from outside to inwards.

Let us have a look at the algorithm for this problem using stacks as the intermediate data structure.

###### Algorithm

1. Initialize a stack S.
2. Process each bracket of the expression one at a time.
3. If we encounter an opening bracket, we simply push it onto the stack. This means we will process it later, let us simply move onto the sub-expression ahead.
4. If we encounter a closing bracket, then we check the element on top of the stack. If the element at the top of the stack is an opening bracket of the same type, then we pop it off the stack and continue processing. Else, this implies an invalid expression.
5. In the end, if we are left with a stack still having elements, then this implies an invalid expression.

We'll have a look a dry run for the algorithm and then move onto the implementation.

##### Complexity analysis:

- Time complexity : $O(n)$ because we simply traverse the given string one character at a time and push and pop operations on a stack take $O(1)$ time.

- Space complexity : $O(n)$ as we push all opening brackets onto the stack and in the worst case, we will end up pushing all the brackets onto the stack. e.g. ((((((((((.

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch(ch) {
                case '(':
                    stack.push(ch);
                    break;
                
                case ')':
                    if(stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
                
                case '[':
                    stack.push(ch);
                    break;

                case ']':
                    if(stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
                
                case '{':
                    stack.push(ch);
                    break;

                case '}':
                    if(stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;

                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}
```

##### Python

```python
        stack = []
        dict = {"]":"[", "}":"{", ")":"("}
        for char in s:
            if char in dict.values():
                stack.append(char)
            elif char in dict.keys():
                if stack == [] or dict[char] != stack.pop():
                    return False
            else:
                return False
        return stack == []
```


##### Approach 2: Iterative

##### Complexity analysis:
- Time complexity:
- Space complexity:

###### Java
```java
public class Solution {
	public boolean isValid(String s) {
		char[] stack = new char[s.length()];
		int head = 0;
		for(char c : s.toCharArray()) {
			switch(c) {
				case '{':
				case '[':
				case '(':
					stack[head++] = c;
					break;
				case '}':
					if(head == 0 || stack[--head] != '{') return false;
					break;
				case ')':
					if(head == 0 || stack[--head] != '(') return false;
					break;
				case ']':
					if(head == 0 || stack[--head] != '[') return false;
					break;
			}
		}
		return head == 0;

	}
}
```