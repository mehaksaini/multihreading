package semaphore;

import java.util.concurrent.locks.ReentrantLock;

public class Semaphore {
   int permits;
   int current=0;
    public Semaphore(int permits){
        this.permits = permits;
    }
    public synchronized void getPermit() throws InterruptedException {
        while(current==permits){
            wait();
        }
       current++;
        notifyAll();
    }

    public synchronized void releasePermit() throws InterruptedException {
        while(current==0){
            wait();
        }
       current--;
        notifyAll();
    }
}
