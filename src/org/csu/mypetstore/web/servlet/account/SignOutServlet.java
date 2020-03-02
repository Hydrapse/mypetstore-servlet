package org.csu.mypetstore.web.servlet.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/10.
 */
@WebServlet("/signOut")
public class SignOutServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    private static final Logger logger = LogManager.getLogger(SignOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        session.setAttribute("account", null);
        if(account == null){
            req.getRequestDispatcher(MAIN).forward(req, resp);
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        CartService cartService = new CartServiceImpl();
        cartService.updateCart(cart, account);
        session.setAttribute("cart", null);//初始化购物车

        session.setAttribute("order", null);//初始化订单

        logger.info("用户" +account.getUsername() + "进行注销");
        req.getRequestDispatcher(MAIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
