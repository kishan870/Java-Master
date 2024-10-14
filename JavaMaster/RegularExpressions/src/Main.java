import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String helloWorld = Main.format("%s %s", "Hello", "World");
        System.out.println("Using Main.format: " + helloWorld);

        //Challenges
        System.out.println("--------------Challenges-----------");
        String sentence = "Hello, World!";
        boolean matches = sentence.matches("Hello, World!");
        System.out.println(matches);

        String pattern = "[A-Z]([a-z\s])*\\.$";
        sentence = "The bike is red.";
        System.out.println(sentence.matches(pattern));

        //Pattern Matching
        sentence = "I like motorcycles.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println(matched + " : " + sentence);

        Pattern firstPattern = Pattern.compile("[A-Z].*[.]");
        var matcher = firstPattern.matcher(sentence);
        System.out.println(matcher.matches() + " : " + sentence);
        System.out.println("sentence.length: " + sentence.length());
        System.out.println("Matched Ending Index: " + matcher.end());

        System.out.println(matcher.lookingAt() + ": " + sentence);
        System.out.println("Matched Ending Index: " + matcher.end());

        String htmlSnippet = """
                <H1>My Heading</H1>
                <h2>Sub-heading</h2>
                <p>This is paragraph about something.</p>
                <p>This is another paragraph about something else.</p>
                <h3>Summary</h3>
                """;

        Pattern htmlPattern = Pattern.compile("<[hH](?<level>\\d)(.*)</[hH]\\d>");
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);

        while (htmlMatcher.find()) {
            System.out.println("Group: " + htmlMatcher.group(1));
        }

        htmlMatcher.reset();
        htmlMatcher.results().forEach(mr -> System.out.println(
                mr.group(1) + " " + mr.group(2)));

    }

    //Defining own format method
    private static String format(String regexp, String... args) {

        int index = 0;
        while(regexp.contains("%s")) {
            regexp = regexp.replaceFirst("%s", args[index++]);
        }

        return regexp;
    }
}