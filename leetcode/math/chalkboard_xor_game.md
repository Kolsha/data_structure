### [810. Chalkboard XOR Game](https://leetcode.com/problems/chalkboard-xor-game/)

We are given non-negative integers nums[i] which are written on a chalkboard.  Alice and Bob take turns erasing exactly one number from the chalkboard, with Alice starting first.  If erasing a number causes the bitwise XOR of all the elements of the chalkboard to become 0, then that player loses.  (Also, we'll say the bitwise XOR of one element is that element itself, and the bitwise XOR of no elements is 0.)

Also, if any player starts their turn with the bitwise XOR of all the elements of the chalkboard equal to 0, then that player wins.

Return True if and only if Alice wins the game, assuming both players play optimally.

Example:
```
Input: nums = [1, 1, 2]
Output: false
Explanation: 
Alice has two choices: erase 1 or erase 2. 
If she erases 1, the nums array becomes [1, 2]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 2 = 3. Now Bob can remove any element he wants, because Alice will be the one to erase the last element and she will lose. 
If Alice erases 2 first, now nums becomes [1, 1]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 1 = 0. Alice will lose.
```

Notes:

- 1 <= N <= 1000. 
- 0 <= nums[i] <= 2^16.

### Solution

#### 方法一：数学
下文将「按位异或运算」简称为「异或」。

根据游戏规则，轮到某个玩家时，如果当前黑板上所有数字异或结果等于 0，则当前玩家获胜。由于 Alice 是先手，因此如果初始时黑板上所有数字异或结果等于 0，则 Alice 获胜。

下面讨论初始时黑板上所有数字异或结果不等于 0 的情况。

由于两人交替擦除数字，且每次都恰好擦掉一个数字，因此对于这两人中的任意一人，其每次在擦除数字前，黑板上剩余数字的个数的奇偶性一定都是相同的。

这启发我们从数组 nums 长度的奇偶性来讨论。如果 nums 的长度是偶数，先手 Alice 是否有可能失败呢？假设 Alice 面临失败的状态，则只有一种情况，即无论擦掉哪一个数字，剩余所有数字的异或结果都等于 0。

下面证明这是不可能的。设数组 nums 的长度为 n，n 是偶数，用 Image 表示异或，记 S 为数组 nums 的全部元素的异或结果，则有

![](https://mmbiz.qpic.cn/mmbiz_png/QD6vPulxyyW8aQZtPDS7nhnTBoDOsBSaSCMbnr3s5JibialeAl7lodNZolPPZiabNzqhlAnXfje2WfkhIEicTQSmBw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

记 Si 为擦掉 nums[i] 之后，剩余所有数字的异或结果，则有

![](https://mmbiz.qpic.cn/mmbiz_png/QD6vPulxyyW8aQZtPDS7nhnTBoDOsBSa9k1rf80WzgbNTalDl0ATaiaC6Go9ia8WNCssugiciaSzfGL595ZLN1ZotA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

等式两边同时异或 nums[i]，由于对任意整数 x 都有 ![](https://mmbiz.qpic.cn/mmbiz_png/QD6vPulxyyW8aQZtPDS7nhnTBoDOsBSag5XxPvkBnibI17NHicDTyZOQwK81Oa1PxoT2OK3legTmL7y2OKGibl0Uw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)，得

![](https://mmbiz.qpic.cn/mmbiz_png/QD6vPulxyyW8aQZtPDS7nhnTBoDOsBSa7ktzfI6xiaeq1hshpwG2ibpRBTRBLEhrdWkEW41BfTjlnC1lUgibRRcFg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

如果无论擦掉哪一个数字，剩余的所有数字异或结果都等于 0，即对任意 ![](https://mmbiz.qpic.cn/mmbiz_png/QD6vPulxyyW8aQZtPDS7nhnTBoDOsBSaCgOtDRtkdDEjhLuWs96j2xSibb9CnibSFUvOhicArAEeFEyboQt6tzu4A/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)，都有 Si = 0。因此对所有的 Si 异或结果也等于 0，即

![](https://mmbiz.qpic.cn/mmbiz_png/QD6vPulxyyW8aQZtPDS7nhnTBoDOsBSa8Qokmg9LQJiaWMmtS6GTCBlEfoNG2nZNT4TUWo9TKQfq9ZP4GUaTSRA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)


将 ![](https://mmbiz.qpic.cn/mmbiz_png/QD6vPulxyyW8aQZtPDS7nhnTBoDOsBSaPhenBTEhfAGtXV4xr5opicWbpe02K1EnibNZu4d22s34u2PHt42WENZA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1) 代入上式，并根据异或运算的交换律和结合律化简，有

![](https://mmbiz.qpic.cn/mmbiz_png/QD6vPulxyyW8aQZtPDS7nhnTBoDOsBSahqlIlMibIPXcENunibemmB0JoaZQhKne9sBzSiaR5FwM1ycdGHmDtOwUA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

上述计算中，第 3 行的左边括号为 n 个 S 异或，由于 n 是偶数，因此 n 个 S 异或的结果是 0。

根据上述计算，可以得到 S = 0，与实际情况 $S \neq 0$ 矛盾。

**因此当数组的长度是偶数时，先手 Alice 总能找到一个数字，在擦掉这个数字之后剩余的所有数字异或结果不等于 0。**

在 Alice 擦掉这个数字后，黑板上剩下奇数个数字，无论 Bob 擦掉哪个数字，留给  Alice 的一定是黑板上剩下偶数个数字，此时 Alice 要么获胜，要么仍可以找到一个数字，在擦掉这个数字之后剩余的所有数字异或结果不等于 0，因此 Alice 总能立于不败之地。

同理可得，当数组的长度是奇数时，Alice 在擦掉一个数字之后，留给 Bob 的一定是黑板上剩下偶数个数字，因此 Bob 必胜。

综上所述，当且仅当以下两个条件至少满足一个时，Alice 必胜：

- 数组 nums 的全部元素的异或结果等于 0；

- 数组 nums 的长度是偶数。

代码实现时，可以先判断数组 nums 的长度是否是偶数，当长度是偶数时直接返回 true，当长度是奇数时才需要遍历数组计算全部元素的异或结果。该实现方法在数组长度是偶数时只需要 O(1) 的时间即可得到答案。

```java

class Solution {
    public boolean xorGame(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }
}
```

#### 复杂度分析
- 时间复杂度：O(n)，其中 n 是数组 nums 的长度。最坏情况下，需要遍历数组一次，计算全部元素的异或结果。

- 空间复杂度：O(1)。