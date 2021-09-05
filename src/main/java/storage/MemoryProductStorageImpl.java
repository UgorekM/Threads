package storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.interfaces.ProductStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;

public class MemoryProductStorageImpl implements ProductStorage {

    private static final int MaxProductsInCell = 1_000_000;

    final static Logger logger = LoggerFactory.getLogger(MemoryProductStorageImpl.class);

    Map<CellType, ConcurrentSkipListSet<Integer>> storage = new HashMap<>();
    List<CellType> cells = new ArrayList<>();

    public MemoryProductStorageImpl() {
        initialize();
    }

    @Override
    public List<CellType> getSortedAvailableTypes() {
        return cells;
    }

    @Override
    public void addProduct(CellType type, int info) {

        ConcurrentSkipListSet<Integer> list = getSafelyProducts(type);
        if(list.size() == MaxProductsInCell)
        {
            logger.warn("Cell " + type + " reach critical size");
        }
        list.add(info);
    }

    @Override
    public List<Integer> pollProduct(CellType type) {
        ConcurrentSkipListSet<Integer> list = getSafelyProducts(type);

        if(list == null)
            return  null;

        List<Integer> products = new ArrayList<>(list);
        list.clear();
        return products;
    }

    /**
     * Cell and collection validation
     * @param cellType Cell for validation
     * @return Collection from cell
     */
    private ConcurrentSkipListSet<Integer> getSafelyProducts(CellType cellType){
        if(!storage.containsKey(cellType))
        {
            logger.warn("Not found cell " + cellType.toString());
            return null;
        }
        ConcurrentSkipListSet<Integer> list = storage.get(cellType);
        if(list == null) {
            logger.error("Collection for type:" + cellType.toString() + "is not initialized");
            throw new NullPointerException();
        }
        return list;
    }

    /**
     * Filling cells and adding them to storage
     */
    private void initialize(){
        cells.add(CellType.A);
        cells.add(CellType.B);
        cells.add(CellType.C);
        for (var cellType :getSortedAvailableTypes()) {
            storage.put(cellType, new ConcurrentSkipListSet<>());
        }
    }
}
