import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Challenge {
    public static void main(String[] args) {

        Course jmc = new Course("JMC", "Java MasterClass");
        Course pymc = new Course("PyMC", "Python MasterClass");

        String delimiter = "," + System.lineSeparator();
        String students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .map(Student::toJSON)
                .collect(Collectors.joining(delimiter, "[", "]"));

       // System.out.println(students);
        Path basePath = Path.of("JavaMaster/WritingFiles");

        try {
            Files.writeString(basePath.resolve("students.json"), students);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
