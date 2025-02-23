package core;

import java.util.LinkedList;

public class ProducerConsumerSyncDemo {
    private static final LinkedList<Integer> queue = new LinkedList<>();
    private static final int MAX_SIZE = 5;
    private static final int MAX_TASKS = 25;

    static class Producer implements Runnable {
        @Override
        public void run() {
            int task = 1;
            while (task <= MAX_TASKS) {
                synchronized (queue) {
                    while (queue.size() >= MAX_SIZE) {
                        System.out.println("Queue is full, Producer is waiting...");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    queue.add(task);
                    System.out.println("Produced: " + task);
                    task++;
                    queue.notify();
                }
                sleep(750);
            }
            System.out.println("Producer completed " + MAX_TASKS + " tasks and is stopping.");
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        System.out.println("Queue is empty, Consumer is waiting...");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    int task = queue.removeFirst();
                    System.out.println("Consumed: " + task);
                    queue.notify();
                }
                sleep(1500);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}