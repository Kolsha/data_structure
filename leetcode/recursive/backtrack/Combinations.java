

/**
 * https://leetcode.com/problems/combinations/submissions/1
 * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 * 
 */
class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void backtrack(List result, List temp,int start, int n, int k) {
        if(k == 0) {
            result.add(new ArrayList<>(temp));
        }

        for(int i = start; i <= n; i++) {
            temp.add(i);
            backtrack(result, temp, i+1, n, k-1);
            temp.remove(temp.size()-1);
        }
    }
}