package exam.review.leetcode.math;

import java.util.HashMap;

/**
 * Created by shanwu on 17-3-2.
 */
public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if(denominator == 0) {
            throw new RuntimeException("divide by 0");
        }

        if(numerator == 0) {
            return "0";
        }


        StringBuilder sb = new StringBuilder();

        // + or -
        String sign = (numerator > 0 ^ denominator > 0 ) ? "-":"";
        sb.append(sign);

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // integral part
        sb.append(num/den);
        num = num % den;
        if(num == 0) {
            return sb.toString();
        }

        sb.append(".");

        // fractional part
        HashMap<Long,Integer> map = new HashMap<>();
        map.put(num, sb.length());

        while(num!=0) {
            num = num * 10;
            sb.append(num/den);
            num = num%den;

            if(map.containsKey(num)) {
                int index = map.get(num);
                sb.insert(index, '(');
                sb.append(')');
                return sb.toString();
            }
            map.put(num, sb.length());
        }
        return sb.toString();
    }
}
