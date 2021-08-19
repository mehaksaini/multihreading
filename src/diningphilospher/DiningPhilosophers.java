package diningphilospher;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    private Semaphore[] forks = new Semaphore[5];
    private Semaphore maxDiners = new Semaphore(4);
    private final Random random = new Random();

    public DiningPhilosophers() {
       forks[0]= new Semaphore(1);
        forks[1]= new Semaphore(1);
        forks[2]= new Semaphore(1);
        forks[3]= new Semaphore(1);
        forks[4]= new Semaphore(1);
    }

    public void lifeOfPhilosopher(int id) {
        while(true){
        contemplate();
        try {
            eat(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
    }

    private void eat(int id) throws InterruptedException {

        maxDiners.acquire();
        forks[id%5].acquire();
        forks[(id+1)%5].acquire();
        int avaialbe = 4- maxDiners.availablePermits();
        System.out.println("Philosophers " + avaialbe + " are eating");
        Thread.sleep(random.nextInt(5000));
        forks[id%5].release();
        forks[(id+1)%5].release();
        maxDiners.release();
    }

    private void contemplate() {
        try {
            Thread.sleep(random.nextInt(50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void startPhilosoper(DiningPhilosophers dp, int id) {

            dp.lifeOfPhilosopher(id);

    }
}
