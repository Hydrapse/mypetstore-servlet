package org.csu.mypetstore.web.servlet.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
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
import java.util.List;

/**
 * Created by Enzo Cotter on 2019/10/13.
 */
@WebServlet("/checkOrderList")
public class CheckOrderListServlet extends HttpServlet {
    private static final String VIEW_LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";
    private static final String CHECK_ORDER_LIST = "/WEB-INF/jsp/order/ListOrders.jsp";

    private static final Logger logger = LogManager.getLogger(CheckOrderListServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        HttpSession session = req.getSession();

        Account account = (Account) session.getAttribute("account");
        if(account == null){
            session.setAttribute("dispatcher", "checkOrderList");
            logger.warn("用户session已过期 请重新登录");
            req.getRequestDispatcher(VIEW_LOGIN_FORM).forward(req, resp);
            return;
        }

        List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
        if(orderList.isEmpty()){
            session.setAttribute("orderList", null);
        }else{
            session.setAttribute("orderList", orderList);
        }

        logger.info("查看历史订单列表");
        req.getRequestDispatcher(CHECK_ORDER_LIST).forward(req, resp);
    }
}
