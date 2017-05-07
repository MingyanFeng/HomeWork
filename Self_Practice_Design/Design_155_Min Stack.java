/*
155. Min Stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

public class MinStack {
    /*
    主要是怎么保存最小值这个问题，每次在向栈中添加数据时，总是和当前的最小值比较，如果比当前的最小值小，那么则把当前的最小值压入栈中，表明当前栈中的最小值为当前最小值，然后再把新的数据压入栈中。
    每次弹出栈顶的元素时候，将弹出的数据与当前的最小值比较，如果相等，表明已经达到当前最小值所在的位置，下面的那个数是当前栈中的最小值，将其弹出，其值为新的最小值。
    */

    /** initialize your data structure here. */
    Deque<Integer> stack = new ArrayDeque<>();
    int min = Integer.MAX_VALUE;
    
    public MinStack() {
        
    }
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        int peek = stack.pop();
        if (peek == min) {
            min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
/*
这类题的时空复杂度如何分析呢？

解答：对每个方法进行时间复杂度分析，一般情况下把成员变量的大小当作n
*/
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */