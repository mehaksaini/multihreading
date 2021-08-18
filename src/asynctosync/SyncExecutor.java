package asynctosync;

public class SyncExecutor extends AsyncExecutor{

    public void execute(CallBack callBack) throws InterruptedException {
        Object object = new Object();
        CallBack cb = () -> {
            callBack.done();
            synchronized (object){
                object.notifyAll();
            }
        };
        super.execute(cb);
        synchronized (object){
            object.wait();
        }

    }
}
