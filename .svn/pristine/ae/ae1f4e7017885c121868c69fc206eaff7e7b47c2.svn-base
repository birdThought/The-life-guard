package com.lifeshs.customer.controller.serve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.serve.ProjectService;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.project.ProjectVO;

@RestController
@RequestMapping(value = "/serve")
public class StoreProjectController extends BaseController {

    @Resource(name = "v2ProjectService")
    private ProjectService projectSerivce;
    
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ModelAndView page() {
        return new ModelAndView("/platform/serve/store-project-list");
    }
    
    /**
     *  获取门店服务列表
     *  @author yuhang.weng 
     *  @DateTime 2018年2月8日 上午11:59:10
     *
     *  @param curPage 页码
     *  @param orgName 机构名称
     *  @param classifyName 服务类型
     *  @param serveCode 服务code
     *  @return
     */
    @RequestMapping(value = "/projectes/{curPage}")
    public JSONObject listStoreProject(@PathVariable(value = "curPage") int curPage,
            @RequestParam(required = false) String orgName,
            @RequestParam(required = false) String classifyName,
            @RequestParam(required = false) String serveCode) {
        JSONObject root = new JSONObject();

        CustomerSharingDataVO user = getUserSharingData();
        PaginationDTO<ProjectVO> pagination = projectSerivce.listProject(user.getUserNo(), curPage, 10, orgName, classifyName, serveCode).getPagination();
        List<Map<String, Object>> dataList = enclosureProjectList(pagination.getData());
        root.put("success", true);
        root.put("data", dataList);
        root.put("nowPage", pagination.getNowPage());
        root.put("totalPage", pagination.getTotalPage());
        root.put("totalSize", pagination.getTotalSize());
        return root;
    }
    
    private List<Map<String, Object>> enclosureProjectList(List<ProjectVO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (ProjectVO data : dataList) {
            String name = Toolkits.projectTilte(data.getName());
            String classifyName = Toolkits.projectType(data.getName());
            
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("orgName", data.getOrgName());
            returnData.put("name", name);
            returnData.put("classifyName", classifyName);
            returnDataList.add(returnData);
        }
        return returnDataList;
    }
}
