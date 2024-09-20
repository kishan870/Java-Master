import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<String> bingoPool = new ArrayList<>(75);
        int start = 1;

        for(char c: "BINGO".toCharArray()) {
            for(int i=start; i<(start+15); i++) {
                bingoPool.add("" + c + i);
                //System.out.println("" + c + i);
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        for(int i=0; i<15; i++)
            System.out.println(bingoPool.get(i));

        System.out.println("-".repeat(20));
       // List<String> firstOnes = bingoPool.subList(0, 15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());

        firstOnes.replaceAll(s -> {
            if(s.indexOf('G') == 0 || s.indexOf("O") == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n" + "-".repeat(20));

        for(int i=0; i<15; i++)
            System.out.println(bingoPool.get(i));
        System.out.println("-".repeat(20));

        //Using stream
//        bingoPool.stream()
//                .limit(15)
//                .filter(s -> s.indexOf('G') == 0 || s.indexOf('O') == 0)
//                .map(s -> s.charAt(0) + "-" + s.substring(1))
//                .sorted()
//                .forEach(s -> System.out.print(s + " "));

        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf('O') == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
                //.forEach(s -> System.out.print(s + " "));

        tempStream.forEach(s -> System.out.print(s + " "));

        //java.lang.IllegalStateException: stream has already been operated upon or closed
        try {
            tempStream.forEach(s -> System.out.print(s.toLowerCase() + " "));
        } catch (IllegalStateException e) {
        }

        System.out.println("\n" + "-".repeat(20));

        //Using Array as source
        String[] strings = {"One", "Two", "Three"};
        Arrays.stream(strings)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        //Using static Stream function
        Stream.of("Six", "Seven", "Eight")
                .map(String::toUpperCase)
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);

        System.out.println("-".repeat(20));
        //Concatenating two streams
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());

        var secondStream =  Stream.of("Six", "Seven", "Eight")
                .map(String::toUpperCase)
                .sorted(Comparator.naturalOrder());

        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + "-" + s)
                .forEach(System.out::println);

        System.out.println("-".repeat(20));
        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c: "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;
        }

        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - " +
                    e.getValue()[e.getValue().length - 1])
                .forEach(System.out::println);

        System.out.println("-".repeat(20));
        //Infinite stream with limit
        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

        //Int Stream
        System.out.println("\n" + "-".repeat(20));
        IntStream.iterate(1, n -> n + 1)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        //Generating top n prime numbers
        System.out.println("\n" + "-".repeat(30));
        IntStream.iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(40)
                .forEach(s -> System.out.print(s + " "));

        //Overloaded iterate function.
        //Uses predicate in the iterate arguments instead of limit
        System.out.println("\n" + "-".repeat(30));
        IntStream.iterate(1, n -> n<=100, n -> n+1)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

        //Intstream range takes upper & lower bound and auto increments by 1
        System.out.println("\n" + "-".repeat(30));
        IntStream.range(1, 100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

    }

    public static boolean isPrime(int wholeNumber) {

        if(wholeNumber <= 2)
            return (wholeNumber == 2);

        for(int divisor = 2; divisor <= wholeNumber/2; divisor++) {
            if(wholeNumber % divisor == 0)
                return false;
        }

        return true;
    }
}