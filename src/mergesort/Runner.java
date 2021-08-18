package mergesort;

public class Runner {
    public static void main(String[]args) {
        MultithreadedMergesort multithreadedMergesort = new MultithreadedMergesort();
        int[] input = {4, -2, 3, 6, 9, 12, 56, 1, 23, -23, -81, 123};
        try {
            multithreadedMergesort.mergeSort(input,0,input.length-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int a: input){
            System.out.print(a+" ");
        }
    }
}
