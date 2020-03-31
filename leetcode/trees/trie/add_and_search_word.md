### 211. Add and Search Word - Data structure design

https://leetcode.com/problems/add-and-search-word-data-structure-design/

Design a data structure that supports the following two operations:
```
void addWord(word)
bool search(word)
```
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:
```
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
```
Note:
- You may assume that all words are consist of lowercase letters a-z.

Solution

```java
class WordDictionary {
    
    private TrieNode root = new TrieNode();

    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode temp = root;
        for(char c: word.toCharArray()) {
            if(temp.childNodes[c - 'a'] == null) {
                temp.childNodes[c - 'a'] = new TrieNode();
            }
            temp = temp.childNodes[c - 'a'];
        }
        temp.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] ch, int curr, TrieNode node) {
        if( curr == ch.length) {
            return node.isWord;
        }
        
        if(ch[curr] == '.') {
            // move forward to next char
            for(int i = 0; i < 26; i++) {
                if(node.childNodes[i]!=null && match(ch, curr+1, node.childNodes[i])) {
                    return true;
                }
            }
        } else {
            // move forward to next char
            return node.childNodes[ch[curr] - 'a']!= null && match(ch, curr+1, node.childNodes[ch[curr] - 'a']);
        }
        return false;
    }

    private static class TrieNode {
        TrieNode[] childNodes = new TrieNode[26];
        boolean isWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
```