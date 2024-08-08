import java.util.Scanner;

//This program demonstrates usage of enhanced switch. It will take a month and print the quarter.
public class EnhancedSwitch {
    public static String getQuarter(String month) {
        return switch (month) {
            case "JANUARY", "FEBRUARY", "MARCH" -> "1st";
            case "APRIL", "MAY", "JUNE" -> "2nd";
            case "JULY", "AUGUST", "SEPTEMBER" -> "3rd";
            case "OCTOBER", "NOVEMBER", "DECEMBER" -> "4th";
            default -> {
                System.out.println(month + " is bad month ");
                yield "bad";
            }
        };
    }

    public static void main(String[] args) {
        String month;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a month: ");
        month = sc.next();

        System.out.println("Quarter: " + getQuarter(month.toUpperCase()));
    }
}
