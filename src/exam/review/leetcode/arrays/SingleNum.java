package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 16-12-17.
 */
public class SingleNum {
    public static int singleNumber(int[] nums) {
        int x = 0;
        for(int i:nums) {
            x^=i;
        }
        return x;
    }

    public static void main(String[] args) {
        int[] array = new int[] {1,1,2,2,3};
        System.out.println(singleNumber(array));
        String a = "abc";
    }
}
