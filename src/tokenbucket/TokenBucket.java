package tokenbucket;

import javax.swing.plaf.TableHeaderUI;

public class TokenBucket {
    int size=0;
    int max;
    long lastRequestTimeStamp = System.currentTimeMillis();
    public TokenBucket(int n){
        this.max = n;
    }

    public synchronized int getToken() throws InterruptedException {
        long old = System.currentTimeMillis()-lastRequestTimeStamp;

        size+=(old)/1000;
        size=Math.min(max,size);

        if(size==0){
            Thread.sleep(1000-old);
        }else{
            size--;
        }
        System.out.println("Token given to: "+Thread.currentThread().getId());
        lastRequestTimeStamp=System.currentTimeMillis();
        return size+1;
    }
}
