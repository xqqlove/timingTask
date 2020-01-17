package threadPools_yiwa;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
            sum+=1;
        }
        return sum;
    }
}

public class TestCallable {
    public static void main(String[] args) {
        Callable<Integer> myCallable=new MyCallable();
        FutureTask<Integer> ft=new FutureTask<Integer>(myCallable);

        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
            if (i==30){
                Thread thread=new Thread(ft);
                thread.start();
            }
        }
        System.out.println("主线程for循环执行完毕");
        try {
            int sum=ft.get();
            System.out.println("SUM= "+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
