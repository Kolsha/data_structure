/**
 * Question description: https://leetcode.com/problems/rotate-string/description/
 * PC: 1
 */
class RotateString {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}