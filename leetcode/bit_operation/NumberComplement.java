package exam.review.leetcode.bit_operation;

/**
 * Created by shanwu on 17-1-10.
 */
public class NumberComplement {
    public int findComplement(int num) {
        int mask = (Integer.highestOneBit(num) << 1) -1;
        return ~num & mask;
    }
}
