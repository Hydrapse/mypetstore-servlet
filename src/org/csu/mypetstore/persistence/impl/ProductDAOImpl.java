package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2019/10/4.
 */
public class ProductDAOImpl implements ProductDAO {
    private static final String GET_PRODUCT_LIST_BY_CATEGORY = "SELECT\n" +
            "        PRODUCTID,\n" +
            "        NAME,\n" +
            "        DESCN as description,\n" +
            "        CATEGORY as categoryId\n" +
            "        FROM PRODUCT\n" +
            "        WHERE CATEGORY = ? ";
    private static final String SEARCH_NAME_PRODUCT_LIST = "SELECT\n" +
            "        PRODUCTID,\n" +
            "        NAME,\n" +
            "        DESCN AS description,\n" +
            "        CATEGORY AS categoryId\n" +
            "        FROM PRODUCT\n" +
            "        WHERE lower(NAME) LIKE ?";
    private static final String SEARCH_CATEGORY_PRODUCT_LIST = "SELECT\n" +
            "        PRODUCTID,\n" +
            "        NAME,\n" +
            "        DESCN AS description,\n" +
            "        CATEGORY AS categoryId\n" +
            "        FROM PRODUCT\n" +
            "        WHERE lower(CATEGORY) LIKE ?";
    private static final String GET_PRODUCT = "SELECT\n" +
            "        PRODUCTID,\n" +
            "        NAME,\n" +
            "        DESCN as description,\n" +
            "        CATEGORY as categoryId\n" +
            "        FROM PRODUCT\n" +
            "        WHERE PRODUCTID = ? ";
    private static final String SEARCH_PRODUCT_NAME_LIST = "SELECT\n" +
            "        NAME\n" +
            "        FROM PRODUCT\n" +
            "        WHERE lower(NAME) LIKE ?";

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            preparedStatement.setString(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SEARCH_NAME_PRODUCT_LIST);
            preparedStatement.setString(1, keywords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> searchCategoryProductList(String keywords) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SEARCH_CATEGORY_PRODUCT_LIST);
            preparedStatement.setString(1, keywords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<String> searchProductNameList(String keywords) throws SQLException {
        List<String> nameList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_NAME_LIST);
        preparedStatement.setString(1, keywords);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            nameList.add(resultSet.getString(1));
        }
        DBUtil.closeResultSet(resultSet);
        DBUtil.closePreparedStatement(preparedStatement);
        DBUtil.closeConnection(connection);

        return  nameList;
    }
}
