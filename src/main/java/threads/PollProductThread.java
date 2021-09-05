package threads;

import storage.interfaces.Preserver;

/**
 * Thread for poll product of storage
 */

public class PollProductThread extends JobThread {

    Preserver preserver;

    /**
     * Create thread for add product
     * @param preserver Preserver for control queue
     * @param sec Work period of thread
     */
    public PollProductThread(Preserver preserver, int sec){
        super(sec);
        this.preserver = preserver;
    }

    /**
     * Poll product from storage
     */
    @Override
    protected void makeAction() {
        preserver.pollProduct();
    }
}