package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 17-1-7.
 */
public class StringToInteger {
    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        long result = 0;
        int sign = 1;
        int start = 0;
        char temp = str.charAt(0);

        while (temp == ' ') {
            temp = str.charAt(++start);
        }

        if (temp == '-') {
            sign = -1;
            start++;
        } else if (temp == '+') {
            sign = 1;
            start++;
        }

        final int size = str.length();

        for (int i = start; i < size; i++) {
            temp = str.charAt(i);

            if (Character.isDigit(temp)) {
                result = result * 10 + (temp - '0');
                if (sign == 1 && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (sign == -1 && sign * result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                return sign * (int) result; // error: incompatible types: possible lossy conversion from long to int
            }
        }
        return sign * (int) result; // error: incompatible types: possible lossy conversion from long to int
    }
}
