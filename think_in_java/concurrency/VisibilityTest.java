package exam.review.think_in_java.concurrency;

/**
 * Created by shanwu on 17-4-15.
 */
public class VisibilityTest extends Thread {
    volatile boolean keepRunning = true; // if it's not volatile, this thread will be running forever

    public void run() {
        long count = 0;
        while (keepRunning) {
            System.out.println("running");
            count++;
        }

        System.out.println("Thread terminated." + count);
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityTest t = new VisibilityTest();
        t.start();
        Thread.sleep(1000);
        t.keepRunning = false;
        System.out.println("keepRunning set to false.");
    }
}