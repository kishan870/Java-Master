
//Demonstrate the usage of methods. COnvert kilometers/hr to miles/hr and use printConversion method to display.
public class SpeedConvertor {
    public static long toMilesPerHour(double kilometersPerHour) {
        if(kilometersPerHour < 0) {
            return -1;
        }

        return Math.round((kilometersPerHour / 1.609));
    }

    public static void printConversion(double kilometersPerHour) {
        long milesPerHour = toMilesPerHour(kilometersPerHour);

        if(milesPerHour == -1) {
            System.out.println("Invalid value");
        }

        else {
            System.out.println(kilometersPerHour + " km/h = " + milesPerHour + " mi/h");
        }
    }

    public static void main(String[] args) {
        printConversion(1.5);
        printConversion(10.25);
        printConversion(-5.6);
        printConversion(25.42);
        printConversion(75.114);
    }
}
