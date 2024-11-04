import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
record Person(String firstName, String lastName, int age) {

    private static final String[] firsts =
            {"Able", "Bob", "Charlie", "Donna", "Eve", "Fred"};

    private static final String[] lasts =
            {"Norton", "OHara", "Peterson", "Quincy", "Richardson", "Smith"};

    private static final Random random = new Random();

    public Person() {
        this(firsts[random.nextInt(firsts.length)],
                lasts[random.nextInt(lasts.length)],
                random.nextInt(18, 100));
    }

    @Override
    public String toString() {
        return "%s, %s (%d)".formatted(lastName, firstName, age);
    }
}
public class Main {
    public static void main(String[] args) {

        var persons = Stream.generate(Person::new)
                        .limit(10)
                                .sorted(Comparator.comparing(Person::lastName)
                                        .thenComparing(Person::firstName))
                                        .toArray();
        for(var person: persons) {
            System.out.println(person);
        }
        System.out.println("-".repeat(50));

        Arrays.stream(persons)
                .limit(10)
                .parallel()
               // .sorted(Comparator.comparing(Person::lastName)
                //        .thenComparing(Person::firstName))
                .forEachOrdered(System.out::println);

        System.out.println("-".repeat(50));

        int sum = IntStream.range(1, 101)
                .parallel()
                .reduce(0, Integer::sum);

        System.out.println("The sum is " + sum);

        String humptyDumpty = """
                Humpty Dumpty sat on a wall.
                Humpty Dumpty had a great fall.
                All the king's horses and all king's men
                couldn't put Humpty together again.
                """;

        System.out.println("-".repeat(50));

        var words = new Scanner(humptyDumpty).tokens().toList();
        words.forEach(System.out::println);
        System.out.println("-".repeat(50));

        var backTogetherAgain = words
                .parallelStream()
                .collect(Collectors.joining(" "));

        System.out.println(backTogetherAgain);
        System.out.println("-".repeat(50));

//        Map<String, Long> lastNameCounts =
//                Stream.generate(Person::new)
//                        .limit(10000)
//                        .parallel()
//                        .collect(Collectors.groupingBy(
//                                Person::lastName,
//                                Collectors.counting()));

        Map<String, Long> lastNameCounts =
                Stream.generate(Person::new)
                        .limit(10000)
                        .parallel()
                        .collect(Collectors.groupingByConcurrent(
                                Person::lastName,
                                Collectors.counting()));

        lastNameCounts.entrySet().forEach(System.out::println);

        long total = lastNameCounts.values().stream()
                .reduce(0L, Long::sum);

        System.out.println("Total = " + total);

        System.out.println(lastNameCounts.getClass().getName());
        System.out.println("-".repeat(50));

//        var lastCounts = new ConcurrentSkipListMap<String, Long>();
        //This is one method

        var lastCounts = Collections.synchronizedMap(
                new TreeMap<String, Long>());

        Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .forEach((person) -> lastCounts.merge(person.lastName(),
                        1L, Long::sum));

        System.out.println(lastCounts);

        total = lastCounts.values().stream()
                .reduce(0L, Long::sum);

        System.out.println("Total sum: " + total);
        System.out.println(lastCounts.getClass().getName());
    }
}