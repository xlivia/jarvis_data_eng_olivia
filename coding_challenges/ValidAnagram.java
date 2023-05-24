import java.util.Arrays;
import java.util.Scanner;

class ValidAnagram {

    /**
     * Checks if two strings are valid anagrams of each other.
     * @param s The first string.
     * @param t The second string.
     * @return true if s is an anagram of t, false otherwise.
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        s = s.toLowerCase();
        t = t.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        String t;
        while (true) {
            try {
                System.out.print("Enter the first string: ");
                s = scanner.nextLine();
                if (s.matches("[a-zA-Z]+")) {
                    break;
                }
                throw new IllegalArgumentException("Invalid input! Please enter a string with only letters.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Enter the second string: ");
                t = scanner.nextLine();
                if (t.matches("[a-zA-Z]+")) {
                    break;
                }
                throw new IllegalArgumentException("Invalid input! Please enter a string with only letters.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        ValidAnagram solution = new ValidAnagram();
        boolean isAnagram = solution.isAnagram(s, t);
        System.out.println("Are the strings anagrams? " + isAnagram);
        scanner.close();
    }

}