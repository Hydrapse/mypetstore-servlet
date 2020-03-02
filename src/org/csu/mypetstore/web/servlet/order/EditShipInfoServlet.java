package org.csu.mypetstore.web.servlet.order;

import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/11.
 */
@WebServlet("/editShipInfo")
public class EditShipInfoServlet extends HttpServlet {
    private static final String VIEW_LINE_ITEMS = "/WEB-INF/jsp/order/LineItems.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order");

        String shipToFirstName = req.getParameter("shipToFirstName");
        String shipToLastName = req.getParameter("shipToLastName");
        String shipAddress1 = req.getParameter("shipAddress1");
        String shipAddress2 = req.getParameter("shipAddress2");
        String shipCity = req.getParameter("shipCity");
        String shipState = req.getParameter("shipState");
        String shipZip = req.getParameter("shipZip");
        String shipCountry = req.getParameter("shipCountry");

        order.setShipToFirstName(shipToFirstName);
        order.setShipToLastName(shipToLastName);
        order.setShipAddress1(shipAddress1);
        order.setShipAddress2(shipAddress2);
        order.setShipCity(shipCity);
        order.setShipState(shipState);
        order.setShipZip(shipZip);
        order.setShipCountry(shipCountry);

        session.setAttribute("order",order);
        req.getRequestDispatcher(VIEW_LINE_ITEMS).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转至确认订单
        resp.sendRedirect("viewOrderForm");
    }
}
