import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static String baseDirectory = "JavaMaster/FileExceptions/files";
    static Path basePath = Path.of(baseDirectory);
    static File baseFilePath = new File(baseDirectory);

    public static void main(String[] args) {

        System.out.println("Current working directory (cwd) = "
                            + new File("").getAbsolutePath());

        String filename = "testing.csv";
        File file = new File(filename);

        for(File f: File.listRoots()) {
            System.out.println(f);
        }

        if(!file.exists()) {
            System.out.println("File does not exist!");

        } else {
            System.out.println("File opened successfully");
        }

        //testFile2(filename);

        Path path = Paths.get(filename);
        System.out.println(file.getAbsolutePath());

        if(!Files.exists(path)) {
            System.out.println("2. File does not exist!");

        } else {
            System.out.println("2. File opened successfully");
        }

        System.out.println("-".repeat(40));
        useFile(filename);

        System.out.println("---------Using Path--------");
        usePath("testing.txt");
    }

    private static void testFile(String filename) {

        try {
            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path);
            System.out.println(lines);

        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            System.out.println("Maybe I will try to log some other way...");
        }
        System.out.println("File exists and able to use as a resource");
    }

    private static void testFile2(String filename) {

        try (FileReader reader = new FileReader(filename)) {
        } catch (FileNotFoundException e) {
            System.out.println("File '" + filename + "' does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File exists and able to use as resource");

    }

    private static void useFile(String fileName) {

        File file = new File(baseFilePath, fileName);
        boolean fileExists = file.exists();

        System.out.printf("File '%s' %s%n", fileName,
                fileExists? "exists" : "does not exist");

        if(fileExists) {
            System.out.println("Deleting file...");
            fileExists = !file.delete();
        }

        if(!fileExists) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create file....");
                e.printStackTrace();
            }
            System.out.println("Created new file...");
            if(file.canWrite()) {
                System.out.println("Can write to file here...");
            }
        }
    }

    private static void usePath(String fileName) {

        Path path = basePath.resolve(fileName);
        boolean fileExists = Files.exists(path);

        System.out.printf("File '%s' %s%n", fileName,
                fileExists? "exists" : "does not exist");

        if(fileExists) {
            System.out.println("Deleting file...");
            try {
                Files.delete(path);
                fileExists = false;

            } catch (IOException e) {
                System.out.println("Error deleting the file....");
                e.printStackTrace();
            }
        }

        if(!fileExists) {

            try {
                Files.createFile(path);

                System.out.println("Created new file...");
                if (Files.isWritable(path)) {
                    System.out.println("Writing to file here...");
                    String writeContent = """
                            Here is some data,
                            for my file,
                            Just to prove,
                            using Files class and path are better.
                            """;
                    Files.writeString(path, writeContent);
                }

                System.out.println("Now reading the same file.....");
                System.out.println("-".repeat(40));
                Files.readAllLines(path).forEach(System.out::println);
                System.out.println("-".repeat(40));

            }  catch (IOException e) {
                    System.out.println("Unable to process file....");
                    e.printStackTrace();
            }
        }
    }
}