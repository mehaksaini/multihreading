package executorservice;

public class Runner {
    public static void main(String[] args){
        MyThreadPool myThreadPool = new MyThreadPool(5);
        for(int i=0;i<100;i++){
            myThreadPool.submit(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            myThreadPool.shutDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
