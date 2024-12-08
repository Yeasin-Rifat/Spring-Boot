package com.rifat.exam.service;

import com.rifat.exam.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    // Database configuration
    private final String jdbcUrl = "jdbc:postgresql://localhost:5432/product_management";
    private final String username = "postgres";
    private final String password = "12345";

    @Override
    public Product save(Product product) {
        Product returnProduct = null;
        Connection connection = null;
        try {
            connection = getConnection();
            String sqlStatement = "INSERT INTO product_info(id, name, price, mfg_date, remarks) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName().toUpperCase()); // Ensure name is uppercase as per your requirement
            statement.setDouble(3, product.getPrice());
            statement.setDate(4, new Date(product.getMfgDate().getTime())); // Converts java.util.Date to java.sql.Date
            statement.setString(5, product.getRemarks());
            int res = statement.executeUpdate();
            System.out.println("Inserted rows: " + res);
            if (res > 0) {
                returnProduct = product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return returnProduct;
    }

    @Override
    public Product update(Product product) {
        Product returnProduct = null;
        Connection connection = null;
        try {
            connection = getConnection();
            String sqlStatement = "UPDATE product_info SET name = ?, price = ?, mfg_date = ?, remarks = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlStatement);

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setDate(3, new Date(product.getMfgDate().getTime())); // Converts java.util.Date to java.sql.Date
            statement.setString(4, product.getRemarks());
            statement.setInt(5, product.getId());

            int res = statement.executeUpdate();
            if (res > 0) {
                returnProduct = product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return returnProduct;
    }
    @Override
    public Product delete(Integer id) {
        Product returnProduct = null;
        Connection connection = null;
        try {
            connection = getConnection();
            String sqlStatement = "DELETE FROM product_info WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            int res = statement.executeUpdate();
            System.out.println("Deleted rows: " + res);
            if (res > 0) {
                returnProduct = new Product();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return returnProduct;
    }

    @Override
    public Product getById(Integer id) {
        Product returnProduct = null;
        Connection connection = null;
        try {
            connection = getConnection();
            String sqlStatement = "SELECT * FROM product_info WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                returnProduct = new Product();
                returnProduct.setId(res.getInt("id"));
                returnProduct.setName(res.getString("name"));
                returnProduct.setPrice(res.getDouble("price"));
                returnProduct.setMfgDate(res.getDate("mfg_date")); // java.sql.Date to java.util.Date
                returnProduct.setRemarks(res.getString("remarks"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return returnProduct;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sqlStatement = "SELECT * FROM product_info";
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Product product = new Product();
                product.setId(res.getInt("id"));
                product.setName(res.getString("name"));
                product.setPrice(res.getDouble("price"));
                product.setMfgDate(res.getDate("mfg_date")); // java.sql.Date to java.util.Date
                product.setRemarks(res.getString("remarks"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return productList;
    }

    // Establish a connection to the database
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    // Close the connection safely
    private void closeConnection(Connection connection) {
        try {
            if (!ObjectUtils.isEmpty(connection)) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
