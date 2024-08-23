import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DaysOfWeek weekDay = DaysOfWeek.TUES;

        System.out.println(weekDay);

        for(int i=0; i<10; i++) {
            weekDay = getRandomDay();
            System.out.printf("Name: %s, Ordinal: %d%n", weekDay.name(), weekDay.ordinal());
        }
    }

    public static DaysOfWeek getRandomDay() {
        int randomIndex = new Random().nextInt(7);

        var allDays = DaysOfWeek.values();
        return allDays[randomIndex];
    }
}