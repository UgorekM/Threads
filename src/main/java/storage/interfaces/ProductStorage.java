package storage.interfaces;

import storage.CellType;
import java.util.List;

/**
 *  Storage of information about products
 **/
public interface ProductStorage {

    List<CellType> getSortedAvailableTypes();

    /**
     * Add product in storage
     * @param type Cell for adding
     * @param info Product Id
     */
    void addProduct(CellType type, int info);

    /**
     * Poll and transferring a product from storage
     * @param type Cell for poll
     * @return Products of cell
     */
    List<Integer> pollProduct(CellType type);
}
