package barrier;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[]args) {
        Barrier barrier = new Barrier();
        List<Thread>all= new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            all.add(thread);
        }

        for(Thread thread:all){
            thread.start();
        }

    }
}
