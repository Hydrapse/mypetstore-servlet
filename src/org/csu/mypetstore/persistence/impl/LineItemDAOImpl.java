package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2019/10/10.
 */
public class LineItemDAOImpl implements LineItemDAO {
    private static final String GET_LINE_ITEMS_BY_ORDER_ID = "SELECT\n" +
            "        ORDERID,\n" +
            "        LINENUM AS lineNumber,\n" +
            "        ITEMID,\n" +
            "        QUANTITY,\n" +
            "        UNITPRICE\n" +
            "        FROM LINEITEM\n" +
            "        WHERE ORDERID = ?";

    private static final String INSERT_LINE_ITEM = "INSERT " +
            "        INTO LINEITEM " +
            "        (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE)\n" +
            "        VALUES " +
            "        (?, ?, ?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemsList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LINE_ITEMS_BY_ORDER_ID);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LineItem item = new LineItem();
                item.setOrderId(resultSet.getInt(1));
                item.setLineNumber(resultSet.getInt(2));
                item.setItemId(resultSet.getString(3));
                item.setQuantity(resultSet.getInt(4));
                item.setUnitPrice(resultSet.getBigDecimal(5));
                lineItemsList.add(item);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineItemsList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINE_ITEM);

            preparedStatement.setInt(1, lineItem.getOrderId());
            preparedStatement.setInt(2, lineItem.getLineNumber());
            preparedStatement.setString(3, lineItem.getItemId());
            preparedStatement.setInt(4, lineItem.getQuantity());
            preparedStatement.setBigDecimal(5, lineItem.getUnitPrice());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
