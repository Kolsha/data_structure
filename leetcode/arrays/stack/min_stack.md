### 155. Min Stack

https://leetcode.com/problems/min-stack/

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

- push(x) -- Push element x onto stack.
- pop() -- Removes the element on top of the stack.
- top() -- Get the top element.
- getMin() -- Retrieve the minimum element in the stack.
 

Example:
```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
```

Solution

Method 1: Stack w/ minimum info on each node

Time complexity:

Space complexity:

In Java
```java
class MinStack {
    private Stack<Node> mStack = null;
    /** initialize your data structure here. */
    public MinStack() {
        mStack = new Stack<>();
    }
    
    public void push(int x) {
        int min = x;
        if(!mStack.isEmpty()) {
            min = Math.min(mStack.peek().min, x);   
        }
        mStack.push(new Node(x, min));
    }
    
    public void pop() {
        mStack.pop();
    }
    
    public int top() {
        return mStack.peek().value;
    }
    
    public int getMin() {
        return mStack.peek().min;
    }
    private static class Node {
        int value;
        int min;
        Node(int val, int mini) {
            value = val;
            min = mini;
        }
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```