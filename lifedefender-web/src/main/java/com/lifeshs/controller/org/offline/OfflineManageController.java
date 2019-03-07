package com.lifeshs.controller.org.offline;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.record.UserRecordService;
import com.lifeshs.vo.member.UserVo;


/**
 * 员工管理
 *
 * @author wenxian.cai
 * @create 2017-06-02 15:15
 **/
@RestController("OfflineManageController")
@RequestMapping(value = "org/offlineManage")
public class OfflineManageController extends BaseController {
    private static final Logger logger = Logger.getLogger(OfflineManageController.class);
    final static int PAGE_SIZE = 10;
    
    @Resource(name = "v2OrderService")
    private OrderService orderService;
    
    @Autowired
    private UserRecordService userRecordService;
    
    
    /**
     * 
     *  获取下线用户
     *  @author liaoguo
     *  @DateTime 2018年12月12日 下午8:11:30
     *
     *  @return
     */
    @RequestMapping()
    public ModelAndView offlineManage() {
        ModelAndView modelAndView = new ModelAndView("platform/org/offline/offline-manage");
//        String userNo = getLoginUser().getUserNo();
//        PaginationDTO<UserPO> paginationDTO = offlineManageService.listOffile(userNo, null, null, 1, PAGE_SIZE);
//        modelAndView.addObject("offline", JSON.toJSON(paginationDTO));
        
        return modelAndView;
    }
    
    /**
     *  用户查看数据
     * @param page
     * @param userName
     * @param realName
     * @param orgName
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/list/{page}")
    public @ResponseBody AjaxJson getMemberDataList(@PathVariable(value = "page") int page, 
                                                    @RequestParam(value = "userName",required = false) String userName,
                                                    @RequestParam(value = "realName",required = false) String realName,
                                                    @RequestParam(value = "mobile",required = false) String mobile){
        String orgName = null;
        if ("".equals(userName)){
            userName =null;
        }
        if ("".equals(realName)){
            realName =null;
        }
        if ("".equals(mobile)){
            mobile =null;
        }

        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        Paging<UserVo> p = userRecordService.listpackage(user.getUserNo(),user.getAgentId(), userName,realName,orgName,mobile,page, PAGE_SIZE);
        PaginationDTO<UserVo> pagination = p.getPagination();
        ajaxJson.setObj(pagination);
        return ajaxJson;
    }
}
