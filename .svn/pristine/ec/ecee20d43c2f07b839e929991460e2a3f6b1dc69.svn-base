package com.lifeshs.controller.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.po.shop.Cart;
import com.lifeshs.po.shop.SkuItem;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.service.shop.CartService;
import com.lifeshs.utils.CookieUtils;
import com.mysql.fabric.xmlrpc.base.Array;

@RequestMapping("cart")
@RestController
public class CartController{

    private static final String COOKIE_CART_LIST = "CART_LIST";
    private static final int COOKIE_CART_LIST_MAX_AGE = 3600*24;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession session;
    @Autowired
    private CartService cartService;
                 

    /**
     * 登录与未登录
     * 将itemId对应的商品和购买数量加入到购物车列表
     * @param itemId 商品sku id
     * @param num 购买数量
     * @return 操作结果
     */
    @RequestMapping(value="/addItemToCartList",method= RequestMethod.POST)
    public AjaxJson addItemToCartList(@RequestParam("itemId")Integer itemId, @RequestParam("num")Integer num){    
    	AjaxJson ajaxJson = new AjaxJson();    	
        List<Cart> cartList = findCartList();           
        //2、将商品sku和购买数量加入到购物车列表
        cartList = cartService.addItemToCartList(itemId, num, cartList);
        
        Integer userId = (Integer) session.getAttribute("userId");      
        if (userId == null || userId == 0 ) {
            String cartListJsonStr = JSONArray.toJSONString(cartList);
            CookieUtils.setCookie(request, response, COOKIE_CART_LIST, cartListJsonStr, COOKIE_CART_LIST_MAX_AGE, true);
            System.out.println("没登录来了。");
        } else {
            //将最新的购物车列表数据写回redis
            cartService.saveCartListToRedis(cartList, userId+"");
        }    
        ajaxJson.setObj(cartList);
        return ajaxJson;       
    }
    
    @RequestMapping("/findCartList")
    public ModelAndView findCart(){
    	ModelAndView mav = null;
    	List<Cart> findCartList = findCartList();
    	if(StringUtils.isEmpty(findCartList)||findCartList.size()==0){
    		mav = new ModelAndView("/shop/shopcar");
    	}else{    		
    		mav = new ModelAndView("/shop/shopcar_list");    		
    		mav.addObject("cartList", JSON.toJSON(findCartList));
    	}
    	return mav;
    }
    

    
    
    /**
     * 查询登录或者未登录情况下的购物车列表
     * @return 购物车列表
     */
    /*@GetMapping("/findCartList")*/
    public List<Cart> findCartList(){   	  	
    	Integer userId = (Integer) session.getAttribute("userId");    	   	         
        List<Cart> cookie_cartList = new ArrayList<>();
        String cartListJsonStr = CookieUtils.getCookieValue(request, COOKIE_CART_LIST, true);
        if (!StringUtils.isEmpty(cartListJsonStr)) {
            cookie_cartList = JSONArray.parseArray(cartListJsonStr, Cart.class);
        }
        
        
        if (userId == null || userId == 0) {           
        	return cookie_cartList; 
        } else {            
            List<Cart> redis_cartList = cartService.findCartListByUserId(userId+"");
            //合并购物车；如果cookie有数据才需要合并
            if (cookie_cartList.size() > 0) {
                redis_cartList = cartService.mergeCartList(cookie_cartList, redis_cartList);
                //将最新的购物车列表写回redis
                cartService.saveCartListToRedis(redis_cartList, userId+"");               
                CookieUtils.deleteCookie(request, response, COOKIE_CART_LIST);
            }           
            return redis_cartList;
        }
    }
    
    
    
    /**
     * 登录与未登录
     * 购物车列表删除对应的sku
     * @return 操作结果
     */
    @RequestMapping(value="/delItemToCartList",method= RequestMethod.GET)
    public AjaxJson delItemToCartList(Integer[] delItemList){
    	AjaxJson ajaxJson = new AjaxJson();       
    	List<Integer> List = Arrays.asList(delItemList);
    	/*Integer userId =  ((UserPO) SecurityUtils.getSubject().getPrincipal()).getId();*/ 
    	Integer userId = (Integer) session.getAttribute("userId");
        List<Cart> cartList = findCartList();
           
       for (int i = 0; i < cartList.size(); i++) {
        	List<SkuItem> orderItemList = cartList.get(i).getOrderItemList();
        	for (int j = 0; j < orderItemList.size(); j++) {
        		if(List.contains(orderItemList.get(j).getId())){
    				orderItemList.remove(j);
    				j--;
    			}
			}        	
        	if(cartList.get(i).getOrderItemList().size() == 0){
        		cartList.remove(i);
        		i--;
        	}        	        	
		}
						 
        if (userId == null || userId == 0 ) {
            String cartListJsonStr = JSONArray.toJSONString(cartList);
            CookieUtils.setCookie(request, response, COOKIE_CART_LIST, cartListJsonStr, COOKIE_CART_LIST_MAX_AGE, true);
            System.out.println("没登录来了。");
        } else {
            //将最新的购物车列表数据写回redis
            cartService.saveCartListToRedis(cartList, userId+"");
        }
        ajaxJson.setObj(cartList);
        return ajaxJson;
       
    }

   
}
