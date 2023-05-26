import java.util.regex.Pattern;

public class StringContainsDigits {

    public static boolean containsOnlyDigits(String input) {
        return Pattern.matches("\\d+", input);
    }

    public static void main(String[] args) {
        String input1 = "1234";
        String input2 = "123,000";
        boolean result1 = containsOnlyDigits(input1);
        boolean result2 = containsOnlyDigits(input2);
        System.out.println("Input 1: " + input1);
        System.out.println("Contains only digits? " + result1);
        System.out.println("Input 2: " + input2);
        System.out.println("Contains only digits? " + result2);
    }

}