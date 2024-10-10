import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.format.DateTimeFormatter.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private record Employee(String name, Locale locale, ZoneId zone) {

        public Employee(String name, String locale, String zone) {
            this(name, Locale.forLanguageTag(locale), ZoneId.of(zone));
        }

        public Employee(String name, Locale locale, String zone) {
            this(name, locale, ZoneId.of(zone));
        }

        String getDateInfo(ZonedDateTime zdt, DateTimeFormatter dtf) {
            return "%s [%s] : %s".formatted(name, zone,
                    zdt.format(dtf.localizedBy(locale)));
        }
    }

    public static void main(String[] args) {

        Employee jane = new Employee("Jane", Locale.US,  "America/New_York");
        Employee joe = new Employee("Joe", "en-AU", "Australia/Eucla");

        ZoneRules janeRules = jane.zone.getRules();
        ZoneRules joeRules = joe.zone.getRules();

        System.out.println(jane + " : " + janeRules);
        System.out.println(joe + " : " + joeRules);

        ZonedDateTime janeNow = ZonedDateTime.now(jane.zone);
        ZonedDateTime joeNow = ZonedDateTime.of(janeNow.toLocalDateTime(), joe.zone);

        long hoursBetween = Duration.between(joeNow, janeNow).toHours();
        long minutesBetween = Duration.between(joeNow, janeNow).toMinutesPart();
        System.out.println("Joe is " + Math.abs(hoursBetween) + " hours " +
                Math.abs(minutesBetween) + " minutes " +
                ((hoursBetween < 0)? "behind": "ahead"));

        System.out.println("Joe in daylight savings? " +
                joeRules.isDaylightSavings(joeNow.toInstant()) + " " +
                joeRules.getDaylightSavings(joeNow.toInstant()) + " : " +
                joeNow.format(ofPattern("zzzz z")));

        System.out.println("Jane in daylight savings? " +
                janeRules.isDaylightSavings(janeNow.toInstant()) + " " +
                janeRules.getDaylightSavings(janeNow.toInstant()) + " : " +
                janeNow.format(ofPattern("zzzz z")));

        int days = 10;

        var map = schedule(jane, joe, days);
        DateTimeFormatter dtf = ofLocalizedDateTime(FormatStyle.FULL,
                FormatStyle.SHORT);

        for(LocalDate ldt: map.keySet()) {
            System.out.println(ldt.format(ofLocalizedDate(FormatStyle.FULL)));
            for(ZonedDateTime zdt: map.get(ldt)) {
                System.out.println("\t" +
                        jane.getDateInfo(zdt, dtf) + " <-----> " +
                        joe.getDateInfo(zdt.withZoneSameInstant(joe.zone), dtf));
            }
        }
    }

    private static Map<LocalDate, List<ZonedDateTime>> schedule(Employee first, Employee second,
                                                                int days) {

        Predicate<ZonedDateTime> rules = zdt ->
                zdt.getDayOfWeek() != DayOfWeek.SATURDAY
                && zdt.getDayOfWeek() != DayOfWeek.SUNDAY
                && zdt.getHour() >= 7 && zdt.getHour() < 21;

        LocalDate startingDate = LocalDate.now().plusDays(2);

        return startingDate.datesUntil(startingDate.plusDays(days + 1))
                .map(dt -> dt.atStartOfDay(first.zone()))
                .flatMap(dt -> IntStream.range(0, 24).mapToObj(dt::withHour))
                .filter(rules)
                .map(dtz -> dtz.withZoneSameInstant(second.zone()))
                .filter(rules)
                .collect(
                        Collectors.groupingBy(ZonedDateTime::toLocalDate,
                                TreeMap::new, Collectors.toList()));

    }
}