package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Enzo Cotter on 2019/10/19.
 */
public interface CartItemDAO {
    void insertCartItemList (List<CartItem> cartItemList, Account account) throws SQLException;

    void deleteCartItemList (Account account) throws SQLException;

    List<CartItem> getCartItemList(Account account) throws SQLException;
}
