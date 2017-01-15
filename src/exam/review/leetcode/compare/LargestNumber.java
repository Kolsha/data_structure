package exam.review.leetcode.compare;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by shanwu on 17-1-15.
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        String[] numStr = new String[nums.length];
        final int size = nums.length;
        for (int i = 0; i < size; i++) {
            numStr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStr, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1);
            }
        });
        if (numStr[0].charAt(0) == '0')
            return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : numStr) {
            sb.append(s);
        }

        return sb.toString();
    }
}
