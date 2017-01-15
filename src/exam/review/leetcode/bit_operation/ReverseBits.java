package exam.review.leetcode.bit_operation;

/**
 * Created by shanwu on 17-1-15.
 */
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;

        for(int i = 0; i < 32; i++) {
            result = result + (n & 1);
            n = n >>> 1;
            if(i < 31) {
                result = result << 1;
            }
        }

        return result;

    }
}
