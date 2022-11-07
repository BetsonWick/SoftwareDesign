package servlet;

import dao.ProductDao;
import model.Product;
import utils.HTMLBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wa5teed
 */
public class GetProductsServlet extends HttpServlet {
    private final ProductDao productDao;

    public GetProductsServlet(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> products = productDao.getAllProducts();
        response.getWriter().println(new HTMLBuilder<Product>().addNewLines(products).build());
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
