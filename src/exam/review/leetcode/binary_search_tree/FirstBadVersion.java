package exam.review.leetcode.binary_search_tree;

/**
 * Created by shanwu on 17-1-23.
 */
public class FirstBadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}

class VersionControl {
    boolean isBadVersion(int n) {
        return n > 100; // temp implementation
    }
}


