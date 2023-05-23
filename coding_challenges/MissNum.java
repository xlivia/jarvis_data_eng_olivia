import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class MissNum {

    /**
     * Approach 1: Sum all numbers
     * Time Complexity: O(n)
     * Justification: The algorithm iterates over the nums array once.
     */
    public long missingNumberSum(long[] nums) {
        long n = nums.length;
        long expectedSum = n * (n + 1) / 2;
        long actualSum = 0;
        for (long num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    /**
     * Approach 2: Using Set
     * Time Complexity: O(n)
     * Justification: The algorithm iterates over the nums array once to build the set.
     *                The contains operation in the set takes O(1) on average.
     */
    public long missingNumberSet(long[] nums) {
        Set<Long> numSet = new HashSet<>();
        for (long num : nums) {
            numSet.add(num);
        }
        int n = nums.length;
        // Change the variable type to int
        for (int i = 0; i < n; i++) {
            // Change the loop variable type to int
            if (!numSet.contains((long) i)) {
                // Cast i to long in the contains check
                return i;
            }
        }
        return -1;
    }

    /**
     * Approach 3: Gauss Formula
     * Time Complexity: O(n)
     * Justification: The algorithm iterates over the nums array once to calculate the actual sum.
     */
    public long missingNumberGauss(long[] nums) {
        long n = nums.length;
        long expectedSum = n * (n + 1) / 2;
        long actualSum = 0;
        for (long num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        MissNum missNum = new MissNum();
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validLength = false;
        while (!validLength) {
            System.out.print("Enter the number of elements in the array (up to 20): ");
            try {
                n = Integer.parseInt(scanner.nextLine());
                if (n >= 1 && n <= 20) {
                    validLength = true;
                } else {
                    System.out.println("Invalid array length. Please enter a number between 1 and 20.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            boolean validNumber = false;
            while (!validNumber) {
                System.out.print("Enter element " + (i + 1) + ": ");
                try {
                    long num = Long.parseLong(scanner.nextLine());
                    nums[i] = num;
                    validNumber = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a valid number.");
                }
            }
        }
        // Approach 1: Sum all numbers
        long missingNumberSum = missNum.missingNumberSum(nums);
        System.out.println("(Sum) Missing number: " + missingNumberSum);
        // Approach 2: Using Set
        long missingNumberSet = missNum.missingNumberSet(nums);
        System.out.println("(Set) Missing number: " + missingNumberSet);
        // Approach 3: Gauss Formula
        long missingNumberGauss = missNum.missingNumberGauss(nums);
        System.out.println("(Gauss) Missing number: " + missingNumberGauss);
    }

}