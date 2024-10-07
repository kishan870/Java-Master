import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");

//        Student tim = new Student("AU", 2019, 30, "M",
//                true, jmc, pymc);
//
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//
//        System.out.println(tim);

//        Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
//                .limit(10)
//                .forEach(System.out::println);

        Student[] students = new Student[1000];
        Arrays.setAll(students, (i) -> Student.getRandomStudent(jmc, pymc));

        var maleStudents = Arrays.stream(students)
                        .filter(s -> s.getGender().equals("M"));

        System.out.println("# of male students: " + maleStudents.count());

        for(String gender: List.of("M", "F", "U")) {
            var myStudents = Arrays.stream(students)
                    .filter(s -> s.getGender().equals(gender));

            System.out.println("# of " + gender + " = " + myStudents.count());
        }

            List<Predicate<Student>> list = List.of(
                    s -> s.getAge() < 30,
                    (Student s) -> s.getAge() >= 30 && s.getAge() < 60
            );

            System.out.println("-".repeat(30));

            long total = 0;
            for(int i=0; i<list.size(); i++) {
                long cnt = Arrays.stream(students)
                        .filter(list.get(i))
                        .count();

                total += cnt;

                System.out.printf("# of students (%s) = %d%n",
                        i == 0 ? "< 30 " : " >=30 && < 60 ", cnt);
            }

        System.out.println("# of students >=60 " + (students.length - total));
        System.out.println("-".repeat(30));

        var ageStream = Arrays.stream(students)
                .mapToInt(Student::getAgeEnrolled);

        System.out.println("Stats for enrollment age: " + ageStream.summaryStatistics());

        var currentAgeStream = Arrays.stream(students)
                .mapToInt(Student::getAge);

        System.out.println("Stats for current age: " + currentAgeStream.summaryStatistics());

        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        System.out.println("\n" + "-".repeat(30));

        boolean longTerm = Arrays.stream(students)
                .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12));
        System.out.println("Long term students? " + longTerm);

        long longTermCount = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                        .count();
        System.out.println("Long term students count " + longTermCount);
        System.out.println("-".repeat(30));

        List<Student> longTimeStudents = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toList(); //This will return unmodifiable list

        longTimeStudents.forEach(System.out::println);

        var longTimeLearners = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toArray(Student[]::new);
                //.toArray(size -> new Student[size]);

        var learners = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .collect(Collectors.toList()); // This will return modifiable list

        Collections.shuffle(learners);
    }
}