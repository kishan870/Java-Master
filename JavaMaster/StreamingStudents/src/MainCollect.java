import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {
    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");

        List<Student> students =
                Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                        .limit(1000)
                        .toList();

        Set<Student> austalianStudents = students.stream()
                .filter(s -> s.getCountryCode().equals("AU"))
                .collect(Collectors.toSet());
        System.out.println("# of Australian students: " + austalianStudents.size());

        Set<Student> underThirty = students.stream()
                .filter(s -> s.getAgeEnrolled() < 30)
                .collect(Collectors.toSet());
        System.out.println("# of under thirty students: " + underThirty.size());

        Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(
                Student::getStudentId
        ));
        youngAussies1.addAll(austalianStudents);
        youngAussies1.retainAll(underThirty);
        youngAussies1.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println("\n" + "-".repeat(30));

        Set<Student> youngAussies2 = students.stream()
                .filter(s -> s.getAgeEnrolled() < 30)
                .filter(s -> s.getCountryCode().equals("AU"))
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)),
                        TreeSet::add, TreeSet::addAll);

        youngAussies2.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println("\n" + "-".repeat(30));

        String countryList = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .reduce("", (r, v) -> r + " " + v);

        System.out.println("CountryList = " + countryList);
    }
}
