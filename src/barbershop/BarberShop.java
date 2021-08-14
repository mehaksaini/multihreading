package barbershop;




import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


public class BarberShop {
    int maxCustomer;
    Boolean gettingHairCut = false;
    int count=0;
    Random random= new Random();
    ReentrantLock reentrantLock= new ReentrantLock();
    Semaphore waitForBarber = new Semaphore(1);
    Semaphore waitForCustomer = new Semaphore(1);
    public BarberShop(int n) throws InterruptedException {
        maxCustomer = n+1;
        barberSleeping();
    }

    public void enterShop() throws InterruptedException {
        reentrantLock.lock();
        if(count==maxCustomer){
            System.out.println("No place for customer: "+Thread.currentThread().getId());
            reentrantLock.unlock();
            return;
        }
            count++;
        reentrantLock.unlock();
        System.out.println("Waiting customer: "+Thread.currentThread().getId());
        waitForCustomer.release();
        waitForBarber.acquire();
        getHairCut();
        waitForBarber.release();
        reentrantLock.lock();
        count--;
        reentrantLock.unlock();

    }
    private void getHairCut(){
        System.out.println("Getting haircut: "+Thread.currentThread().getId());
        try {
            Thread.sleep(random.nextInt(15000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void barberSleeping() throws InterruptedException {
        waitForCustomer.acquire();
     waitForBarber.release();
    }


}
