import dao.ProductDao;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import servlet.AddProductServlet;
import servlet.GetProductsServlet;
import servlet.QueryServlet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AbstractServletTest {
    protected HttpClient httpClient = HttpClient.newHttpClient();
    protected HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
    protected static Server server;
    protected static ProductDao productDao;
    protected static Path testDatabaseFile;
    protected static final String BASE_URI = "http://localhost:8081";
    protected static final String GET_PRODUCTS = "/get-products";
    protected static final String ADD_PRODUCT = "/add-product";
    protected static final String QUERY_COMMAND = "/query";

    @BeforeAll
    static void init() throws Exception {
        server = new Server(8081);
        testDatabaseFile = Files.createTempFile(null, "db");
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        productDao = new ProductDao(testDatabaseFile.toString());
        context.addServlet(new ServletHolder(new AddProductServlet(productDao)), ADD_PRODUCT);
        context.addServlet(new ServletHolder(new GetProductsServlet(productDao)), GET_PRODUCTS);
        context.addServlet(new ServletHolder(new QueryServlet(productDao)), QUERY_COMMAND);

        productDao.createProductsTable();

        productDao.insertProduct("First", 10);
        productDao.insertProduct("Second", 30);
        productDao.insertProduct("Third", 20);

        server.start();

    }

    @AfterAll
    static void cleanUp() throws Exception {
        server.stop();
        Files.delete(testDatabaseFile);
    }

    protected String readTestResourceFile(String fileName) throws URISyntaxException, IOException {
        return Files.readString(Paths.get(Objects.requireNonNull(getClass().getResource("/results")).toURI())
                .resolve(fileName));
    }
}
