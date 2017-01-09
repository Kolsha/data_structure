package exam.review.leetcode.hashmap;

import java.util.HashMap;

/**
 * Created by shanwu on 17-1-9.
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap();

        final int size = s.length();
        for(int i = 0; i < size; i++) {
            char temp = s.charAt(i);
            Object val = map.get(temp);
            if(val == null) {
                map.put(temp, 1);
            } else {
                int num = (int) val;
                map.put(temp, ++num);
            }
        }


        final int size1 = t.length();
        for(int i = 0; i < size1; i++) {
            char temp = t.charAt(i);
            Object val = map.get(temp);
            int num = (val!=null) ? (int) val: -1;
            if(val == null || num < 1) {
                return false;
            } else {
                map.put(temp, --num);
            }
        }
        return size == size1;
    }
}
