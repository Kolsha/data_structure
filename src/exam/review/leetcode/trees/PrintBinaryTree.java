package exam.review.leetcode.recursive;

/**
 * https://leetcode.com/problems/print-binary-tree/description/
 */
public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new LinkedList<>();
        int height = (root == null) ? 1 : getHeight(root);
        int rows = height;
        int columns = (int) (Math.pow(2, height) - 1);
        List<String> row = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            row.add("");
        }
        for (int i = 0; i < rows; i++) {
            res.add(new ArrayList<>(row));
        }
        populateRes(root, res, 0, rows, 0, columns - 1);
        return res;
    }

    public void populateRes(TreeNode root, List<List<String>> res, int curRow, int totalRows, int left, int right) {
        if (curRow == totalRows || root == null) {
            return;
        }
        int center = (left + right) / 2;
        res.get(curRow).set(center, Integer.toString(root.val));
        populateRes(root.left, res, curRow + 1, totalRows, left, center - 1);
        populateRes(root.right, res, curRow + 1, totalRows, center + 1, right);
    }

    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}