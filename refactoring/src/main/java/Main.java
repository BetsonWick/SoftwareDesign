import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import dao.ProductDao;
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

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet(productDao)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(productDao)), "/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(productDao)), "/query");

        server.start();
    }
}
