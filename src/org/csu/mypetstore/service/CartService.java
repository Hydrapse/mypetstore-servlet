package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;

/**
 * Created by Enzo Cotter on 2019/10/19.
 */
public interface CartService {
    void updateCart(Cart cart, Account account);

    Cart getCartByAccount(Account account);

    Item getItemByProductName(String productName);
}
