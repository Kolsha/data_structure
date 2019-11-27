package exam.review.leetcode.bit_operation;

/**
 * Created by shanwu on 17-1-8.
 */
public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        if(b == 0) {
            return a;
        }

        int carry = (a & b) << 1;
        int sum = (a ^ b);
        return getSum(sum, carry);
    }
}
