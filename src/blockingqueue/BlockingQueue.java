package blockingqueue;

public class BlockingQueue<T> {
    private T[]arrayData = (T[]) new Object[10];
    int size;
    public Object lock1= new Object();
    public Object lock2= new Object();

    public  T addToQueue(T data) throws InterruptedException {
synchronized (lock1){
        if(this.size==10){
            lock1.wait();
        }
            arrayData[size]=data;
            size++;
        lock1.notifyAll();
        return data;}
    }

    public T removeFromQueue() throws InterruptedException {
        synchronized (lock1){
        if(this.size==0){
            lock1.wait();
        }
            T data = arrayData[size-1];
            arrayData[size-1]=null;
            size--;
        lock1.notifyAll();
        return data;
    }}
}
