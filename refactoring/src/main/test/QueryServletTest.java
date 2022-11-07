import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryServletTest extends AbstractServletTest {
    @Test
    void testGetMaxPriceProduct() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = queryCommand("max");
        assertEquals(Response.SC_OK, response.statusCode());
        assertEquals(
                readTestResourceFile("QueryServletTestFile.max.txt").stripIndent(),
                response.body().stripIndent()
        );
    }

    @Test
    void testGetMinPriceProduct() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = queryCommand("min");
        assertEquals(Response.SC_OK, response.statusCode());
        assertEquals(
                readTestResourceFile("QueryServletTestFile.min.txt").stripIndent(),
                response.body().stripIndent()
        );
    }

    @Test
    void testGetProductSum() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = queryCommand("sum");
        assertEquals(Response.SC_OK, response.statusCode());
        assertEquals(
                readTestResourceFile("QueryServletTestFile.sum.txt").stripIndent(),
                response.body().stripIndent()
        );
    }

    @Test
    void testGetProductCount() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = queryCommand("count");
        assertEquals(Response.SC_OK, response.statusCode());
        assertEquals(
                readTestResourceFile(
                        "QueryServletTestFile.count.txt").stripIndent(),
                response.body().stripIndent()
        );

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
