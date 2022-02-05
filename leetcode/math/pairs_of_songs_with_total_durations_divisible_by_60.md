### 1010. Pairs of Songs With Total Durations Divisible by 60

You are given a list of songs where the ith song has a duration of `time[i]` seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by `60`. Formally, we want the number of indices `i`, `j` such that `i < j` with `(time[i] + time[j]) % 60 == 0`.

 

Example 1:
```
Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
```
Example 2:
```
Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
``` 

Constraints:

- $1 <= time.length <= 6 * 10^4$
- $1 <= time[i] <= 500$

##### Solution

##### Approach 1: Math

##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int c[]  = new int[60], res = 0;
        for (int t : time) {
            res += c[(600 - t) % 60];
            c[t % 60] += 1;
        }
        return res;
    }
}
```



##### [Approach 2: HashMap](https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/discuss/636651/Java-Simple-Solution-with-with-a-crystal-clear-explanation(biginners-mindset)-HashMap-in-O(n))

reason for (60 - t % 60) % 60 expression: If the map already has 30, we need to look for the number is having remainder or not, this can be achieved with 60 - t%60. Eg, if the number is 210. 60 - 210 % 60 returns 30. 30 is already in the list this can be paired up to form (30, 210).

Reason for an extra % 60 over (60 - t % 60). if the t = 60, the expression 60 - t % 60 returns 60. this is beyond our remainers range (0,59)for 60. to make it with in the range in the case of 60 and multiples of 60, we are ufing an extra %60 on top of (60 - t % 60). this makes the remainder 0. which is with in the range of remainders for 60(0,59)

Explanation for map.put(t % 60, map.getOrDefault(t % 60, 0) + 1);
every time you visit a new number add first and increment then. Eg: if I have 30,150,90when I read 30 map has 30,1 ... cntr(counter) is 0
when I read 150, counter increments(30,150) map has 30,2 ... cntr(counter) is 1The map should have 2, reasoningwhen I Read 90,this can form 2 pairs(90,30),90,150.
This makes the counter will add by 2 ..... cntr = 1+2 == 3

##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(n)

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int cntr = 0;
        for (int t : time) {
            cntr += map.getOrDefault((60 - t % 60) % 60, 0);
            map.put(t % 60, map.getOrDefault(t % 60, 0) + 1);
        }
        return cntr;
    }
}
```