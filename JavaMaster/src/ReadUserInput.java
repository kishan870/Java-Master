import java.util.Scanner;

//Program to demonstrate reading valid user input
public class ReadUserInput {
    public static void main(String[] args) {
        int x;
        System.out.println("Enter 5 positive numbers and not 0");

        Scanner scanner = new Scanner(System.in);
        int counter = 1;

        do {
            System.out.println("Enter number " + counter +" greater than zero");
            try {
                String nextNum = scanner.nextLine();
                x = Integer.parseInt(nextNum);

                if(x < 0)
                    System.out.println("Enter a valid number");
                else
                    counter++;
            } catch(NumberFormatException e) {
                System.out.println("Invalid number!!!");
            }
        }while(counter <=5);
    }
}
