package exam.review.leetcode.stack;

public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null) {
            return path;
        }

        Stack<String> stack = new Stack<>();
        HashSet<String> set = new HashSet<>(Arrays.asList(".", "..", ""));
        String[] dirs = path.split("/");

        for (String dir : dirs) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            }

            if (!set.contains(dir)) {
                stack.push(dir);
            }
        }

        String result = "";
        for (String folder : stack) {
            result = result + "/" + folder;
        }
        return stack.isEmpty() ? "/" : result;
    }
}