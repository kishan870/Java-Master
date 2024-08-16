import java.util.Arrays;

public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,7,8};
        System.out.println("Array: " + Arrays.toString(arr));

        reverseArray(arr);
        System.out.println("Reverse array: " + Arrays.toString(arr));
    }

    public static void reverseArray(int[] arr) {
        for(int i=0; i<arr.length/2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length-i-1];
            arr[arr.length-i-1] = temp;
        }
    }
}
