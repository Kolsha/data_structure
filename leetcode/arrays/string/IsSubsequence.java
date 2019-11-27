package exam.review.leetcode.arrays.string;

/**
 * Created by shanwu on 17-1-23.
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }

        int indexT = 0, indexS = 0;
        final int sizeT = t.length();
        final int sizeS = s.length();
        while (indexT < sizeT) {
            if (s.charAt(indexS) == t.charAt(indexT)) {
                indexS++;
                if (indexS == sizeS) {
                    return true;
                }
            }
            indexT++;
        }
        return false;
    }
}
