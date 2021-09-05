package threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for thread
 */

public abstract class JobThread extends Thread {
    final static Logger logger = LoggerFactory.getLogger(JobThread.class);

    /**
     * Work period for thread
     */
    int periodInSec;

    /**
     * Create thread
     * @param sec Work period of thread
     */
    public JobThread(int sec){
        periodInSec = sec;
    }

    @Override
    public void run() {
        while (true) {
            try {
                makeAction();
                Thread.sleep(periodInSec * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void makeAction();
}
