package reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTester {
    ReentrantLock reentrantLock= new ReentrantLock();
    Condition condition = reentrantLock.newCondition();
    public void second() throws InterruptedException {
        reentrantLock.lock();
        System.out.println("notifying");
        condition.signalAll();
        System.out.println("notified");
        reentrantLock.unlock();
        Thread.sleep(1000);
        System.out.println("second unlocked");
    }
    public void first() throws InterruptedException {
        reentrantLock.lock();
        System.out.println("waiting");
        condition.await(20000, TimeUnit.MILLISECONDS);
        System.out.println("waiting done");
        reentrantLock.unlock();
        System.out.println("first unlocked");
    }
}
