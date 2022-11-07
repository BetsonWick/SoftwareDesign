import dao.ProductDao;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import server.ProductsServer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractServletTest {
    protected HttpClient httpClient = HttpClient.newHttpClient();
    protected HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
    protected static Server productsServer;
    protected static ProductDao productDao;
    protected static Path testDatabaseFile;

    @BeforeAll
    static void init() throws Exception {
        testDatabaseFile = Files.createTempFile(null, "db");

        productDao = new ProductDao(testDatabaseFile.toString());
        productDao.createProductsTable();
        productsServer = new ProductsServer(productDao);

        productDao.insertProduct("First", 10);
        productDao.insertProduct("Second", 30);
        productDao.insertProduct("Third", 20);

        productsServer.start();
    }

    @AfterAll
    static void cleanUp() throws Exception {
        productsServer.stop();
        Files.delete(testDatabaseFile);
    }

    protected void assertTestResourceFile(String fileName, String actual) throws URISyntaxException, IOException {
        assertEquals(
                Files.readString(
                        Paths.get(Objects.requireNonNull(getClass().getResource("/results")).toURI())
                                .resolve(fileName)
                ).stripIndent(),
                actual.stripIndent()
        );
    }
}
