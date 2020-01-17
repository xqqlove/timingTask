package atomic_yiwa;
//https://blog.csdn.net/zbw18297786698/article/details/53420780
public class AtomicIntegerTest {
    private static final int THREADS_COUNT=20;
    public static int count=0;
    public static void increase(){
        count++;
    }

    public static void main(String[] args) {
        Thread[] threads=new Thread[THREADS_COUNT];
        for (int i=0;i<THREADS_COUNT;i++){
            threads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<1000;i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(count);
    }

}
