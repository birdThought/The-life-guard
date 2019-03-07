package com.lifeshs.customer.controller.order;

import javax.annotation.Resource;

import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.po.OrgStatementPO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.order.IStatementService;

@RestController
@RequestMapping(value = "/order/statement")
public class OrderStatementController extends BaseController {

    @Resource(name = "statementService")
    private IStatementService statementService;
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/platform/ordermanager/ord-statement");
    }
    
    @RequestMapping(value = "/data/list/{curPage}", method = RequestMethod.GET)
    public JSONObject listData(@PathVariable(value = "curPage") int curPage) {
        CustomerSharingDataVO user = getUserSharingData();
        PaginationDTO<OrgStatementPO> pagination = statementService.listStatement(user.getUserNo(), curPage, 10).getPagination();
        
        JSONObject root = new JSONObject();
        root.put("success", true);
        root.put("data", pagination.getData());
        root.put("nowPage", pagination.getNowPage());
        root.put("totalPage", pagination.getTotalPage());
        root.put("totalSize", pagination.getTotalSize());
        return root;
    }
}
