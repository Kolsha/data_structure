### [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:
```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```
Example 2:
```
input: "cbbd"
Output: "bb"
```

### Solution

#### [Approach 1: Dynamic Programming](https://leetcode.com/problems/longest-palindromic-substring/solution/)
`dp(i, j)` represents whether `s(i ... j)` can form a palindromic substring, <b>`dp(i, j)` is true when `s(i)` equals to `s(j)` and `s(i+1 ... j-1)` is a palindromic substring</b>. When we found a palindrome, check if it's the longest one. Time complexity O(n^2).


#### Complexity analysis
- Time complexity: $O(n^2)$
- Space complexity: $O(n^2)$

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        String res = null;

        boolean[][] dp = new boolean[n][n];
    
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
            
                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }
    
        return res;
    }
}
```

Method 2: Optimized Expand Around Center
// todo https://leetcode.com/problems/longest-palindromic-substring/solution/


Approach 1: Longest Common Substring
Common mistake

Some people will be tempted to come up with a quick solution, which is unfortunately flawed (however can be corrected easily):

Reverse SS and become S'S 
′
 . Find the longest common substring between SS and S'S 
′
 , which must also be the longest palindromic substring.

This seemed to work, let’s see some examples below.

For example, SS = "caba", S'S 
′
  = "abac".

The longest common substring between SS and S'S 
′
  is "aba", which is the answer.

Let’s try another example: SS = "abacdfgdcaba", S'S 
′
  = "abacdgfdcaba".

The longest common substring between SS and S'S 
′
  is "abacd". Clearly, this is not a valid palindrome.

Algorithm

We could see that the longest common substring method fails when there exists a reversed copy of a non-palindromic substring in some other part of SS. To rectify this, each time we find a longest common substring candidate, we check if the substring’s indices are the same as the reversed substring’s original indices. If it is, then we attempt to update the longest palindrome found so far; if not, we skip this and find the next candidate.

This gives us an O(n^2)O(n 
2
 ) Dynamic Programming solution which uses O(n^2)O(n 
2
 ) space (could be improved to use O(n)O(n) space). Please read more about [Longest Common Substring here](http://en.wikipedia.org/wiki/Longest_common_substring).

 Approach 2: Brute Force
The obvious brute force solution is to pick all possible starting and ending positions for a substring, and verify if it is a palindrome.

Complexity Analysis

Time complexity : O(n^3)O(n 
3
 ). Assume that nn is the length of the input string, there are a total of \binom{n}{2} = \frac{n(n-1)}{2}( 
2
n
​	
 )= 
2
n(n−1)
​	
  such substrings (excluding the trivial solution where a character itself is a palindrome). Since verifying each substring takes O(n)O(n) time, the run time complexity is O(n^3)O(n 
3
 ).

Space complexity : O(1)O(1).


Approach 3: Dynamic Programming
To improve over the brute force solution, we first observe how we can avoid unnecessary re-computation while validating palindromes. Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome since the two left and right end letters are the same.

We define P(i,j)P(i,j) as following:

P(i,j) = \begin{cases} \text{true,} &\quad\text{if the substring } S_i \dots S_j \text{ is a palindrome}\\ \text{false,} &\quad\text{otherwise.} \end{cases}P(i,j)={ 
true,
false,
​	
  
if the substring S 
i
​	
 …S 
j
​	
  is a palindrome
otherwise.
​	
 

Therefore,

P(i, j) = ( P(i+1, j-1) \text{ and } S_i == S_j )P(i,j)=(P(i+1,j−1) and S 
i
​	
 ==S 
j
​	
 )

The base cases are:

P(i, i) = trueP(i,i)=true

P(i, i+1) = ( S_i == S_{i+1} )P(i,i+1)=(S 
i
​	
 ==S 
i+1
​	
 )

This yields a straight forward DP solution, which we first initialize the one and two letters palindromes, and work our way up finding all three letters palindromes, and so on...

Complexity Analysis

Time complexity : O(n^2)O(n 
2
 ). This gives us a runtime complexity of O(n^2)O(n 
2
 ).

Space complexity : O(n^2)O(n 
2
 ). It uses O(n^2)O(n 
2
 ) space to store the table.

Additional Exercise

Could you improve the above space complexity further and how?


Approach 4: Expand Around Center
In fact, we could solve it in O(n^2)O(n 
2
 ) time using only constant space.

We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are only 2n - 12n−1 such centers.

You might be asking why there are 2n - 12n−1 but not nn centers? The reason is the center of a palindrome can be in between two letters. Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.

```java
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}
```

Complexity Analysis

Time complexity : O(n^2)O(n 
2
 ). Since expanding a palindrome around its center could take O(n)O(n) time, the overall complexity is O(n^2)O(n 
2
 ).

Space complexity : O(1)O(1).

Approach 5: Manacher's Algorithm
There is even an O(n)O(n) algorithm called Manacher's algorithm, [explained here in detail](https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm). However, it is a non-trivial algorithm, and no one expects you to come up with this algorithm in a 45 minutes coding session. But, please go ahead and understand it, I promise it will be a lot of fun.

