import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i+1)
                .filter(Character::isAlphabetic)
                .forEach(s -> System.out.printf("%c ", s));

        System.out.println("\n" + "-".repeat(30));

        Random random = new Random();

        Stream.generate(() -> random.nextInt((int)'A', (int)'Z' + 1))
                .limit(50)
                .distinct()
                .sorted()
                .forEach(s -> System.out.printf("%c ", s));

        System.out.println("\n" + "-".repeat(30));

        int maxSeats = 100;
        int seatsInRow = 10;
        var stream =
                Stream.iterate(0, i -> i<maxSeats, i -> i+1)
                        .map(i -> new Seat((char) ('A' + i / seatsInRow),
                                i % seatsInRow + 1));

        stream.forEach(System.out::println);
        System.out.println("\n" + "-".repeat(30));

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i+1)
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                .distinct()
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .forEach(s -> System.out.printf("%c ", s));

        System.out.println("\n" + "-".repeat(30));

        var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow),
                        i % seatsInRow + 1))
                .skip(5)
                .limit(10)
                .peek(s -> System.out.println("---> " + s))
                .sorted(Comparator.comparing(Seat::price)
                        .thenComparing(Seat::toString));

        stream1.forEach(s -> System.out.print(s + " "));
    }
}