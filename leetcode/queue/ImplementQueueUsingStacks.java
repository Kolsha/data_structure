package exam.review.leetcode.queue;

import java.util.Stack;

/**
 * Created by shanwu on 17-1-15.
 * PC: 1
 */
public class ImplementQueueUsingStacks {
    private Stack<Integer> mStack = new Stack();
    // Push element x to the back of queue.
    public void push(int x) {
        Stack<Integer> temp = new Stack();
        while(!mStack.isEmpty()) temp.push(mStack.pop());
        temp.push(x);
        while(!temp.isEmpty()) mStack.push(temp.pop());
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(!mStack.isEmpty()) {
            mStack.pop();
        }
    }

    // Get the front element.
    public int peek() {
        return mStack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return mStack.isEmpty();
    }
}
