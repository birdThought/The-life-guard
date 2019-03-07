/*@ 废弃*/
/*
package com.lifeshs.controller.org.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.exception.org.AuthorityException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.org.TOrgServe;
import com.lifeshs.entity.org.TServe;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.org.OrgDTO;
import com.lifeshs.pojo.org.employee.OrgEmploy;
import com.lifeshs.pojo.org.group.CourseTimeDTO;
import com.lifeshs.pojo.org.group.LessonDTO;
import com.lifeshs.pojo.org.group.LessonGroupOrgUserDTO;
import com.lifeshs.pojo.org.server.OrgMemberBase;
import com.lifeshs.pojo.org.server.OrgMemberMessageDetailVO;
import com.lifeshs.pojo.org.server.OrgServeDTO;
import com.lifeshs.pojo.org.server.OrgServeRecord;
import com.lifeshs.pojo.org.server.OrgServer;
import com.lifeshs.pojo.org.server.OrgServerGroupBase;
import com.lifeshs.pojo.org.server.OrgServerGroupInfo;
import com.lifeshs.pojo.record.DietVO;
import com.lifeshs.pojo.record.MedicalVO;
import com.lifeshs.pojo.record.PhysicalsVO;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.org.lesson.ILessonGroup;
import com.lifeshs.utils.ImageUtil;
import com.lifeshs.utils.image.ImageUtilV2;

@RequestMapping("orgServeControl")
@Controller
public class OrgServeController extends BaseController {

    @Autowired
    IServiceOrgService serviceOrgService;

    @Autowired
    ILessonGroup lessonGroup;

    */

/**
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 服务管理页面
     */

/*

@RequestMapping(params = "serviceManage")
public ModelAndView serviceManage() {
 ModelAndView modelAndView = new ModelAndView("com/QYPart/serviceManage");
 LoginUser user = getLoginUser();
 OrgUserSharingData orgUserSharingData = getOrgUserSharingData();

 Integer orgId = user.getOrgId();
 if (user.getType() == 0)
     return null;
 List<OrgServer> serList = serviceOrgService.getOrgServiceListAndMemberCount(orgId,
         orgUserSharingData.getUserType() == 1 ? user.getId() : null);
 modelAndView.addObject("serList", serList);
 return modelAndView;
}

*/

/**
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 健康咨询页面
     * @param server
     *            服务的id
     */

/*

@RequestMapping(params = "healthAsk")
public ModelAndView healthAsk(@RequestParam int server) {
 ModelAndView modelAndView = new ModelAndView("com/QYPart/healthAsk");
 LoginUser loginUser = getLoginUser();
 OrgUserSharingData orgUserSharingData = getOrgUserSharingData();

 if (loginUser.getType() == 0)
     return null;
 Integer orgId = loginUser.getOrgId();

 List<OrgServerGroupBase> groupList = serviceOrgService.findGroupByKey(orgId, server,
         orgUserSharingData.getUserType() == 1 ? loginUser.getId() : null);
 modelAndView.addObject("groupList", groupList);
 if (!groupList.isEmpty()) {
     int groupId = groupList.get(0).getId();
     OrgServerGroupInfo firstGroupInfo = serviceOrgService.getGroupInfo(orgId, server, groupId);
     firstGroupInfo.setAdmin(orgUserSharingData.getRealName());
     firstGroupInfo.setAdminId(loginUser.getId());
     modelAndView.addObject("groupInfo", firstGroupInfo);
     List<OrgMemberBase> memberList = serviceOrgService.findAllUserInGroup(groupId, 1);
     modelAndView.addObject("memberList", memberList);
     int count = groupList.get(0).getMemberCount();
     count = (int) (count % 10 == 0 ? count / 10 : Math.floor(count / 10) + 1);
     modelAndView.addObject("groupPageCount", count);
 }
 modelAndView.addObject("serve", server);
 Map<String, Object> params = new HashMap<String, Object>();
 params.put("page", 1);
 params.put("dPage", 15);
 params.put("orgId", orgId);
 params.put("serverId", server);
 List<OrgServeRecord> historyRecord = serviceOrgService.findHistoryServeRecord(params);
 int rCount = serviceOrgService.getRecordCount(params);
 modelAndView.addObject("rCount", rCount % 15 == 0 ? rCount / 15 : (int) Math.floor(rCount / 15) + 1);
 modelAndView.addObject("historyRecord", historyRecord);

 modelAndView.addObject("orgServeId", groupList.get(0).getOrgServeId());
 modelAndView.addObject("nick", orgUserSharingData.getRealName());
 modelAndView.addObject("headUrl", Normal.PHOTO_PREFIX+orgUserSharingData.getPhotoPath());
 modelAndView.addObject("hxCode", loginUser.getUserCode());

 return modelAndView;
}

*/

/**
     * 获取群组里的管理员、服务师基本信息
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月9日
     * @serverComment
     */

/*

@RequestMapping(params = "getEmployInfo")
public @ResponseBody OrgEmploy getEmployInfo(String id) {
 LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0)
     return null;
 Integer orgId = loginUser.getOrgId();
 OrgEmploy employ = serviceOrgService.getUserInfo(orgId, Integer.parseInt(id));
 return employ;
}

*/

/**
     * 获取群组的基本信息和群组的人员
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月9日
     * @serverComment
     */

/*

@RequestMapping(params = "getGroupDatas")
public @ResponseBody AjaxJson getGroupDatas(String groupId, String serveTarget) {
 AjaxJson resJson = new AjaxJson();
 LoginUser loginUser = getLoginUser();
 OrgUserSharingData orgUserSharingData = getOrgUserSharingData();

 Integer orgId = loginUser.getOrgId();
 if (loginUser.getType() == 0)
     return null;
 OrgServerGroupInfo groupInfo = serviceOrgService.getGroupInfo(orgId, Integer.parseInt(serveTarget),
         Integer.parseInt(groupId));
 groupInfo.setAdmin(orgUserSharingData.getRealName());
 groupInfo.setAdminId(loginUser.getId());
 List<OrgMemberBase> memberList = serviceOrgService.findAllUserInGroup(Integer.parseInt(groupId), 1);
 Map<String, Object> results = new HashMap<String, Object>();

 results.put("groupInfo", groupInfo);
 results.put("memberList", memberList);
 resJson.setAttributes(results);
 resJson.setSuccess(true);
 return resJson;
}

*/

/**
     * 获取添加群组下的服务师以及管理的群数量
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月19日
     * @serverComment
     */

/*

@RequestMapping(params = "getAddGroupCondition")
public @ResponseBody AjaxJson getAddGroupCondition() {
 AjaxJson resJson = new AjaxJson();
 LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0)
     return null;
 Integer orgId = loginUser.getOrgId();
 List<OrgEmploy> emList = serviceOrgService.getOrgServersWithGroupCount(orgId);
 resJson.setObj(emList);
 return resJson;
}

*/

/**
     * 添加群组实现
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月21日
     * @serverComment
     */

/*

@RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
@RequestMapping(params = "addGroup")
public @ResponseBody AjaxJson addGroup(@RequestParam int serveId, @RequestBody Map<String, Object> params) {
 AjaxJson resJson = new AjaxJson();
 LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0)
     return null;
 Integer orgId = loginUser.getOrgId();
 params.put("defaultGroup", false);
 resJson.setObj(serviceOrgService.addGroup(orgId, serveId, params, null));
 return resJson;
}

*/

/**
     * 获取编辑群组里已管理该群组的服务师
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月21日
     * @serverComment
     */

/*

@RequestMapping(params = "getEditGroupCondition")
public @ResponseBody AjaxJson getEditGroupCondition(@RequestBody Map<String, Object> condition) {

 AjaxJson resJson = new AjaxJson();
 Map<String, Object> result = new HashMap<String, Object>();
 result.put("servers",
         serviceOrgService.getControlGroupServer(Integer.parseInt((String) condition.get("groupId"))));
 if (condition.containsKey("noServerCache")) {// 如果页面没有机构服务师的缓存
     LoginUser loginUser = getLoginUser();
     Integer orgId = loginUser.getOrgId();
     List<OrgEmploy> emList = serviceOrgService.getOrgServersWithGroupCount(orgId);
     result.put("emList", emList);
 }
 resJson.setAttributes(result);
 return resJson;
}

@RequestMapping(params = "editGroup")
@RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
public @ResponseBody AjaxJson editGroup(@RequestBody Map<String, Object> params) {
 AjaxJson resJson = new AjaxJson();
 serviceOrgService.editGroup(params);
 return resJson;
}

*/

/**
     * 获取群组用户的详细信息
     *
     * @author yuhang.weng
     * @DateTime 2016年9月19日 上午11:26:01
     *
     * @param userId
     * @param groupId
     * @return
     * @throws AuthorityException
     */

/*

@RequestMapping(params = "getMemberDetailMessage", method = RequestMethod.GET)
public @ResponseBody AjaxJson getMemberDetailMessage(Integer userId, Integer groupId, Integer mode) {
 AjaxJson resObject = new AjaxJson();
 System.out.println("inter");
 LoginUser user = getLoginUser();
 Integer orgId = user.getOrgId();

 serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);

 OrgMemberMessageDetailVO detailVo = serviceOrgService.getMemberMessageDetail(userId, groupId, mode);
 resObject.setObj(detailVo);
 System.out.println("detailVo" + detailVo.toString());
 return resObject;
}

*/

/**
     * 按测量日期获取病历数据
     *
     * @author yuhang.weng
     * @DateTime 2016年9月20日 上午9:14:50
     *
     * @param userId
     *            会员ID
     * @param visitingDate
     *            测量日期
     * @return
     * @throws AuthorityException
     */

/*

@RequestMapping(params = "getMemberMedicalDataByVisitingDate", method = RequestMethod.GET)
public @ResponseBody AjaxJson getMemberMedicalDataByVisitingDate(Integer userId, String visitingDate) {
 AjaxJson resObject = new AjaxJson();

 LoginUser user = getLoginUser();
 Integer orgId = user.getOrgId();

 serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);

 List<MedicalVO> medicals = serviceOrgService.getMemberMedicalDataSplitByVisitingDate(userId, visitingDate);

 resObject.setObj(medicals);

 return resObject;
}

*/

/**
     * 按测量日期获取体检数据
     *
     * @author yuhang.weng
     * @DateTime 2016年9月20日 下午12:02:43
     *
     * @param userId
     * @param physicalsDate
     * @return
     */

/*

@RequestMapping(params = "getMemberPhysicalDataByPhysicalsDate", method = RequestMethod.GET)
public @ResponseBody AjaxJson getMemberPhysicalDataByPhysicalsDate(Integer userId, String physicalsDate) {
 AjaxJson resObject = new AjaxJson();

 LoginUser user = getLoginUser();
 Integer orgId = user.getOrgId();

 serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);

 List<PhysicalsVO> physicals = serviceOrgService.getPhysicalDataSplitByPhysicalsDate(userId, physicalsDate);

 resObject.setObj(physicals);

 return resObject;
}

*/

/**
     * 按测量日期获取饮食数据
     *
     * @author yuhang.weng
     * @DateTime 2016年9月20日 下午3:42:41
     *
     * @param userId
     * @param recordDate
     * @return
     */

/*

@RequestMapping(params = "getMemberDietDataByRecordDate", method = RequestMethod.GET)
public @ResponseBody AjaxJson getMemberDietDataByRecordDate(Integer userId, String recordDate) {
 AjaxJson resObject = new AjaxJson();

 LoginUser user = getLoginUser();
 Integer orgId = user.getOrgId();

 serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);

 List<DietVO> diets = serviceOrgService.getDietDataSplitByRecordDate(userId, recordDate);

 resObject.setObj(diets);

 return resObject;
}

*/

/**
     * 按日期获取会员健康数据
     *
     * @author yuhang.weng
     * @DateTime 2016年9月18日 下午1:46:50
     *
     * @param userId
     * @param measureDate
     * @return
     */

/*

@RequestMapping(params = "getMemberHealthDataByMeasureDate", method = RequestMethod.GET)
public @ResponseBody AjaxJson getMemberHealthData(Integer userId, String measureDate) {
 AjaxJson resObject = new AjaxJson();

 LoginUser user = getLoginUser();
 Integer orgId = user.getOrgId();

 serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);

 Map<String, Object> data = serviceOrgService.getMemberHealthDataSplitByMeasureDate(userId, measureDate);

 resObject.setAttributes(data);
 return resObject;
}

*/

/**
     * 获取群组的组员的翻页列表数据信息
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月9日
     * @serverComment
     */

/*

@RequestMapping(params = "getMemberList")
public @ResponseBody List<OrgMemberBase> getMemberList(String groupId, String page) {
 List<OrgMemberBase> memberList = serviceOrgService.findAllUserInGroup(Integer.parseInt(groupId),
         Integer.parseInt(page));
 return memberList;
}

*/

/**
     * 翻页查询服务历史记录
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */

/*

@RequestMapping(params = "getServiceHistoryRecord")
public @ResponseBody AjaxJson getServiceHistoryRecord(@RequestBody Map<String, Object> params) {
 AjaxJson resJson = new AjaxJson();
 LoginUser loginUser = getLoginUser();
 Integer orgId = loginUser.getOrgId();
 params.put("orgId", orgId);
 params.put("dPage", 15);
 List<OrgServeRecord> historyRecord = serviceOrgService.findHistoryServeRecord(params);
 int rCount = serviceOrgService.getRecordCount(params);
 Map<String, Object> map = new HashMap<String, Object>();
 map.put("data", historyRecord);
 map.put("count", rCount);
 resJson.setAttributes(map);
 return resJson;
}

*/

/**
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月5日
     * @serverComment 服务页面
     */

/*

@RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
@RequestMapping(params = "service")
public ModelAndView servicePage() {
 LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0)
     return null;
 Integer orgId = loginUser.getOrgId();
 ModelAndView modelAndView = new ModelAndView("com/QYPart/service");
 List<TServe> serList = serviceOrgService.getServeDatas(orgId);
 modelAndView.addObject("serList", serList);
 return modelAndView;
}

*/

/**
     * 定制服务
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */

/*

@RequestMapping(params = "orderService")
@RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
public @ResponseBody AjaxJson orderService(@RequestBody TOrgServe serve) {
 AjaxJson resJson = new AjaxJson();
 LoginUser loginUser = getLoginUser();
 OrgUserSharingData orgUserSharingData = getOrgUserSharingData();

 Integer orgId = loginUser.getOrgId();
 if (loginUser.getType() == 0)
     return null;
 String createrName = orgUserSharingData.getRealName();

 serviceOrgService.openOrderService(serve, createrName, orgId);
 resJson.setSuccess(true);
 return resJson;
}

*/

/**
     * 获取已定制的服务详情信息
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月30日
     * @serverComment
     */

/*

@RequestMapping(params = "getOrdeServiceMsg")
public @ResponseBody AjaxJson getOrdeServiceMsg(@RequestParam int serId) {
 AjaxJson resJson = new AjaxJson();
 LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0)
     return null;
 TOrgServe serve = serviceOrgService.getOrderServiceDetail(loginUser.getOrgId(), serId);
 resJson.setObj(serve);
 return resJson;
}

*/

/**
     * 修改价格的定制服务
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月30日
     * @serverComment
     */

/*

@RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
@RequestMapping(params = "updateOrgSerivce")
public @ResponseBody AjaxJson updateOrgSerivce(@RequestBody TOrgServe orgserve) {
 AjaxJson resJson = new AjaxJson();
 LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0)
     return null;
 serviceOrgService.updateOrderServiceDetail(loginUser.getOrgId(), orgserve);
 return resJson;
}

*/

/**
     * 移动群组
     *
     * @return
     */

/*

@RequestMapping(params = "moveGroup")
public @ResponseBody AjaxJson moveGroup(@RequestBody Map<String, Object> params) {
 LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0)
     return null;
 AjaxJson ajaxJson = new AjaxJson();
 ajaxJson.setSuccess(serviceOrgService.moveGroup(params, loginUser.getOrgId()));
 return ajaxJson;
}

*/

/**
     * 删除群组
     *
     * @param params
     * @return
     */

/*

@RequestMapping(params = "delGroup")
@RequiresPermissions(value = { "user:0", "user:2" }, logical = Logical.OR)
public @ResponseBody AjaxJson delGroup(@RequestBody Map<String, Object> params) {
 AjaxJson ajaxJson = new AjaxJson();
 LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0)
     return null;
 ajaxJson.setObj(serviceOrgService.delGroup(params, loginUser.getOrgId(), false));
 return ajaxJson;
}

*/

/**
     * @author wenxian.cai
     * @DateTime 2017年2月28日下午3:24:07
     * @serverComment 进入健康课堂列表界面
     * @param
     */

/*

@RequestMapping(params = "healthLessons")
public ModelAndView healthLessons(@RequestParam Integer orgServeId) {
     ModelAndView modelAndView = new ModelAndView("com/QYPart/healthLesson/healthLessons");
     LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0) {
         return null;
 }
     List<LessonDTO> lessonDTOs = lessonGroup.listServeLesson(orgServeId);
     String jsonStr = JSONObject.toJSON(lessonDTOs).toString();
     modelAndView.addObject("lessons", jsonStr);
     modelAndView.addObject("userId", loginUser.getId());
     return modelAndView;
}

*/

/**
     * @author wenxian.cai
     * @DateTime 2017年3月1日下午4:16:26
     * @serverComment 进入健康课堂群聊界面
     * @param
     */

/*

@RequestMapping(params = "healthLessonChat", method = RequestMethod.GET)
public ModelAndView healthLessonChat(@RequestParam Integer groupId) {
     ModelAndView modelAndView = new ModelAndView("com/QYPart/healthLesson/healthLessonChat");
     LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0) {
         return null;
 }

 Integer orgServeId = lessonGroup.getLesson(groupId).getOrgServeId();
//        Integer orgServeId = serviceOrgService.getOrderServiceDetail(loginUser.getOrgId(), 4).getId();
 OrgUserSharingData memberSharingData = getOrgUserSharingData();
 List<LessonDTO> lessonDTOs = lessonGroup.listServeLesson(orgServeId);
 List<LessonDTO> lessons = new ArrayList<>();
 for (LessonDTO lesson : lessonDTOs) {
         List<LessonGroupOrgUserDTO> list = lesson.getOrgUsers();
         for (LessonGroupOrgUserDTO lessonGroupOrgUserDTO : list) {
                 if (lessonGroupOrgUserDTO.getId() == loginUser.getId()) {
                         */

/*lesson.setCourseTime(null);
                                lesson.setCreatorId(null);
                                lesson.setDefaultGroup(null);
                                lesson.setDescription(null);
                                lesson.setOrgServeId(null);
                                lesson.setOrgUsers(null);
                                lesson.setUserOrders(null);*/

/*

lessons.add(lesson);
break;
}
}
}
modelAndView.addObject("remain", "1次");
modelAndView.addObject("targetServer", groupId);
modelAndView.addObject("orgServeId", orgServeId);
modelAndView.addObject("headUrl", Normal.PHOTO_PREFIX + memberSharingData.getPhotoPath());
modelAndView.addObject("nick", memberSharingData.getRealName());
modelAndView.addObject("hxCode", loginUser.getUserCode());
modelAndView.addObject("userId", loginUser.getId());
modelAndView.addObject("lesson", JSON.toJSON(lessons));
return modelAndView;
}

*/

/**
     * @author wenxian.cai
     * @DateTime 2017年3月6日下午3:26:41
     * @serverComment 获取课堂信息
     * @param groupId
     */

/*

@RequestMapping(params = "getHealthLesson", method = RequestMethod.POST)
@ResponseBody
public AjaxJson getHealthLesson(@RequestParam(value = "groupId") int groupId) {
     AjaxJson ajaxJson = new AjaxJson();
     LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0) {
         return null;
 }
 LessonDTO lessonDTO = lessonGroup.getLesson(groupId);
 if (lessonDTO == null) {
         ajaxJson.setSuccess(false);
         ajaxJson.setMsg("获取课堂信息失败");
         return ajaxJson;
         }
 ajaxJson.setObj(lessonDTO);
 return ajaxJson;

}

*/

/**
     * @author wenxian.cai
     * @DateTime 2017年3月1日下午4:20:16
     * @serverComment 添加健康课堂
     * @param
     */

/*

@RequestMapping(params = "addHealthLesson")
@ResponseBody
public AjaxJson addHealthLesson(@RequestBody Map<String, Object> map) {

     AjaxJson reObject = new AjaxJson();
     LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0) {
         return null;
 }
 String name = (String) map.get("name");
 String description = (String) map.get("description");
 List<CourseTimeDTO> lessonTimeDTO = (List<CourseTimeDTO>) map.get("lessonTimeDTO");

 Integer orgServeId = null;
 OrgDTO org = serviceOrgService.getOrg(loginUser.getOrgId());
 for (OrgServeDTO orgServeDTO : org.getOrgServes()) {
     if ("02".equals(orgServeDTO.getServe().getCode())) {
         orgServeId = orgServeDTO.getServe().getId();
         break;
     }
 }

//        int orgServeId = serviceOrgService.getOrderServiceDetail(loginUser.getOrgId(), 4).getId();
 String relativePath = (String) map.get("relativePath");
 // 将图片从tmp目录转移到upload下
          String newRelativePath = null;
          if (StringUtils.isNotBlank(relativePath)) {
             try {
              newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(relativePath, "head");
          } catch (IOException e) {
              reObject.setMsg("创建健康课堂失失败");
              reObject.setSuccess(false);
              return reObject;
          }
          }
 LessonDTO lesson = new LessonDTO();
 lesson.setCourseTime(lessonTimeDTO);
 lesson.setDefaultGroup(false);
 lesson.setName(name);
 lesson.setOrgServeId(orgServeId);
 lesson.setDescription(description);
 lesson.setCreatorId(loginUser.getId());
 lesson.setPhoto(newRelativePath);
 lessonGroup.addLesson(lesson, loginUser.getId(), loginUser.getUserCode());
 // 删除旧头像
 ImageUtilV2.delImg(relativePath);
 return reObject;
}

*/

/**
     * @author wenxian.cai
     * @DateTime 2017年3月2日上午10:43:05
     * @serverComment 删除健康课堂
     * @param id 群聊Id
     */

/*

@RequestMapping(params = "deleteHealthLesson")
@ResponseBody
public AjaxJson deleteHealthLesson(@RequestParam(value = "id") int id) {
     AjaxJson reObject = new AjaxJson();
     String msg = "删除失败";
     reObject.setMsg(msg);
     reObject.setSuccess(false);
     boolean bool =lessonGroup.deleteLesson(id);
     if (bool) {
             msg = "删除成功";
                 reObject.setMsg(msg);
                 reObject.setSuccess(true);
         }
     return reObject;
}

*/

/**
     * @author wenxian.cai
     * @DateTime 2017年3月2日下午3:48:44
     * @serverComment 修改课堂
     * @param
     */

/*

@SuppressWarnings("unchecked")
 @RequestMapping(params = "updateHealthLesson")
@ResponseBody
public AjaxJson updateHealthLesson(@RequestBody Map<String, Object> map) {

     AjaxJson reObject = new AjaxJson();
     LoginUser loginUser = getLoginUser();
 if (loginUser.getType() == 0) {
         return null;
 }
 int id = (int) map.get("id");
 String name = (String) map.get("name");
 String description = (String) map.get("description");
 boolean silence = (boolean) map.get("silence");
 String relativePath = (String) map.get("relativePath");
 String newRelativePath = null;
 if (!relativePath.equals("")) {        //图片改变
          // 将图片从tmp目录转移到upload下
               try {
                       newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(relativePath, "head");
               } catch (IOException e) {
                       reObject.setMsg("修改健康课堂失失败");
                       reObject.setSuccess(false);
                       return reObject;
               }
 }
 List<CourseTimeDTO> lessonTimeDTO = (List<CourseTimeDTO>) map.get("lessonTimeDTO");

 LessonDTO lesson = new LessonDTO();
 lesson.setId(id);
 lesson.setCourseTime(lessonTimeDTO);
 lesson.setName(name);
 lesson.setDescription(description);
 lesson.setSilence(silence);
 if (newRelativePath != null) {
         lesson.setPhoto(newRelativePath);
         }
 lessonGroup.updateLesson(lesson);

     return reObject;
}

 */

/**
         * @author wenxian.cai
         * @DateTime 2017年3月4日上午11:20:07
         * @serverComment 群组禁言(让群组向每个群成员发送一条信息,成员根据信息状态判断是否处于禁言状态);
         * @param groupId 群组ID
         */

/*

@RequestMapping(params = "bindSilented")
@ResponseBody
public AjaxJson bindSilented(@RequestParam(value = "groupId")int groupId) {
 AjaxJson reObject = new AjaxJson();
 boolean bool = lessonGroup.gag(groupId);
 if (!bool) {
             reObject.setSuccess(false);
             reObject.setMsg("操作失败");
     }
 return reObject;
}

*/

/**
         * @author wenxian.cai
         * @DateTime 2017年3月4日上午11:29:50
         * @serverComment 群组移除禁言(让群组向每个群成员发送一条信息,成员根据信息状态判断是否处于禁言状态);
         * @param groupId 群组ID
         */

/*

@RequestMapping(params = "unbindSilented")
@ResponseBody
public AjaxJson unbindSilented(@RequestParam(value = "groupId")int groupId) {
 AjaxJson reObject = new AjaxJson();
 boolean bool = lessonGroup.removeGag(groupId);
 if (!bool) {
             reObject.setSuccess(false);
             reObject.setMsg("操作失败");
     }
 return reObject;
}

*/

/**
         * @author wenxian.cai
         * @DateTime 2017年3月7日上午9:49:36
         * @serverComment 更新群组服务师
         * @param addOrgUserIds 需添加服务师集合
         * @param delOrgUserIds 需删除服务师集合
         */

/*

@RequestMapping(params = "updateOrgUser")
@ResponseBody
@Transactional
public AjaxJson updateOrgUser(@RequestParam(value = "groupId")int groupId,
         @RequestParam(value = "addOrgUserIds[]", required = false)List<Integer> addOrgUserIds,
         @RequestParam(value = "delOrgUserIds[]", required = false)List<Integer> delOrgUserIds) {
 AjaxJson reObject = new AjaxJson();
 boolean bool = true;
 if (addOrgUserIds != null) {
         bool = lessonGroup.addOrgUser(groupId, addOrgUserIds);
 }
 if (delOrgUserIds != null) {
             bool = lessonGroup.removeOrgUser(groupId, delOrgUserIds);
     }
 if (!bool) {
             reObject.setSuccess(false);
             reObject.setMsg("操作失败");
             return reObject;
     }
 return reObject;
}
}
*/
