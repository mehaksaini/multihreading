package uber;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberRide {

    int republic = 0;
    int democrat = 0;
    Semaphore repubSema = new Semaphore(0);
    Semaphore demoSema = new Semaphore(0);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    ReentrantLock lock  = new ReentrantLock();
    public void demoRequest() throws InterruptedException, BrokenBarrierException {
        boolean isLeader = false;
        lock.lock();
        democrat++;
        if(democrat==4){
            demoSema.release(3);
            democrat-=4;
            isLeader=true;
        }else if(democrat==2&&republic>=2){
            demoSema.release(1);
            repubSema.release(2);
            democrat-=2;
            republic-=2;
            isLeader=true;
        }else{
            lock.unlock();
            demoSema.acquire();

        }


        seated("democrat");
        cyclicBarrier.await();
        if(isLeader){
          drive();
            lock.unlock();
        }

    }
    public void republicRequest() throws InterruptedException, BrokenBarrierException {
        boolean isLeader = false;
        lock.lock();
        republic++;
        if(republic==4){
            repubSema.release(3);
            republic-=4;
            isLeader=true;
        }else if(democrat>=2&&republic==2){
            repubSema.release(1);
            demoSema.release(2);
            democrat-=2;
            republic-=2;
            isLeader=true;
        }else{
            lock.unlock();
            repubSema.acquire();

        }

        seated("republic");
        cyclicBarrier.await();
        if(isLeader){
           drive();
            lock.unlock();
        }
    }
    public void seated(String id){System.out.println("seated: "+id
    );}
    public void drive(){System.out.println("driving");}
}
