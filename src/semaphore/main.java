package semaphore;

import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(10);
        List<Thread> threadList = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<100;i++){
            Thread thread = new Thread(()->{

                try {
                    semaphore.getPermit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Acquired permit: "+semaphore.current);
                try {
                    Thread.sleep(random.nextInt(15000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    semaphore.releasePermit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Released permit: "+semaphore.current);

            });
            threadList.add(thread);
        }
        for(Thread thread: threadList){
            thread.start();
        }
    }
}
