package leetcode.easy;

/**
 * Created by shanwu on 16-12-22.
 */
public class AddDigits {
    /**
     * todo: try to figure it out http://stackoverflow.com/questions/14950422/fastest-method-for-adding-summing-the-individual-digit-components-of-a-number
     * @param num
     * @return
     */
    public int addDigits2(int num) {
        if (num == 0) {
            return 0;
        }
        if (num % 9 == 0) {
            return 9;
        } else {
            return num % 9;
        }
    }

}
