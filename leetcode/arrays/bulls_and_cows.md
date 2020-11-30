### 299. Bulls and Cows
https://leetcode.com/problems/bulls-and-cows/

You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:
```
Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
```
Example 2:
```
Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
```
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

Solution:
```java
class Solution {
    public String getHint(String secret, String guess) {
        // find bulls
        int bullCount = 0;
        int[] guessArray = new int[10];
        int[] secretArray = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bullCount++;
            } else {
                guessArray[guess.charAt(i) - '0']++;
                secretArray[secret.charAt(i) - '0']++;
            }
        }

        int cowCount = 0;
        for (int i = 0; i < 10; i++) {
            cowCount += Math.min(guessArray[i], secretArray[i]);
        }

        return bullCount + "A" + cowCount + "B";
    }
}
```


Overview
At first glance, this popular Google question seems to be easy. The only difficulty here is to define how to count.

To count bulls, one could parse both strings and count the matches, and that's a correct way to do things.
![](https://leetcode.com/problems/bulls-and-cows/Figures/299/bulls2.png)

To count cows, one could parse guess string and count the characters which are present in the string secret but located in the wrong positions. Though the cows are more complicated creatures than bulls. For some test cases, it might work.

![](https://leetcode.com/problems/bulls-and-cows/Figures/299/bulls2.png)
Although, in general, one has to count the characters as well. For example, if secret contains just one digit "4" then the maximum number of 4-cows is 1. Even if guess contains many "4"s. In the following example, only one of two "4"s should be counted as a cow.

![](https://leetcode.com/problems/bulls-and-cows/Figures/299/cows.png)

To figure out these three points is enough to solve the problem.

In this article, we start from a straightforward two-pass solution which already has the best time (\mathcal{O}(N)O(N)) and space complexity (\mathcal{O}(1)O(1)).

As a follow-up, we implement a one-pass solution and discuss some Java-related optimizations in the approach 2.

Approach 1: HashMap: Two Passes
Algorithm

Initialize the number of bulls and cows to zero.

Initialize the hashmap character -> its frequency for the string secret. This hashmap could be later used during the iteration over the string guess to keep the available characters.

It's time to iterate over the string guess.

If the current character ch of the string guess is in the string secret: if ch in h, then there could be two situations.

The corresponding characters of two strings match: ch == secret[idx].

Then it's time to update the bulls: bulls += 1.

The update of the cows is needed if the count for the current character in the hashmap is negative or equal to zero. That means that before it was already used for cows, and the cows counter should be decreased: cows -= int(h[ch] <= 0).

The corresponding characters of two strings don't match: ch != secret[idx]. Then increase the cows counter: cows += int(h[ch] > 0).

In both cases, one has to update hashmap, marking the current character as used: h[ch] -= 1.

Return the number of bulls and cows.

```java
class Solution {
    public String getHint(String secret, String guess) {
        HashMap<Character, Integer> h = new HashMap();
        for (char s : secret.toCharArray()) {
            h.put(s, h.getOrDefault(s, 0) + 1);
        }
            
        int bulls = 0, cows = 0;
        int n = guess.length();
        for (int idx = 0; idx < n; ++idx) {
            char ch = guess.charAt(idx);
            if (h.containsKey(ch)) {
                // corresponding characters match
                if (ch == secret.charAt(idx)) {
                    // update the bulls
                    bulls++;
                    // update the cows 
                    // if all ch characters from secret 
                    // were used up
                    if (h.get(ch) <= 0)
                        cows--;    
                // corresponding characters don't match
                } else {
                    // update the cows
                    if (h.get(ch) > 0)
                        cows++;     
                }
                // ch character was used
                h.put(ch, h.get(ch) - 1); 
            }
        }
                
        return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
    }
}
```

Complexity Analysis

Time complexity: \mathcal{O}(N)O(N), we pass over the strings two times.

Space complexity: \mathcal{O}(1)O(1) to keep hashmap h which contains at most 10 elements.


Approach 2: One Pass
Intuition

Let's optimize approach 1 by building the hashmap during the strings' parsing. That would allow us to reduce the number of passes to one.

Algorithm

Initialize the number of bulls and cows to zero.

Initialize the hashmap to count characters. During the iteration, secret string gives a positive contribution, and guess - negative contribution.

Iterate over the strings: s is the current character in the string secret and g - the current character in the string guess.

If s == g, update bulls counter: bulls += 1.

Otherwise, if s != g:

Update cows by adding 1 if so far guess contains more s characters than secret: h[s] < 0.

Update cows by adding 1 if so far secret contains more g characters than guess: h[g] > 0.

Update the hashmap by marking the presence of s character in the string secret: h[s] += 1.

Update the hashmap by marking the presence of g character in the string guess: h[g] -= 1.

Return the number of bulls and cows.

Implementation


```java
class Solution {
    public String getHint(String secret, String guess) {
        HashMap<Character, Integer> h = new HashMap();
            
        int bulls = 0, cows = 0;
        int n = guess.length();
        for (int idx = 0; idx < n; ++idx) {
            char s = secret.charAt(idx);
            char g = guess.charAt(idx);
            if (s == g) {
                bulls++;    
            } else {
                if (h.getOrDefault(s, 0) < 0) 
                    cows++;
                if (h.getOrDefault(g, 0) > 0)
                    cows++;
                h.put(s, h.getOrDefault(s, 0) + 1);
                h.put(g, h.getOrDefault(g, 0) - 1);
            }
        } 
                
        return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
    }
}
```

To further optimize the Java solution, one could use an array instead of hashmap because there are known problems with Java HashMap performance. Another small improvement is to replace string concatenation by a StringBuilder.


```java
class Solution {
    public String getHint(String secret, String guess) {
        int[] h = new int[10];
            
        int bulls = 0, cows = 0;
        int n = guess.length();
        for (int idx = 0; idx < n; ++idx) {
            char s = secret.charAt(idx);
            char g = guess.charAt(idx);
            if (s == g) {
                bulls++;    
            } else {
                if (h[s - '0'] < 0) 
                    cows++;
                if (h[g - '0'] > 0)
                    cows++;
                h[s - '0']++;
                h[g - '0']--;
            }
        } 
                
        StringBuilder sb = new StringBuilder();
        sb.append(bulls); 
        sb.append("A"); 
        sb.append(cows); 
        sb.append("B");
        return sb.toString();
    }
}
```
Complexity Analysis

Time complexity: \mathcal{O}(N)O(N), we do one pass over the strings.

Space complexity: \mathcal{O}(1)O(1) to keep hashmap (or array) h which contains at most 10 elements.

