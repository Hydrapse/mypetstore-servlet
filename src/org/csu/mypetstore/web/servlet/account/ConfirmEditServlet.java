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

/**
 * Created by Enzo Cotter on 2019/10/10.
 */
@WebServlet("/confirmEdit")
public class ConfirmEditServlet extends HttpServlet {
    private static final String VIEW_LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";
    private static final String VIEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/EditAccount.jsp";

    private static final Logger logger = LogManager.getLogger(ConfirmEditServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountService service = new AccountServiceImpl();

        if (account == null) {
            logger.warn("用户session已过期 请重新登录");
            req.getRequestDispatcher(VIEW_LOGIN_FORM).forward(req, resp);
            return;
        }

        //检测是否通过验证
        String test = (String) session.getAttribute("verification");
        session.setAttribute("verification", "false");
        if (test == null || !test.equals("true")){
            logger.warn("请先填写验证码");
            req.getRequestDispatcher(VIEW_ACCOUNT_FORM).forward(req, resp);
            return;
        }

        String password = req.getParameter("password");
        String repeatedPassword = req.getParameter("repeatedPassword");
        Boolean hasChangedPassword = false;

        if (!password.isEmpty() && password != null && password.equals(repeatedPassword)) {
            account.setPassword(password);
            hasChangedPassword = true;
        }
        account.setFirstName(req.getParameter("firstName"));
        account.setLastName(req.getParameter("lastName"));
        account.setEmail(req.getParameter("email"));
        account.setPhone(req.getParameter("phone"));
        account.setAddress1(req.getParameter("address1"));
        account.setAddress2(req.getParameter("address2"));
        account.setCity(req.getParameter("city"));
        account.setState(req.getParameter("state"));
        account.setZip(req.getParameter("zip"));
        account.setCountry(req.getParameter("country"));
        account.setBannerOption(req.getParameter("isMyBannerEnabled") == null ? false : true);
        account.setListOption(req.getParameter("isMyListEnabled") == null ? false : true);
        account.setLanguagePreference(req.getParameter("languages"));

        //如果不同需要从数据库中取出banner图片资源位置
        String category = req.getParameter("categories");
        if(!account.getFavouriteCategoryId().equals(category)){
            account.setFavouriteCategoryId(category);
            account.setBannerName(service.getBannerName(category));
            logger.info("重新设置横幅");
        }

        service.updateAccount(account);

        logger.info("用户 " + account.getUsername() + " 个人信息修改成功");

        if (hasChangedPassword) {
            logger.info("更改密码后需重新登录");
            session.setAttribute("account", null);
            req.getRequestDispatcher(VIEW_LOGIN_FORM).forward(req, resp);
            return;
        }

        req.getRequestDispatcher(VIEW_ACCOUNT_FORM).forward(req, resp);
    }
}
