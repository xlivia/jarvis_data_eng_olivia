public class ValidPalindrome {

    /**
     * Approach 1: Two Pointers
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public boolean isPalindromeApproach1(String s) {
        // Convert the string to lowercase and remove non-alphanumeric characters
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        // Check if the string is a palindrome
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Approach 2: Recursion
     * Time complexity: O(n)
     * Space complexity: O(n) - due to recursive calls
     */
    public boolean isPalindromeApproach2(String s) {
        // Convert the string to lowercase and remove non-alphanumeric characters
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        return isPalindromeRecursive(sb.toString(), 0, sb.length() - 1);
    }

    private boolean isPalindromeRecursive(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();
        // Test approach 1
        System.out.println(solution.isPalindromeApproach1("A man, a plan, a canal: Panama")); // Output: true
        System.out.println(solution.isPalindromeApproach1("race a car")); // Output: false
        System.out.println(solution.isPalindromeApproach1("")); // Output: true
        // Test approach 2
        System.out.println(solution.isPalindromeApproach2("A man, a plan, a canal: Panama")); // Output: true
        System.out.println(solution.isPalindromeApproach2("race a car")); // Output: false
        System.out.println(solution.isPalindromeApproach2("")); // Output: true
    }

}