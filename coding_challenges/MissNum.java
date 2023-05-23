import java.util.HashSet;
import java.util.Set;

class MissNum {

    /**
     * Approach: Sum all numbers
     * Time Complexity: O(n)
     * Justification: The algorithm iterates over the nums array once.
     */
    public int missingNumberSum(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
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
    public int missingNumberSet(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Problem: Missing Number
     * 
     * Approach: Gauss Formula
     * Time Complexity: O(n)
     * Justification: The algorithm iterates over the nums array once to calculate the actual sum.
     */
    public int missingNumberGauss(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        MissNum missNum = new MissNum();
        int[] nums = {3, 0, 1};

        // Approach: Sum all numbers
        int missingNumberSum = missNum.missingNumberSum(nums);
        System.out.println("(Sum) Missing number: " + missingNumberSum);

        // Approach: Using Set
        int missingNumberSet = missNum.missingNumberSet(nums);
        System.out.println("(Set) Missing number: " + missingNumberSet);

        // Approach: Gauss Formula
        int missingNumberGauss = missNum.missingNumberGauss(nums);
        System.out.println("(Gauss) Missing number: " + missingNumberGauss);
    }

}