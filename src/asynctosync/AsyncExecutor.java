package asynctosync;

public class AsyncExecutor {
    public void execute(CallBack callBack) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callBack.done();
        });
       thread.start();
    }
}
