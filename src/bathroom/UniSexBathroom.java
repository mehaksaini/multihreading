package bathroom;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UniSexBathroom {

    Semaphore occupied = new Semaphore(3);
    int womanInside = 0;
    int manInside = 0;
    volatile int womanWaiting = 0;
    volatile int manWaiting = 0;
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition menWaiting = reentrantLock.newCondition();
    Condition womenWaiting = reentrantLock.newCondition();
    public void enterWoman() throws InterruptedException {
        reentrantLock.lock();
        womanWaiting++;
        while(manInside>0 || occupied.availablePermits()==0){
            //   System.out.println("Women: "+Thread.currentThread().getId()+" waiting.");
            womenWaiting.await();
        }
        occupied.acquire();
        womanWaiting--;
        System.out.println("Women: "+Thread.currentThread().getId()+" inside.");
        womanInside++;
        reentrantLock.unlock();
        useBathRoom();
        occupied.release();
        reentrantLock.lock();
        womanInside--;
        //System.out.println("Women: "+Thread.currentThread().getId()+" exited.");
        if(manWaiting>0){
            menWaiting.signalAll();
        }else{
            womenWaiting.signalAll();
        }
        reentrantLock.unlock();

    }
    public void enterMan() throws InterruptedException {
        reentrantLock.lock();
        manWaiting++;
        while(womanInside>0 || occupied.availablePermits()==0){

           menWaiting.await();

        }
        occupied.acquire();
        manWaiting--;
        System.out.println("Men: "+Thread.currentThread().getId()+" inside.");
        manInside++;
        reentrantLock.unlock();
        useBathRoom();
        occupied.release();
        reentrantLock.lock();
        manInside--;
     //   System.out.println("Men: "+Thread.currentThread().getId()+" exited.");
        if(womanWaiting>0){
            womenWaiting.signalAll();
        }else {
            menWaiting.signalAll();
        }
            reentrantLock.unlock();
    }
    public void useBathRoom() throws InterruptedException {
        Thread.sleep(3000);
    }

}
