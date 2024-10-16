import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static String baseDirectory = "JavaMaster/PathListings/files";
    static Path basePath = Path.of(baseDirectory);

    public static void main(String[] args) {

        Path path = basePath.resolve("testing.txt");
        printPathInfo(path);

        System.out.println("-".repeat(40));
        logStatement(path);
        extraInfo(path);
    }

    private static void printPathInfo(Path path) {

        System.out.println("Path: " + path);
        System.out.println("filename = " + path.getFileName());
        System.out.println("parent= " + path.getParent());

        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute path = " + absolutePath);
        System.out.println("Absolute path root = " + absolutePath.getRoot());
        System.out.println("Root = " + path.getRoot());
        System.out.println("isAbsolute = " + path.isAbsolute());

        //int i=1;
//        var it = path.toAbsolutePath().iterator();
//        while (it.hasNext()) {
//            System.out.println(".".repeat(i++) + " " + it.next());
//        }

        System.out.println(path.getRoot());
        int pathParts = absolutePath.getNameCount();
        for(int i=0; i<pathParts; i++) {
            System.out.println(".".repeat(i+1) + " " + absolutePath.getName(i));
        }

        System.out.println("-".repeat(40));
    }

    private static void logStatement(Path path) {

        try {
            Path parent = path.getParent();
            if(!Files.exists(parent)) {
                Files.createDirectory(parent);
            }

            Files.writeString(path, Instant.now() +
                    ": hello file world\n", StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extraInfo(Path path) {

        try {
            var atts = Files.readAttributes(path, "*");
            atts.entrySet().forEach(System.out::println);
            System.out.println(Files.probeContentType(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}