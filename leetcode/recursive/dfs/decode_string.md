### 394. Decode String

https://leetcode.com/problems/decode-string/

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:
```
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```

##### Solution

##### Approach 1: DFS

##### Complexity analysis
- Time complexity: O(n * max_count) -> One pass and we need to add repeated strings to StringBuilder

- Space complexity: O(n) -> depends on how many '[' we have in the String

```java
class Solution {
    int pos = 0;
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int repeatCount = 0;
        
        for(int i = pos; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                repeatCount = repeatCount * 10 + (ch - '0');
            } else if(ch == '[') {
                pos = i + 1;
                String next = decodeString(s);
                while(repeatCount!=0) {
                    sb.append(next);
                    repeatCount--;
                }
                i = pos;
            } else if(ch == ']') {
                pos = i;
                return sb.toString();
            } else {
                // if it's alphabet
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
```

##### Approach 2: Using Stack
###### Intuition

We have to decode the result in a particular pattern. We know that the input is always valid. The pattern begins with a number k, followed by opening braces [, followed by string. Post that, there could be one of the following cases :

1. There is another nested pattern k[string k[string]]
2. There is a closing bracket k[string]

Since we have to start decoding the innermost pattern first, continue iterating over the string s, pushing each character to the stack until it is not a closing bracket ]. Once we encounter the closing bracket ], we must start decoding the pattern.

As we know that stack follows the Last In First Out (LIFO) Principle, the top of the stack would have the data we must decode.

###### Algorithm

The input can contain an alphabet (a-z), digit (0-9), opening braces [ or closing braces ]. Start traversing string s and process each character based on the following rules:

Case 1) Current character is not a closing bracket ].

Push the current character to stack.

Case 2) Current character is a closing bracket ].

Start decoding the last traversed string by popping the string decodedString and number k from the top of the stack.

- Pop from the stack while the next character is not an opening bracket [ and append each character (a-z) to the decodedString.
- Pop opening bracket [ from the stack.
- Pop from the stack while the next character is a digit (0-9) and build the number k.

Now that we have k and decodedString , decode the pattern k[decodedString] by pushing the decodedString to stack k times.

Once the entire string is traversed, pop the result from stack and return.
##### Complexity analysis
- Time Complexity: $\mathcal{O}(\text{maxK} ^ {\text{countK}}\cdot n)$, where $\text{maxK}$ is the maximum value of $k$, $\text{countK}$ is the count of nested $k$ values and $n$ is the maximum length of encoded string. Example, for $s = 20[a10[bc]]$, $\text{maxK}$ is $20$, $\text{countK}$ is $2$ as there are $2$ nested $k$ values (20 and 10) . Also, there are $2$ encoded strings a and bc with maximum length of encoded string ,$n$ as $2$

- Space Complexity: $\mathcal{O}(\text{sum}(\text{maxK} ^ {\text{countK}}\cdot n))$, where $\text{maxK}$ is the maximum value of $k$, $\text{countK}$ is the count of nested $k$ values and $n$ is the maximum length of encoded string.

```java
class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }
            // push the current character to stack
            else {
                stack.push(s.charAt(i));
            }
        }      
        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }
}
```

##### Approach 3: Using Recursion

###### Intuition

In the previous approach, we implemented an external stack to keep the track of each character traversed. Ideally, a stack is required when we have nested encoded string in the form $k[string k[string]]$.

Using this intuition, we could start by building k and string and recursively decode for each nested substring. The recursion uses an internal call stack to store the previous state. Let's understand the algorithm in detail.

###### Algorithm

- Build result while next character is letter (a-z) and build the number k while next character is a digit (0-9) by iterating over string s.
- Ignore the next [ character and recursively find the nested decodedString.
- Decode the current pattern $k[decodedString]$ and append it to the result.
- Return the current result.
- The above steps are repeated recursively for each pattern until the entire string s is traversed.

Base Condition: We must define a base condition that must be satisfied to backtrack from the recursive call. In this case, we would backtrack and return the result when we have traversed the string s or the next character is ] and there is no nested substring.

##### Complexity analysis
- Time Complexity: $\mathcal{O}(\text{maxK} \cdot n)$
- Space Complexity: $\mathcal{O}(n)$. This is the space used to store the internal call stack used for recursion. As we are recursively decoding each nested pattern, the maximum depth of recursive call stack would not be more than $n$

```java
class Solution {
    int index = 0;
    String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index)))
                result.append(s.charAt(index++));
            else {
                int k = 0;
                // build k while next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index)))
                    k = k * 10 + s.charAt(index++) - '0';
                // ignore the opening bracket '['    
                index++;
                String decodedString = decodeString(s);
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                while (k-- > 0)
                    result.append(decodedString);
            }
        }
        return new String(result);
    }
}

```