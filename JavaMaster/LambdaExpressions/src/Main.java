import java.util.Arrays;
import java.util.function.BiConsumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int result = calculator((a, b) -> a+b, 6,7);
        System.out.println(result);

        String res = calculator((s1, s2) -> s1.concat(" ").concat(s2),
                "Hello", "World");
        System.out.println(res);

        var coords = Arrays.asList(
                new Double[]{1.456, 8.43},
                new Double[]{4.37, 5.987},
                new Double[]{3.215, 7.876}
        );

        BiConsumer<Double, Double> consumer = (lat, lng) ->
                System.out.printf("[lat:%.3f, lon:%.3f]%n", lat, lng);
        
       coords.sort((s1, s2) -> {
           int r = (int) (s1[0] - s2[0]);
           return r == 0? (int)(s1[1] - s2[1]):r;
       });

        coords.forEach(s -> processPoint(s[0], s[1], consumer));
    }

    public static <T> T calculator(Operation<T> function, T value1, T value2) {

        T result = function.operate(value1, value2);
        System.out.println("Result of the operation: ");
        return result;
    }

    public static <T> void processPoint(T t1, T t2, BiConsumer<T,T> consumer) {
        consumer.accept(t1, t2);
    }
}