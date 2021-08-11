package deferredcallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class caller {
    public static void main(String[]args) throws InterruptedException {
        CallBackApi callBackApi = new CallBackApi();
        Thread thread = new Thread(()->{
            try {
                callBackApi.executorThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Random random= new Random();
        List<Thread> threadList= new ArrayList<>();

        for(int i=0;i<5;i++){
            CallBack callBack= new CallBack();
            callBack.msg="msg: "+i;
            callBack.executeAt = System.currentTimeMillis()+random.nextInt(5678);
            Thread.sleep(100);
           Thread thread1 = new Thread(()->{
               callBackApi.getCallBackApi(callBack);
           });
           threadList.add(thread1);
        }
        for(Thread thread1: threadList){
            thread1.start();
        }
        for(Thread thread1: threadList){
            thread1.join();
        }
    }
}
