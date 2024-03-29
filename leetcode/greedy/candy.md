### 135. Candy
https://leetcode.com/problems/candy/

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.


Solution
Approach 1: Brute Force
The simplest approach makes use of a 1-d array, candiescandies to keep a track of the candies given to the students. Firstly, we give 1 candy to each student. Then, we start scanning the array from left-to-right. At every element encountered, firstly, if the current element's ratings, ratings[i]ratings[i], is larger than the previous element ratings[i-1]ratings[i−1] and candies[i]<=candies[i-1]candies[i]<=candies[i−1], then we update candies[i]candies[i] as candies[i]=candies[i-1] + 1candies[i]=candies[i−1]+1. Thus, now the candy distribution for these two elements candies[i-1]candies[i−1] and candies[i]candies[i] becomes correct for the time being(locally). In the same step, we also check if the current element's ratings, ratings[i]ratings[i], is larger than the next element's ratings, i.e. ratings[i]>ratings[i+1]ratings[i]>ratings[i+1]. If so, we again update candies[i]=candies[i+1] + 1candies[i]=candies[i+1]+1. We continue this process for the whole ratingsratings array. If in any traversal, no updation of the candiescandies array occurs, it means we've reached at the final required distribution of the candies and we can stop the traversals. To keep a track of this we make use of a flagflag which is set to \text{True}True if any updation occurs in a traversal.

At the end, we can sum up all the elements of the candiescandies array to obtain the required minimum number of candies.

```java
public class Solution {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        boolean flag = true;
        int sum = 0;
        while (flag) {
            flag = false;
            for (int i = 0; i < ratings.length; i++) {
                if (i != ratings.length - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    flag = true;
                }
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    flag = true;
                }
            }
        }
        for (int candy : candies) {
            sum += candy;
        }
        return sum;
    }
}
```

Complexity Analysis

Time complexity : O(n^2)O(n 
2
 ). We need to traverse the array at most nn times.

Space complexity : O(n)O(n). One candiescandies array of size nn is used.

Approach 2: Using two arrays
Algorithm

In this approach, we make use of two 1-d arrays left2rightleft2right and right2leftright2left. The left2rightleft2right array is used to store the number of candies required by the current student taking care of the distribution relative to the left neighbours only. i.e. Assuming the distribution rule is: The student with a higher ratings than its left neighbour should always get more candies than its left neighbour. Similarly, the right2leftright2left array is used to store the number of candies candies required by the current student taking care of the distribution relative to the right neighbours only. i.e. Assuming the distribution rule to be: The student with a higher ratings than its right neighbour should always get more candies than its right neighbour. To do so, firstly we assign 1 candy to each student in both left2rightleft2right and right2leftright2left array. Then, we traverse the array from left-to-right and whenever the current element's ratings is larger than the left neighbour we update the current element's candies in the left2rightleft2right array as left2right[i] = left2right[i-1] + 1left2right[i]=left2right[i−1]+1, since the current element's candies are always less than or equal candies than its left neighbour before updation. After the forward traversal, we traverse the array from left-to-right and update right2left[i]right2left[i] as right2left[i] = right2left[i + 1] + 1right2left[i]=right2left[i+1]+1, whenever the current ( i^{th}i 
th
  ) element has a higher ratings than the right ( (i+1)^{th}(i+1) 
th
  ) element.

Now, for the i^{th}i 
th
  student in the array, we need to give \text{max}(left2right[i], right2left[i])max(left2right[i],right2left[i]) to it, in order to satisfy both the left and the right neighbour relationship. Thus, at the end, we obtain the minimum number of candies required as:

\text{minimum_candies}=\sum_{i=0}^{n-1} \text{max}(left2right[i], right2left[i]), \text{where } n = \text{length of the ratings array.}

The following animation illustrates the method:

![](https://leetcode.com/problems/candy/Figures/135_Candy_Two_Pass.gif)

```java
public class Solution {
    public int candy(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }
}
```

Complexity Analysis

Time complexity : O(n)O(n). left2rightleft2right and right2leftright2left arrays are traversed thrice.

Space complexity : O(n)O(n). Two arrays left2rightleft2right and right2leftright2left of size nn are used.

Approach 3: Using one array
Algorithm

In the previous approach, we used two arrays to keep track of the left neighbour and the right neighbour relation individually and later on combined these two. Instead of this, we can make use of a single array candiescandies to keep the count of the number of candies to be allocated to the current student. In order to do so, firstly we assign 1 candy to each student. Then, we traverse the array from left-to-right and distribute the candies following only the left neighbour relation i.e. whenever the current element's ratings is larger than the left neighbour and has less than or equal candies than its left neighbour, we update the current element's candies in the candiescandies array as candies[i] = candies[i-1] + 1candies[i]=candies[i−1]+1. While updating we need not compare candies[i]candies[i] and candies[i - 1]candies[i−1], since candies[i] \leq candies[i - 1]candies[i]≤candies[i−1] before updation. After this, we traverse the array from right-to-left. Now, we need to update the i^{th}i 
th
  element's candies in order to satisfy both the left neighbour and the right neighbour relation. Now, during the backward traversal, if ratings[i]>ratings[i + 1]ratings[i]>ratings[i+1], considering only the right neighbour criteria, we could've updated candies[i]candies[i] as candies[i] = candies[i + 1] + 1candies[i]=candies[i+1]+1. But, this time we need to update the candies[i]candies[i] only if candies[i] \leq candies[i + 1]candies[i]≤candies[i+1]. This happens because, this time we've already altered the candiescandies array during the forward traversal and thus candies[i]candies[i] isn't necessarily less than or equal to candies[i + 1]candies[i+1]. Thus, if ratings[i] > ratings[i + 1]ratings[i]>ratings[i+1], we can update candies[i]candies[i] as candies[i] = \text{max}(candies[i], candies[i + 1] + 1)candies[i]=max(candies[i],candies[i+1]+1), which makes candies[i]candies[i] satisfy both the left neighbour and the right neighbour criteria.

Again, we need sum up all the elements of the candiescandies array to obtain the required result.

\text{minimum_candies} = \sum_{i=0}^{n-1} candies[i], \text{where } n = \text{length of the ratings array.}

```java
public class Solution {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int sum = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            sum += candies[i];
        }
        return sum;
    }
}
```

Complexity Analysis

Time complexity : O(n)O(n). The array candiescandies of size nn is traversed thrice.

Space complexity : O(n)O(n). An array candiescandies of size nn is used.

Approach 4: Single Pass Approach with Constant Space
Algorithm

This approach relies on the observation(as demonstrated in the figure below as well) that in order to distribute the candies as per the given criteria using the minimum number of candies, the candies are always distributed in terms of increments of 1. Further, while distributing the candies, the local minimum number of candies given to a student is 1. Thus, the sub-distributions always take the form: \text{1, 2, 3, ..., n}1, 2, 3, ..., n or \text{n,..., 2, 1}n,..., 2, 1, whose sum is simply given by n(n+1)/2n(n+1)/2.

Now, we can view the given rankingsrankings as some rising and falling slopes. Whenever the slope is rising, the distribution takes the form: \text{1, 2, 3, ..., m}1, 2, 3, ..., m. Similarly, a falling slope takes the form: \text{k,..., 2, 1}k,..., 2, 1. An issue that arises now is that the local peak point can be included in only one of the slopes. Whether to include the local peak point(\text{n}n) in the rising slope or the falling slope?

In order to decide it, we can observe that in order to satisfy both the right neighbour and the left neighbour criteria, the peak point's count needs to be the max. of the counts determined by the rising and the falling slopes. Thus, in order to determine the number of candies required, the peak point needs to be included in the slope which contains more number of points. The local valley point can also be included in only one of the slopes, but this issue can be resolved easily, since the local valley point will always be assigned a candy count of 1(which can be subtracted from the next slope's count calculations).

Coming to the implementation, we maintain two variables old\_slopeold_slope and new\_slopenew_slope to determine the occurence of a peak or a valley. We also use upup and downdown variables to keep a track of the count of elements on the rising slope and on the falling slope respectively(without including the peak element). We always update the total count of candiescandies at the end of a falling slope following a rising slope (or a mountain). The leveling of the points in rankingsrankings also works as the end of a mountain. At the end of the mountain, we determine whether to include the peak point in the rising slope or in the falling slope by comparing the upup and downdown variables up to that point. Thus, the count assigned to the peak element becomes: \text{max}(up, down) + 1max(up,down)+1. At this point, we can reset the upup and downdown variables indicating the start of a new mountain.

The following figure shows the cases that need to be handled for this example:

rankings: [1 2 3 4 5 3 2 1 2 6 5 4 3 3 2 1 1 3 3 3 4 2]

![](https://leetcode.com/problems/candy/Figures/135_Candy_Constant_Space.PNG)

From this figure, we can see that the candy distributions in the subregions always take the form \text{1, 2, ...n}1, 2, ...n or \text{n, ..., 2, 1}n, ..., 2, 1. For the first mountain comprised by the regions aa and bb, while assigning candies to the local peak point (pt. 5pt.5), it needs to be included in aa to satisfy the left neighbour criteria. The local valley point at the end of region bb (pt. 8pt.8) marks the end of the first mountain (region cc). While performing the calculations, we can include this point in either the current or the following mountain. The pt. 13pt.13 marks the end of the second mountain due to levelling of the pt. 13pt.13 and pt. 14pt.14. Since, region ee has more points than region dd, the local peak (pt. 10pt.10) needs to be included in region ee to satisfy the right neighbour criteria. Now, the third mountain ff can be considered as a mountian with no rising slope (up=0up=0) but only a falling slope. Similarly, pt. 16, 18, 19pt.16,18,19 also act as the mountain ends due to the levelling of the points.

```java
public class Solution {
    public int count(int n) {
        return (n * (n + 1)) / 2;
    }
    public int candy(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int candies = 0;
        int up = 0;
        int down = 0;
        int old_slope = 0;
        for (int i = 1; i < ratings.length; i++) {
            int new_slope = (ratings[i] > ratings[i - 1]) ? 1 : (ratings[i] < ratings[i - 1] ? -1 : 0);
            if ((old_slope > 0 && new_slope == 0) || (old_slope < 0 && new_slope >= 0)) {
                candies += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }
            if (new_slope > 0)
                up++;
            if (new_slope < 0)
                down++;
            if (new_slope == 0)
                candies++;

            old_slope = new_slope;
        }
        candies += count(up) + count(down) + Math.max(up, down) + 1;
        return candies;
    }
}
```

Complexity Analysis

Time complexity : O(n)O(n). We traverse the rankingsrankings array once only.

Space complexity : O(1)O(1). Constant Extra Space is used.
