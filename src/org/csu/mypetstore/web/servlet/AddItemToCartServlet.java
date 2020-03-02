package org.csu.mypetstore.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.impl.CatalogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/7.
 */
public class AddItemToCartServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddItemToCartServlet.class);

    //定义处理该请求所需要的数据
    private String workingItemId;
    private Cart cart;

    //调用业务逻辑层
    private CatalogService catalogService;


    public String getWorkingItemId() {
        return workingItemId;
    }

    public void setWorkingItemId(String workingItemId) {
        this.workingItemId = workingItemId;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }
        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            catalogService = new CatalogServiceImpl();
            Item item = catalogService.getItem(workingItemId);
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            cart.addItem(item, isInStock);
        }

        session.setAttribute("cart", cart);
        logger.info("添加item " + workingItemId +" 至购物车");
        resp.sendRedirect("viewCart");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
