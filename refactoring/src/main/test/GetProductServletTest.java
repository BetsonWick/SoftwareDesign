import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static server.ProductsServer.BASE_URI;
import static server.ProductsServer.GET_PRODUCTS;

public class GetProductServletTest extends AbstractServletTest {
    @Test
    void testGetAllProducts() throws IOException, InterruptedException, URISyntaxException {
        productDao.createProductsTable();
        productDao.insertProduct("Test", 10);
        HttpResponse<String> response = httpClient.send(
                HttpRequest.newBuilder(
                                URI.create(BASE_URI + GET_PRODUCTS)
                        )
                        .build(),
                bodyHandler
        );
        assertEquals(Response.SC_OK, response.statusCode());
        assertTestResourceFile("GetProductServletTestFile.txt", response.body());
    }
}
