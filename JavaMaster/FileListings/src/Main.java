import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static String baseDirectory = "JavaMaster";
    static Path basePath = Path.of(baseDirectory);

    public static void main(String[] args) {

        Path path = basePath.resolve("");
        System.out.println("cwd= " + path.toAbsolutePath());

        try (Stream<Path> paths = Files.list(path)) {
            paths.filter(Files::isRegularFile)
                    .map(Main::listDir)
                    .forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("EXCEPTION");
            e.printStackTrace();
        }

        System.out.println("-".repeat(40));

        try (Stream<Path> paths = Files.walk(path, 2)) {
            paths.map(Main::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-".repeat(40));

        try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
                //(p, attr) -> Files.isRegularFile(p))) {
                (p, attr) -> attr.isRegularFile() && attr.size() > 300)) {
            paths.map(Main::listDir)
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println("============Directory Stream============");
        try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
            dirs.forEach(d -> System.out.println(Main.listDir(d)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String listDir(Path path) {

        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateField = Files.getLastModifiedTime(path);
            LocalDateTime modDT = LocalDateTime.ofInstant(
                    dateField.toInstant(), ZoneId.systemDefault());
            return "%tD %tT %-5s %12s %s"
                    .formatted(modDT, modDT, isDir? "<DIR>" : "",
                            (isDir? "" : Files.size(path)), path);

        } catch (IOException e) {
            System.out.println("Whoops! Something went wrong with " + path);
            return path.toString();
        }
    }
}