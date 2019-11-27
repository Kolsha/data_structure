package exam.review.leetcode.arrays.string;

/**
 * Created by shanwu on 17-3-6.
 */
public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if(haystack == null || needle == null) {
            return -1;
        }

        final int hSize = haystack.length();
        final int nSize = needle.length();

        if(hSize == 0 && hSize == nSize) {
            return 0;
        } else if(hSize < nSize) {
            return -1;
        }

        final int diff = hSize - nSize;
        for(int i = 0; i <= diff; i++) {
            if(haystack.substring(i, i+ nSize).equals(needle)) {
                return i;
            }
        }

        return -1;
    }
}
