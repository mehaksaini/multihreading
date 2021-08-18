package blockingqueue;

public class BlockingQueue<T> {
    private T[]arrayData = (T[]) new Object[10];
    int head=0;
    int tail=0;
    int size;

    public synchronized void addToQueue(T data) throws InterruptedException {
        while(this.size==10){
           wait();
        }
            arrayData[tail++]=data;
        if(tail==10){
            tail=0;
        }
            size++;
        System.out.println("added: "+data);
       notifyAll();
        }

    public synchronized T removeFromQueue() throws InterruptedException {

        while(this.size==0){
           wait();
        }
            T data = arrayData[head++];
        if(head==10){
            head=0;
        }
            size--;
       notifyAll();
        System.out.println("removed: "+data);
        return data;
    }
}
