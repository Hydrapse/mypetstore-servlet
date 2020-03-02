package org.csu.mypetstore.web.servlet.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;

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
@WebServlet("/viewAccountForm")
public class ViewAccountFormServlet extends HttpServlet {
    private static final String VIEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/EditAccount.jsp";
    private static final String VIEW_LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";

    private static final Logger logger = LogManager.getLogger(ViewAccountFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            req.getRequestDispatcher(VIEW_LOGIN_FORM).forward(req, resp);
            return;
        }

        logger.info("查看用户 " + account.getUsername() + " 个人信息");
        req.getRequestDispatcher(VIEW_ACCOUNT_FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
