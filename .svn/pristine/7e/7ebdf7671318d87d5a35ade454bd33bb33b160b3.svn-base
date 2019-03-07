package com.lifeshs.service.shop;

import java.util.List;
import com.lifeshs.po.shop.Cart;

public interface CartService {
    /**
     * 将itemId对应的商品和购买数量加入到购物车列表
     * @param itemId 商品sku id
     * @param num 购买数量
     * @return 购物车列表
     */
    List<Cart> addItemToCartList(Integer itemId, Integer num, List<Cart> cartList);

    /**
     * 根据用户id查询在redis中的购物车列表
     * @param username 用户id
     * @return 购物车列表
     */
    List<Cart> findCartListByUserId(String username);

    /**
     * 将购物车列表数据写入到redis
     * @param cartList 购物车列表
     * @param username 用户id
     */
    void saveCartListToRedis(List<Cart> cartList, String username);

    /**
     * 将两个购物车列表合并为一个
     * @param cartList1 购物车列表
     * @param cartList2 购物车列表
     * @return 新购物车列表
     */
    List<Cart> mergeCartList(List<Cart> cartList1, List<Cart> cartList2);
}
