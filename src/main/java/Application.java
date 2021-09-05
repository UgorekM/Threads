import storage.MemoryProductStorageImpl;
import storage.OrderedProductPreserver;
import storage.interfaces.Preserver;
import storage.interfaces.ProductStorage;
import threads.AddProductThread;
import threads.PollProductThread;

public class Application {
    public static void main(String[] args) {

        ProductStorage productStorage = new MemoryProductStorageImpl();
        Preserver preserver = new OrderedProductPreserver(productStorage);
        AddProductThread threadAdd = new AddProductThread(preserver,1);
        PollProductThread threadRemove = new PollProductThread(preserver, 20);
        threadAdd.start();
        threadRemove.start();
    }
}
