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
@WebServlet("/confirmAccountInfo")
public class ConfirmAccountInfoServlet extends HttpServlet {
    private static final String EDIT_SHIP_INFO = "/WEB-INF/jsp/order/EditShipInfo.jsp";
    private static final String VIEW_LINE_ITEMS = "/WEB-INF/jsp/order/LineItems.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String cardType = req.getParameter("cardType");
        String creditCard = req.getParameter("creditCard");
        String expiryDate = req.getParameter("expiryDate");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String country = req.getParameter("country");

        // 修改订单消息, order一定不为null
        Order order = (Order)session.getAttribute("order");
        order.setCardType(cardType);
        order.setCreditCard(creditCard);
        order.setExpiryDate(expiryDate);
        order.setBillToFirstName(firstName);
        order.setBillToLastName(lastName);
        order.setShipToFirstName(firstName);
        order.setShipToLastName(lastName);
        order.setBillAddress1(address1);
        order.setBillAddress2(address2);
        order.setShipAddress1(address1);
        order.setShipAddress2(address2);
        order.setBillCity(city);
        order.setShipCity(city);
        order.setBillState(state);
        order.setShipState(state);
        order.setBillZip(zip);
        order.setShipZip(zip);
        order.setBillCountry(country);
        order.setShipCountry(country);

        session.setAttribute("order", order);

        if(req.getParameter("shippingAddressRequired") != null){
            session.setAttribute("isShipAddReq", "true");
            req.getRequestDispatcher(EDIT_SHIP_INFO).forward(req,resp);
        }else{
            session.setAttribute("isShipAddReq", "false");
            req.getRequestDispatcher(VIEW_LINE_ITEMS).forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转至确认订单
        resp.sendRedirect("viewOrderForm");
    }
}
