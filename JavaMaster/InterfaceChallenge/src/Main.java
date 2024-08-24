import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Mappable> mappables = new ArrayList<>();

        mappables.add(new Building("Sydney Town", UsageType.GOVERNMENT));
        mappables.add(new Building("Sydney Opera Type", UsageType.ENTERTAINMENT));
        mappables.add(new Building("Sydney Cricket Ground", UsageType.SPORTS));

        mappables.add(new UtilityLine("Sydney Power authority", UtilityType.ELECTRICAL));
        mappables.add(new UtilityLine("Spectrum Telecom", UtilityType.FIBER_OPTIC));
        mappables.add(new UtilityLine("Sydney Water Corporation", UtilityType.WATER));

        for(var m: mappables) {
            Mappable.mapIt(m);
        }

    }
}