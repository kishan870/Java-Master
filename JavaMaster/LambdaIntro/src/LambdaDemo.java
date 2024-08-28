import java.util.ArrayList;
import java.util.List;

public class LambdaDemo {

    public static void main(String[] args) {

        //Using Lambda expression on Iterable interface

        List<String> myList = new ArrayList<>(List.of(
                "alpha", "beta", "gamma", "delta", "pi"
        ));

        for(String s: myList) {
            System.out.println(s);
        }

        System.out.println("-".repeat(15));
        System.out.println("Using lambda expression for the above print");

        myList.forEach(myString -> System.out.println(myString));

        System.out.println("-".repeat(15));

        myList.forEach(myString -> {
            char firstChar = myString.charAt(0);
            System.out.println(myString + " means " + firstChar);
        });
    }
}
