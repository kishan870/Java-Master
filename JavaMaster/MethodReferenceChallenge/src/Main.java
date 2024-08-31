import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Main {

    private static Random random = new Random();

    public static void main(String[] args) {

        List<String> namesList = new ArrayList<>(List.of(
                "Anna", "Mary", "Bill", "Cagney", "Lorenz", "Maria", "Rabba"
        ));

        List<UnaryOperator<String>> methods = new ArrayList<>(List.of(
                String::toUpperCase,
                s -> s += " " + getRandomChar('A', 'D'),
                s -> s += " " + getReverse(s.split(" ")[0])
        ));

        applyMethod(namesList.toArray(new String[0]), methods);

    }

    private static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt(startChar, (int) endChar + 1);
    }

    private static String getReverse(String s) {
        StringBuilder str = new StringBuilder(s);
        return str.reverse().toString();
    }

    private static void applyMethod(String[] names, List<UnaryOperator<String>> functions) {

        List<String> backedByArray = Arrays.asList(names);

        for(var function: functions) {
            backedByArray.replaceAll(s -> s.transform(function));
            System.out.println(backedByArray);
        }
    }
}