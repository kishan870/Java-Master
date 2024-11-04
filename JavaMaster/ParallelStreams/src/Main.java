import java.util.Arrays;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int numbersLength = 100_000_000;
        long[] numbers = new Random().longs(numbersLength, 1,
                numbersLength).toArray();

        long delta = 0;
        long iterations = 25;

        for(int i=0; i<iterations; i++) {
            long start = System.nanoTime();
            double averageSerial = Arrays.stream(numbers).average().orElseThrow();
            long elapsedSerial = System.nanoTime() - start;

            start = System.nanoTime();
            double averageParallel = Arrays.stream(numbers).parallel()
                    .average().orElseThrow();
            long elapsedParallel = System.nanoTime() - start;

            delta += (elapsedSerial - elapsedParallel);
        }

        System.out.printf("Parallel is [%d] nanos or %.2f ms faster than serial",
                delta / iterations, delta / 1000000.0 / iterations);
    }
}