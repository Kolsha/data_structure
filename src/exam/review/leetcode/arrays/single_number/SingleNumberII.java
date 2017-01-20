package exam.review.leetcode.arrays.single_number;

import java.util.HashMap;

/** todo bit manipulation solution
 * Created by shanwu on 17-1-20.
 */
public class SingleNumberII {
    public static int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        for(int n:nums) {
            Object val = map.get(n);
            if(val!=null) {
                int num = (int) val;
                map.put(n,++num);
            } else {
                map.put(n, 1);
            }
        }

        for(int i = 0; i < nums.length; i++) {
            Object val = map.get(nums[i]);
            int c = (int) val;
            if(c!=3) {
                return nums[i];
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2,2,3,2}));
    }
}
