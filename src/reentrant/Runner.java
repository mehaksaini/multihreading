package reentrant;

import javax.swing.plaf.TableHeaderUI;

public class Runner {
    public static void main(String[]args) throws InterruptedException {
        ReentrantTester reentrantTester= new ReentrantTester();
        Thread first= new Thread(()->{
            try {
                reentrantTester.first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread second= new Thread(()->{
            try {
                reentrantTester.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        first.start();
        Thread.sleep(1000);
        second.start();
        // statment afer await only happens after lock is release
    }
}
