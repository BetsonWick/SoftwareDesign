import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddProductServletTest extends AbstractServletTest {
    @Test
    void testAddProduct() throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(
                HttpRequest.newBuilder(
                                URI.create(BASE_URI + ADD_PRODUCT + "?name=pixel6&price=200")
                        )
                        .build(),
                bodyHandler
        );
        assertEquals(Response.SC_OK, response.statusCode());
    }
}
