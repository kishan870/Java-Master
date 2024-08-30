import java.util.function.Function;
import java.util.function.Predicate;

public class ChallengeTwo {

    public static void main(String[] args) {

        String s = "Atmosphere";

        Function<String, String> function = (source) -> {

            StringBuilder retVal = new StringBuilder();
            for(int i=0; i<source.length(); i++) {
                if(i%2 == 1) {
                    retVal.append(source.charAt(i));
                }
            }

            return retVal.toString();
        };

        System.out.println(everySecondCharacter(s, function));

    }

    public static String everySecondCharacter(String source, Function<String, String> function) {
        return function.apply(source);
    }
}
