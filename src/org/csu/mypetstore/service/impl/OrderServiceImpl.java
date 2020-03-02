package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.ItemDAO;
import org.csu.mypetstore.persistence.LineItemDAO;
import org.csu.mypetstore.persistence.OrderDAO;
import org.csu.mypetstore.persistence.SequenceDAO;
import org.csu.mypetstore.persistence.impl.ItemDAOImpl;
import org.csu.mypetstore.persistence.impl.LineItemDAOImpl;
import org.csu.mypetstore.persistence.impl.OrderDAOImpl;
import org.csu.mypetstore.persistence.impl.SequenceDAOImpl;
import org.csu.mypetstore.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2019/10/11.
 */
public class OrderServiceImpl implements OrderService {
    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private SequenceDAO sequenceDAO;
    private LineItemDAO lineItemDAO;

    public OrderServiceImpl(){
        itemDAO = new ItemDAOImpl();
        orderDAO = new OrderDAOImpl();
        lineItemDAO = new LineItemDAOImpl();
        sequenceDAO = new SequenceDAOImpl();
    }

    //向数据库插入订单
    @Override
    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            Map<String, Object> param = new HashMap<>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            itemDAO.updateInventoryQuantity(param);
        }

        orderDAO.insertOrder(order);
        orderDAO.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemDAO.insertLineItem(lineItem);
        }
    }

    //查看历史订单具体信息
    @Override
    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrder(orderId);
        if(order == null){
            return order;
        }
        order.setLineItems(lineItemDAO.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = order.getLineItems().get(i);
            Item item = itemDAO.getItem(lineItem.getItemId());
            //item中的quantity代表库存量
            item.setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        return orderDAO.getOrdersByUsername(username);
    }

    @Override
    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = sequenceDAO.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        if(sequenceDAO.updateSequence(parameterObject)){
            return parameterObject.getNextId();
        }else {
            throw new RuntimeException("Can't updateSequence!");
        }
    }
}
