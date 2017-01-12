package exam.review.leetcode.arrays.string;

/**
 * Created by shanwu on 17-1-12.
 */
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        if(version1 == null || version2 == null) {
            return -1;
        }

        String[] verArray1 = version1.split("\\.");
        String[] verArray2 = version2.split("\\.");
        final int ver1Size = verArray1.length;
        final int ver2Size = verArray2.length;
        final int length = Math.max(ver1Size, ver2Size);
        for(int i = 0; i < length; i++) {
            int ver1 = (i < ver1Size) ? Integer.parseInt(verArray1[i]):0;
            int ver2 = (i < ver2Size) ? Integer.parseInt(verArray2[i]):0;

            if(ver1 > ver2) {
                return 1;
            }

            if(ver1 < ver2) {
                return -1;
            }
        }

        return 0;
    }
}
