package tokenbucket;

public class main {
    public static void main(String []args) throws InterruptedException {
        TokenBucket tokenBucket = new TokenBucket(10);

Thread.sleep(1200);
        for(int i=0;i<50;i++){
            Thread newThread = new Thread(()->{
                try {
                    System.out.println(tokenBucket.getToken());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            newThread.start();
        }

    }
}
