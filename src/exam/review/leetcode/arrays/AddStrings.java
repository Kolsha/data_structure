package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 17-1-8.
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        final int size1 = num1.length();
        final int size2 = num2.length();
        StringBuilder aSb = new StringBuilder(num1);
        StringBuilder bSb = new StringBuilder(num2);

        int diff = size1 - size2;
        while(diff!=0) {
            if(diff > 0) {
                bSb.insert(0,0);
                diff--;
            } else if(diff < 0) {
                aSb.insert(0,0);
                diff++;
            }
        }

        String s1 = aSb.toString();
        String s2 = bSb.toString();
        int carry = 0;
        int sum = 0;
        StringBuilder result = new StringBuilder();
        for(int i = s1.length() -1; i >= 0; i--) {
            int temp1 = s1.charAt(i) - '0';
            int temp2 = s2.charAt(i) - '0';
            sum = temp1 + temp2 + carry;
            result.insert(0, sum%10);
            carry = sum/10;
        }

        if(carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }
}
