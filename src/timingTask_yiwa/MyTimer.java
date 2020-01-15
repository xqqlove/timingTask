package timingTask_yiwa;

import java.util.Arrays;
import java.util.TimerTask;

public class MyTimer {
}

class MyTimerThread extends Thread{

}
class MyTaskQueue{
   private TimerTask[] queue=new TimerTask[128];
   private int size=0;
   int size(){return  size;}
   void add(TimerTask task){
       if (size+1==queue.length)
           queue= Arrays.copyOf(queue,2*queue.length);
       queue[++size]=task;

   }

}