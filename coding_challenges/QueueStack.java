import java.util.Stack;

public class QueueStack {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public QueueStack() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public static void main(String[] args) {
        QueueStack queue = new QueueStack();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());  // Output: 1
        System.out.println(queue.peek()); // Output: 2
        queue.push(4);
        System.out.println(queue.pop());  // Output: 2
        System.out.println(queue.empty()); // Output: false
    }

}