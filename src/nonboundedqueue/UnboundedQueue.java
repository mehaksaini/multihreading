package nonboundedqueue;

import java.util.ArrayList;
import java.util.List;

public class UnboundedQueue<T> {

    List<T>content= new ArrayList<>();

    public synchronized void addItem(T item){
        content.add(0,item);
        System.out.println("Added: "+item);
        notifyAll();
    }
    public synchronized T popItem() throws InterruptedException {

        while(content.size()==0){
            wait();
        }
        T item = content.get(content.size()-1);
        content.remove(content.size()-1);
        System.out.println("removed: "+item);
        return item;
    }
}
