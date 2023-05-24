import java.util.Scanner;

class FibStairs {

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter a number (0 <= n <= 45): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n >= 0 && n <= 45) {
                    validInput = true;
                }
                else {
                    System.out.println("Invalid input! Please enter a number between 0 and 45.");
                }
            }
            else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
        FibStairs solution = new FibStairs();
        int fibonacciNumber = solution.fib(n);
        System.out.println("Fibonacci number: " + fibonacciNumber);
        int distinctWays = solution.climbStairs(n);
        System.out.println("Number of distinct ways to climb stairs: " + distinctWays);
        scanner.close();
    }

}