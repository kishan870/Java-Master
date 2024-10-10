import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate Five5 = LocalDate.of(2024, 5, 5);
        System.out.println(Five5);

        LocalDate May5th = LocalDate.of(2024, Month.MAY, 5);
        System.out.println(May5th);

        LocalDate Day125 = LocalDate.ofYearDay(2024, 128);
        System.out.println(Day125);

        LocalDate May5 = LocalDate.parse("2024-05-05");
        System.out.println(May5.getYear() + " " + May5.getMonth() + " " + May5.getDayOfMonth() + " "
        + May5.getDayOfWeek());

        System.out.println(May5.get(ChronoField.YEAR));
        System.out.println(May5.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(May5.get(ChronoField.DAY_OF_MONTH));
        System.out.println(May5.get(ChronoField.DAY_OF_YEAR));
        System.out.println("-".repeat(30));

        System.out.println(May5.withYear(2000));
        System.out.println(May5.withMonth(3));
        System.out.println(May5.withDayOfMonth(4));
        System.out.println(May5.withDayOfYear(126));

        System.out.println(May5.plusYears(1));
        System.out.println(May5.plusMonths(12));
        System.out.println(May5.plusDays(565));
        System.out.println("-".repeat(30));

        System.out.println(May5.minusYears(2));
        System.out.println(May5.minusMonths(14));
        System.out.println(May5.minusDays(677));

        System.out.println("May5 > today? " + May5.isAfter(today));
        System.out.println("today > May5? " + May5.isBefore(today));
        System.out.println("May5 > today? " + May5.compareTo(today));
        System.out.println("today > May5? " + today.compareTo(May5));
        System.out.println("today == now? " + today.compareTo(LocalDate.now()));
        System.out.println("IsLeapYear? " + today.isLeapYear());

        System.out.println("-".repeat(30));
        May5.datesUntil(May5.plusDays(7))
                .forEach(System.out::println);

        System.out.println("-".repeat(30));
        May5.datesUntil(May5.plusYears(1), Period.ofDays(7))
                .forEach(System.out::println);
        System.out.println("-".repeat(30));

        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalTime sevenThirtyTwoAM = LocalTime.of(7, 32, 45);
        System.out.println(sevenThirtyTwoAM);

        LocalTime eightThirtyPM = LocalTime.parse("20:30:43.7656");
        System.out.println(eightThirtyPM);
        System.out.println(eightThirtyPM.getMinute());
        System.out.println(eightThirtyPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(eightThirtyPM.get(ChronoField.HOUR_OF_AMPM));

        LocalDateTime todayAndNow = LocalDateTime.now();
        System.out.println(todayAndNow);

        LocalDateTime May5Noon = LocalDateTime.of(2024, 5, 5, 11, 59, 12);
        System.out.printf("%tD %tr %n", May5Noon, May5Noon);
        System.out.printf("%1$tF %1$tr %n", May5Noon);

        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_WEEK_DATE));

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(May5Noon.format(dtf));

        System.out.println(May5Noon.format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM
        )));

        System.out.println(May5Noon.format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.SHORT
        )));
    }
}