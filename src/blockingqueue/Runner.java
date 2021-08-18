package blockingqueue;


public class Runner {

    public static void main(String[]args) throws InterruptedException {
        BlockingQueue<Integer>integerBlockingQueue = new BlockingQueue<>();
        Thread thread1 = new Thread(()->{
            try {
                Object removed;
                removed = integerBlockingQueue.removeFromQueue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(()->{
            try {
                integerBlockingQueue.addToQueue(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }
}
