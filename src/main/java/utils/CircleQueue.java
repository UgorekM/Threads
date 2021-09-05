package utils;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

/**
 * Generic for scrolling a queue
 * @param <T> type of queue
 */

public class CircleQueue<T> {
    Queue<T> pendingItems = new ArrayDeque<>();

    public CircleQueue(Collection<T> allItems)
    {
        pendingItems.addAll(allItems);
    }

    /**
     * Poll item of queue and add in begin
     * @return Current item of queue
     */
    public T getAndCircleItem(){
        T currentItem = pendingItems.poll();
        pendingItems.add(currentItem);
        return currentItem;
    }
}
