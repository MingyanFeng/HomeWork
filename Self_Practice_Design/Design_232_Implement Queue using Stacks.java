/*
232. Implement Queue using Stacks

Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

public class MyQueue {
    /*
    这里用到两个栈，两种思路，第一种就是在进栈的时候，把栈逆序放在另外一个栈，出的时候直接出另外一个栈就可以了。
    
    第二种思路，进栈的时候不做任何处理，出栈的时候把栈逆序放在另外一个栈，出另外一个栈。
    */

// Version 1: 进栈时进行处理

    /** Initialize your data structure here. */
    
    Deque<Integer> stack1 = new ArrayDeque<>();
    Deque<Integer> stack2 = new ArrayDeque<>();
    
    public MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        // 先把stack1中的元素逆序装入stack2
        // stack1: 开口 1 2 3
        // stack2: 开口 3 2 1
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // 把x push到stack2，假设x == 4
        // stack2: 开口 4 3 2 1
        stack2.push(x);
        
        // 再把stack2中的元素逆序装入stack1
        // stack1: 开口 1 2 3 4
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack1.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack1.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty();
    }
    /*
    Time Complexity: O(m), m == # of input times, 即输入m个数字，就是O(m);
    Space Complexity: O(m), stack2 == O(m) extra space;
    */


// Version 2: 在出栈时进行处理

    /** Initialize your data structure here. */
    Deque<Integer> stack1 = new ArrayDeque<>();
    Deque<Integer> stack2 = new ArrayDeque<>();
    
    public MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        // 先把stack2中的元素逆序装入stack1
        // stack2: 开口 1 2 3
        // stack1: 开口 3 2 1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        // 把x push到stack1，假设x == 4
        // stack1: 开口 4 3 2 1
        stack1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 把stack1中的元素逆序装入stack2
        // stack1: 开口 4 3 2 1
        // stack2: 开口 1 2 3 4
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        // 把stack1中的元素逆序装入stack2
        // stack1: 开口 4 3 2 1
        // stack2: 开口 1 2 3 4
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
/*
这里不太明白，为什么不能直接判断stack2是否为空呢？

解答：因为我们无法预知stack1和stack2是否有东西。
        原因是，经过了多次push(), pop(), peek()后，stack1和stack2都有可能有元素，所以不能单纯地因为stack2为空整个队列就为空
*/
        // 把stack2中的元素逆序装入stack1
        // stack2: 开口 1 2 3 4
        // stack1: 开口 4 3 2 1
        // 如果stack2是empty的，那么stack1也是empty
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return stack1.isEmpty();
    }
/*
请问这里的空间复杂度是哪个stack占用了额外的空间呢？

Time Complexity: O(m), m == # of input times, 即输入m个数字，就是O(m);
Space Complexity: O(m), stack ?  == O(m) extra space;

解答：经过了多次push(), pop(), peek()后，我们根本无法预知stack1有东西还是stack2有东西，
        所以空间复杂度跟stack1和stack2同时有关系，或者说跟push()了几次pop()了几次有关系
*/
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */