package blockingqueue;


public class main {

    public static void main(String[]args) throws InterruptedException {
        BlockingQueue<Integer>integerBlockingQueue = new BlockingQueue<>();
        Thread thread1 = new Thread(()->{
            try {
                Object removed;
                removed = integerBlockingQueue.removeFromQueue();
                System.out.println("removed: "+removed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(()->{
            try {
                Object added = integerBlockingQueue.addToQueue(2);
                System.out.println("added: "+added);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }
}
