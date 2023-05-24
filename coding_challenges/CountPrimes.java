import java.util.Arrays;
import java.util.Scanner;

class CountPrimes {

    /**
     * Counts the number of prime numbers that are strictly less than n.
     * @param n The input number.
     * @return The count of prime numbers less than n.
     */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter a number (greater than 2): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please enter a number greater than 2.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
        CountPrimes solution = new CountPrimes();
        int count = solution.countPrimes(n);
        System.out.println("Count of prime numbers less than " + n + ": " + count);
        scanner.close();
    }

}