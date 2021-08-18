package bathroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {
    public static void main(String[]args){
        Random random = new Random();
        UniSexBathroom uniSexBathroom = new UniSexBathroom();
        List<Thread> all = new ArrayList<>();
        for(int i=0;i<10;i++){
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(8000));
                    uniSexBathroom.enterMan();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            all.add(thread);
            Thread thread1 = new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(8000));
                    uniSexBathroom.enterWoman();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            all.add(thread1);
        }
        for(Thread thread:all){
            thread.start();
        }
    }
}
