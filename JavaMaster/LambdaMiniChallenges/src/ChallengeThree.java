import java.util.function.Supplier;

public class ChallengeThree {

    public static void main(String[] args) {

        Supplier<String> supplier = () -> "I love Java";

        String iLoveJava = supplier.get();
        System.out.println(iLoveJava);
    }
}
