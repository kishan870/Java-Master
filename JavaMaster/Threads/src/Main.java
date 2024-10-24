import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        var currentThread = Thread.currentThread();
        System.out.println(currentThread.getClass().getName());
        printThreadState(currentThread);

        currentThread.setName("MainGuy");
        currentThread.setPriority(Thread.MAX_PRIORITY);
        printThreadState(currentThread);

        CustomThread customThread = new CustomThread();

        Thread nextThread = new Thread(() -> {
            for(int i=1; i<=4; i++) {
                System.out.print(" 2 ");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //customThread.run();
        //If we use run() method, this thread will run synchronously
        customThread.start();
        nextThread.start();

        for(int i=1; i<=3; i++) {
            System.out.print(" 0 ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void printThreadState(Thread thread) {

        System.out.println("-".repeat(30));
        System.out.println("Thread ID: " + thread.getId());
        System.out.println("Thread Name: " + thread.getName());
        System.out.println("Thread priority: " + thread.getPriority());
        System.out.println("Thread State: " + thread.getState());
        System.out.println("Thread group: " + thread.getThreadGroup());
        System.out.println("Thread is Alive: " + thread.isAlive());
        System.out.println("-".repeat(30));
    }
}