package exam.review.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanwu on 16-12-29.
 */
public class FirstUniqueCharacterinaString {
    public static int firstUniqChar(String s) {
        HashMap<Integer, Integer> map = new HashMap();
        ArrayList<Integer> list = new ArrayList();

        for (int i = 0; i < s.length(); i++) {
            int c = (int) s.charAt(i);

            if (map.get(c) == null) {
                map.put(c, i);
                list.add(new Integer(i));
            } else {
                int index = map.get(c);
                list.remove(new Integer(index));
            }

        }
        return list.isEmpty() ? -1 : (int) list.get(0);
    }
}
