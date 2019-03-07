package com.lifeshs.customer.controller.systemManage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.appVersion.AppVersionService;
import com.lifeshs.utils.FileUtils;
import com.lifeshs.vo.systemManage.AppVersionVo;

@RestController
@RequestMapping(value = "/app-version")
public class AppVersionController {

	static final Logger logger = Logger.getLogger(AppVersionController.class);

	static final int PAGESIZE = 10;

	@Autowired
	AppVersionService appVersionService;

	@RequestMapping(value = "/page")
	private ModelAndView AppVersionPage() {
		return new ModelAndView("platform/systemmanage/app-version");
	}

	/**
	 * 获取版本列表
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list-version/{page}", method = RequestMethod.GET)
	public AjaxJsonV2 listVersion(@PathVariable("page") int page) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		Paging paging = appVersionService.findVersion(page, PAGESIZE);
		ajaxJsonV2.setObj(paging.getPagination());
		return ajaxJsonV2;

	}

	/**
	 * 添加
	 * 
	 * @param appVersionVo
	 * @return
	 */
	@RequestMapping(value = "add-version", method = RequestMethod.POST)
	public AjaxJsonV2 addVersion(AppVersionVo appVersionVo) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			appVersionService.addVersion(appVersionVo);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

	/**
	 * 更改
	 * 
	 * @param appVersionVo
	 * @return
	 */
	@RequestMapping(value = "edit-version", method = RequestMethod.POST)
	public AjaxJsonV2 editVersion(AppVersionVo appVersionVo) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			appVersionService.updateVersion(appVersionVo);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete-version", method = RequestMethod.POST)
	public AjaxJsonV2 delete(@RequestParam("id") Integer id) {
		AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
		try {
			appVersionService.deleteVersion(id);
		} catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
	
	/**
     * apk文件上传
     * 
     * @param target
     * @param uploadFile
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "uploadFile/{target}", method = RequestMethod.POST)
    public @ResponseBody AjaxJson uploadFile(@PathVariable String target,
            @RequestParam(value = "file", required = false) MultipartFile uploadFile,HttpServletRequest request) throws IOException {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        switch (target) {
        case "apk":// apk
//            String name = FileUtils.saveAPKFile(request.getParameter("name"), uploadFile.getBytes());
            String appName = request.getParameter("name");
            if(StringUtils.isBlank(appName)){
                appName = uploadFile.getOriginalFilename();
            }
            String name = FileUtils.saveAPKFile(appName, uploadFile.getBytes());
            
            resObject.setSuccess(true);
            resObject.setObj(name);
            resObject.setMsg("上传文件成功");

            return resObject;
        }
        return resObject;
    }
	
}