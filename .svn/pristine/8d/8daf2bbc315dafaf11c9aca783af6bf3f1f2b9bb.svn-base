package com.lifeshs.controller.record;

import com.alibaba.fastjson.JSONObject;

import com.lifeshs.common.model.AjaxJson;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.entity.record.TRecordPhysicals;

import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.page.PaginationDTO;

import com.lifeshs.service.record.IRecordService;

import com.lifeshs.utils.image.ImageUtilV2;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import java.util.List;


@Controller
@RequestMapping("/physicalControl")
public class PhysicalController extends BaseController {
    @Autowired
    private IRecordService recordService;

    @RequestMapping(params = "enterPhysicalReport")
    public ModelAndView enterPhysicalReport() {
        ModelAndView modelAndView = new ModelAndView(
                "com/lifeshs/member/testReport");
        Integer userId = getLoginUser().getId();
        PaginationDTO pagination = recordService.selectPhysicalsByUserIdPageSplit(userId,
                1, 5);

        /*model.addAttribute("data", pagination);*/
        modelAndView.addObject("totalPage", pagination.getTotalPage());
        modelAndView.addObject("curPage", pagination.getNowPage());
        modelAndView.addObject("data", JSONObject.toJSON(pagination.getData()));

        return modelAndView;
    }

    /**
     *  获取体检报告的数据
     *  @author zhiguo.lin
     *        @DateTime 2016年8月12日 下午1:49:25
     *
     *  @param nowPage 分页的页码
     *  @param pageSize 分页的页面大小
     *  @return
     */
    @RequestMapping(params = "getPhysicalReportByPage")
    public @ResponseBody
    AjaxJson getPhysicalReportByPage(Integer nowPage, Integer pageSize) {
        AjaxJson resObject = new AjaxJson();

        int userId = getLoginUser().getId();

        //数据分页
        PaginationDTO pagination = recordService.selectPhysicalsByUserIdPageSplit(userId,
                nowPage, pageSize);
        resObject.setObj(pagination);

        return resObject;
    }

    /**
     *  @author zhiguo.lin
     *        @DateTime 2016年8月13日 下午1:53:52
     *  @serverComment        通过获取体检报告id获取体检记录
     *
     *  @param reportId
     *  @return
     */
    @RequestMapping(params = "getPhysicalReport")
    @ResponseBody
    public AjaxJson getPhysicalReport(
        @RequestParam(value = "reportId", required = true)
    Integer reportId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        TRecordPhysicals physicalReport = (TRecordPhysicals) recordService.selectPhysicalsById(reportId);

        if (physicalReport != null) {
            resObject.setSuccess(true);
        }

        resObject.setObj(physicalReport);

        return resObject;
    }

    /**
     *  @author zhiguo.lin
     *        @DateTime 2016年8月10日 上午9:59:22
     *  @serverComment 添加体检报告
     *
     *  @return
     */
    @RequestMapping(params = "addPhysicalReport", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson addPhysicalReport(TRecordPhysicals recordPhysical)
        throws Exception {
        AjaxJson resObject = new AjaxJson();
        Integer userId = getLoginUser().getId();
        recordPhysical.setUserId(userId);

        boolean isSuccess = recordService.addPhysicals(recordPhysical);
        resObject.setSuccess(isSuccess);

        if (isSuccess) {
            resObject.setMsg("添加成功");

            return resObject;
        }

        resObject.setMsg("添加失败");

        return resObject;
    }

    /**
     *  @author zhiguo.lin
     *        @DateTime 2016年8月12日 下午2:57:29
     *  @serverComment 修改体检报告
     *
     *  @param reportId
     */
    @RequestMapping(params = "updatePhysicalReport", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson updatePhysicalReport(TRecordPhysicals recordPhysical)
        throws Exception {
        AjaxJson resObject = new AjaxJson();
        String img1 = recordPhysical.getImg1();
        String img2 = recordPhysical.getImg2();
        String img3 = recordPhysical.getImg3();

        if (img1 == null) {
            recordPhysical.setImg1("");
        }

        if (img2 == null) {
            recordPhysical.setImg2("");
        }

        if (img3 == null) {
            recordPhysical.setImg3("");
        }

        Boolean isSuccess = recordService.updatePhysicals(recordPhysical);
        resObject.setSuccess(isSuccess);

        if (isSuccess) {
            resObject.setMsg("编辑成功");

            return resObject;
        }

        resObject.setMsg("编辑失败");

        return resObject;
    }

    /**
     *  @author zhiguo.lin
     *        @DateTime 2016年8月12日 下午2:55:33
     *  @serverComment 删除体检报告
     *
     *  @param reportId
     *  @return
     */
    @RequestMapping(params = "deletePhysicalReport", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson deletePhysicalReport(@RequestParam
    Integer reportId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        LoginUser user = getLoginUser();
        Integer userId = user.getId();

        if (recordService.deletePhysicals(userId, reportId).intValue() != 0) {
            resObject.setMsg("删除成功");
            resObject.setSuccess(true);

            return resObject;
        }

        resObject.setMsg("删除失败");

        return resObject;
    }

    /**
     *  @author zhiguo.lin
     *        @DateTime 2016年8月12日 下午1:47:43
     *  @serverComment 上传体检照片
     *
     *  @param files        上传的文件
     */
    @RequestMapping(params = "uploadPhysicalPhoto", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson uploadPhysicalPhoto(@RequestParam
    MultipartFile[] pictures) {
        AjaxJson resObject = new AjaxJson();

        List<String> picPathList = null;

        try {
            picPathList = recordService.uploadPhoto(pictures);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (picPathList != null) {
            resObject.setObj(picPathList);
        }

        return resObject;
    }

    @RequestMapping(params = "deletePicture", method = RequestMethod.POST)
    @ResponseBody
    public void deletePicture(String path) {
        ImageUtilV2.delImg(path);
    }
}
