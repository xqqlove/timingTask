package threadPools_yiwa;

import java.util.concurrent.*;

public class TestExecutorsDemo {

    private static int MAX=10;

    public static void main(String[] args) {
        try {
            fixedThreadPool(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private static void fixedThreadPool(int coreSize) throws InterruptedException, ExecutionException {
        ExecutorService exec= Executors.newFixedThreadPool(coreSize);
        for (int i=0;i<MAX;i++){
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+Thread.currentThread().getName()+": "+fibc(20));
                }
            });
        }
    }

    private static int fibc(int num){
        if (num==0)
            return 0;
        if (num==1)
            return 1;
        return fibc(num-1)+fibc(num-2);
    }
}
