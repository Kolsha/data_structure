package exam.review.leetcode.arrays.string.string_math_ops;

/**
 * Created by shanwu on 17-3-25.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        final int m = num1.length();
        final int n = num2.length();
        int[] pos = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // p1 高位, p2 低位
                int p1 = i + j, p2 = p1 + 1;
                int sum = mul + pos[p2];

                pos[p1] = pos[p1] + sum / 10;
                pos[p2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int p : pos)
            if (!(sb.length() == 0 && p == 0))
                sb.append(p);


        return sb.length() == 0 ? "0" : sb.toString();
    }
}
