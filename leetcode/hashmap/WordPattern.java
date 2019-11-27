package exam.review.leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shanwu on 17-1-17.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        if(pattern!=null && str == null) {
            return false;
        } else if(pattern== null && str!=null) {
            return false;
        } else if (pattern == null && str == null) {
            return true;
        }

        String[] array = str.split(" ");

        final int sizeA = pattern.length();
        final int sizeB = array.length;

        if(sizeA!=sizeB) {
            return false;
        }

        HashMap map = new HashMap(); // important key here

        for(Integer i = 0; i < sizeA; i++) {
            // important key here
            Character tempA = pattern.charAt(i);
            String tempB = array[i];
            if(map.put(tempA,i) != map.put(tempB, i)) {
                return false;
            }
        }
        return true;
    }
}
