import java.util.Stack;

public class ValidParentheses {

    /**
     * Approach: Using a stack to check for valid parentheses
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                return false; // Invalid closing parentheses or mismatch
            }
        }
        return stack.isEmpty(); // Stack should be empty for valid parentheses
    }

    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        System.out.println(solution.isValid("()")); // Output: true
        System.out.println(solution.isValid("()[]{}")); // Output: true
        System.out.println(solution.isValid("(]")); // Output: false
        System.out.println(solution.isValid("([)]")); // Output: false
        System.out.println(solution.isValid("{[]}")); // Output: true
    }

}