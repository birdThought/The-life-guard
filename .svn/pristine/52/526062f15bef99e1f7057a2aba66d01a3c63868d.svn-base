package com.lifeshs.controller.app.h5;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.pojo.information.InformationCollectDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.paper.PaperDTO;
import com.lifeshs.pojo.paper.PaperOptionDTO;
import com.lifeshs.pojo.paper.PaperPhysicalStandardDTO;
import com.lifeshs.pojo.paper.PaperStrokeStandardDTO;
import com.lifeshs.pojo.paper.PaperSubHealthStandardDTO;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.paper.IPaperService;
import com.lifeshs.service.terminal.IWebIndexService;


/**
 * Created by XuZhanSi on 2017/2/20 0020.
 */
@Controller
@RequestMapping("/app/appweb")
public class APPWebController extends BaseController {
    @Autowired
    InformationService informationService;
    @Autowired
    IWebIndexService webIndexService;
    @Autowired
    IPaperService question;
    @Autowired
    IMemberService memberService;

    /**
     * 移动端资讯
     *
     * @return
     */
    @RequestMapping(params = "informationIndex")
    public ModelAndView informationIndex() {
        ModelAndView modelAndView = new ModelAndView("mobile/index/newsApp");
        List<TInfomationColumn> columns = webIndexService.loadInformationColumns(
                "资讯信息");
        modelAndView.addObject("columns", columns);

        // modelAndView.addObject("infoList",columns==null?null:webIndexService.getInformationsByPage(columns.get(0).getId(),1,10));
        return modelAndView;
    }

    /**
     * 获取资讯列表
     *
     * @param f
     *            分类column
     * @param page
     *            当前页数
     * @return
     */
    @RequestMapping(params = "getInformationDatas")
    public @ResponseBody
    AjaxJson getInformationList(@RequestParam
    Integer f, @RequestParam
    Integer page) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(webIndexService.getInformationsByPage(f, page, 10));

        return ajaxJson;
    }

    /**
     * 浏览一个资讯
     *
     * @param id
     *            资讯id
     * @return
     */
    @RequestMapping(params = "informationInfo")
    public ModelAndView informationInfo(@RequestParam
    Integer id) {
        ModelAndView modelAndView = new ModelAndView("mobile/index/newsAppInfo");
        DataResult result = informationService.lookNewsById(id);
        modelAndView.addObject("info", result.get("info"));
        modelAndView.addObject("columnName", result.get("columnName"));
        modelAndView.addObject("hot", result.get("hot"));
        modelAndView.addObject("hangye", result.get("hangye"));

        return modelAndView;
    }

    /**
     * 浏览一个套餐详情
     *
     * @param id 资讯id
     * @return
     */
    @RequestMapping(params = "comboDetail")
    public ModelAndView comboDetail(@RequestParam
    Integer id) {
        ModelAndView modelAndView = new ModelAndView("mobile/index/comboDetail");
        DataResult result = informationService.lookNewsById(id);
        modelAndView.addObject("info", result.get("info"));
        return modelAndView;
    }
    
    /**
     * @Description: 添加收藏
     * @author: wenxian.cai
     * @create: 2017/5/2 11:13
     */
    @RequestMapping(params = "addInformationCollect")
    public @ResponseBody
    AjaxJson addInformationCollect(@RequestParam
    Integer informationId) {
        AjaxJson ajaxJson = new AjaxJson();
        Integer userId = null;

        try {
            userId = getLoginUser().getId();
        } catch (Exception e) {
        }

        InformationCollectDTO collect = new InformationCollectDTO();
        collect.setInformationId(informationId);
        collect.setUserId(userId);

        boolean bool = informationService.addInformationCollect(collect);

        if (!bool) {
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }

        return ajaxJson;
    }

    /**
     * @Description: 公益课堂
     * @Author: wenxian.cai
     * @Date: 2017/7/18 9:53
     */
    @RequestMapping(params = "publiclesson")
    public ModelAndView publicLesson() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/publiclesson/public-lesson");

        return modelAndView;
    }

    /**
     * @Description: 中医体质问卷
     * @Author: wenxian.cai
     * @Date: 2017/7/18 10:04
     */
    @RequestMapping(value = "physical-paper")
    public ModelAndView physicalPaper(ServletRequest request, ServletResponse response) {
        if (request.getParameter("userId") != null) {
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            UserRecordDTO userRecordDTO = memberService.getRecord(userId);
            try {
                String result = userRecordDTO.getCorporeityResult();
                String score = userRecordDTO.getCorporeityScore();
                result = URLEncoder.encode(result);
                if (score != null) {
                    WebUtils.issueRedirect(request, response, "/app/appweb/physical-analyze?result=" + result + "&scoreArr=" + score);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ModelAndView modelAndView = new ModelAndView(
                "mobile/paper/physical-paper");
        List<PaperDTO> questionnaireDTOs = question.listPaper("体质");
        List<PaperOptionDTO> optionDTOs = question.listPaperOption("体质");
        modelAndView.addObject("paper", JSON.toJSON(questionnaireDTOs));
        modelAndView.addObject("paperOption", JSON.toJSON(optionDTOs));

        return modelAndView;
    }

    /**
     * 体质结果分析界面
     * @param physicalName
     * @param scoreArr
     * @return
     */
    @RequestMapping(value = "physical-analyze")
    public ModelAndView physicalAnalyze(@RequestParam(value = "result") String physicalName,
                                        @RequestParam(value = "scoreArr")
    List<Integer> scoreArr) {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/paper/physical-analyze");

        PaperPhysicalStandardDTO physicalStandardDTO = question.getPaperPhysicalStandard(physicalName);
        modelAndView.addObject("physicalStandard",
            JSON.toJSON(physicalStandardDTO));
        modelAndView.addObject("scoreArr", scoreArr);

        return modelAndView;
    }

    /**
     * @Description: 亚健康测试页面
     * @Author: wenxian.cai
     * @Date: 2017/7/18 14:32
     */
    @RequestMapping(value = "subhealth-paper")
    public ModelAndView subHealth(ServletRequest request, ServletResponse response) {
        if (request.getParameter("userId") != null) {
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            UserRecordDTO userRecordDTO = memberService.getRecord(userId);
            try {
                int score = userRecordDTO.getSubHealthSymptomScore();
                WebUtils.issueRedirect(request, response, "/app/appweb/subhealth-analyze?score=" + score);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("mobile/paper/subhealth-paper");
    }

    /**
     * @Description: 亚健康分析页面
     * @Author: wenxian.cai
     * @Date: 2017/7/18 15:10
     */
    @RequestMapping(value = "subhealth-analyze")
    public ModelAndView subHealthAnalyze(@RequestParam(value = "score") Integer score) {
        ModelAndView modelAndView = new ModelAndView("mobile/paper/subhealth-analyze");
        PaperSubHealthStandardDTO subHealthStandardDTO = question.getPaperSubHealthStandard(score);
        int points = -1;

        switch (subHealthStandardDTO.getIntervalNumber()) {
        case 1:
            points = 0;

            break;

        case 2:
            points = 2;

            break;

        case 3:
            points = 6;

            break;

        case 4:
            points = 10;

            break;

        case 5:
            points = 15;

            break;

        default:
            break;
        }

        modelAndView.addObject("subHealthStandard",
            JSON.toJSON(subHealthStandardDTO));
        modelAndView.addObject("score", score);
        modelAndView.addObject("points", points);

        return modelAndView;
    }

    /**
     * 中风风险测试
     * @return
     */
    @RequestMapping(value = "stroke-paper")
    public ModelAndView strokePaper(ServletRequest request, ServletResponse response) {
        if (request.getParameter("userId") != null) {
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            UserRecordDTO userRecordDTO = memberService.getRecord(userId);
            try {
                String result = null;
                int score = userRecordDTO.getStrokeRiskScore();
                if (score > 0) {
                    result = "中危";
                    if (score > 2) {
                        result = "高危";
                    }
                } else {
                    result = "低危";
                }
                result = URLEncoder.encode(result);
                WebUtils.issueRedirect(request, response, "/app/appweb/stroke-analyze?result="+ result +"&score=" + score);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ModelAndView modelAndView = new ModelAndView(
                "mobile/paper/stroke-paper");
        List<PaperDTO> questionnaireDTOs = question.listPaper("中风");
        List<PaperOptionDTO> optionDTOs = question.listPaperOption("中风");
        modelAndView.addObject("paper", JSON.toJSON(questionnaireDTOs));
        modelAndView.addObject("paperOption", JSON.toJSON(optionDTOs));

        return modelAndView;
    }

    /**
     * 中风分析
     * @param result
     * @param score
     * @return
     */
    @RequestMapping(value = "stroke-analyze")
    public ModelAndView strokeAnalyze(@RequestParam(value = "result") String result,
                                      @RequestParam(value = "score") String score) {
        ModelAndView modelAndView = new ModelAndView("mobile/paper/stroke-analyze");
        PaperStrokeStandardDTO subHealthStandardDTO = question.getPaperStrokeStandard(result);
        modelAndView.addObject("score", score);
        modelAndView.addObject("subHealthStandard",
            JSON.toJSON(subHealthStandardDTO));

        return modelAndView;
    }

    /**
     * app注册调用地图
     * @param address
     * @return
     */
    @RequestMapping(value = "register-map")
    public ModelAndView registerMap(
        @RequestParam(value = "address")
    String address) {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/register/register-map");
        modelAndView.addObject("address", address);

        return modelAndView;
    }

    /**
     * 操作说明-肺活仪
     * author: wenxian.cai
     * date: 2017/8/17 15:31
     */
    @RequestMapping(value = "operate-lunginstrument")
    public ModelAndView lunginstrument() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-lunginstrument");

        return modelAndView;
    }

    /**
     * 操作说明-心率手环
     * author: wenxian.cai
     * date: 2017/8/17 16:01
     */
    @RequestMapping(value = "operate-band")
    public ModelAndView band() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-band");

        return modelAndView;
    }

    /**
     * 血脂仪
     * author: wenxian.cai
     * date: 2017/8/17 16:00
     */
    @RequestMapping(value = "operate-bloodlipid")
    public ModelAndView bloodlipid() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-bloodlipid");

        return modelAndView;
    }

    /**
     * 操作说明-血压计
     * author: wenxian.cai
     * date: 2017/8/17 16:00
     */
    @RequestMapping(value = "operate-bloodpressure")
    public ModelAndView bloodpressure() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-bloodpressure");

        return modelAndView;
    }

    /**
     * 操作说明-体脂秤
     * author: wenxian.cai
     * date: 2017/8/17 16:00
     */
    @RequestMapping(value = "operate-bodyfatscale")
    public ModelAndView bodyfatscale() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-bodyfatscale");

        return modelAndView;
    }

    /**
     * 操作说明-血糖分析仪
     * author: wenxian.cai
     * date: 2017/8/17 15:59
     */
    @RequestMapping(value = "operate-glucometer")
    public ModelAndView glucometer() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-glucometer");

        return modelAndView;
    }

    /**
     * 操作说明-血氧分析仪
     * author: wenxian.cai
     * date: 2017/8/17 15:59
     */
    @RequestMapping(value = "operate-oxygen")
    public ModelAndView oxygen() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-oxygen");

        return modelAndView;
    }

    /**
     * 操作说明-体温计
     * author: wenxian.cai
     * date: 2017/8/17 15:59
     */
    @RequestMapping(value = "operate-temperate")
    public ModelAndView temperate() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-temperate");

        return modelAndView;
    }

    /**
     * 操作说明-尿酸分析仪
     * author: wenxian.cai
     * date: 2017/8/17 15:59
     */
    @RequestMapping(value = "operate-ua")
    public ModelAndView ua() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-ua");

        return modelAndView;
    }

    /**
     * 操作说明-尿液分析仪
     * author: wenxian.cai
     * date: 2017/8/17 15:57
     */
    @RequestMapping(value = "operate-uran")
    public ModelAndView uran() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/operateinstruction/operate-uran");

        return modelAndView;
    }

    @RequestMapping(value = {"download", "download/user"})
    public ModelAndView downloadUser() {
        ModelAndView modelAndView = new ModelAndView("mobile/download/user");

        return modelAndView;
    }

    @RequestMapping(value = "download/org")
    public ModelAndView downloadOrg() {
        ModelAndView modelAndView = new ModelAndView("mobile/download/org");

        return modelAndView;
    }

    @RequestMapping(value = "public-lesson-2")
    public ModelAndView publicLesson2() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/publiclesson/v2.4.0/index");

        return modelAndView;
    }

    @RequestMapping(value = "public-lesson-2/hot")
    public ModelAndView publicLessonHot() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/publiclesson/v2.4.0/hot-class");

        return modelAndView;
    }

    @RequestMapping(value = "public-lesson-2/hot2")
    public ModelAndView publicLessonHot2() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/publiclesson/v2.4.0/hot-list");

        return modelAndView;
    }

    @RequestMapping(value = "public-lesson-2/hot-class-details")
    public ModelAndView hotClassDetails() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/publiclesson/v2.4.0/hot-class-details");

        return modelAndView;
    }

    @RequestMapping(value = "public-lesson-2/health-baoyin")
    public ModelAndView healthBaoYin() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/publiclesson/v2.4.0/health-baoyin");

        return modelAndView;
    }

    @RequestMapping(value = "public-lesson-2/health-hongyuantang")
    public ModelAndView healthHongYuanTang() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/publiclesson/v2.4.0/health-hongyuantang");

        return modelAndView;
    }

    @RequestMapping(value = "public-lesson-2/health-wangyuantong")
    public ModelAndView healthWangYuanTong() {
        ModelAndView modelAndView = new ModelAndView(
                "mobile/publiclesson/v2.4.0/health-wangyuantong");

        return modelAndView;
    }
}
