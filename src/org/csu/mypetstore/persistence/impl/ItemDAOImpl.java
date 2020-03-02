package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ItemDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2019/10/5.
 */
public class ItemDAOImpl implements ItemDAO {
    private static final String UPDATE_INVENTORY_QUANTITY = "UPDATE\n" +
            "        INVENTORY SET\n" +
            "        QTY = QTY - ?\n" +
            "        WHERE ITEMID = ?";

    private static final String GET_INVENTORY_QUANTITY = "SELECT\n" +
            "        QTY AS value\n" +
            "        FROM INVENTORY\n" +
            "        WHERE ITEMID = ?";

    private static final String GET_ITEM_LIST_BY_PRODUCT = "SELECT\n" +
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
            "        ATTR5 AS attribute5\n" +
            "        FROM ITEM I, PRODUCT P\n" +
            "        WHERE P.PRODUCTID = I.PRODUCTID\n" +
            "        AND I.PRODUCTID = ?";

    private static final String GET_ITEM = "SELECT\n" +
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
            "        QTY AS quantity\n" +
            "        from ITEM I, INVENTORY V, PRODUCT P\n" +
            "        where P.PRODUCTID = I.PRODUCTID\n" +
            "        and I.ITEMID = V.ITEMID\n" +
            "        and I.ITEMID = ? ";

    private static final String GET_ITEM_BY_PRODUCT_NAME = "SELECT\n" +
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
            "        QTY AS quantity\n" +
            "        from ITEM I, INVENTORY V, PRODUCT P\n" +
            "        where P.PRODUCTID = I.PRODUCTID\n" +
            "        and I.ITEMID = V.ITEMID\n" +
            "        and P.NAME = ? ";

    @Override
    //<itemID, qty>
    public void updateInventoryQuantity(Map<String, Object> param) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY);

            //Integer的toString
            preparedStatement.setString(1, param.get("increment").toString());
            //本来就是String类型
            preparedStatement.setString(2, (String)param.get("itemId"));
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int quantity = -1;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY);
            preparedStatement.setString(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                quantity = resultSet.getInt(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT);
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                Product product = new Product();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
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
                itemList.add(item);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = new Item();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
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
                item.setQuantity(resultSet.getInt(15));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item getItemByProductName(String productName) {
        Item item = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_BY_PRODUCT_NAME);
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = new Item();
                Product product = new Product();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
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
                item.setQuantity(resultSet.getInt(15));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
