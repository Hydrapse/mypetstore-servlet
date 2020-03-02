package org.csu.mypetstore.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/8.
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = "/updateCart")
public class UpdateCartServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private static final Logger logger = LogManager.getLogger(UpdateCartServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String workingItemId = req.getParameter("workingItemId");
        String quantity = req.getParameter("itemQuantity");

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
            resp.sendRedirect(MAIN);
            return;
        }
        CartItem cartItem = cart.getCartItemByItemId(workingItemId);

        if (workingItemId == null || workingItemId.isEmpty() || quantity == null || quantity.isEmpty() || cartItem == null) {
            session.setAttribute("message", "Attempted to update invalid cart items");
            req.getRequestDispatcher(ERROR).forward(req, resp);
            logger.error("试图更新不存在购物车项数据");
        }
        else {
            cart.setQuantityByItemId(workingItemId, Integer.parseInt(quantity));

            resp.getWriter().append(workingItemId).append("?").append(String.valueOf(cartItem.getTotal())).append("?").append(String.valueOf(cart.getSubTotal()));

            logger.debug("<Msg>" + workingItemId + "?" + cartItem.getTotal() + "?" + cart.getSubTotal() +
                    "</Msg>");
            logger.debug("Change item:'" + workingItemId + "' 's quantity to " + quantity);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
