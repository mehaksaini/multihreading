package executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;

 class MyThreadPool implements MyExecutorService{

    private  int capacity;
    private  int currentCapacity;
    LinkedBlockingDeque<Runnable>linkedBlockingDeque;

    List<Thread> threads= new ArrayList<>();
    public MyThreadPool(int capacity){
       this.capacity =capacity;
       currentCapacity =0;
       linkedBlockingDeque = new LinkedBlockingDeque<>();
    }
    @Override
    public void submit(Runnable r) {
        linkedBlockingDeque.add(r);
        System.out.println("Added to queue .");
        if(currentCapacity<capacity){
            currentCapacity++;
            Thread thread = new Thread(()->{
               while(true){
                   if(!linkedBlockingDeque.isEmpty()){
                       linkedBlockingDeque.poll().run();
                       System.out.println("Running on thread: "+Thread.currentThread().getId());
                   }
               }

            });
            thread.start();

        }
    }

    @Override
    public void shutDown() throws InterruptedException {
        while (true){
            if(linkedBlockingDeque.isEmpty()){
                System.out.println("Shutting down service.");
                for(Thread thread: threads){
                    thread.join();
                }
                break;
            }
        }
        System.out.println("Shut down service.");
    }
}
