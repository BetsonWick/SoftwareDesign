import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static server.ProductsServer.BASE_URI;
import static server.ProductsServer.QUERY_COMMAND;

public class QueryServletTest extends AbstractServletTest {
    @Test
    void testGetMaxPriceProduct() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = queryCommand("max");
        assertEquals(Response.SC_OK, response.statusCode());
        assertTestResourceFile("QueryServletTestFile.max.txt", response.body());
    }

    @Test
    void testGetMinPriceProduct() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = queryCommand("min");
        assertEquals(Response.SC_OK, response.statusCode());
        assertTestResourceFile("QueryServletTestFile.min.txt", response.body());
    }

    @Test
    void testGetProductSum() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = queryCommand("sum");
        assertEquals(Response.SC_OK, response.statusCode());
        assertTestResourceFile("QueryServletTestFile.sum.txt", response.body());
    }

    @Test
    void testGetProductCount() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = queryCommand("count");
        assertEquals(Response.SC_OK, response.statusCode());
        assertTestResourceFile("QueryServletTestFile.count.txt", response.body());

    }

    private HttpResponse<String> queryCommand(String commandName) throws IOException, InterruptedException {
        return httpClient.send(
                HttpRequest.newBuilder(
                                URI.create(BASE_URI + QUERY_COMMAND + "?command=" + commandName)
                        )
                        .build(),
                bodyHandler
        );
    }
}
