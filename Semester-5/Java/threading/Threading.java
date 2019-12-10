public class Threading {

    public static void main(String[] args) {

        System.out.println("Main thread started!");

        final Task t1 = new Task(2000, 1, 5);
        final Task t2 = new Task(2500, 6, 10);
        final Task t3 = new Task(3000, 11, 15);
        final Task t4 = new Task(3500, 1, 10);

        final Thread thread1 = new Thread(t1);
        final Thread thread2 = new Thread(t2);
        final Thread thread3 = new Thread(t3);
        final Thread thread4 = new Thread(t4);

        // thread1.setName("Thread 1");
        // thread2.setName("Thread 2");
        // thread3.setName("Thread 3");
        // thread4.setName("Thread 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {

            System.out.printf("%s is waiting for %s to complete\n", Thread.currentThread().getName(),
                    thread1.getName());
            thread1.join();
            System.out.printf("Result of %s is : %d\n", thread1.getName(), t1.getSum());

            System.out.printf("%s is waiting for %s to complete\n", Thread.currentThread().getName(),
                    thread2.getName());
            thread2.join();
            System.out.printf("Result of %s is : %d\n", thread2.getName(), t2.getSum());

            System.out.printf("%s is waiting for %s to complete\n", Thread.currentThread().getName(),
                    thread3.getName());
            thread3.join();
            System.out.printf("Result of %s is : %d\n", thread3.getName(), t3.getSum());

            System.out.printf("%s is waiting for %s to complete\n", Thread.currentThread().getName(),
                    thread4.getName());
            thread4.join();
            System.out.printf("Result of %s is : %d\n", thread4.getName(), t4.getSum());

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("Main thread ended!\n");
    }

}

class Task implements Runnable {

    private long sleep;
    private int sum;
    private int start;
    private int end;

    public Task(final long sleepTime, int start, int end) {
        this.sleep = sleepTime;
        this.start = start;
        this.end = end;
        sum = 0;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.printf("[Thread - %s] > Adding %d\n", Thread.currentThread().getName(), i);
            sum = sum + i;
        }
        try {
            Thread.sleep(sleep);
        } catch (final InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public int getSum() {
        return this.sum;
    }

}