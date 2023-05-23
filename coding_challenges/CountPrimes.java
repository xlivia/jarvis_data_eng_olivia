import java.util.Arrays;

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
        CountPrimes solution = new CountPrimes();
        int n = 10;
        int count = solution.countPrimes(n);
        System.out.println("Count of prime numbers less than " + n + ": " + count);
    }

}