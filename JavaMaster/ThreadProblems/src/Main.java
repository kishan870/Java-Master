import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String filePath = "JavaMaster/ThreadProblems";

        File resourceA = new File(String.join("/", filePath, "inputData.csv"));
        File resourceB = new File(String.join("/", filePath, "outputData.json"));

        Thread threadA = new Thread(() -> {

            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceA (csv) ");
            synchronized (resourceA) {
                System.out.println(threadName + " has lock on resourceA (csv)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(threadName + " has lock on resourceA (csv) " +
                        "and attempting to acquire lock on resourceB (json)");
                synchronized (resourceB) {
                    System.out.println(threadName + " has lock on resourceB (json)");
                }
                System.out.println(threadName + " has released lock on resourceB (json)");
            }
            System.out.println(threadName + " has released lock on resourceA (csv)");
        }, "THREAD-A");

        //This will cause circular wait leading to deadlock
//        Thread threadB = new Thread(() -> {
//
//            String threadName = Thread.currentThread().getName();
//            System.out.println(threadName + " attempting to lock resourceB (json) ");
//            synchronized (resourceB) {
//                System.out.println(threadName + " has lock on resourceB (json)");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//                System.out.println(threadName + " has lock on resourceB (json) " +
//                        "and attempting to acquire lock on resourceA (csv)");
//                synchronized (resourceA) {
//                    System.out.println(threadName + " has lock on resourceA (csv)");
//                }
//                System.out.println(threadName + " has released lock on resourceA (csv)");
//            }
//            System.out.println(threadName + " has released lock on resourceB (json)");
//        }, "THREAD-B");

        //Acquiring lock in the same order as thread A
        Thread threadB = new Thread(() -> {

            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " attempting to lock resourceA (csv) ");
            synchronized (resourceA) {
                System.out.println(threadName + " has lock on resourceA (csv)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(threadName + " has lock on resourceA (csv) " +
                        "and attempting to acquire lock on resourceB (json)");
                synchronized (resourceB) {
                    System.out.println(threadName + " has lock on resourceB (json)");
                }
                System.out.println(threadName + " has released lock on resourceB (json)");
            }
            System.out.println(threadName + " has released lock on resourceA (csv)");
        }, "THREAD-B");


        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}