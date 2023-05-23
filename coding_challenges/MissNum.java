import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class MissNum {

    /**
     * Approach: Sum all numbers
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
     * Approach: Using Set
     * Time Complexity: O(n)
     * Justification: The algorithm iterates over the nums array once to build the set.
     *                The contains operation in the set takes O(1) on average.
     */
    public long missingNumberSet(long[] nums) {
        Set<Long> numSet = new HashSet<>();
        for (long num : nums) {
            numSet.add(num);
        }
        long n = nums.length;
        for (long i = 0; i <= n; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Approach: Gauss Formula
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
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        long[] nums = new long[n];
        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextLong();
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