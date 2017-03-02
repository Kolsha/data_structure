package exam.review.leetcode.math;

/**
 * Created by shanwu on 17-3-2.
 */
public class DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (divisor == 0) {
            throw new RuntimeException("divide by zero exception");
        }

        // positive or negative
        int sign = (dividend > 0 ^ divisor > 0) ? -1 : 1;

        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        if (sign == 1 && ldividend < ldivisor) {
            return 0;
        }


        if (ldividend == ldivisor) {
            return sign;
        }

        // get divide
        long num = calDivide(ldividend, ldivisor);

        // handle overflow
        if (num > Integer.MAX_VALUE) {
            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }


        return (int) (sign * num);

    }

    // use recursive to prevent time limit exceeded
    private static long calDivide(long ldividend, long ldivisor) {
        // Recursion exit condition
        if (ldividend < ldivisor) return 0;

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        //Look for additional value for the multiple from the reminder (dividend - sum) recursively.
        return multiple + calDivide(ldividend - sum, ldivisor);
    }

    public static void main(String[] args) {
        int num = divide(-2147483648, -1);
        System.out.println(num);
    }
}
