package org.csu.mypetstore.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.impl.CartServiceImpl;
import org.csu.mypetstore.service.impl.CatalogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Enzo Cotter on 2019/10/31.
 */
@WebServlet("/addItemByName")
public class AddItemByNameServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddItemByNameServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        logger.debug(productName);
        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        CartService service = new CartServiceImpl();
        Item item = service.getItemByProductName(productName);
        if(item == null){
            logger.warn("产品 " + productName + " 不存在");
            return;
        }

        String workingItemId = item.getItemId();
        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            CatalogService catalogService = new CatalogServiceImpl();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            cart.addItem(item, isInStock);
        }

        session.setAttribute("cart", cart);
        logger.info("添加item " + workingItemId +" 至购物车");
//        resp.sendRedirect("viewCart");//不一定有用
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
