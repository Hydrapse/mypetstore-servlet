package org.csu.mypetstore.web.servlet.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.impl.AccountServiceImpl;

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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String VIEW_LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";
    private static final String VIEW_REGISTER_FORM = "/WEB-INF/jsp/account/register.jsp";

    private static final Logger logger = LogManager.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("111");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        Account account = new Account();

        account.setUsername(req.getParameter("username-reg"));
        account.setPassword(req.getParameter("password-reg"));
        account.setFirstName(req.getParameter("firstName"));
        account.setLastName(req.getParameter("lastName"));
        account.setEmail(req.getParameter("Email"));
        account.setPhone(req.getParameter("phone"));
        account.setAddress1(req.getParameter("address1"));
        account.setAddress2(req.getParameter("address2"));
        account.setCity(req.getParameter("city"));
        account.setState(req.getParameter("state"));
        account.setZip(req.getParameter("zip"));
        account.setCountry(req.getParameter("country"));
        account.setBannerOption(req.getParameter("isMyListEnabled") == null ? false : true);
        account.setListOption(req.getParameter("isMyBannerEnabled") == null ? false : true);
        account.setFavouriteCategoryId(req.getParameter("categories"));
        account.setLanguagePreference(req.getParameter("languages"));

        AccountService accountService = new AccountServiceImpl();
        if (accountService.insertAccount(account)) {
            logger.info("用户 " + account.getUsername() + " 注册成功");
            session.setAttribute("account", null);
            out.print("true");
        } else {
            logger.info("注册失败");
            session.setAttribute("account", null);
            out.print("false");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
