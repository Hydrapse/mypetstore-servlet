package org.csu.mypetstore.web.servlet.account;

import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Enzo Cotter on 2019/10/28.
 */
@WebServlet("/checkUsernameUnused")
public class CheckUsernameUnusedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountService service = new AccountServiceImpl();
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username-reg");

        if(username == null || username.isEmpty()){
            out.print("empty");
        }
        else if(service.isUsernameExist(username)) {
            out.print("deny");
        }else{
            out.print("confirm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
