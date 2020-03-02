package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2019/10/5.
 */
public interface ItemDAO {
    // 更新库存
    void updateInventoryQuantity(Map<String, Object> param);

    // 得到商品库存
    int getInventoryQuantity(String itemId);

    // 根据productId得到对应的所有的商品
    List<Item> getItemListByProduct(String productId);

    // 根据itemId 得到对应的某个商品
    Item getItem(String itemId);

    Item getItemByProductName(String productName);
}
