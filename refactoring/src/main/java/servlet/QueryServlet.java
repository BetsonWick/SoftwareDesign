package servlet;

import dao.ProductDao;
import model.Product;
import utils.HTMLBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wa5teed
 */
public class QueryServlet extends HttpServlet {
    private final ProductDao productDao;

    public QueryServlet(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        switch (command) {
            case "max" -> response.getWriter().println(
                    new HTMLBuilder<Product>()
                            .addHeader("Product with max price: ")
                            .addNewLines(productDao.getHighestPriceProduct())
                            .build()
            );
            case "min" -> response.getWriter().println(
                    new HTMLBuilder<Product>()
                            .addHeader("Product with min price: ")
                            .addNewLines(productDao.getLowestPriceProduct())
                            .build()
            );
            case "sum" -> response.getWriter().println(
                    new HTMLBuilder<Product>()
                            .addNewRawLine("Summary price: ")
                            .addNewRawLine(String.valueOf(productDao.getProductsPriceSum()))
                            .build()
            );
            case "count" -> response.getWriter().println(
                    new HTMLBuilder<Product>()
                            .addNewRawLine("Number of products:")
                            .addNewRawLine(String.valueOf(productDao.getProductsCount()))
                            .build()
            );
            default -> response.getWriter().println("Unknown command: " + command);
        }
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
