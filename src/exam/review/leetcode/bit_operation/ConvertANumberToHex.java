package exam.review.leetcode.bit_operation;

/** http://stackoverflow.com/questions/12398889/why-is-0xf-equal-to-0xfffffff0-on-a-32-bit-machine
 *  Created by shanwu on 17-1-9.
 */
public class ConvertANumberToHex {
    public String toHex(int num) {
        if(num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while(num != 0) {
            int remain = num & 0xf;
            char temp = ( remain >= 10 ) ? (char) (remain - 10 + 'a') : (char) (remain + '0');
            sb.insert(0, temp);
            num = num >>> 4;
        }

        return sb.toString();
    }
}
