import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

class MissNum {

    public long missNumSum(long[] nums) {
        // Approach 1: Sum all numbers
        long n = nums.length;
        long expectedSum = n * (n + 1) / 2;
        long actualSum = 0;
        for (long num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    public long missNumSet(long[] nums) {
        // Approach 2: Using Set
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

    public long missNumGauss(long[] nums) {
        // Approach 3: Gauss Formula
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
        System.out.println("(Sum) Missing number: " + missNum.missNumSum(nums));
        System.out.println("(Set) Missing number: " + missNum.missNumSet(nums));
        System.out.println("(Gauss) Missing number: " + missNum.missNumGauss(nums));
    }

}