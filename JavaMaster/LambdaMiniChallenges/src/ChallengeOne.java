import java.util.Arrays;
import java.util.function.Consumer;

public class ChallengeOne {

    public static void main(String[] args) {

        String s = "Hello there! How are you?";

        Consumer<String> consumer = (sentence) -> {

            Arrays.asList(sentence.split(" "))
                    .forEach(word -> System.out.println(word));
        };

        printString(s, consumer);
    }

    public static <T> void printString(T string, Consumer<T> function) {
        function.accept(string);
    }
}
