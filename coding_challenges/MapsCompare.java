import java.util.*;

public class MapsCompare {

    public static boolean compareMaps(Map<?, ?> map1, Map<?, ?> map2) {
        return map1.equals(map2);
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

    private static Map<Integer, Integer> getMapFromUser(Scanner scanner, int size, String mapName) {
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println("Enter the values for " + mapName + ":");
        for (int i = 0; i < size; i++) {
            System.out.print("Value " + (i + 1) + ": ");
            int value = scanner.nextInt();
            scanner.nextLine();
            map.put(i + 1, value);
        }
        System.out.println();
        System.out.println("Map " + mapName + ":");
        printMapDiagram(map);
        System.out.println();
        return map;
    }

    private static void printMapDiagram(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " | Value: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of values for Map 1: ");
        int size1 = getValidInput(scanner);
        Map<Integer, Integer> map1 = getMapFromUser(scanner, size1, "Map 1");
        System.out.print("Enter the number of values for Map 2: ");
        int size2 = getValidInput(scanner);
        Map<Integer, Integer> map2 = getMapFromUser(scanner, size2, "Map 2");
        boolean result = compareMaps(map1, map2);
        System.out.println("The two maps are " + (result ? "equal." : "not equal."));
    }

}