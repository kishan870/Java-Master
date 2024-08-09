import java.util.Scanner;

//Program to print the sum of digits
public class DigitSum {
    public static int sumOfDigits(long x) {
        if(x < 0)
            return 0;

        int sum = 0;

        while(x != 0) {
            sum += (int) (x%10);
            x = x/10;
        }

        return sum;
    }

    public static void main(String[] args) {
        long x;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a number");
        x = sc.nextLong();

        System.out.println("Sum of digits: " + sumOfDigits(x));
    }
}
