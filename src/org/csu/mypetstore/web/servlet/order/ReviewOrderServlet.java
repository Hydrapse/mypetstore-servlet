package org.csu.mypetstore.web.servlet.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Created by Enzo Cotter on 2019/10/13.
 */
@WebServlet("/reviewOrder")
public class ReviewOrderServlet extends HttpServlet {
    private static final String REVIEW_HISTORY_ORDER = "/WEB-INF/jsp/order/ReviewHistoryOrder.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private static final Logger logger = LogManager.getLogger(ReviewOrderServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        OrderService orderService = new OrderServiceImpl();
        Order reviewOrder = orderService.getOrder(orderId);

        if(reviewOrder == null){
            session.setAttribute("message", "Attempted to review non-existent order");
            session.setAttribute("reviewOrder", null);
            req.getRequestDispatcher(ERROR).forward(req, resp);
            logger.error("试图访问不存在订单");
            return;
        }

        logger.info("查看历史订单 " + reviewOrder.getOrderId());
        session.setAttribute("reviewOrder", reviewOrder);
        req.getRequestDispatcher(REVIEW_HISTORY_ORDER).forward(req, resp);
    }
}
