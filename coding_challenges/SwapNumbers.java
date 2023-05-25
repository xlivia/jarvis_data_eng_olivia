class SwapNumbers {

    public void swapUsingBitManipulation(int[] nums) {
        nums[0] = nums[0] ^ nums[1];
        nums[1] = nums[0] ^ nums[1];
        nums[0] = nums[0] ^ nums[1];
    }

    public void swapUsingArithmeticOperators(int[] nums) {
        nums[0] = nums[0] + nums[1];
        nums[1] = nums[0] - nums[1];
        nums[0] = nums[0] - nums[1];
    }

    public static void main(String[] args) {
        SwapNumbers swapNumbers = new SwapNumbers();
        // Test case 1: [2, 3]
        int[] nums1 = {2, 3};
        System.out.println("Before swap: " + nums1[0] + ", " + nums1[1]);
        swapNumbers.swapUsingBitManipulation(nums1);
        System.out.println("After swap (using bit manipulation): " + nums1[0] + ", " + nums1[1]);
        // Test case 2: [5, -7]
        int[] nums2 = {5, -7};
        System.out.println("Before swap: " + nums2[0] + ", " + nums2[1]);
        swapNumbers.swapUsingArithmeticOperators(nums2);
        System.out.println("After swap (using arithmetic operators): " + nums2[0] + ", " + nums2[1]);
    }

}