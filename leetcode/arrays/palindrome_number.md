### 9. Palindrome Number

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:
```
Input: 121
Output: true
```
Example 2:
```
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```
Example 3:
```
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```
Follow up:

Coud you solve it without converting the integer to a string?

Solution

Method 1: String

```java
class Solution {
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        char[] reversedStr = reverse(str);
        return str.equals(new String(reversedStr));
    }

    private char[] reverse(String str) {
        char[] res = str.toCharArray();
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            char temp = res[i];
            res[i] = res[j];
            res[j] = temp;
            i++;
            j--;
        }
        return res;
    }
}
```

Method 2: Follow up - div & mod approach
```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || x != 0 && x % 10 == 0) {
            return false;
        }
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        // when we have odd digits
        // For example:
        // number 232 use x == rev / 10.
        // number 22 use x == rev.
        return (rev == x || rev / 10 == x);
    }
}
```

