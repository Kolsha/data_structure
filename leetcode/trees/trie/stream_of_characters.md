### [1032. Stream of Characters](https://leetcode.com/problems/stream-of-characters)


Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings `words`.

For example, if `words = ["abc", "xyz"]` and the stream added the four characters (one by one) `'a'`, `'x'`, `'y'`, and `'z'`, your algorithm should detect that the suffix `"xyz"` of the characters `"axyz"` matches `"xyz"` from words.

Implement the `StreamChecker` class:

- `StreamChecker(String[] words)` Initializes the object with the strings array words.<br/><br/>

- `boolean query(char letter)` Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in `words`.
 

Example 1:
```
Input
["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
[[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
Output
[null, false, false, false, true, false, true, false, false, false, false, false, true]

Explanation
StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
streamChecker.query("a"); // return False
streamChecker.query("b"); // return False
streamChecker.query("c"); // return False
streamChecker.query("d"); // return True, because 'cd' is in the wordlist
streamChecker.query("e"); // return False
streamChecker.query("f"); // return True, because 'f' is in the wordlist
streamChecker.query("g"); // return False
streamChecker.query("h"); // return False
streamChecker.query("i"); // return False
streamChecker.query("j"); // return False
streamChecker.query("k"); // return False
streamChecker.query("l"); // return True, because 'kl' is in the wordlist
``` 

Constraints:

- `1 <= words.length <= 2000`
- `1 <= words[i].length <= 2000`
- `words[i]` consists of lowercase English letters.
- `letter` is a lowercase English letter.
- At most <code>$4 * 10^4$</code> calls will be made to query.


##### Solution

##### Approach 1: Trie
![](https://assets.leetcode.com/users/images/ce2bdef1-e727-4f1b-aa40-015eb766fd3b_1597172741.6132298.png)
##### Complexity analysis
Assuming average word length for input string array words is `L`, length of words is `M`, length of query buffer size is `N`.

- Time complexity: O(N)

- Space complexity: O(26 * L * M) → O(LM)



```java
class StreamChecker {
    private TrieNode root = new TrieNode();
    private StringBuilder holder = new StringBuilder();

    public StreamChecker(String[] words) {
        initTrie(words, root);
    }

    public boolean query(char letter) {
        holder.append(letter);
        TrieNode itr = root;
        for(int i = holder.length() - 1; i >= 0 && itr != null; i--) {
            itr = itr.next[holder.charAt(i) - 'a'];
            if(itr != null && itr.isWord) {
                return true;
            }
        }
        return false;
    }

    private void initTrie(String[] words, TrieNode root) {
        for(String word: words) {
            TrieNode itr = root;
            int size = word.length();
            for(int i = size - 1; i >= 0; i--) {
                char c = word.charAt(i);
                if (itr.next[c - 'a'] == null) {
                    itr.next[c - 'a'] = new TrieNode();
                }
                itr = itr.next[c - 'a'];
            }
            itr.isWord = true;
        }
    }

    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
```