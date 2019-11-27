package exam.review.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shanwu on 17-1-11.
 * PC: 1
 */
public class ImplementStackUsingQueues {
    LinkedList<Integer> a = new LinkedList();
    // Push element x onto stack.
    public void push(int x) {
        a.add(x);
        // reverse the queue order, e.g. 1-2-[3] you need to call remove() 2 times(size - 1)
        for(int i = 1; i<a.size();i++) { // we don't need to remove the last element
            a.add(a.remove());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        a.remove();
    }

    // Get the top element.
    public int top() {
        return a.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return a.isEmpty();
    }
}