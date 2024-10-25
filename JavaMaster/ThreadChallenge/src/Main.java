//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class OddThread extends Thread {
    @Override
    public void run() {
        for(int i=1; i <10; i = i+2) {
            System.out.println("OddThread: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("OddThread interrupted!");
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Thread evenThread = new Thread(() -> {
            for(int i=2; i <=10; i = i+2) {
                System.out.println("EvenRunnable: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("EvenRunnable interrupted!");
                    break;
                }
            }
        });

        OddThread oddThread = new OddThread();
        evenThread.start();
        oddThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        oddThread.interrupt();
    }
}