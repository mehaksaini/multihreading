package executorservice;

interface MyExecutorService {
    void submit(Runnable r);
   void shutDown() throws InterruptedException;
}