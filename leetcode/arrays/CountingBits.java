package exam.review.leetcode.arrays;

import java.util.Arrays;

/**
 * Created by shanwu on 17-1-8.
 */
public class CountingBits {
    public static int[] countBits(int num) {
        int[] result = new int[num+1];
        for(int i = 0; i <=num; i++) {
            int j = i;
            result[i] = getBits(j);
        }

        return result;
    }

    public static int getBits(int target) {
        int count = 0;
        while(target > 0) {
            count = count + (target & 1); // must have the ()
            target = target >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(4)));
    }
}
