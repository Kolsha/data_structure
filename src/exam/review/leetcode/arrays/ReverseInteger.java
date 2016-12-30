package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 16-12-17.
 */
public class ReverseInteger {
    // FIXME: 16-12-17 wrong approach
    public static int reverse(int x) {
        if (x > 0) {
            char[] valueArray = String.valueOf(x).toCharArray();
            final int size = valueArray.length;
            for (int i = 0; i < size / 2; i++) {
                char temp = valueArray[i];
                valueArray[i] = valueArray[size - 1 - i];
                valueArray[size - 1 - i] = temp;
            }

            return Integer.parseInt(String.valueOf(valueArray));
        } else if (x < 0) {
            char[] valueArray = String.valueOf(-x).toCharArray();
            final int size = valueArray.length;
            for (int i = 0; i < size / 2; i++) {
                char temp = valueArray[i];
                valueArray[i] = valueArray[size - 1 - i];
                valueArray[size - 1 - i] = temp;
            }
            return -Integer.parseInt(String.valueOf(valueArray));


        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));

    }
}
