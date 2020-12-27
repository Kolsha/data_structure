
##### Approach #1 Brute force [Time Limit Exceeded]

###### Intuition

According to the question, we need to find mm such that $[S2,m]$ is the largest subsequence that can be found in $S1$. $S2$ is essentially $[s2,n2]$ and $S1$ is $[s1,n1]$ and so, we can find the number of times $s2$ repeats in $[s1,n1]$, say \text{repeat_count}. And the number of times $S2$ repeats in $S1$ is therefore \text{(repeat_count/n2)}. Simple.

###### Algorithm

Initialize index = 0 and repeat_count = 0. index represents the current index in $s2$ to be checked against $s1$ and repeat_count represents the number of times s2 repeats in S1.
Iterate over the variable ii from $0$ to $n1-1$:
  - Iterate over the variable $j$ from $0$ to $size(s1)-1$:
    - If $s1[j]$ is equal to $s2[index]$, increment index.

    - If index is equal to size(s2), this implies that s2 has completed one repartition and hence set index=0 and increment the repeat_count.

  - Return (repeat_count / n2) since, S2 is [s2,n2].

```C++
int getMaxRepetitions(string s1, int n1, string s2, int n2)
{
    int index = 0, repeat_count = 0;
    int s1_size = s1.size(), s2_size = s2.size();
    for (int i = 0; i < n1; i++) {
        for (int j = 0; j < s1_size; j++) {
            if (s1[j] == s2[index])
                ++index;
            if (index == s2_size) {
                index = 0;
                ++repeat_count;
            }
        }
    }
    return repeat_count / n2;
}
```

#### Complexity Analysis

- Time complexity: $O(n1*size(s1))$

    - We iterate over the entire length of string $s1$ for $n1$ times.

- Space complexity: $O(1)$ extra space for index and repeat_count.


#### Approach #2 A better brute force [Accepted]
##### Intuition

In Approach #1, we simply checked for repetition over the entire [s1,n1]. However, n1 could be quiet large and thus, is inefficient to iterate over complete S1. We can take advantage of the fact that s1 is repeating and hence, we could find a pattern of repetition of s2 in S1. Once, we get the repetition pattern, we can easy calculate how many times the pattern repeats in n2 in O(1).

But what's the pattern!

In approach #1, we kept index which tells the index to search in s2. We try to see in the below illustration if this index repeats itself after some fixed iterations of s1 or not and if so, then how can we leverage it.

![](https://leetcode.com/problems/count-the-repetitions/Figures/466/count_the_repititions.png)

After finding the repitition pattern, we can calculate the sum of repeating pattern, part before repitition and part left after repitition as the result in O(1)O(1).

But will this repitition always take place?

Yes! By **Pigeonhole principle**, which states that if nn items are put into mm containers, with n > m, then at least one container must contain more than one item. So, according to this, we are sure to find 2 same index after scanning at max size(s2) blocks of s1.

##### Algorithm

- Intialize count = 0 and index = 0, which are same as in Approach #1.
- Initialize 2 arrays, say indexr and countr of size size(s2)+1), initialized with 0. The size (size(s2)+1) is based on the Pigeonhole principle as discussed above. The 2 arrays specifies the index and count at the start of each s1 block.
- Iterate over i from 0 to n1−1:
  - Iterate over j from 0 to size(s1)−1:

    - If s1[j]==s2[index], increment index.
    - If index is equal to size(s2), set index=0 and increment count.
  - Set countr[i]=count and indexr[i]=index

  - Iterate over k from 0 to i−1:

    - If we find the repitition, i.e. current index=indexr[k], we calculate the count for block before the repitition starts, the repeating block and the block left after repitition pattern, which can be calculated as:
$\begin{align} \text{prev_count} &= \text{countr}[k] \\ \text{pattern_count} &= (\text{countr}[i] - \text{countr}[k]) * \frac{n1 - 1 - k}{i - k} \\ \text{remain_count} &= \text{countr}\left[k + \left(n1 - 1 - k\right) \% \left(i - k \right)\right] - \text{countr}[k] \end{align}$

    - Sum the 3 counts and return the sum divided by n2n2, since \text{S2 = [s2,n2]}S2 = [s2,n2]
- If no repetition is found, return countr[n1-1]/n2.

```C++
int getMaxRepetitions(string s1, int n1, string s2, int n2)
{
    if (n1 == 0)
        return 0;
    int indexr[s2.size() + 1] = { 0 }; // index at start of each s1 block
    int countr[s2.size() + 1] = { 0 }; // count of repititions till the present s1 block
    int index = 0, count = 0;
    for (int i = 0; i < n1; i++) {
        for (int j = 0; j < s1.size(); j++) {
            if (s1[j] == s2[index])
                ++index;
            if (index == s2.size()) {
                index = 0;
                ++count;
            }
        }
        countr[i] = count;
        indexr[i] = index;
        for (int k = 0; k < i; k++) {
            if (indexr[k] == index) {
                int prev_count = countr[k];
                int pattern_count = (countr[i] - countr[k]) * (n1 - 1 - k) / (i - k);
                int remain_count = countr[k + (n1 - 1 - k) % (i - k)] - countr[k];
                return (prev_count + pattern_count + remain_count) / n2;
            }
        }
    }
    return countr[n1 - 1] / n2;
}
```

#### Complexity analysis

- Time complexity: O(size(s1)*size(s2)).

  - According to the Pigeonhole principle, we need to iterate over s1s1 only (\text{size(s2)+1})(size(s2)+1) times at max.

- Space complexity: O(size(s2)) extra space for indexr and countr string.
