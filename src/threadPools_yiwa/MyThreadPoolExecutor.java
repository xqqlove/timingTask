package threadPools_yiwa;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

class Task{
    private int taskId;
    public Task(int taskId){
        this.taskId=taskId;
    }
    public void doJob(){
        System.out.println("线程"+Thread.currentThread().getName()+"正在处理任务！");
    }
    public int getId(){
        return taskId;
    }
}
public class MyThreadPoolExecutor {
    private BlockingDeque<Task> blockingDeque;
    private int poolSize=0;
    private int coreSize=0;
    private volatile boolean shutDown=false;
    public MyThreadPoolExecutor(int size){
        this.poolSize=size;
        blockingDeque=new LinkedBlockingDeque<>(poolSize);
    }
    public void execute(Task task) throws InterruptedException{
        if (shutDown==true)
            return;
        if (coreSize<poolSize){
            blockingDeque.put(task);
            produceWorker(task);
        }else {
            blockingDeque.put(task);
        }
    }
    private void  produceWorker(Task task) throws InterruptedException{
        if (task==null)
            throw  new NullPointerException("非法参数，传入的task对象为空");
        Thread thread=new Thread(new Worker());
        thread.start();
        coreSize++;
    }
    public void shutDown(){
        shutDown=true;
    }
    class Worker implements Runnable{

        @Override
        public void run() {
            while (!shutDown){
                try {
                    blockingDeque.take().doJob();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程："+Thread.currentThread().getName()+"退出运行");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        MyThreadPoolExecutor myThreadPoolExecutor=new MyThreadPoolExecutor(3);
        for (int i=0;i<10;i++){
            Task task=new Task(i);
            myThreadPoolExecutor.execute(task);
        }
        myThreadPoolExecutor.shutDown();
    }


}


