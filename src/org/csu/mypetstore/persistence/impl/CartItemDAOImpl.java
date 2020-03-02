package org.csu.mypetstore.persistence.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CartItemDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2019/10/19.
 */
public class CartItemDAOImpl implements CartItemDAO {
    private static final String INSERT_CART_ITEM_LIST = "INSERT\n" +
            "        INTO CARTITEM\n" +
            "        (USERID, ITEMID, QUANTITY, TOTAL)\n" +
            "        VALUES\n";

    private static final String DELETE_CART_ITEM_LIST = "DELETE\n" +
            "        FROM CARTITEM\n" +
            "        WHERE USERID = ?";

    private static final String GET_CART_ITEM_LIST = "SELECT\n" +
            "        I.ITEMID,\n" +
            "        LISTPRICE,\n" +
            "        UNITCOST,\n" +
            "        SUPPLIER AS supplierId,\n" +
            "        I.PRODUCTID AS \"product.productId\",\n" +
            "        NAME AS \"product.name\",\n" +
            "        DESCN AS \"product.description\",\n" +
            "        CATEGORY AS \"product.categoryId\",\n" +
            "        STATUS,\n" +
            "        ATTR1 AS attribute1,\n" +
            "        ATTR2 AS attribute2,\n" +
            "        ATTR3 AS attribute3,\n" +
            "        ATTR4 AS attribute4,\n" +
            "        ATTR5 AS attribute5,\n" +
            "        QTY,\n" +
            "        QUANTITY,\n" +
            "        TOTAL\n" +
            "        FROM ITEM I, INVENTORY V, PRODUCT P, CARTITEM C\n" +
            "        WHERE P.PRODUCTID = I.PRODUCTID\n" +
            "        AND I.ITEMID = V.ITEMID\n" +
            "        AND I.ITEMID = C.ITEMID\n" +
            "        AND C.USERID = ? ";

    private static final Logger logger = LogManager.getLogger(CartItemDAOImpl.class);

    @Override
    public void insertCartItemList(List<CartItem> cartItemList, Account account) throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder(INSERT_CART_ITEM_LIST);
        String userName = account.getUsername();
        for(CartItem cartItem : cartItemList){
            sqlBuilder.append("('").append(userName).append("','").append(cartItem.getItem().getItemId()).append("',")
                     .append(cartItem.getQuantity()).append(",").append(cartItem.getTotal()).append("),");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length()-1);

        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());

        int test = preparedStatement.executeUpdate();
        if (test != cartItemList.size()){
            logger.error("用户 " + account.getUsername() + " 购物车数据插入出现异常" + "sql语句: " + sqlBuilder.toString());
        }

        DBUtil.closePreparedStatement(preparedStatement);
        DBUtil.closeConnection(connection);
    }

    @Override
    public void deleteCartItemList(Account account) throws SQLException {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_ITEM_LIST);
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.executeUpdate();

        DBUtil.closePreparedStatement(preparedStatement);
        DBUtil.closeConnection(connection);
    }

    @Override
    public List<CartItem> getCartItemList(Account account) throws SQLException {
        List<CartItem>  cartItemList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_ITEM_LIST);
        preparedStatement.setString(1, account.getUsername());
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            CartItem cartItem = new CartItem();
            Item item = new Item();
            Product product = new Product();

            item.setItemId(resultSet.getString(1));
            item.setListPrice(resultSet.getBigDecimal(2));
            item.setUnitCost(resultSet.getBigDecimal(3));
            item.setSupplierId(resultSet.getInt(4));
            item.setProductId(resultSet.getString(5));
            product.setProductId(resultSet.getString(5));
            product.setName(resultSet.getString(6));
            product.setDescription(resultSet.getString(7));
            product.setCategoryId(resultSet.getString(8));
            item.setProduct(product);
            item.setStatus(resultSet.getString(9));
            item.setAttribute1(resultSet.getString(10));
            item.setAttribute2(resultSet.getString(11));
            item.setAttribute3(resultSet.getString(12));
            item.setAttribute4(resultSet.getString(13));
            item.setAttribute5(resultSet.getString(14));
            int qty = resultSet.getInt(15);
            item.setQuantity(qty);

            cartItem.setInStock(qty > 0 ? true : false);
            cartItem.setItem(item);
            cartItem.setQuantity(resultSet.getInt(16));
            cartItem.setTotal(resultSet.getBigDecimal(17));
            cartItemList.add(cartItem);
        }

        DBUtil.closeResultSet(resultSet);
        DBUtil.closePreparedStatement(preparedStatement);
        DBUtil.closeConnection(connection);
        return cartItemList;
    }
}
