### 468. Validate IP Address

https://leetcode.com/problems/validate-ip-address/

Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
```
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
```
Example 2:
```
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
```
Example 3:
```
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.
```

Solution
https://howtodoinjava.com/java8/intstream-examples/

```java
class Solution {
    public String validIPAddress(String IP) {
        String[] ipv4 = IP.split("\\.");
        String[] ipv6 = IP.split("\\:");
        
        if(IP.chars().filter(ch -> ch == '.').count() == 3 && ipv4.length == 4) {
            for(String ipAddress: ipv4) {
                if(!isIPv4(ipAddress)) {
                    return "Neither";
                }
            }
            return "IPv4";
        }
        
        if(IP.chars().filter(ch -> ch == ':').count() == 7 && ipv6.length == 8) {
            for(String ipAddress: ipv6) {
                if(!isIPv6(ipAddress)) {
                    return "Neither";
                }
            }
            return "IPv6";
        }
        return "Neither";
    }
    
    boolean isIPv4(String address) {
        try {
            int value = Integer.parseInt(address);
            // leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
            // so we use String.valueOf(value).equals(address);
            return value >= 0 && value <= 255 && String.valueOf(value).equals(address);
        } catch(Exception e) {
            return false;
        }
    }
    
    boolean isIPv6(String address) {
        // https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html#parseInt(java.lang.String,%20int)
        if(address.length() > 4) {
            return false;
        }
        try {
            int val = Integer.parseInt(address, 16);
            // no negative sign: test case -> "1081:db8:85a3:01:-0:8A2E:0370:7334"
            // address.charAt(0) != '-'
            return val >= 0 && address.charAt(0) != '-';
        } catch(Exception e) {
            return false;
        }
    }
}
```

#facebook