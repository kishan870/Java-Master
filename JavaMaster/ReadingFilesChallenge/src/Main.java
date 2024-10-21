import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String filePath = "JavaMaster/ReadingFilesChallenge";
        try (BufferedReader br = new BufferedReader(
                new FileReader(String.join("/", filePath, "bigben.txt")))) {

            //System.out.printf(" %,d lines are in the file%n", br.lines().count());
            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
//            System.out.printf("%,d words in file%n",
//                    br.lines()
//                            .flatMap(pattern::splitAsStream)
//                            //.flatMap(l -> Arrays.stream(l.split(pattern.toString())))
//                            .count());

//            System.out.printf("%,d words in file%n",
//                    br.lines()
//                            .mapToLong(l -> l.split(pattern.toString()).length)
//                            .sum());

            List<String> excluded = List.of(
                    "grand",
                    "canyon",
                    "retrieved",
                    "archived",
                    "service",
                    "original");

            var result = br.lines()
                    .flatMap(pattern::splitAsStream)
                    .map(w -> w.replaceAll("\\p{Punct}",""))
                    .filter(w -> w.length() > 4)
                    .map(String::toLowerCase)
                    .filter(w -> !excluded.contains(w))
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

            result.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(e -> System.out.println(
                            e.getKey() + " - " + e.getValue() + " times"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}