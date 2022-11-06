package ru.akirakozov.sd.refactoring.dao;

import ru.akirakozov.sd.refactoring.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProductDao {
    private static final String SELECT_ALL_PRODUCTS = """
            SELECT * FROM PRODUCT
            """;

    private static final String SELECT_HIGHEST_PRICE_PRODUCT = SELECT_ALL_PRODUCTS +
            "\nORDER BY PRICE DESC LIMIT 1";


    private static final String SELECT_LOWEST_PRICE_PRODUCT = SELECT_ALL_PRODUCTS +
            "\nORDER BY PRICE LIMIT 1";

    private static final String SELECT_PRODUCTS_COUNT = """
            SELECT COUNT(*) FROM PRODUCT
            """;

    private static final String SELECT_PRODUCTS_PRICE_SUM = """
            SELECT SUM(price) FROM PRODUCT
            """;

    private static final String INSERT_PRODUCT = """
            INSERT INTO PRODUCT (NAME, PRICE) VALUES ("%s", %d)
            """;

    private static final String CREATE_PRODUCTS_TABLE = """
            CREATE TABLE IF NOT EXISTS PRODUCT
            (
                ID              INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                NAME            TEXT    NOT NULL,
                PRICE           INT     NOT NULL
            )
                    """;

    public List<Product> getHighestPriceProduct() {
        return selectProducts(SELECT_HIGHEST_PRICE_PRODUCT, this::mapProduct);
    }

    public List<Product> getLowestPriceProduct() {
        return selectProducts(SELECT_LOWEST_PRICE_PRODUCT, this::mapProduct);
    }

    public List<Product> getAllProducts() {
        return selectProducts(SELECT_ALL_PRODUCTS, this::mapProduct);
    }

    public int getProductsCount() {
        return selectIntColumnAggregated(SELECT_PRODUCTS_COUNT);
    }

    public int getProductsPriceSum() {
        return selectIntColumnAggregated(SELECT_PRODUCTS_PRICE_SUM);
    }

    public void insertProduct(String name, long price) {
        updateProducts(INSERT_PRODUCT.formatted(name, price));
    }

    public void createProductsTable() {
        updateProducts(CREATE_PRODUCTS_TABLE);
    }

    private void updateProducts(String query) {
        try {
            try (
                    Connection connection = DriverManager
                            .getConnection("jdbc:sqlite:test.db");
                    Statement st = connection.createStatement()
            ) {
                st.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> List<T> selectProducts(String query, Function<ResultSet, T> mapper) {
        List<T> result = new ArrayList<>();
        try {
            try (
                    Connection connection = DriverManager
                            .getConnection("jdbc:sqlite:test.db");
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(query)
            ) {
                while (rs.next()) {
                    result.add(mapper.apply(rs));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private int selectIntColumnAggregated(String query) {
        return selectProducts(query, rs -> {
            try {
                return rs.getInt(1);
            } catch (SQLException e) {
                return 0;
            }
        }).get(0);
    }

    private Product mapProduct(ResultSet rs) {
        try {
            return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price")
            );
        } catch (SQLException e) {
            return null;
        }
    }
}
