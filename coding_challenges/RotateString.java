class RotateString {

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        String doubleS = s + s;
        return doubleS.contains(goal);
    }

    public static void main(String[] args) {
        RotateString solution = new RotateString();
        // Test case 1
        String s1 = "abcde";
        String goal1 = "cdeab";
        boolean result1 = solution.rotateString(s1, goal1);
        System.out.println(result1); // Expected output: true
        // Test case 2
        String s2 = "abcde";
        String goal2 = "abced";
        boolean result2 = solution.rotateString(s2, goal2);
        System.out.println(result2); // Expected output: false
        // Test case 3
        String s3 = "hello";
        String goal3 = "lohel";
        boolean result3 = solution.rotateString(s3, goal3);
        System.out.println(result3); // Expected output: true
    }

}