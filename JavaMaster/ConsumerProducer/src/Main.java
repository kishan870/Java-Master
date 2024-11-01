import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class MessageRepository {

    private String message;
    private boolean hasMessage = false;

    private final Lock lock = new ReentrantLock();

    public String read() {

        if(lock.tryLock()) {
            try {
                while (!hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;

            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("**** Read message blocked***");
            hasMessage = false;
        }
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public void write(String message) {

        if(lock.tryLock()) {
            try {
                while (hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = true;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("***** Write Message blocked ****");
            hasMessage = true;
        }
        setMessage(message);
    }
}

class MessageWriter implements Runnable {

    private MessageRepository outgoingMessage;

    private final String text = """
            Humpty Dumpty sat on a wall,
            Humpty Dumpty had a great fall,
            All the king's horses and all king's men,
            Couldn't put Humpty together again.
            """;

    public MessageWriter(MessageRepository outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {

        Random random = new Random();
        String[] lines = text.split("\n");

        for(int i=0; i< lines.length; i++) {
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        outgoingMessage.write("Finished...");
    }
}

class MessageReader implements Runnable {

    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {

        Random random = new Random();
        String latestMessage = "";

        do {
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = incomingMessage.read();
            System.out.println(latestMessage);
        } while (!latestMessage.equals("Finished..."));
    }
}

public class Main {
    public static void main(String[] args) {

        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository));
        Thread writer = new Thread(new MessageWriter(messageRepository));

        writer.setUncaughtExceptionHandler((thread, exc) -> {
            System.out.println("Writer had an exception: " + exc);
            if(reader.isAlive()) {
                System.out.println("Interrupting the reader...");
                reader.interrupt();
            }
        });

        reader.setUncaughtExceptionHandler((thread, exc) -> {
            System.out.println("Reader had an exception: " + exc);
            if(writer.isAlive()) {
                System.out.println("Interrupting the writer...");
                writer.interrupt();
            }
        });

        reader.start();
        writer.start();
    }
}