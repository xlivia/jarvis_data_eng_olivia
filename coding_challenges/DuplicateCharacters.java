import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DuplicateCharacters {

    public static List<Character> findDuplicates(String input) {
        List<Character> duplicates = new ArrayList<>();
        HashSet<Character> set = new HashSet<>();
        // Convert the string to lowercase for case insensitivity
        String lowerCaseInput = input.toLowerCase();
        for (int i = 0; i < lowerCaseInput.length(); i++) {
            char c = lowerCaseInput.charAt(i);
            // Skip spaces and special characters
            if (c != ' ' && !Character.isLetterOrDigit(c)) {
                continue;
            }
            if (set.contains(c)) {
                duplicates.add(c);
            }
            else {
                set.add(c);
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        String input = "A black cat";
        List<Character> duplicates = findDuplicates(input);
        System.out.println("Input: " + input);
        System.out.println("Duplicates: " + duplicates);
    }

}