package uber;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UberRide {
    int seated=0;
    int r=0;
    int d=0;
    int rs=0;
    int ds=0;
    ReentrantLock lock = new ReentrantLock();
    Condition condition  = lock.newCondition();
    int maxr=0;
    int maxd=0;
    public synchronized void request(String person) throws InterruptedException {
        if(person=="d"){
            condition.await();
            d++;
        }else{
            r++;
        }
        while(r+d<4||r<2||d<2||(person=="d"&&ds==maxd)||(person=="r"&&rs==maxr)){
        wait();
        }
        if(r>=2&&d>=2){
            maxd=2;
            maxr=2;
            if(person=="d"){
              ds++;
            }else{
              rs++;
            }
        }else if(r>=4){
            maxd=0;
            maxr=4;
            if(person=="d"){
                ds++;
            }else{
                rs++;
            }
        }else{
            maxd=4;
            maxr=0;
            if(person=="d"){
                ds++;
            }else{
                rs++;
            }
        }
        seated();
        seated++;

        if(seated==4){
            d-=maxd;
            r-=maxr;
            ds=0;
            rs=0;
            maxr=-1;
            maxd=-1;
            drive();
            seated=0;
            notifyAll();
        }
    }

    public void seated(){}
    public void drive(){}
}
