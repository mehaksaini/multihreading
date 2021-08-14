package diningphilospher;

import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[]args){
        DiningPhilosophers diningPhilosophers= new DiningPhilosophers();


        Thread thread= new Thread(()->{
            DiningPhilosophers.startPhilosoper(diningPhilosophers,0);
        });
        Thread thread1= new Thread(()->{
            DiningPhilosophers.startPhilosoper(diningPhilosophers,1);
        });
        Thread thread2= new Thread(()->{
            DiningPhilosophers.startPhilosoper(diningPhilosophers,2);
        });
        Thread thread3= new Thread(()->{
            DiningPhilosophers.startPhilosoper(diningPhilosophers,3);
        });
        Thread thread4= new Thread(()->{
            DiningPhilosophers.startPhilosoper(diningPhilosophers,4);
        });
       // thread.run();
        thread1.start();
        thread3.start();
        thread2.start();
        thread4.start();
    }
}
