### 767. Reorganize String

Given a string `S`, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:
```
Input: S = "aab"
Output: "aba"
```
Example 2:
```
Input: S = "aaab"
Output: ""
```
Note:

- `S` will consist of lowercase letters and have length in range [1, 500].

Solution

Approach 1: Priority Queue
```java
class Solution {
    public String reorganizeString(String S) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            int count = map.getOrDefault(ch, 0) + 1;
             // Impossible to form a solution
            if(count > (S.length() +1)/2) {
                return "";
            }
            map.put(ch, count);
        }

        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        
        for(char key: map.keySet()) {
            pq.add(new int[] {key, map.get(key)});
        }
        
        StringBuilder sb  = new StringBuilder();
        while(!pq.isEmpty()) {
            int[] first = pq.poll();
            // while sb length == 0 or last char is not "first"
            if(sb.length() == 0 || first[0] != sb.charAt(sb.length()-1)) {
                sb.append((char)first[0]);
                first[1]--;
                if(first[1] > 0) {
                    pq.offer(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char)second[0]);
                second[1]--;
                if(second[1] > 0) {
                    pq.offer(second);
                }
                pq.offer(first);
            }
        }
        return sb.toString();
    }
}
```