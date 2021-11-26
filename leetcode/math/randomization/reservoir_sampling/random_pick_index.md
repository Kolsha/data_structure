### 398. Random Pick Index

https://leetcode.com/problems/random-pick-index/

Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

**Note:**
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:
```
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
```

Solution

**Approach 1: Reservoir Sampling approach**

To those who don't understand why it works. Consider the example in the OJ
{1,2,3,3,3} with target 3, you want to select 2,3,4 with a probability of 1/3 each.

2 : It's probability of selection is `1 * (1/2) * (2/3) = 1/3`
3 : It's probability of selection is `(1/2) * (2/3) = 1/3`
4 : It's probability of selection is just `1/3`

A bit more explanation. Let's take index 2 for example.

- The first time we saw target 3 is at index 2. The count is 0. Our reservoir only have 0 and we need to pick rnd.nextInt(++count) == 0. The probability is 1. Result = 2.
- Then we went to index 3. The count is 1. Our reservoir is has [0,1]. We say if we get 0, we'll change the the result, otherwise we keep it. Then chance that we keep the result=2 is 1/2 which means we got 1 from the reservoir.
- Then we went to index 4. count =2. Our reservoir has [0,1,2]. Same as before, if we get 0, then we'll change the result. The chance we get 0 is 1/3, while the chance we didn't get is 2/3. i.e The chance we keep the result == 2 is 2/3.
The chance we get index=2 is 1*1/2*2/3=1/3

```java
class Solution {
    private int[] array = null;
    private Random rand = new Random();
    public Solution(int[] nums) {
        array = nums;
    }
    
    public int pick(int target) {
        int count = 0;
        int result = -1;
        for(int i = 0; i < array.length; i++) {
            if(array[i] != target) {
                continue;
            }
            
            if(rand.nextInt(++count) == 0) {
                result = i;
            }
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
```