package readwritelock;

import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {

    public static void main(String[] args){
        ReadWrite readWrite= new ReadWrite();
        Random random= new Random();
        List<Thread> all= new ArrayList<>();
        for(int i=0;i<10;i++){
            Thread thread= new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(30));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    readWrite.aquireWriteLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readWrite.write(Thread.currentThread().getName());
                try {
                    readWrite.reLeaseWriteLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            all.add(thread);
        }
        for(int i=0;i<100;i++){
            Thread thread= new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    readWrite.aquireReadLock();
                    System.out.println(readWrite.read());
                    readWrite.reLeaseReadLock();
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
