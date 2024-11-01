import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Random random = new Random();

    public static void main(String[] args) {

        ShoeWarehouse warehouse = new ShoeWarehouse();
        Thread producerThread = new Thread(() -> {
            for(int j=0; j<10; j++) {
                warehouse.receiveOrder(new Order(
                        random.nextLong(1000000, 9999999),
                        ShoeWarehouse.PRODUCT_LIST[random.nextInt(0, 5)],
                        random.nextInt(0, 4)));
            }
        });
        producerThread.start();

        for(int i=0; i < 2; i++) {
            Thread consumerThread = new Thread(() -> {
                for(int j=0; j<5; j++) {
                    Order item = warehouse.fulfillOrder();
                }
            });
            consumerThread.start();
        }
    }
}