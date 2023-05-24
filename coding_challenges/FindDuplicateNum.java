import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class FindDuplicateNum {

    public static int[] readIntArray(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(",");
                int[] nums = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    nums[i] = Integer.parseInt(parts[i].trim());
                }
                return nums;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter comma-separated integers only.");
                System.out.print("Enter the numbers separated by commas: ");
            }
        }
    }

    public static int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        throw new IllegalArgumentException("No duplicate number found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the numbers separated by commas: ");
        int[] nums = readIntArray(scanner);
        try {
            int duplicate = findDuplicate(nums);
            System.out.println("Duplicate number: " + duplicate);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

}