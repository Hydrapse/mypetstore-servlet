package org.csu.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.impl.CatalogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Enzo Cotter on 2019/10/27.
 */
@WebServlet("/searchAutoComplete")
public class SearchAutoCompleteServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(SearchAutoCompleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        PrintWriter out = resp.getWriter();

        logger.debug(keyword);

        if(keyword == null || keyword.isEmpty()){
            logger.debug("字符串为空");
            out.print("false");
            return;
        }

        CatalogService service = new CatalogServiceImpl();
        String[] nameArray = service.getAutoCompleteArray(keyword);
        if (nameArray.length == 0 || nameArray[0] == null || nameArray[0].isEmpty()){
            logger.debug("Cannot find product by '" + keyword + "'");
            out.print("false");
            return;
        }

        logger.debug(JSON.toJSONString(nameArray));

        out.print(JSON.toJSONString(nameArray));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
