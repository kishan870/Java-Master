import java.util.Arrays;
import java.util.Map;

public interface Mappable {

    void render();

    static double[] stringToLatLon(String location) {
        String[] splitString = location.split(",");
        double lat = Double.valueOf(splitString[0]);
        double lon = Double.valueOf(splitString[1]);
        return new double[]{lat, lon};
    }
}

abstract class Point implements Mappable {
    private double[] location = new double[2];

    public Point(String location) {
        this.location = Mappable.stringToLatLon(location);
    }

    @Override
    public void render() {
        System.out.println("Render "+ this + " as POINT (" + printLocation() + ")");
    }

    private String printLocation() {
        return Arrays.toString(location);
    }
}

abstract class Line implements Mappable {
    private double[][] locations;

    public Line(String... locations) {
        this.locations = new double[locations.length][];
        int index = 0;
        for(var l: locations) {
            this.locations[index++] = Mappable.stringToLatLon(l);
        }
    }

    @Override
    public void render() {
        System.out.println("Render "+ this + " as LINE (" + printLocations() + ")");
    }

    private String printLocations() {
        return Arrays.deepToString(locations);
    }
}
