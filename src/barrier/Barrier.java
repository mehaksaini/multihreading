package barrier;

public class Barrier {
    int count=0;
    int free=0;

    public Barrier(){
    }
    public synchronized void await() throws InterruptedException {
        while(count==5){
            wait();
        }
        count++;
        while(count<5){
        wait();
        }
       if(free==0){
           notifyAll();
       }
        free++;
        if(free==5){
            count=0;
            free=0;
            Thread.sleep(1500);
            notifyAll();
        }

        System.out.println("Thread: "+Thread.currentThread().getId());
    }
}
