package exam.review.leetcode.easy;

import java.util.HashMap;

/**
 * Created by shanwu on 16-12-30.
 */
public class BullsAndCows {
    public static String getHint(String secret, String guess) {
        int bull = 0;
        int cow = 0;
        HashMap<Integer, Integer> map = new HashMap();
        for(int i =0 ; i < secret.length(); i++) {
            char key = secret.charAt(i);
            Object temp = map.get((int)key);
            if(temp == null) {
                map.put((int)key ,1);
            } else {
                int count = (Integer) temp;
                map.put((int)key, ++count);
            }
        }

        for(int i = 0; i < guess.length(); i++) {
            if(guess.charAt(i) == secret.charAt(i)) {
                bull++;
                int count = map.get((int)guess.charAt(i));
                map.put((int) guess.charAt(i), --count);
            }
        }

        for(int i = 0; i < guess.length(); i++) {
            if (map.get((int) guess.charAt(i)) != null && map.get((int) guess.charAt(i)) > 0) {
                cow++;
                int count = map.get((int) guess.charAt(i));
                map.put((int) guess.charAt(i), --count);
            }
        }
        return bull+ "A" + cow + "B";
    }

    public static void main(String[] args) {
        System.out.println(getHint("1122","1222"));
    }
}
