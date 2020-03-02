package org.csu.mypetstore.web.servlet.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/9.
 */
@WebServlet("/viewRegisterForm")
public class ViewRegisterFormServlet extends HttpServlet {
    private static final String VIEW_REGISTER_FORM = "/WEB-INF/jsp/account/register.jsp";

    private static final Logger logger = LogManager.getLogger(ViewRegisterFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("进入注册页面");
        req.getRequestDispatcher(VIEW_REGISTER_FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
