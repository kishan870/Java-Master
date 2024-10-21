import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String filePath = "JavaMaster/ScannerProject";

        try (Scanner scanner = new Scanner(new File(
                String.join("/", filePath, "file.txt")))) {
//            while (scanner.hasNextLine()) {
//                System.out.println(scanner.nextLine());
//            }

//            System.out.println(scanner.delimiter());
//            scanner.useDelimiter("$");
//            scanner.tokens().forEach(System.out::println);

            //Listing words more than 10 letters
//            scanner.findAll("[A-Za-z]{10,}")
//                    .map(MatchResult::group)
//                    .distinct()
//                    .sorted()
//                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(new File(
                String.join("/", filePath, "fixedWidth.txt")))) {
            var results = scanner.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
                    .map(m -> m.group(3).trim())
                    .skip(1)
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(results));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}