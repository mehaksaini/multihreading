package deferredcallback;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CallBackApi {
    PriorityQueue<CallBack>callBackPriorityQueue= new PriorityQueue<>(Comparator.comparing(o -> o.executeAt));
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Condition added = lock.newCondition();
    public static void main(String[]args) throws InterruptedException {

        CallBackApi callBackApi = new CallBackApi();
        callBackApi.executorThread();
        for(int i=0;i<5;i++){
            CallBack callBack = new CallBack();
            callBack.msg = "msg: "+i;
            callBack.executeAt = System.currentTimeMillis()+new Random().nextInt(2000);
            callBackApi.getCallBackApi(callBack);
        }

    }

    public void getCallBackApi(CallBack callBack){
       lock.lock();
       callBackPriorityQueue.add(callBack);
       condition.signalAll();
       lock.unlock();
        System.out.println("Added: "+callBack.msg);
    }
    public void executorThread() throws InterruptedException {
        while (true){

            lock.lock();
            while(callBackPriorityQueue.size()==0){
                condition.await();
            }
            while (callBackPriorityQueue.size()!=0){
                CallBack callBack = callBackPriorityQueue.peek();
                long sleepFor = callBack.executeAt - System.currentTimeMillis();
                if(sleepFor<=0){
                    break;
                }

                condition.await(sleepFor, TimeUnit.MILLISECONDS);

            }
            CallBack callBack = callBackPriorityQueue.poll();
            System.out.println("msg: "+callBack.msg);
            lock.unlock();
        }

    }



}
