package org.csu.mypetstore.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.impl.CartServiceImpl;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Enzo Cotter on 2019/10/18.
 */
@WebListener
public class MySessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(MySessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session  = se.getSession();
        logger.warn("sessionId '" + session.getId() + "' 访问服务器");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session  = se.getSession();
        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");
        CartService cartService = new CartServiceImpl();
        if(account != null){
            cartService.updateCart(cart, account);
        }
        logger.warn("sessionId '" + session.getId() + "' 断开连接");
    }
}
