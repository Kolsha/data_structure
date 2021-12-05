### [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)

Given a string `s`, partition `s` such that every substring of the partition is a palindrome. Return all possible **palindrome** partitioning of `s`.

A **palindrome** string is a string that reads the same backward as forward.

 

Example 1:
```
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
```
Example 2:
```
Input: s = "a"
Output: [["a"]]
```

Constraints:

- 1 <= s.length <= 16
- `s` contains only lowercase English letters.

##### [Solution](https://leetcode-cn.com/problems/palindrome-partitioning/solution/fen-ge-hui-wen-chuan-by-leetcode-solutio-6jkv/)

##### Approach 1: 回溯 + 动态规划预处理
由于需要求出字符串 s 的所有分割方案，因此我们考虑使用 `搜索 + 回溯` 的方法枚举所有可能的分割方法并进行判断。

假设我们当前搜索到字符串的第 `i` 个字符，且 $s[0..i-1]$ 位置的所有字符已经被分割成若干个回文串，并且分割结果被放入了答案数组 $\textit{ans}$ 中，那么我们就需要枚举下一个回文串的右边界 $j$，使得 $s[i..j]$ 是一个回文串。

因此，我们可以从 $i$ 开始，从小到大依次枚举 $j$。对于当前枚举的 $j$ 值，我们使用双指针的方法判断 $s[i..j]$ 是否为回文串：如果 $s[i..j]$ 是回文串，那么就将其加入答案数组 $\textit{ans}$ 中，并以 $j+1$ 作为新的 $i$ 进行下一层搜索，并在未来的回溯时将 $s[i..j]$ 从 $\textit{ans}$ 中移除。

如果我们已经搜索完了字符串的最后一个字符，那么就找到了一种满足要求的分割方法。

细节

当我们在判断 $s[i..j]$ 是否为回文串时，常规的方法是使用双指针分别指向 $i$ 和 $j$，每次判断两个指针指向的字符是否相同，直到两个指针相遇。然而这种方法会产生重复计算，例如下面这个例子：

当 $s = \texttt{aaba}$ 时，对于前 2 个字符 $\texttt{aa}$，我们有 2 种分割方法 $[\texttt{aa}]$ 和 $[\texttt{a}, \texttt{a}]$，当我们每一次搜索到字符串的第 $i=2$ 个字符 $\texttt{b}$ 时，都需要对于每个 $s[i..j]$ 使用双指针判断其是否为回文串，这就产生了重复计算。

因此，我们可以将字符串 $s$ 的每个子串 $s[i..j]$ 是否为回文串预处理出来，使用动态规划即可。设 $f(i, j)$ 表示 $s[i..j]$ 是否为回文串，那么有状态转移方程：

$f(i, j) = \begin{cases} \texttt{True}, & \quad i \geq j \\ f(i+1,j-1) \wedge (s[i]=s[j]), & \quad \text{otherwise} \end{cases}$
 

其中 $\wedge$ 表示逻辑与运算，即 $s[i..j]$ 为回文串，当且仅当其为空串（$i>j$），其长度为 1（$i=j$），或者首尾字符相同且 $s[i+1..j-1]$ 为回文串。

预处理完成之后，我们只需要 $O(1)$ 的时间就可以判断任意 $s[i..j]$ 是否为回文串了。

##### Complexity analysis

- Time complexity: $O(n \cdot 2^n)$，其中 $n$ 是字符串 $s$ 的长度。在最坏情况下，$s$ 包含 $n$ 个完全相同的字符，因此它的任意一种划分方法都满足要求。而长度为 $n$ 的字符串的划分方案数为 $2^{n-1}=O(2^n)$，每一种划分方法需要 $O(n)$ 的时间求出对应的划分结果并放入答案，因此总时间复杂度为 $O(n \cdot 2^n)$。尽管动态规划预处理需要 $O(n^2)$ 的时间，但在渐进意义下小于 $O(n \cdot 2^n)$，因此可以忽略。

- Space complexity: $O(n^2)$，这里不计算返回答案占用的空间。数组 $f$ 需要使用的空间为 $O(n^2)$，而在回溯的过程中，我们需要使用 $O(n)$ 的栈空间以及 $O(n)$ 的用来存储当前字符串分割方法的空间。由于 $O(n)$ 在渐进意义下小于 $O(n^2)$，因此空间复杂度为 $O(n^2)$


```java
class Solution {
    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }
}

```

---
---







##### Complexity analysis
- Time complexity:
- Space complexity:

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }


    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start) {
        if(start == s.length()) {
            list.add(new ArrayList<>(tempList));
        } else {
            for(int i = start; i < s.length(); i++) {
                if(isPalindrome(s, start, i)) {
                    tempList.add(s.substring(start, i + 1));
                    backtrack(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }     
            }
        }
    }


    public boolean isPalindrome(String s, int low, int high){
        while(low < high) {
            if(s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    } 
}
```