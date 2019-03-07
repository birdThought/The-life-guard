package com.lifeshs.service.shop.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.po.shop.Cart;
import com.lifeshs.po.shop.SkuItem;
import com.lifeshs.service.shop.CartService;
import com.lifeshs.service.shop.ShopService;


@Service
public class CartServiceImpl implements CartService {

    //购物车列表在redis中的key的名称
    private static final String REDIS_CART_LIST = "CART_LIST";
    
    @Autowired
    private ShopService shopService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Cart> addItemToCartList(Integer itemId, Integer num, List<Cart> cartList) {
    	/*String st = shopService.selectAttrLengthById(itemId);*/
    	/*String[] split = st.split(",");
    	Integer length =  split.length;*/
    	SkuItem item = shopService.selectByPrimaryKey(itemId);   
    	if(item.getGroup_spec()==null){
    		item.setGroup_spec("");
    	}
    	
        //判断该商品对应的商家的购物车是否存在
        Cart cart = findCartInCartListBySellerId(cartList, item.getShopId());
      
        if(cart == null) {
            if (num > 0) {
                //购物车列表中该商品对应的商家不存在；重新创建一个购物车对象cart并加入到购物车列表cartList
                cart = new Cart();
                cart.setSellerId(item.getShopId());
                cart.setSeller(item.getShopName());

                //创建一个购物车商品列表
                List<SkuItem> orderItemList = new ArrayList<>();
                item.setNum(num);
                orderItemList.add(item);

                cart.setOrderItemList(orderItemList);
                //将新建的购物车对象加入购物车列表
                cartList.add(cart);
            } else {
                throw new RuntimeException("购买数量非法");
            }
        } else {
            //购物车列表中该商品对应的商家存在

        	SkuItem orderItem = findOrderItemInCartByItemId(cart, itemId);
            if(orderItem != null) {
                //商家对应的商品列表中存在商品；购买数量叠加
                orderItem.setNum(orderItem.getNum() + num);
                
                //如果在商品的购买数量为0的时候；应该将该商品从商品列表中删除
                if (orderItem.getNum() < 1) {
                    cart.getOrderItemList().remove(orderItem);
                }
                //如果购物车中的商品列表为0的时候；应该将该购物车从购物车列表中删除
                if (cart.getOrderItemList().size() == 0) {
                    cartList.remove(cart);
                }

            } else {
                //商家对应的商品列表中不存在商品；重新创建一个购买车商品orderItem并加入到该商家的商品列表orderItemList
                if (num > 0) {
                	item.setNum(num);                   
                    cart.getOrderItemList().add(item);
                } else {
                    throw new RuntimeException("购买数量非法");
                }
            }
        }
        return cartList;
    }

    @Override
    public List<Cart> findCartListByUserId(String username) {
        String object  = (String) redisTemplate.boundHashOps(REDIS_CART_LIST).get(username);
        List<Cart> cartList = JSONArray.parseArray(object, Cart.class);
        
       if (cartList != null) {
            return cartList;
        }
        return new ArrayList<>();
    }

    @Override
    public void saveCartListToRedis(List<Cart> cartList, String userId) {
    	String json = JSON.toJSONString(cartList);
        redisTemplate.boundHashOps(REDIS_CART_LIST).put(userId, json);
        System.out.println("登陆的redis来了来了");
    }

    
    
    @Override
    public List<Cart> mergeCartList(List<Cart> cartList1, List<Cart> cartList2) {
        for (Cart cart : cartList1) {
            for (SkuItem orderItem : cart.getOrderItemList()) {
                addItemToCartList(orderItem.getId(), orderItem.getNum(), cartList2);
            }
        }
        return cartList2;
    }

    
    
    /**
     * 根据商品sku id到购物车对应的购物车商品列表中查询该购物车商品orderItem
     * @param cart 购物车
     * @param Id 商品sku id
     * @return 购物车商品orderItem
     */
    private SkuItem findOrderItemInCartByItemId(Cart cart, Integer id) {
        if (cart.getOrderItemList() != null && cart.getOrderItemList().size() > 0) {
            for (SkuItem orderItem : cart.getOrderItemList()) {            	
                if (id == orderItem.getId() || id.equals(orderItem.getId())) {
                    return orderItem;
                }
            }
        }
        return null;
    }
   

    /**
     * 根据商家id在一个购物车列表中查询购物车对象
     * @param cartList 购物车列表
     * @param sellerId 商家id
     * @return 购物车对象Cart
     */
    private Cart findCartInCartListBySellerId(List<Cart> cartList, Integer sellerId) {
        if (cartList != null && cartList.size() > 0) {
            for (Cart cart : cartList) {
                if (sellerId == cart.getSellerId()) {
                    return cart;
                }
            }
        }
        return null;
    }
}
