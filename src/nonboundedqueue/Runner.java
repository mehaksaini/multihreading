package nonboundedqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {
    public static void main(String[]args){
        UnboundedQueue<Integer>integerUnboundedQueue= new UnboundedQueue<>();
        Random random= new Random();
        List<Thread>threadList = new ArrayList<>();
        for(int i=0;i<25;i++){
            int finalI = i;
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integerUnboundedQueue.addItem(finalI);
            });
            threadList.add(thread);
        }
        for(int i=0;i<15;i++){
            Thread thread = new Thread(()->{
                try {
                    integerUnboundedQueue.popItem();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threadList.add(thread);
        }
        for(Thread thread: threadList){
            thread.start();
        }
    }
}
