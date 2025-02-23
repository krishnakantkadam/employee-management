package core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
*
* Multiple tasks appear to run simultaneously but may not actually execute at the same moment.
* It involves context switching between tasks (threads).
* It is about dealing with multiple tasks at once, not necessarily running them at the same time.
* Typically used when multiple operations need to be handled efficiently,
* like handling multiple user requests in a web server.
* */
public class ConcurrencyExample
{
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Two threads

        Runnable task1 = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Task 1 - " + i);
                sleep(100);
            }
        };

        Runnable task2 = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Task 2 - " + i);
                sleep(100);
            }
        };

        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}