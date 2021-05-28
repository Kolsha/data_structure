### 187. Repeated DNA Sequences
The DNA sequence is composed of a series of nucleotides abbreviated as `'A'`, `'C'`, `'G'`, and `'T'`.

- For example, `"ACGAATTCCG"` is a **DNA sequence**.
When studying **DNA**, it is useful to identify repeated sequences within the DNA.

Given a string `s` that represents a **DNA sequence**, return all the **10-letter-long** sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in **any order**.

 

Example 1:
```
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
```
Example 2:
```
Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]
``` 

Constraints:

- 1 <= s.length <= $10^5$
- s[i] is either 'A', 'C', 'G', or 'T'.

### Solution
#### Approach 1: HashSet

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {        
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        int dnaSize = 10;
        ArrayList<String> res = new ArrayList<>();
        for(int start = 0; start + dnaSize - 1 < s.length(); start++) {
            String dna = s.substring(start, start + dnaSize);
            if(!seen.add(dna)) {
                repeated.add(dna);
            }
        }
        return new ArrayList<>(repeated);
    }
}
```

#### [Approach 2: hashmap + bits manipulation](https://leetcode.com/problems/repeated-dna-sequences/discuss/53867/Clean-Java-solution-(hashmap-%2B-bits-manipulation))

