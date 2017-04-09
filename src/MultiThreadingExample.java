import javax.print.attribute.standard.RequestingUserName;

/**
 * Created by Dave on 4/9/17.
 */
public class MultiThreadingExample {

    public static void main(String[] args) {

        // tasks
        PrintString stringToPrint = new PrintString("Thread 1", 25);
        PerfectSquares perfectSquares = new PerfectSquares(25);

        // Construct threads
        Thread thread1 = new Thread(stringToPrint);
        Thread thread2 = new Thread(perfectSquares);

        // Start the threads or nothing will happen
        thread1.start();
        thread2.start();

        // since these threads have the same priority this is a bad example thread 1 finishes then 2 starts
        // we can change this by using the yield method
    }
}


class PrintString implements Runnable {

    private String string;
    private int timesToPrint;

    public PrintString(String string, int timesToPrint) {
        this.string = string;
        this.timesToPrint = timesToPrint;
    }

    @Override
    public void run() {
        for (int i = 0; i < timesToPrint; i++) {
            System.out.println(string);

            // By adding in the yield method we see this thread executes first then gives up it's status to
            // thread2

            Thread.yield();
        }
    }
}


class PerfectSquares implements Runnable {

    private int numOfPerfectSquares;

    public PerfectSquares(int numOfPerfectSquares) {
        this.numOfPerfectSquares = numOfPerfectSquares;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < numOfPerfectSquares; i++) {
                System.out.println(i * i);
                Thread.sleep(2); // thread waits 2 milliseconds
            }
        }
        catch (InterruptedException ie) {
            System.out.println(ie.getLocalizedMessage());
        }
    }
}