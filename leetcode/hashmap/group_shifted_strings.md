### 249. Group Shifted Strings

https://leetcode.com/problems/group-shifted-strings/

Given a string, we can "shift" each of its letter to its successive letter, for example: `"abc" -> "bcd"`. We can keep "shifting" which forms the sequence:

`"abc" -> "bcd" -> ... -> "xyz"`

Given a list of **non-empty** strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:
```
Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
```

Solution

Basically we need to form some sort of key for each word to group them. (Remember the idea of group all anagrams?)

Consider `acf` and `pru`. Now notice the differnce between each characters.
`acf = 0->2->3`, and `pru = 0->2->3`. So these two form same group. So in this case, you can simply use integers ASCII difference to form key.

Now consider corner case, `az` and `ba`, where `az = 0->25` and `ba = 0->-1`. When it goes negative, just make it positive(rotate or mod equivalent) by adding 26. So it becomes, `ba = 0->25`, which forms same group.

NOW, talking about "concise" code, here it is.

```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        
        for(String word: strings) {
            String key = getKey(word);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(word);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
    
    private String getKey(String word) {
        StringBuilder key = new StringBuilder();
        for(int i = 1; i < word.length(); i++) {
            int diff = word.charAt(i) - word.charAt(i-1);
            if(diff < 0) {
                diff+=26;
            }
            key.append(diff).append(",");
        }
        return key.toString();
        
    }
}
```