//Program to generate random array and sort generated array in descending order

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ArraysChallenge {
    public static void main(String[] args) {
        Integer[] arr = getRandomArray(100);
        System.out.println("Array created: " + Arrays.toString(arr));

        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println("Descending order: " + Arrays.toString(arr));
    }

    public static Integer[] getRandomArray(int len) {
        Random random = new Random();
        Integer[] arr = new Integer[len];

        for(int i=0; i<len; i++) {
            arr[i] = random.nextInt(100);
        }

        return arr;
    }
}
