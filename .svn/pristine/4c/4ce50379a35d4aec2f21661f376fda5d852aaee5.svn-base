package com.lifeshs.controller.officialwebsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.vo.paging.WebPaging;

/**
 * Created by XuZhanSi on 2017/1/10 0010.
 * 动态资讯
 */
@RestController
@RequestMapping("informationControl")
public class InformationController extends BaseController {

	@Autowired
    private InformationService informationService;

    /**
     * 动态资讯首页
     *
     * @return
     */
    @RequestMapping(params = "newsIndex")
    public ModelAndView informationIndex(@RequestParam(required = false) Integer f, @RequestParam(required = false) Integer page, @RequestParam(required = false) String search) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/newshome");
        modelAndView.addObject("data", informationService.loadNewsInformationDatas("资讯信息",f, page, search, null));
        if (f!=null)
            modelAndView.addObject("f",f);
        if (search!=null)
            modelAndView.addObject("search",search);
        return modelAndView;
    }

    @RequestMapping(params = "companyNews")
    public AjaxJsonV2 informationIndex(@RequestParam(required = false, name = "curPage") Integer curPage) {
        curPage = curPage == null ? 1 : curPage;
        int pageSize = 6;
        
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        DataResult result = informationService.loadNewsInformationDatas("资讯信息", null, curPage, null, pageSize);
        @SuppressWarnings("unchecked")
        PaginationDTO<TInformation> data = (PaginationDTO<TInformation>) result.get("informations");
        
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (TInformation info : data.getDataObject()) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("id", info.getId());
            returnData.put("title", info.getTitle());
            returnData.put("content", info.getContent());
            returnData.put("date", DateTimeUtilT.date(info.getCreateDate()));
            returnData.put("image", info.getImage());
            returnDataList.add(returnData);
        }
        
        WebPaging<Map<String, Object>> pageData = new WebPaging<>();
        pageData.setCurPage(data.getNowPage());
        pageData.setTotalPage(data.getTotalPage());
        pageData.setPageSize(pageSize);
        pageData.setTotalSize(data.getTotalSize());
        pageData.setData(returnDataList);
        
        ajaxJson.setSuccess(true);
        ajaxJson.setObj(pageData);
        return ajaxJson;
    }
    
    /**
     * 看一个资讯
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "inforLook")
    public ModelAndView informationLook(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/newsinfor");
        DataResult result=informationService.lookNewsById(id);
        modelAndView.addObject("info", result.get("info"));
        modelAndView.addObject("columnName", result.get("columnName"));
        modelAndView.addObject("hot",result.get("hot"));
        modelAndView.addObject("hangye",result.get("hangye"));
        return modelAndView;
    }


    /**
     * 帮助中心首页
     * @param f
     * @param page
     * @param search
     * @return
     */
    @RequestMapping(params = "helpCenterIndex")
    public ModelAndView helpCenterIndex(@RequestParam(required = false) Integer f, @RequestParam(required = false) Integer page, @RequestParam(required = false) String search){
        ModelAndView modelAndView = new ModelAndView("officialwebsite/helpindex");
        modelAndView.addObject("data", informationService.loadHelpInformationDatas("帮助中心",f, page, search));
        if (f!=null)
            modelAndView.addObject("f",f);
        if (search!=null)
            modelAndView.addObject("search",search);
        return modelAndView;
    }

    @RequestMapping(params = "helpCenterColumn", method = RequestMethod.GET)
    public AjaxJson helpCenterColumn() {
        AjaxJson resObject= new AjaxJson();
        List<TInfomationColumn> columns = informationService.loadColumnByParentColName("帮助中心");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("columnList", columns);
        resObject.setAttributes(attributes);
        return resObject;
    }
    
    /**
     *  帮助中心信息获取
     *  @author yuhang.weng 
     *  @DateTime 2017年3月23日 上午9:50:23
     *
     *  @param f
     *  @param page
     *  @param search
     *  @return
     */
    @RequestMapping(params = "helpCenter", method = RequestMethod.GET)
    public AjaxJson helpCenter(Integer f, Integer page, String search) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);
        Map<String, Object> attributes = new HashMap<>();
        DataResult dataResult = informationService.loadHelpInformationDatas("帮助中心", f, page, search);
        PaginationDTO<TInformation> informations = (PaginationDTO<TInformation>)dataResult.getAttr().get("informations");
        List<TInformation> infomations = informations.getDataObject();
        List<Map<String, Object>> inforVOs = new ArrayList<>();
        for (TInformation info : infomations) {
            Map<String, Object> inforVO = new HashMap<>();
            inforVO.put("id", info.getId());
            inforVO.put("title", info.getTitle());
            inforVOs.add(inforVO);
        }
        attributes.put("informations", inforVOs);
        if (f != null) {
            attributes.put("f", f);
        }
        if (search != null) {
            attributes.put("search", search);
        }
        resObject.setAttributes(attributes);
        return resObject;
    }
    
    /**
     *  获取资讯内容
     *  @author yuhang.weng 
     *  @DateTime 2017年11月23日 下午5:04:01
     *
     *  @param id
     *  @return
     */
    @RequestMapping(params = "information")
    public AjaxJsonV2 getInformation(int id) {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        TInformation information = commonTrans.get(TInformation.class, id);
        ajaxJson.setSuccess(true);
        ajaxJson.setObj(information.getContent());
        return ajaxJson;
    }
    
    /**
     * 看一个帮助中心问题
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "helpLook")
    public ModelAndView helpLook(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/helpLook");
        TInformation information = commonTrans.get(TInformation.class, id);
        String columnName = commonTrans.get(TInfomationColumn.class, information.getColumnId()).getName();
        modelAndView.addObject("columns",informationService.loadColumnByParentColName("帮助中心"));
        modelAndView.addObject("info", information);
        modelAndView.addObject("columnName", columnName);
        return modelAndView;
    }
}
