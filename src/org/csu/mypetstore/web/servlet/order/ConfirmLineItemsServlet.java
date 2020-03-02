package org.csu.mypetstore.web.servlet.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/11.
 */
@WebServlet("/confirmLineItems")
public class ConfirmLineItemsServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ConfirmLineItemsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        if(order == null){
            resp.sendRedirect("viewOrderForm");
            return;
        }

        OrderService service = new OrderServiceImpl();
        service.insertOrder(order);
        logger.info("订单 " + order.getOrderId() + " 成交成功");

        //结束一笔订单
        session.setAttribute("cart", new Cart());
        session.setAttribute("order", null);

        //查看已成交订单
        String orderId = String.valueOf(order.getOrderId());
        resp.sendRedirect("reviewOrder?orderId=" + orderId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
