public class StringToIntegerAtoi {

    /**
     * Approach 1: Use Java built-in parsing method
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int myAtoiApproach1(String str) {
        str = str.trim(); // Remove leading and trailing white spaces
        if (str.isEmpty()) {
            return 0;
        }
        int sign = 1;
        int index = 0;
        long result = 0; // Use long to handle overflow
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        while (index < str.length()) {
            char c = str.charAt(index);
            if (!Character.isDigit(c)) {
                break;
            }
            result = result * 10 + (c - '0');
            // Check for overflow
            if (result > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            index++;
        }
        return (int) (result * sign);
    }

    /**
     * Approach 2: Without Java built-in parsing methods
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int myAtoiApproach2(String str) {
        str = str.trim(); // Remove leading and trailing white spaces
        if (str.isEmpty()) {
            return 0;
        }
        int sign = 1;
        int index = 0;
        int result = 0;
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        while (index < str.length()) {
            char c = str.charAt(index);
            if (!Character.isDigit(c)) {
                break;
            }
            int digit = c - '0';
            // Check for overflow
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            index++;
        }
        return result * sign;
    }

    public static void main(String[] args) {
        StringToIntegerAtoi solution = new StringToIntegerAtoi();
        // Test approach 1
        System.out.println(solution.myAtoiApproach1("42")); // Output: 42
        System.out.println(solution.myAtoiApproach1("   -42")); // Output: -42
        System.out.println(solution.myAtoiApproach1("4193 with words")); // Output: 4193
        System.out.println(solution.myAtoiApproach1("words and 987")); // Output: 0
        System.out.println(solution.myAtoiApproach1("-91283472332")); // Output: -2147483648
        // Test approach 2
        System.out.println(solution.myAtoiApproach2("42")); // Output: 42
        System.out.println(solution.myAtoiApproach2("   -42")); // Output: -42
        System.out.println(solution.myAtoiApproach2("4193 with words")); // Output: 4193
        System.out.println(solution.myAtoiApproach2("words and 987")); // Output: 0
        System.out.println(solution.myAtoiApproach2("-91283472332")); // Output: -2147483648
    }

}