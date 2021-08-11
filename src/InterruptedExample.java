public class InterruptedExample {

        public static void main(String args[]) throws InterruptedException {
            final Thread sleepyThread = new Thread(() -> {
                try {
                    System.out.println("I am too sleepy... Let me sleep for an hour.");
                    Thread.sleep(1000 * 60 * 60);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.out.println("The interrupt flag is cleard : " + Thread.currentThread().isInterrupted());

                    System.out.println("Oh someone woke me up ! ");
                    System.out.println("The interrupt flag is set now : " + Thread.interrupted());
                }
            });
            sleepyThread.start();
            Thread.sleep(1);
            System.out.println("About to wake up the sleepy thread ...");
            sleepyThread.interrupt();
            System.out.println("Woke up sleepy thread ...");
            sleepyThread.join();
        }


}
