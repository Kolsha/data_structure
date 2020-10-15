### 49. Group Anagrams

https://leetcode.com/problems/group-anagrams/

Given an array of strings, group anagrams together.

Example:
```
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```
Note:

- All inputs will be in lowercase.
- The order of your output does not matter.

##### Complexity Analysis

- Time Complexity: O(NKlogK), where N is the length of `strs`, and `K` is the maximum length of a string in `strs`. The outer loop has complexity O(N) as we iterate through each string. Then, we sort each string in O(KlogK) time.

- Space Complexity: O(NK), the total information content stored in ans

Solution
```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String mod = String.valueOf(charArray);
            if (map.containsKey(mod)) {
                map.get(mod).add(str);
            } else {
                ArrayList<String> holder = new ArrayList<>();
                holder.add(str);
                map.put(mod, holder);
            }
        }

        ArrayList<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;

    }
}
```