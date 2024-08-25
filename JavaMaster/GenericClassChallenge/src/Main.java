//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        var nationalUSParks = new Park[]{
                new Park("Yellowstone", "44.4882, -110.5916"),
                new Park("Grand Canyon", "36.0636, -112.1079"),
                new Park("Yosemite", "37.8855, -119.5360")
        };

        var majorUSRivers = new River[]{
          new River("Mississipi",
                  "47.2160, -95.2348", "35.1556, -90.0659",
                  "29.1566, -89.2495"),

          new River("Missouri",
                  "45.9239, -111.4983", "38.8146, -90.1218")
        };

        Layer<Mappable> majorUSSites = new Layer<>(nationalUSParks);
        majorUSSites.addElements(majorUSRivers);

        majorUSSites.renderLayer();

    }
}