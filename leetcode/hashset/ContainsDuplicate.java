package exam.review.leetcode.hashset;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by shanwu on 16-12-22.
 */
public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num:nums) {
            if(!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean b = containsDuplicate(new int[]{3,3});
        System.out.println(b);
    }
}
