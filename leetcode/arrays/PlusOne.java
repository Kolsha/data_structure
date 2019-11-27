package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 16-12-29.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int result = 0;
        for(int i = digits.length-1; i >=0; i--) {
            if(i == digits.length - 1) {
                digits[i] = result + digits[i] + 1;
            } else {
                digits[i] = result + digits[i];
            }
            result = 0;
            if(digits[i] == 10) {
                digits[i] = 0;
                result = 1;
            }
        }

        int finalSize = digits.length;
        if(result!=0) {
            finalSize = digits.length + 1;
        } else {
            return digits;
        }

        int[] resultArray = new int[finalSize];
        resultArray[0] = 1;
        System.arraycopy(digits,0,resultArray,1,digits.length);

        return resultArray;
    }
}
