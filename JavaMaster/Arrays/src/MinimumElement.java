import java.util.Scanner;

public class MinimumElement {
    public static void main(String[] args) {
        int[] arr = readIntegers();
        System.out.println("Minimum element in the array is " + getMinElement(arr));
    }

    public static int getMinElement(int[] arr) {
        int min = Integer.MAX_VALUE;

        for(int i=0; i<arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        return min;
    }
    public static int[] readIntegers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter list of numbers seperated by commas: ");

        String input = sc.nextLine();
        String[] numbersList = input.split(",");

        int[] arr = new int[numbersList.length];
        int i=0;
        for(String s: numbersList) {
            try {
                arr[i] = Integer.parseInt(s);
                i++;
            } catch(NumberFormatException e) {
            }
        }

        return arr;
    }
}
