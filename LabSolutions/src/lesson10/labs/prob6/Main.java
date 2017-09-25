package lesson10.labs.prob6;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        testQueue();
        testThreadSafeQueue();
    }

    private static void testQueue()  {
        Queue queue = new Queue();

        CountDownLatch tasks = new CountDownLatch(2);
        Runnable addTask =  () -> {
            IntStream.range(0, 10000).forEach(i->queue.add(i));
            tasks.countDown();
        };

        (new Thread(addTask)).start();
        (new Thread(addTask)).start();

        try {
            tasks.await();
        } catch (Exception e) {
        }

        int i=0;
        while (true) {
            if (queue.remove() !=null) {
                i++;
            } else {
                break;
            }
        }
        System.out.println("queue added 20000 items, but only have [" + i + "] items." ) ;

    }

    private static void testThreadSafeQueue()  {
        ThreadSafeQueue queue = new ThreadSafeQueue();

        CountDownLatch tasks = new CountDownLatch(2);
        Runnable addTask =  () -> {
            IntStream.range(0, 10000).forEach(i->queue.add(i));
            tasks.countDown();
        };

        (new Thread(addTask)).start();
        (new Thread(addTask)).start();

        try {
            tasks.await();
        } catch (Exception e) {
        }

        int i=0;
        while (true) {
            if (queue.remove() !=null) {
                i++;
            } else {
                break;
            }
        }
        System.out.println("ThreadSafeQueue added 20000 items, but only have [" + i + "] items." ) ;

    }

}
