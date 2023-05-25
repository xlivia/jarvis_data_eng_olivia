import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicates {

    // Approach 1: Using an array
    public boolean containsDuplicateArray(int[] nums) {
        boolean[] visited = new boolean[100001];
        for (int num : nums) {
            if (visited[num]) {
                return true;
            }
            visited[num] = true;
        }
        return false;
    }

    // Approach 2: Using a HashSet
    public boolean containsDuplicateSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicates solution = new ContainsDuplicates();
        // Test case 1
        int[] nums1 = {1, 2, 3, 4, 5};
        System.out.println(solution.containsDuplicateArray(nums1)); // false
        System.out.println(solution.containsDuplicateSet(nums1)); // false
        // Test case 2
        int[] nums2 = {1, 2, 3, 2, 4, 5};
        System.out.println(solution.containsDuplicateArray(nums2)); // true
        System.out.println(solution.containsDuplicateSet(nums2)); // true
    }

}