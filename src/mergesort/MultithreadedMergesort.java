package mergesort;

public class MultithreadedMergesort {

    public void mergeSort(int []array, int start, int end) throws InterruptedException {
        if(start>=end){
            return;
        }
        int mid=(start+end)/2;
        Thread thread= new Thread(()->{
            try {
                mergeSort(array,start,mid);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread1= new Thread(()->{
            try {
                mergeSort(array,mid+1,end);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        int i=start;
        int j=mid+1;
        int []temp = array.clone();

        int k=start;
        while(k<=end){
            int a= i<=mid?temp[i]:Integer.MAX_VALUE;
            int b= j<=end?temp[j]:Integer.MAX_VALUE;
            if(a<b){
                array[k++]=a;
                i++;
            }else{
                array[k++]=b;
                j++;
            }
        }
    }
}
