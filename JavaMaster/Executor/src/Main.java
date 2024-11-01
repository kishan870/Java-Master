import java.util.List;
import java.util.Timer;
import java.util.concurrent.*;

class ColorThreadFactory implements ThreadFactory {

    private String threadName;
    private int colorValue = 1;

    public ColorThreadFactory() {
    }

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
    }

    @Override
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);
        String name = threadName;

        if(name == null) {
            name = ThreadColor.values()[colorValue].name();
        }

        if(++colorValue > (ThreadColor.values().length - 1)) {
            colorValue = 1;
        }

        thread.setName(name);
        return thread;
    }
}
public class Main {
    public static void main(String[] args) {

        var multiExecutor = Executors.newCachedThreadPool();
        List<Callable<Integer>> taskList = List.of(
                () -> Main.sum(1, 10, 1, "red"),
                () -> Main.sum(10, 100, 10, "blue"),
                () -> Main.sum(2, 20, 2, "green")
        );
        try {
            var results = multiExecutor.invokeAll(taskList);
            for(var result: results) {
                System.out.println(result.get(500, TimeUnit.MILLISECONDS));
            }
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        finally {
            multiExecutor.shutdown();
        }
    }
    public static void cachedMain(String[] args) {

        var multiExecutor = Executors.newCachedThreadPool();

        try {
            var redValue = multiExecutor.submit(
                    () -> Main.sum(1, 10, 1, "red"));
            var blueValue = multiExecutor.submit(
                    () -> Main.sum(10, 100, 10, "blue"));
            var greenValue = multiExecutor.submit(
                    () -> Main.sum(2, 20, 2, "green"));

            try {
                System.out.println("Red value = " + redValue.get(500, TimeUnit.SECONDS));
                System.out.println("Blue value = " + blueValue.get(500, TimeUnit.SECONDS));
                System.out.println("Green value = " + greenValue.get(500, TimeUnit.SECONDS));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } finally {
            multiExecutor.shutdown();
        }
    }

    public static void fixedMain(String[] args) {

        int count = 6;
        var multiExecutor = Executors.newFixedThreadPool(
                count, new ColorThreadFactory());

        for(int i=0; i<count; i++) {
            multiExecutor.execute(Main::countDown);
        }

        multiExecutor.shutdown();
    }
    public static void singleMain(String[] args) {

        var blueExecutor = Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_BLUE));

        blueExecutor.execute(Main::countDown);
        blueExecutor.shutdown();

        boolean isDone = false;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(isDone) {
            var yellowExecutor = Executors.newSingleThreadExecutor(
                    new ColorThreadFactory(ThreadColor.ANSI_YELLOW));

            yellowExecutor.execute(Main::countDown);
            yellowExecutor.shutdown();

            try {
                isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(isDone) {
                var redExecutor = Executors.newSingleThreadExecutor(
                        new ColorThreadFactory(ThreadColor.ANSI_RED));

                redExecutor.execute(Main::countDown);
                redExecutor.shutdown();
            }
        }
    }

    public static void notMain(String[] args) throws InterruptedException {

        Thread blue = new Thread(
                Main::countDown, ThreadColor.ANSI_BLUE.name());

        Thread yellow = new Thread(
                Main::countDown, ThreadColor.ANSI_YELLOW.name());

        Thread red = new Thread(
                Main::countDown, ThreadColor.ANSI_RED.name());

        blue.start();
        blue.join();

        yellow.start();
        yellow.join();

        red.start();
        red.join();

//        blue.join();
//        yellow.join();
//        red.join();
    }

    private static void countDown() {

        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException ignore) {
            // User may pass bad color name, will just ignore
        }

        String color = threadColor.color();
        for(int i=20; i>=0; i--) {
            System.out.println(color + " " +
                    threadName.replace("ANSI_", "") + " " + i);
        }
    }

    private static int sum(int start, int end, int delta, String colorString) {

        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf("ANSI_" +
                    colorString.toUpperCase());
        } catch (IllegalArgumentException ignore) {
            // User may pass a bad color name, will just ignore
        }

        String color = threadColor.color();
        int sum = 0;
        for(int i=start; i<=end; i += delta) {
            sum += i;
        }
        System.out.println(color + Thread.currentThread().getName() + ", " +
                colorString + " " + sum);
        return sum;
    }
}