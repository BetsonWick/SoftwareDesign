import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import dao.ProductDao;
import server.ProductsServer;
import servlet.AddProductServlet;
import servlet.GetProductsServlet;
import servlet.QueryServlet;

/**
 * @author wa5teed
 */
public class Main {
    private static final String DATABASE = "test.db";
    static ProductDao productDao = new ProductDao(DATABASE);

    public static void main(String[] args) throws Exception {
        productDao.createProductsTable();
        Server server = new ProductsServer(productDao);
        server.start();
        server.join();
    }
}
