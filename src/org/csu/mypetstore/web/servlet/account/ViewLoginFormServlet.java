package org.csu.mypetstore.web.servlet.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/9.
 */
@WebServlet(urlPatterns = "/viewLoginForm")
public class ViewLoginFormServlet extends HttpServlet {
    private static final String VIEW_LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    private static final Logger logger = LogManager.getLogger(ViewLoginFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("verification", "false");

        if (session.getAttribute("account") != null) {
            logger.warn("已经登录过，切换账号请先注销");
            resp.sendRedirect("viewAccountForm");
            return;
        }

        logger.info("进入登录界面");
        req.getRequestDispatcher(VIEW_LOGIN_FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
