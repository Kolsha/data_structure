### [93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)


Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:
```
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
```

Solution

```java
class Solution {
        public List<String> restoreIpAddresses(String s) {
            ArrayList<String> result = new ArrayList<>();
            helper(s, "", result, 0);
            return result;
        }

        private void helper(String s, String temp, List<String> result, int count) {
            if (count == 4) {
                if (s.length() == 0) {
                    String ans = temp.substring(0, temp.length() - 1);
                    result.add(ans);
                }
                return;
            }
            for (int len = 1; len <= 3; len++) {
                if (s.length() < len) {
                    continue;
                }
                int ipAddr = Integer.parseInt(s.substring(0, len));
                if (ipAddr > 255 || String.valueOf(ipAddr).length() != len) {
                    continue;
                }
                helper(s.substring(len), temp + ipAddr + ".", result, count + 1);
            }
        }
    }
```
