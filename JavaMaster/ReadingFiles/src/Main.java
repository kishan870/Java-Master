import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String filePath = "JavaMaster/ReadingFiles";
        try (FileReader fileReader = new FileReader(String.join("/", filePath, "file.txt"))) {

            char[] block = new char[1000];
            int data;
            while((data = fileReader.read(block)) != -1) {
                String content = new String(block, 0, data);
                System.out.printf("----> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-".repeat(80));

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(String.join("/", filePath, "file.txt")))) {

//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
            bufferedReader.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}