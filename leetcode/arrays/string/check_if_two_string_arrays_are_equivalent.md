### 1662. Check If Two String Arrays are Equivalent
https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/

Given two string arrays `word1` and `word2`, return `true` if the two arrays **represent** the same string, and `false` otherwise.

A string is **represented** by an array if the array elements concatenated **in order** forms the string.

 

Example 1:
```
Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.
```
Example 2:
```
Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false
```
Example 3:
```
Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
Output: true
``` 

Constraints:

- 1 <= word1.length, word2.length <= $10^3$
- 1 <= word1[i].length, word2[i].length <= $10^3$
- 1 <= sum(word1[i].length), sum(word2[i].length) <= $10^3$
- word1[i] and word2[i] consist of lowercase letters.



#### Solution

#### Approach 1: Char Iterator

##### Complexity Analysis
- Time complexity: O(n)
- Space complexity: O(1)

```java
class CharIterator { 
    String[] words;
    int wordIndex = 0;
    int charIndex = 0;
    
    CharIterator(String[] words) { 
        this.words = words;
    } 
    
    public boolean hasNext() { 
        return this.wordIndex != this.words.length;
    } 

    public char next() {
        char currChar = words[wordIndex].charAt(charIndex++);
        if (charIndex == words[wordIndex].length()) {
            charIndex = 0;
            wordIndex++;
        }
        return currChar;
    }
}
    
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        CharIterator charIterator1 = new CharIterator(word1);
        CharIterator charIterator2 = new CharIterator(word2);
        while (charIterator1.hasNext() && charIterator2.hasNext()) {
            if (charIterator1.next() != charIterator2.next()) {
                return false;
            }
        }
        return !charIterator1.hasNext() && !charIterator2.hasNext();
    }
}
```
