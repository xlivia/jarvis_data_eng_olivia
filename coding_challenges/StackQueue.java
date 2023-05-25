import java.util.LinkedList;
import java.util.Queue;

class StackQueue {

    // Approach 1: Two Queues - Push and Pop
    static class MyStack {

        private Queue<Integer> pushQueue;
        private Queue<Integer> popQueue;

        public MyStack() {
            pushQueue = new LinkedList<>();
            popQueue = new LinkedList<>();
        }

        public void push(int x) {
            pushQueue.add(x);
        }

        public int pop() {
            while (pushQueue.size() > 1) {
                popQueue.add(pushQueue.remove());
            }
            int poppedElement = pushQueue.remove();
            Queue<Integer> temp = pushQueue;
            pushQueue = popQueue;
            popQueue = temp;
            return poppedElement;
        }

        public int top() {
            while (pushQueue.size() > 1) {
                popQueue.add(pushQueue.remove());
            }
            int topElement = pushQueue.peek();
            popQueue.add(pushQueue.remove());
            Queue<Integer> temp = pushQueue;
            pushQueue = popQueue;
            popQueue = temp;
            return topElement;
        }

        public boolean empty() {
            return pushQueue.isEmpty();
        }

    }

    // Approach 2: One Queue
    static class MyStack2 {

        private Queue<Integer> queue;

        public MyStack2() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.add(x);
            int size = queue.size();
            while (size > 1) {
                queue.add(queue.remove());
                size--;
            }
        }

        public int pop() {
            return queue.remove();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }

    }

    public static void main(String[] args) {
        // Test the implementation of both approaches
        // Approach 1: Two Queues - Push and Pop
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());  // Output: 3
        System.out.println(stack.top());  // Output: 2
        System.out.println(stack.empty());  // Output: false
        // Approach 2: One Queue
        MyStack2 stack2 = new MyStack2();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        System.out.println(stack2.pop());  // Output: 3
        System.out.println(stack2.top());  // Output: 2
        System.out.println(stack2.empty());  // Output: false
    }

}