import java.time.ZoneId;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println(ZoneId.systemDefault());
        System.out.println("Number of available timezones: " + ZoneId.getAvailableZoneIds().size());
        ZoneId.getAvailableZoneIds().stream()
                .filter(s -> s.contains(""))
                .forEach(System.out::println);
    }
}