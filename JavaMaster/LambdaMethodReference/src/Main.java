import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

class PlainOld {
    private static int LAST_ID = 1;
    private int id;

    public PlainOld() {
        id = PlainOld.LAST_ID++;
        System.out.println("Creating new PlainOld object with object id: " + id);
    }
}

public class Main {
    public static void main(String[] args) {

        List<String> myList = new ArrayList<>(List.of(
           "Anna", "Beth", "Cathy", "Douglas", "Elli"
        ));

        myList.forEach(System.out::println);

        System.out.println("---> Method reference on sum");

        calculator((a,b) -> a+b, 24, 56);
        calculator(Integer::sum, 24, 56);

        boolean result = isGreater((a, b) -> a>b, 25, 20);
        System.out.println("IsGreater " + result);

        //Object method reference
        Supplier<PlainOld> reference1 = PlainOld::new;
        PlainOld newObject = reference1.get();

        seedArray(reference1, 10);

        //Method reference for string concatenation
        calculator((s1, s2) -> s1 + s2, "Hello", "World");
        calculator(String::concat, "Hello", "World");

    }

    public static <T> void calculator(BinaryOperator<T> function, T val1, T val2) {
        T result = function.apply(val1, val2);
        System.out.println(result);
    }

    public static <T> boolean isGreater(BiPredicate<T, T> function, T val1, T val2) {
        return function.test(val1, val2);
    }

    public static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {
        PlainOld[] plainOldArray = new PlainOld[count];
        Arrays.setAll(plainOldArray, i -> reference.get());
        return plainOldArray;
    }
}