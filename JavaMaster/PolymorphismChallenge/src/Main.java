//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Car car = new Car("2022 Blue Ferrari 296 GTS");
        runRace(car);

        Car ferrari = new HybridCar("2022 Blue Ferrari 296 GTS",
                15.4, 6, 8);
        runRace(ferrari);

        Car tesla = new ElectricCar("2022 red Tesla model 3", 568, 75);
        runRace(tesla);
    }

    public static void runRace(Car car) {
        car.startEngine();
        car.drive();
    }
}