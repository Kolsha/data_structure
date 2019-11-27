package exam.review.leetcode.hashmap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanwu on 17-1-2.
 */
public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        HashMap<Integer, Integer> map = new HashMap();
        ArrayList<Integer> result = new ArrayList();
        for(int i = 0; i < nums1.length; i++) {
            Object val = map.get(nums1[i]);

            if(val == null) {
                map.put(nums1[i], 1);
            } else {
                int num = (int) val;
                map.put(nums1[i], ++num);
            }
        }

        for(int i = 0 ; i < nums2.length; i++) {
            Object val = map.get(nums2[i]);

            if(val!=null && ((int)val) > 0) {
                int num =(int) val;
                map.put(nums2[i], --num);
                result.add(nums2[i]);
            }
        }

        int[] resultArray = new int[result.size()];
        int j = 0;
        for(Integer val: result) {
            resultArray[j] = val;
            j++;
        }
        return resultArray;
    }
}
