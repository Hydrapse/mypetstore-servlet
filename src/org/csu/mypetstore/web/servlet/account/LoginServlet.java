package org.csu.mypetstore.web.servlet.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.impl.AccountServiceImpl;
import org.csu.mypetstore.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Enzo Cotter on 2019/10/9.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String VIEW_LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();

        //检测是否已经登录
        if (session.getAttribute("account") != null) {
            logger.warn("已经登录 切换用户请先注销");
            out.print("alreadyLogin");
            return;
        }

        //检测是否通过验证
        String test = (String) session.getAttribute("verification");
        session.setAttribute("verification", "false");
        if (test == null || !test.equals("true")){
            logger.warn("请先填写验证码");
            out.print("false");
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String dispatcher = (String) session.getAttribute("dispatcher");
        if (dispatcher == null){
            dispatcher = "main";
        }
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        AccountService accountService = new AccountServiceImpl();
        Account confirmAccount = accountService.getAccount(account);

        if (confirmAccount != null) {
            session.setAttribute("account", confirmAccount);

            logger.info("用户 " + account.getUsername() +" 登录成功");

            CartService cartService = new CartServiceImpl();
            Cart historyCart = cartService.getCartByAccount(account);
            Cart currentCart = (Cart) session.getAttribute("cart");

            logger.debug("购物车取出成功");

            if(!historyCart.isEmpty()){
                if(currentCart==null || currentCart.isEmpty()){
                    session.setAttribute("cart", historyCart);
                    out.print("history-cart-true-current-empty|" + dispatcher);
                    logger.debug("原始购物车为空");
                } else{
                    out.print("history-cart-true-current-notEmpty|" + dispatcher);
                    logger.debug("原始购物车不为空");
                }
            } else {
                out.print("history-cart-false|" + dispatcher);
                logger.debug("无历史购物车");
            }
        } else {
            logger.info("登录失败");
            out.print("false|" + dispatcher);
        }
        session.setAttribute("dispatcher", "main");
    }
}
