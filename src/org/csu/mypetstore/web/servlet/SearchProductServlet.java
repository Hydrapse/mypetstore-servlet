package org.csu.mypetstore.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.impl.CatalogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Enzo Cotter on 2019/10/6.
 */
public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCT = "/WEB-INF/jsp/catalog/SearchProduct.jsp";

    private static final Logger logger = LogManager.getLogger(SearchProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        HttpSession session = req.getSession();

        if(keyword == null || keyword.isEmpty()){
            session.setAttribute("productList", null);
            req.getRequestDispatcher(SEARCH_PRODUCT).forward(req, resp);
            return;
        }

        CatalogService service = new CatalogServiceImpl();
        List<Product> productList = service.searchProductList("%" + keyword.toLowerCase() + "%");
        session.setAttribute("productList", productList);

        logger.info("根据关键字 '"+ keyword +"' 搜索相关产品");
        req.getRequestDispatcher(SEARCH_PRODUCT).forward(req, resp);
    }
}
