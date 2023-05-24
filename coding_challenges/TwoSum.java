import java.util.*;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return null;
    }

    private static int getValidInput(Scanner scanner) {
        int size = 0;
        boolean validInput = false;
        while (!validInput) {
            String input = scanner.nextLine();
            try {
                size = Integer.parseInt(input);
                if (size >= 0) {
                    validInput = true;
                }
                else {
                    System.out.print("Invalid input! Please enter a non-negative integer: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid integer: ");
            }
        }
        return size;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int size = getValidInput(scanner);
        int[] nums = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            nums[i] = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();
        int[] result = twoSum(nums, target);
        if (result != null) {
            System.out.println("Indices of the two numbers that add up to the target: " + result[0] + ", " + result[1]);
        }
        else {
            System.out.println("No two numbers found that add up to the target.");
        }
    }

}