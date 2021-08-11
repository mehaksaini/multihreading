package readwritelock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWrite {
    private String currentState;
    Boolean locked =false;
    int readers =0;

    public synchronized void aquireWriteLock() throws InterruptedException {

        while(locked||readers>0){
            wait();
        }
        locked=true;
    }
    public synchronized void reLeaseWriteLock() throws InterruptedException {

       locked=false;
       notifyAll();
    }
    public synchronized void aquireReadLock() throws InterruptedException {

        while(locked){
            wait();
        }
        readers++;
    }
    public synchronized void reLeaseReadLock() throws InterruptedException {

        readers=readers==0?0:readers--;
        notifyAll();
    }
    public synchronized String write(String newState){
        locked=true;
        this.currentState = newState;
       notifyAll();
       locked=false;
        return this.currentState;
    }

    public String read() throws InterruptedException {
      return this.currentState;
    }
}
