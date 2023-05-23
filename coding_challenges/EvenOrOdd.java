import java.util.Scanner;

class EvenOrOdd {

    public String checkEvenOrOdd(long num) {
        if (num % 2 == 0) {
            return "even";
        }
        else {
            return "odd";
        }
    }

    public static void main(String[] args) {
        System.out.println("~~~ Check if a Number is Even or Odd ~~~");
        EvenOrOdd even_or_odd = new EvenOrOdd();
        Scanner scanner = new Scanner(System.in);
        System.out.print("input: ");
        long num = scanner.nextLong();
        String result = even_or_odd.checkEvenOrOdd(num);
        System.out.println("output: " + result);
    }

}