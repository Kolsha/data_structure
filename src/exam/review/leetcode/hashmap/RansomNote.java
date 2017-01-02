package exam.review.leetcode.hashmap;

import java.util.HashMap;

/**
 * Created by shanwu on 17-1-1.
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<String, Integer> map = new HashMap();
        for (int i = 0; i < magazine.length(); i++) {
            String key = String.valueOf(magazine.charAt(i));
            Object value = map.get(key);
            if (value == null) {
                map.put(key, 1);
            } else {
                int count = (int) value;
                map.put(key, ++count);
            }
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            String key = String.valueOf(ransomNote.charAt(i));
            Object value = map.get(key);

            if (value == null || (int) value < 1) {
                return false;
            } else {
                int count = (int) value;
                map.put(key, --count);
            }
        }

        return true;
    }
}
