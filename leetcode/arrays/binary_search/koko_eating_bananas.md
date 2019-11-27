### 875. Koko Eating Bananas
https://leetcode.com/problems/koko-eating-bananas/

Koko loves to eat bananas.  There are `N` piles of bananas, the `i`-th pile has `piles[i]` bananas.  The guards have gone and will come back in `H` hours.

Koko can decide her bananas-per-hour eating speed of `K`.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than `K` bananas, she eats all of them instead, and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer `K` such that she can eat all the bananas within H hours.

**Example 1:**
```
Input: piles = [3,6,7,11], H = 8
Output: 4
Explaination:
1st hour: 3 bananas from 1st pile
2nd hour: 4 bananas from 2nd pile
3rd hour: 2 bananas from 2nd pile
4th hour: 4 bananas from 3rd pile
5th hour: 3 bananas from 3rd pile
6th hour: 4 bananas from 4th pile
7th hour: 4 bananas from 4th pile
8th hour: 3 bananas from 4th pile
```

**Solution:**

每小时 Koko 从数组中任选一堆香蕉，并从那堆香蕉中吃完 K 个。在 H 小时内以 K banana/hrs 吃完所有香蕉，**其 K 值大小有明确固定范围，因此使用 Binary Search 来减少搜索范围即可**。

K的最小值为`1`，最大值为`数组的最大值`，将合格条件自 `if (k == target)` 改为 `if(canEatAll(piles, K, H))` 即可
```java
class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1, hi = getMaxPile(piles);

        // Binary search to find the smallest valid K.
        while (lo <= hi) {
            int K = lo + ((hi - lo) >> 1);
            if (canEatAll(piles, K, H)) {
                hi = K - 1;
            } else {
                lo = K + 1;
            }
        }

        return lo;
    }

    private boolean canEatAll(int[] piles, int K, int H) {
        int countHour = 0; // Hours take to eat all bananas at speed K.

        for (int pile : piles) {
            countHour += pile / K;
            if (pile % K != 0)
                countHour++;
        }
        return countHour <= H;
    }

    private int getMaxPile(int[] piles) {
        int maxPile = Integer.MIN_VALUE;
        for (int pile : piles) {
            maxPile = Math.max(pile, maxPile);
        }
        return maxPile;
    }
}
```