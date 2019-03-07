package com.lifeshs.business.controller.business;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service1.business.TemporaryService;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.business.TemporaryDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 * @create 2018-03-15
 * 15:34   推广数据
 * @desc
 */
@Controller("temporaryController")
@RequestMapping("temp")
public class TemporaryController extends BaseController {

    private static final int PAGESIZE =15;

    @Autowired
    private TemporaryService temporaryService;

    @Autowired
    private UserService userService;

    @RequestMapping("page")
    public ModelAndView getTempJsp(){
        return new ModelAndView("platform/business/temporary");
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getListData(@PathVariable("page") Integer page,@RequestParam("name")String name, @RequestParam("date")String date, @RequestParam("bname")String bname){
        AjaxJson ajaxJson = new AjaxJson();
        if ("".equals(name)){
            name = null;
        }if ("".equals(date)){
            date = null;
        }if ("".equals(bname)){
            bname = null;
        }
        LoginUser loginUser = getLoginUser();
        Integer id = loginUser.getId();
        BusinessUserPO user = userService.getUser(id);
        Integer superior = user.getSuperior();
        Integer type = user.getType();
        Paging<TemporaryDataVO> byDataList = temporaryService.findByDataList(id,type,superior,name, date, bname, page, PAGESIZE);
        ajaxJson.setObj(byDataList.getPagination());
        return ajaxJson;
    }
}
