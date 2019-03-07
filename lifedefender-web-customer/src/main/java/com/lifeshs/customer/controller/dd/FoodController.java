package com.lifeshs.customer.controller.dd;

import java.io.IOException;
import java.util.List;

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
import com.lifeshs.customer.controller.common.CommonController;
import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.food.FoodService;
import com.lifeshs.service1.systemManage.foodKind.FoodKindService;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.systemManage.FoodVo;

@RestController
@RequestMapping(value="/data/food")
public class FoodController extends CommonController {
	
	static final Logger logger =Logger.getLogger(FoodController.class);
	
	static final int PAGESIZE=10;
	
	@Autowired
	FoodService foodService;
	
	@Autowired
	FoodKindService foodKindService;
	
	@RequestMapping(value="/page")
	
	private ModelAndView FoodPage() {
		return new ModelAndView("platform/systemmanage/food");
	}
	
	/**
	 * 食物管理页面
	 * @param page
	 * @param kind
	 * @return
	 */
	@RequestMapping(value="/list/{page}",method=RequestMethod.GET)
	public AjaxJsonV2 listFood(@PathVariable("page") int page,
							@RequestParam(value="kind" ,required = false) Integer kind,
							@RequestParam(value="name",required = false) String name) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		Paging paging=foodService.findFood(kind,name,page, PAGESIZE);
		ajaxJsonV2.setObj(paging.getPagination());
		return ajaxJsonV2;
	}
	
	/**
	 * 获取食物种类列表
	 * @return
	 */
	@RequestMapping(value="/kind/list",method=RequestMethod.GET)
	public AjaxJsonV2 listFoodKind() {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		List<TDataFoodKind> foodKinds=foodKindService.findfoodKind();
		ajaxJsonV2.setObj(foodKinds);
		return ajaxJsonV2;
	}
	
	/**
	 * 添加食物
	 * @param foodVo
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public AjaxJsonV2 addFood(FoodVo foodVo) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			foodService.addFood(foodVo);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
	
	/**
	 * 编辑食物
	 * @param foodVo
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public AjaxJsonV2 editFood(FoodVo foodVo) {
		AjaxJsonV2 ajaxJsonV2=new AjaxJsonV2();
		try {
			foodService.updateFood(foodVo);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
	
	/**
	 * 删除食物
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public AjaxJsonV2 delete(@RequestParam("id")Integer id) {
		AjaxJsonV2 ajaxJsonV2 =new AjaxJsonV2();
		try {
			foodService.deleteFood(id);
		}catch (OperationException o) {
			logger.error(o.getMessage());
			ajaxJsonV2.setMsg(o.getMessage());
			ajaxJsonV2.setSuccess(false);
		}
		return ajaxJsonV2;
	}
	
	 /**
     * 文件上传
     * 
     * @param target
     * @param uploadFile
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "uploadFile/{target}", method = RequestMethod.POST)
    public @ResponseBody AjaxJson uploadFile(@PathVariable String target,
            @RequestParam(value = "file", required = false) MultipartFile uploadFile) throws IOException {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        switch (target) {
        case "img":// 图片
            String netPath = "";
            String uploadName = uploadFile.getOriginalFilename();
            String arr[] = uploadName.split("\\.");
            if (!"png".equals(arr[arr.length - 1]) && !"jpg".equals(arr[arr.length - 1])) {
                resObject.setMsg("图片类型不合法");
                break;
            }
            if (uploadFile.getSize() > 1024 * 1024) {
                resObject.setMsg("图片大小超出限制");
                break;
            }
            if (uploadFile.getSize() > 200 * 1024) {
                netPath = ImageUtilV2.saveByte(uploadFile.getBytes(), "", true, 500, 500);
            } else {
                netPath = ImageUtilV2.saveByte(uploadFile.getBytes(), "", true);
            }
            resObject.setSuccess(true);
            resObject.setObj(netPath);
            resObject.setMsg("上传文件成功");
            return resObject;
        }
        return resObject;
    }

}
