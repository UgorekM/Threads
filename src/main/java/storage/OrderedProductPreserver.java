package storage;

import storage.interfaces.Preserver;
import storage.interfaces.ProductStorage;
import utils.CircleQueue;
import java.util.List;

public class OrderedProductPreserver implements Preserver {

    ProductStorage productStorage;
    CircleQueue<CellType> pollCellsQueue;
    CircleQueue<CellType> addCellsQueue;

    /**
     * Create preserver and all queues
     * @param productStorage Storage of product
     */
    public  OrderedProductPreserver(ProductStorage productStorage) {
        this.productStorage = productStorage;
        pollCellsQueue = new CircleQueue<>(productStorage.getSortedAvailableTypes());
        addCellsQueue = new CircleQueue<>(productStorage.getSortedAvailableTypes());
    }

    @Override
    public void addProduct(int product) {
        productStorage.addProduct(addCellsQueue.getAndCircleItem(), product);
    }

    @Override
    public List<Integer> pollProduct() {
        return productStorage.pollProduct(pollCellsQueue.getAndCircleItem());
    }
}
