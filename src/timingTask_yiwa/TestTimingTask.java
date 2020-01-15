package timingTask_yiwa;


import java.util.Timer;
import java.util.TimerTask;

class MyTask extends TimerTask{

    @Override
    public void run() {
        System.out.println("hello world");
    }
}

public class TestTimingTask {
    public static void main(String[] args) {
        //创建定时器对象
        Timer t=new Timer();
        //在3秒后执行MyTask类中的run方法,后面每10秒跑一次
        t.schedule(new MyTask(),3000,10000);
    }
}
