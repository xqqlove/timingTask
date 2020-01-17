package atomic_yiwa;
//https://blog.csdn.net/zbw18297786698/article/details/53420780
public class AtomicIntegerTest {
    private static final int THREADS_COUNT=20;
    public static int count=0;
    public static void increase(){
        count++;
    }

}
