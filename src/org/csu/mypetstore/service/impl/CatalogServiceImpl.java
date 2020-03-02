package org.csu.mypetstore.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CategoryDAO;
import org.csu.mypetstore.persistence.ItemDAO;
import org.csu.mypetstore.persistence.ProductDAO;
import org.csu.mypetstore.persistence.impl.CatagoryDAOImpl;
import org.csu.mypetstore.persistence.impl.ItemDAOImpl;
import org.csu.mypetstore.persistence.impl.ProductDAOImpl;
import org.csu.mypetstore.service.CatalogService;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Enzo Cotter on 2019/10/4.
 */
public class CatalogServiceImpl implements CatalogService {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private ItemDAO itemDAO;

    private static final Logger logger = LogManager.getLogger(CatalogService.class);

    public CatalogServiceImpl() {
        productDAO = new ProductDAOImpl();
        categoryDAO = new CatagoryDAOImpl();
        itemDAO = new ItemDAOImpl();
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    @Override
    public Category getCategory(String categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    @Override
    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }

    @Override
    public List<Product> searchProductList(String keyword) {
        List<Product> pd1 = productDAO.searchProductList(keyword);
        List<Product> pd2 = productDAO.searchCategoryProductList(keyword);
        for(Product p2 : pd2){
            boolean flag = true;
            for(Product p1 : pd1){
                if(p2.getProductId().equals(p1.getProductId())){
                    flag = false;
                    break;
                }
            }
            if(flag){
                pd1.add(p2);
            }
        }
        return pd1;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    @Override
    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    @Override
    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }

    @Override
    public String[] getAutoCompleteArray(String keyword) {
        String[] rtn = new String[1];

        //塞入是否匹配种类名称
        String pattern = ".*" + keyword.toLowerCase() + ".*";
        if (Pattern.matches(pattern, "dog")){
            rtn[0] = "DOGS";
        }else if (Pattern.matches(pattern, "cat")){
            rtn[0] = "CATS";
        }else if (Pattern.matches(pattern, "bird")){
            rtn[0] = "BIRDS";
        }else if (Pattern.matches(pattern, "fish")){
            rtn[0] = "FISH";
        }else if (Pattern.matches(pattern, "reptile")){
            rtn[0] = "REPTILES";
        }

        try {
            List<String> nameList = productDAO.searchProductNameList("%" + keyword.toLowerCase() + "%");
            if(nameList.isEmpty())
                return rtn;
            int nlSize = nameList.size();

            String[] nameArray;
            if(rtn[0] == null){ //不匹配种类名的情况
                nameArray = new String[nlSize];
                for(int i=0; i< nlSize; i++){
                    nameArray[i] = nameList.get(i);
                }
            }else{//匹配种类名的情况
                nameArray = new String[nlSize+1];
                nameArray[0] = rtn[0];
                for(int i=0; i< nlSize; i++){
                    nameArray[i+1] = nameList.get(i);
                }
            }

            logger.debug(JSON.toJSONString(nameArray));
            return nameArray;

        }catch (SQLException e){
            logger.error("AutoComplete Keyword: '" + keyword + "' throws Exception");
            e.printStackTrace();
        }

        return rtn;
    }
}
