import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String header = """
                Student Id,Country Code,Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";

        Course jmc = new Course("JMC", "Java MasterClass");
        Course pymc = new Course("PyMC", "Python MasterClass");

        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(25)
                .toList();

//        System.out.println(header);
//        students.forEach(s -> s.getEngagementRecords().forEach(System.out::println));

        Path basePath = Path.of("JavaMaster/WritingFiles");
        Path path = basePath.resolve("students.csv");

//        try {
//            Files.writeString(path, header);
//            for(Student student: students) {
//                Files.write(path, student.getEngagementRecords(),
//                        StandardOpenOption.APPEND);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            List<String> data = new ArrayList<>();
//            data.add(header);
//            for(Student student: students) {
//                data.addAll(student.getEngagementRecords());
//            }
//            Files.write(path, data);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //try (BufferedWriter writer = Files.newBufferedWriter(basePath.resolve("take2.csv"))) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("JavaMaster/WritingFiles/take21.csv"))) {
            writer.write(header);
            for(Student student: students) {
                for(var record:student.getEngagementRecords()) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("JavaMaster/WritingFiles/output.json"))) {

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}