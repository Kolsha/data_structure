package exam.review.leetcode.easy;

/**
 * Created by shanwu on 16-12-21.
 */
public class ReverseInteger {
    public static int reverse(int x) {
        long result = 0; // try to avoid overflow,, long which has 64bits will have wider value range compare to int


        while (x != 0) {
            result = 10 * result;
            result = result + x % 10;

            if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) { // try to avoid overflow, for example: 1534236469
                return 0;
            }
            x = x / 10;
        }

        result += x;

        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));

    }
}
