package leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by shanwu on 16-12-24.
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap();
        final int size1 = nums1.length;
        final int size2 = nums2.length;
        HashSet<Integer> set = new HashSet();
        for(int i = 0; i < size1; i++) {
            map.put(nums1[i], 1);
        }

        for(int i = 0; i < size2; i++) {
            if(map.get(nums2[i])!= null) {
                set.add(nums2[i]);
            }
        }

        int[] result = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            result[i] = iterator.next();
            i++;
        }
        return result;
    }
}
