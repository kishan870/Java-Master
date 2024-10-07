import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class MainMapping {
    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");

        List<Student> students =
                Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                        .limit(5000)
                        .collect(toList());

        var mappedStudents = students.stream()
                .collect(groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k, v) -> System.out.println(k + " : " + v.size()));
        System.out.println("-".repeat(30));

        int minAge = 25;
        var youngsterSet = students.stream()
                .collect(groupingBy(Student::getCountryCode,
                        filtering(s -> s.getAge() <= minAge, toList())));

        youngsterSet.forEach((k, v) -> System.out.println(k + " : " + v.size()));
        System.out.println("-".repeat(30));

        var experienced = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience));
        System.out.println("Experienced students = " + experienced.get(true).size());

        var expCount = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience, counting()));
        System.out.println("Experienced students = " + expCount.get(true));

        var experiencedAndActive = students.stream()
                .collect(partitioningBy(s -> s.hasProgrammingExperience()
                        && s.getMonthsSinceActive() == 0
                        , counting()));
        System.out.println("Experienced and Active students = " + experiencedAndActive.get(true));

        System.out.println("-".repeat(30));
        var multiLevel = students.stream()
                .collect(groupingBy(Student::getCountryCode,
                        groupingBy(Student::getGender)));

        multiLevel.forEach((key, value) -> {
            System.out.println("Country: " + key);
            value.forEach((key1, value1) ->
                    System.out.println("\t" + key1 + " " + value1.size()));
        });
        System.out.println("-".repeat(30));

        long studentBodyCount = 0;
        for(var list: experienced.values()) {
            studentBodyCount += list.size();
        }
        System.out.println("StudentBodyCount = " + studentBodyCount);

        studentBodyCount = experienced.values().stream()
                .map(l -> l.stream()
                        .filter(s -> s.getMonthsSinceActive() <= 3)
                        .count())
                .mapToLong(l -> l)
                .sum();
        System.out.println("StudentBodyCount = " + studentBodyCount);

        long count = experienced.values().stream()
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students = " + count);

        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream()
                        .flatMap(l -> l.stream()))
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students in multilevel = " + count);
    }
}
