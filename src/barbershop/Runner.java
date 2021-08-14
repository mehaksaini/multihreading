package barbershop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {

    public static void main(String[]args) throws InterruptedException {

        Random random= new Random();
        BarberShop barberShop= new BarberShop(4);
        List<Thread> threadList= new ArrayList<>();
        for(int i=0;i<34;i++){
            Thread thread= new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(30000));
                    barberShop.enterShop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threadList.add(thread);
        }
        for(Thread thread:threadList){
            thread.start();
        }
    }
}
