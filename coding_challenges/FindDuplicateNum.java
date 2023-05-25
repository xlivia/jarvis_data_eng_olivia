import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class FindDuplicateNum {

    public static int[] readIntArray(Scanner scanner) {
        while (true) {
            System.out.print("Enter the numbers separated by commas: ");
            String input = scanner.nextLine();
            try {
                String[] parts = input.split(",");
                int[] nums = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    nums[i] = Integer.parseInt(parts[i].trim());
                }
                return nums;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter comma-separated integers only.");
            }
        }
    }

    public static List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();
        for (int num : nums) {
            if (set.contains(num)) {
                duplicates.add(num);
            }
            set.add(num);
        }
        if (duplicates.isEmpty()) {
            throw new IllegalArgumentException("No duplicate numbers found.");
        }
        return duplicates;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = readIntArray(scanner);
        try {
            List<Integer> duplicates = findDuplicates(nums);
            StringBuilder output = new StringBuilder("Duplicate number(s): ");
            for (int i = 0; i < duplicates.size(); i++) {
                output.append(duplicates.get(i));
                if (i != duplicates.size() - 1) {
                    output.append(", ");
                }
            }
            System.out.println(output.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

}