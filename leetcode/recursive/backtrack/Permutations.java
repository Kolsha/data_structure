package exam.review.leetcode.recursive.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 17-1-17.
 */
public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList(), nums);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums) {
        if(temp.size() == nums.length) {
            result.add(new ArrayList(temp));
            return;
        }

        final int size = nums.length;
        for(int i = 0; i < size; i++) {
            int n = nums[i];
            if(temp.contains(n)) continue;

            temp.add(n);
            backtrack(result, temp, nums);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3,}));

        System.out.println(getPerms("abc"));

    }


    // todo make it backtrack algo
    public static ArrayList<String> getPerms(String s) {
        ArrayList<String> permutations = new ArrayList<String>();
        if (s == null) { // error case
            return null;
        } else if (s.length() == 0) { // base case
            permutations.add("");
            return permutations;
        }

        char first = s.charAt(0); // get the first character
        String remainder = s.substring(1); // remove the first character
        ArrayList<String> words = getPerms(remainder);
        for (String word : words) {
            for (int j = 0; j <= word.length(); j++) {
                permutations.add(insertCharAt(word, first, j));
            }
        }

        return permutations;
    }

    public static String insertCharAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }

}
