package server;

import dao.ProductDao;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.AddProductServlet;
import servlet.GetProductsServlet;
import servlet.QueryServlet;

public class ProductsServer extends Server{

    private static final int PORT = 8081;
    public static final String BASE_URI = "http://localhost:" + PORT;
    public static final String GET_PRODUCTS = "/get-products";
    public static final String ADD_PRODUCT = "/add-product";
    public static final String QUERY_COMMAND = "/query";

    public ProductsServer(ProductDao productDao) {
        super(PORT);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet(productDao)), ADD_PRODUCT);
        context.addServlet(new ServletHolder(new GetProductsServlet(productDao)), GET_PRODUCTS);
        context.addServlet(new ServletHolder(new QueryServlet(productDao)), QUERY_COMMAND);
    }
}
