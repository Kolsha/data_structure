### 277. Find the Celebrity

https://leetcode.com/problems/find-the-celebrity/

Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

 

Example 1:
```

Input: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
```
Example 2:
```

Input: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
Output: -1
Explanation: There is no celebrity.
``` 

Note:

1. The directed graph is represented as an adjacency matrix, which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
2. Remember that you won't have direct access to the adjacency matrix.


Solution

Approach 1: DAG
```java
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (knows(i, j)) {
                    indegree[i]--;
                    indegree[j]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (indegree[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
```

Approach 2: Brute force
##### Intuition
As per the problem statement, for a given person i, we can check whether or not i is a celebrity by using the knows(...) API to see if everybody knows i, and that i know nobody.

Therefore, the simplest way of solving this problem is to go through each of the people in turn, and check whether or not they are a celebrity.

##### Complexity Analysis

We don't know what time and space the knows(...) API uses. Because it's not our concern, we'll assume it's O(1) for the purpose of analysing our algorithm.

- Time Complexity : O(n^2) 
  For each of the n people, we need to check whether or not they are a celebrity.
  
  Checking whether or not somebody is a celebrity requires making 22 API calls for each of the n - 1 other people, for a total of 2 * (n - 1) = = 2 ⋅ n − 2 calls. In big-oh notation, we drop the constants, leaving O(n).
  
  So each of the nn celebrity checks will cost O(n)O(n), giving a total of O(n^2).

- Space Complexity : O(1).
  Our code only uses constant extra space. The results of the API calls are not saved.


```java
public class Solution extends Relation {
    
    private int numberOfPeople;
    
    public int findCelebrity(int n) {
        numberOfPeople = n;
        for (int i = 0; i < n; i++) {
            if (isCelebrity(i)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean isCelebrity(int i) {
        for (int j = 0; j < numberOfPeople; j++) {
            if (i == j) continue; // Don't ask if they know themselves.
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
}
```