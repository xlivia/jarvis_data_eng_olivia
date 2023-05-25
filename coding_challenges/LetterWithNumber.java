class LetterWithNumber {

    public String printLetterWithNumber(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int index = ch - 'a' + 1;
            if (Character.isUpperCase(ch)) {
                index = ch - 'A' + 27;
            }
            result.append(ch).append(index);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        LetterWithNumber letterWithNumber = new LetterWithNumber();
        // Test case 1: "abcee"
        String input1 = "abcee";
        String output1 = letterWithNumber.printLetterWithNumber(input1);
        System.out.println("Input: " + input1);
        System.out.println("Output: " + output1);
        // Test case 2: "ABCxyz"
        String input2 = "ABCxyz";
        String output2 = letterWithNumber.printLetterWithNumber(input2);
        System.out.println("Input: " + input2);
        System.out.println("Output: " + output2);
    }

}