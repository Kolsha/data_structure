package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 17-1-7.
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int sizeA = a.length(), sizeB = b.length();
        int diff = sizeA - sizeB;
        String newA = a;
        String newB = b;

        if(diff > 0) {
            while(diff-- > 0) {
                sb.append("0");
            }
            sb.append(b);
            newB = sb.toString();
        } else if (diff < 0) {
            while(diff++ <0) {
                sb.append("0");
            }
            sb.append(a);
            newA = sb.toString();
        }

        final int size = newA.length();
        sb = new StringBuilder();
        int remain = 0;
        int sum = 0;
        for (int i = size -1; i >=0; i--) {
            sum = remain + (newA.charAt(i) - '0' ) + (newB.charAt(i) - '0' );
            remain = 0;
            if(sum >= 2) {
                sum = sum % 2;
                remain = 1;
            }
            sb.insert(0, sum);
            sum = 0;
        }

        if(remain == 1) {
            sb.insert(0, remain);
        }

        return sb.toString();
    }
}
