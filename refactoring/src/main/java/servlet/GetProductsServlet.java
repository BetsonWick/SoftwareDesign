package servlet;

import dao.ProductDao;
import model.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        response.getWriter().println("<html><body>");
        List<Product> products = productDao.getAllProducts();
        for (Product product : products) {
            response.getWriter().println(product + "</br>");
        }
        response.getWriter().println("</body></html>");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}