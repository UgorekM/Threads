package threads;

import storage.interfaces.Preserver;

/**
 * Thread for add product of storage
 */

public class AddProductThread extends JobThread {

    Preserver preserver;
    int currentId = 1;

    /**
     * Create thread for add product
     * @param preserver Preserver for control queue
     * @param sec Work period of thread
     */
    public AddProductThread(Preserver preserver, int sec){
        super(sec);
        this.preserver = preserver;
    }

    /**
     * Add product in storage with new Id
     */
    @Override
    protected void makeAction() {
        preserver.addProduct(currentId);
        currentId = safeGenerateNewId(currentId);
    }

    /**
     * Method for safely generation Integer Id
     * @param lastId past Integer Id of product
     * @return new Id for product
     */
    private int safeGenerateNewId(int lastId){
        int newId = 0;

        if (lastId  == Integer.MAX_VALUE - 1)
           return newId;

        newId = lastId+1;
        return newId;
    }
}
