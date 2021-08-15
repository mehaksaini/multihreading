package uber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Runner { public static void main(String[]args){
    UberRide uberRide= new UberRide();
    Random random= new Random();
    List<Thread> all= new ArrayList<>();

    for(int i=0;i<50;i++) {
        Thread thread= new Thread(()->{
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                uberRide.demoRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        all.add(thread);
    }
    for(int i=0;i<50;i++) {
        Thread thread= new Thread(()->{
            try {
                Thread.sleep(random.nextInt(4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                uberRide.republicRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        all.add(thread);
    }
    for(Thread thread: all){
        thread.start();
    }

}


}
