package org.csu.mypetstore.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.CartItemDAO;
import org.csu.mypetstore.persistence.ItemDAO;
import org.csu.mypetstore.persistence.impl.CartItemDAOImpl;
import org.csu.mypetstore.persistence.impl.ItemDAOImpl;
import org.csu.mypetstore.service.CartService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Enzo Cotter on 2019/10/19.
 */
public class CartServiceImpl implements CartService {
    CartItemDAO cartItemDAO = new CartItemDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();

    Logger logger = LogManager.getLogger(CartServiceImpl.class);

    @Override
    public void updateCart(Cart cart, Account account) {
        try{
            cartItemDAO.deleteCartItemList(account);
            if (cart == null || cart.isEmpty()){
                logger.info("用户 " + account.getUsername() + " 删除购物车");
            }else{
                cartItemDAO.insertCartItemList(cart.getCartItemList(), account);
                logger.info("用户 " + account.getUsername() + " 更新购物车");
            }
        }catch (SQLException e){
            logger.error("用户 " + account.getUsername() +" 更新购物车时出现了错误");
            e.printStackTrace();
        }
    }

    @Override
    public Cart getCartByAccount(Account account) {
        Cart cart = new Cart();
        try{
            List<CartItem> cartItemList = cartItemDAO.getCartItemList(account);
            if(cartItemList.isEmpty()){
                logger.info("用户 " + account.getUsername() + " 没有历史购物车记录");
                return cart;
            }
            cart.setCartByCartItemList(cartItemList);
        }catch (SQLException e) {
            logger.error("用户 " + account.getUsername() + " 获取购物车时出现了错误");
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public Item getItemByProductName(String productName) {
        if(productName == null || productName.equals("")) {
            return null;
        }
        Item rtn = itemDAO.getItemByProductName(productName);
        return rtn;
    }
}
