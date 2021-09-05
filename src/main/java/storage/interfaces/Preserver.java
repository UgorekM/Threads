package storage.interfaces;

import java.util.List;

/**
 *  Queue preserver and adding product
 **/
public interface Preserver {

    /**
     * Add product in cell of storage
     * @param product Product to add
     */
    void addProduct(int product);

    /**
     * Poll products from cell of storage
     * @return products from cell
     */
    List<Integer> pollProduct();
}
