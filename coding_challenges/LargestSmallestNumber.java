import java.util.Arrays;

class LargestSmallestNumber {

    public static int findLargestNumber(int[] arr) {
        int largest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
        return largest;
    }

    public static int findSmallestNumber(int[] arr) {
        int smallest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        return smallest;
    }

    public static void main(String[] args) {
        int[] numbers = {5, 3, 9, 1, 7, 2};
        int largest = findLargestNumber(numbers);
        int smallest = findSmallestNumber(numbers);
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Largest number: " + largest);
        System.out.println("Smallest number: " + smallest);
    }

}