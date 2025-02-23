package core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueueDemo {
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    static class Producer implements Runnable {
        private final String name;

        Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int task = 1;
            while (true) {
                try {
                    queue.put(task);
                    System.out.println(name + " produced: " + task);
                    task++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private final String name;

        Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int task = queue.take();
                    System.out.println(name + " consumed: " + task);
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer("Producer-1"), "Producer-1");
        Thread consumerThread = new Thread(new Consumer("Consumer-1"), "Consumer-1");

        producerThread.start();
        consumerThread.start();
    }
}