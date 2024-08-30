import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Random random = new Random();
    public static void main(String[] args) {

        String names[] = {"Anna", "Bob", "Carole", "David",
                            "Ed", "Fred", "Gary"};

        Arrays.setAll(names, i -> names[i].toUpperCase());
        System.out.println("-".repeat(10) + "Upper Case");
        System.out.println(Arrays.toString(names));

        List<String> backedByArray = Arrays.asList(names);

        backedByArray.replaceAll(s -> s + " " + getRandomChar('A', 'D') + ".");
        System.out.println("-".repeat(10) + "With random middle initial");
        System.out.println(backedByArray);

        backedByArray.replaceAll(s -> s + " " + getReversedName(s.split(" ")[0]));
        System.out.println("-".repeat(10) + "With last name");
        System.out.println(backedByArray);
        backedByArray.forEach(s -> System.out.println(s));

        List<String> newList = new ArrayList<>(List.of(names));
        newList.removeIf(s -> s.substring(0, s.indexOf(" ")).equals(
           s.substring(s.lastIndexOf(" ")+1)
        ));

        System.out.println("---> After removing lastnames which are same as firstNames");
        System.out.println(newList);
    }

    public static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt((int)startChar, (int) endChar + 1);
    }

    private static String getReversedName(String firstName) {
        return new StringBuilder(firstName).reverse().toString();
    }
}