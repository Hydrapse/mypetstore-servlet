package org.csu.mypetstore.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/8.
 */
public class RemoveItemFromCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private static final Logger logger = LogManager.getLogger(RemoveItemFromCartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workingItemId = req.getParameter("workingItemId");
        HttpSession session = req.getSession();

        if(workingItemId.equals("ClearAll")){
            session.setAttribute("cart", null);
            resp.sendRedirect("viewCart");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        Item item = cart.removeItemById(workingItemId);
        if (item == null) {
            session.setAttribute("message", "Attempted to remove null CartItem from Cart");
            logger.warn("试图将空购物车项移出购物车");
//            req.getRequestDispatcher(ERROR).forward(req, resp);
        } else {
            logger.info("将产品 " + workingItemId + " 移出购物车");
//            req.getRequestDispatcher(VIEW_CART).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
