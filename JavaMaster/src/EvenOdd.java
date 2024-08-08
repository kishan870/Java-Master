import java.util.Scanner;

// This program will take an integer input and print if odd or even
public class EvenOdd {
    public static void main(String[] args) {
        int x;
        System.out.println("Enter a number: ");

        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();

        if(x%2 == 0) {
            System.out.println("Number is even");
        }

        else {
            System.out.println("Number is odd");
        }
    }
}
