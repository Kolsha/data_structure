package exam.review.leetcode.bit_operation;

/**
 * Created by shanwu on 17-1-1.
 */
public class NumberOf1Bits {
    // you need to treat n as an unsigned value
    // http://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
    // http://stackoverflow.com/questions/2811319/difference-between-and
    public int hammingWeight(int n) {
        int count = 0;
        while(n!=0) {
            count = count + (n & 1);
            n = n >>> 1;
        }
        return count;
    }
}
