package org.csu.mypetstore.web.servlet.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
/**
 * Created by Enzo Cotter on 2019/10/11.
 */
@WebServlet("/viewOrderForm")
public class ViewOrderFormServlet extends HttpServlet {
    private static final String VIEW_ORDER_FORM = "/WEB-INF/jsp/order/AccountInfo.jsp";
    private static final String VIEW_LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";

    private static final Logger logger = LogManager.getLogger(ViewOrderFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String confirm = req.getParameter("confirm");

        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order");
        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart)session.getAttribute("cart");

        if(account == null){
            session.setAttribute("dispatcher", "checkout");
            req.getRequestDispatcher(VIEW_LOGIN_FORM).forward(req, resp);
        }
        else if(cart == null || cart.isEmpty()){
            resp.sendRedirect("viewCart");
        }
        else {
            //重新创建订单
            if(confirm == null || confirm.isEmpty() || confirm.equals("default") || order == null){
                order = new Order();
                order.initOrder(account, cart);
                order.setOrderDate(new Date());
                session.setAttribute("order", order);
                logger.info("创建新订单");
            }else{
                logger.info("继续未完成订单");
            }

            //如果选择继续先前的操作，则什么也不变
            req.getRequestDispatcher(VIEW_ORDER_FORM).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
