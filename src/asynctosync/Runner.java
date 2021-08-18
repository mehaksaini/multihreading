package asynctosync;

public class Runner {
    public static void main(String[]args) throws InterruptedException {
    SyncExecutor syncExecutor = new SyncExecutor();
    CallBack callBack = new CallBack() {
        @Override
        public void done() {
            System.out.println("We are done");
        }
    };
    syncExecutor.execute(callBack);
        System.out.println("Exiting main");
    }

}
