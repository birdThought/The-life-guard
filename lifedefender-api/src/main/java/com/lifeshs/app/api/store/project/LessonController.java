package com.lifeshs.app.api.store.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.constants.common.ReturnStatus;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.dto.manager.member.GetMemberByHXData;
import com.lifeshs.dto.manager.member.UpMemberbyHXData;
import com.lifeshs.dto.manager.serve.*;
import com.lifeshs.po.LessonPO;
import com.lifeshs.service1.member.IMemberService;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.service1.store.employee.IEmployeeService;
import com.lifeshs.utils.ListUtil;
import com.lifeshs.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康课堂
 * Created by dengfeng on 2017/7/5 0005.
 */
@RestController
@RequestMapping("mapp/project/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @Autowired
    IMemberService memberService;

    @Autowired
    IEmployeeService employeeService;

    /**
     * 获取健康课堂及成员信息
     * @return
     */
    @RequestMapping(value = "getLessonGroup", method = RequestMethod.POST)
    public @ResponseBody JSONObject getLessonGroup(SubmitDTO sumbitDTO, UpLessonGroupData lessonSumbitData) {
        String projectCode = lessonSumbitData.getProjectCode();
        if(StringUtil.isBlank(projectCode)){
            if(StringUtil.isBlank(lessonSumbitData.getHuanxinId()))
                return ReturnDataAgent.error("参数没有值");
            LessonPO lessonPO = lessonService.findLessonByHuanxinId(lessonSumbitData.getHuanxinId());
            if(lessonPO == null)
                return ReturnDataAgent.error("环信ID无效或没有相应课堂");
            projectCode = lessonPO.getCode();
        }
        LessonPO lessonPO = lessonService.getLesson(projectCode);
        GetLessonGroupData lessonGroupData = new GetLessonGroupData();
        lessonGroupData.setPhoto(lessonPO.getImage());
        lessonGroupData.setDescript(lessonPO.getIntroduce());
        lessonGroupData.setProjectCode(lessonPO.getCode());
        lessonGroupData.setProjectName(lessonPO.getName());
        lessonGroupData.setSilence(lessonPO.getSilence());
        List<LessonUserInfo> userList = memberService.findMemberListByLesson(projectCode);
        List<LessonUserInfo> employeeList = employeeService.findEmployeeListByLesson(projectCode);
        //按老接口格式转换一下
        List<LessonUserInfoTemp> groupList = new ArrayList<>();
        for(LessonUserInfo userInfo : userList){
            LessonUserInfoTemp userInfoTemp = new LessonUserInfoTemp();
            userInfoTemp.setEmail(userInfo.getEmail());
            userInfoTemp.setMobile(userInfo.getMobile());
            //姓名为空则填充用户名
            if(StringUtil.isBlank(userInfo.getRealName()))
                userInfoTemp.setName(userInfo.getUserName());
            else
                userInfoTemp.setName(userInfo.getRealName());
            userInfoTemp.setPhoto(userInfo.getPhoto());
            if(userInfo.isSex())
                userInfoTemp.setSex("1");
            else
                userInfoTemp.setSex("0");
            userInfoTemp.setUserCode(userInfo.getUserCode());
            userInfoTemp.setUserId(String.valueOf(userInfo.getUserId()));
            userInfoTemp.setUserType("2");
            groupList.add(userInfoTemp);
        }
        for(LessonUserInfo userInfo : employeeList){
            LessonUserInfoTemp userInfoTemp = new LessonUserInfoTemp();
            userInfoTemp.setEmail(userInfo.getEmail());
            userInfoTemp.setMobile(userInfo.getMobile());
            //姓名为空则填充用户名
            if(StringUtil.isBlank(userInfo.getRealName()))
                userInfoTemp.setName(userInfo.getUserName());
            else
                userInfoTemp.setName(userInfo.getRealName());
            userInfoTemp.setPhoto(userInfo.getPhoto());
            if(userInfo.isSex())
                userInfoTemp.setSex("1");
            else
                userInfoTemp.setSex("0");
            userInfoTemp.setUserCode(userInfo.getUserCode());
            userInfoTemp.setUserId(String.valueOf(userInfo.getUserId()));
            userInfoTemp.setUserType("1");
            groupList.add(userInfoTemp);
        }
        lessonGroupData.setGroupUsers(groupList);
        //---------以上，以后再改正回来，不要两种用户混在一起
        if(StringUtil.isNotEmpty(lessonPO.getLessonTime())){
            List<GetLessonGroupData.CourseTime> courseTime = new ArrayList<>();
            JSONArray data = JSONArray.parseArray(lessonPO.getLessonTime());
            for(int i=0;i<data.size();i++){
                JSONObject jobj =  (JSONObject) data.get(i);
                GetLessonGroupData.CourseTime ct = new GetLessonGroupData.CourseTime(jobj.getString("weeks"), jobj.getString("startTime"));
                courseTime.add(ct);
            }
            lessonGroupData.setCourseTime(courseTime);
        }
        return ReturnDataAgent.success(lessonGroupData);
    }

    /**
     * 禁言
     * @author dengfeng
     * @return
     */
    @RequestMapping(value = "gag", method = RequestMethod.POST)
    public JSONObject gag(SubmitDTO sumbitDTO, String projectCode) {

        boolean success = lessonService.gag(projectCode);
        if (!success) {
            return ReturnDataAgent.error(ReturnStatus.Fail);
        }
        return ReturnDataAgent.success();
    }

    /**
     * 解除禁言
     * @author dengfeng
     */
    @RequestMapping(value = "removeGag", method = RequestMethod.POST)
    public JSONObject removeGag(SubmitDTO sumbitDTO, String projectCode) {

        boolean success = lessonService.removeGag(projectCode);
        if (!success) {
            return ReturnDataAgent.error(ReturnStatus.Fail);
        }
        return ReturnDataAgent.success();
    }

    /**
     * 通过环信查询课堂(含用户)信息
     * @return
     */
    @RequestMapping(value = "getLessonUsersByHX", method = RequestMethod.POST)
    public JSONObject getLessonUsersByHX(SubmitDTO sumbitDTO, UpMemberbyHXData memberbyHXData) {
        int userId = sumbitDTO.getUser().getId();
        List<String> names = memberbyHXData.getHuanxinUserNames();
        List<String> huanxinGroupNames = memberbyHXData.getHuanxinGroupIds();

        GetLessonUsersByHXData lessonUsersByHXData = new GetLessonUsersByHXData();
        if (names != null && names.size() > 0) {
            names = ListUtil.removeRepeatElement(names, String.class);
            List<GetMemberByHXData> users = memberService.getUsersByHuanxinId(names);
            lessonUsersByHXData.setUserList(users);
        }
        if (huanxinGroupNames != null && huanxinGroupNames.size() > 0) {
            huanxinGroupNames = ListUtil.removeRepeatElement(huanxinGroupNames, String.class);
            List<GetLessonByHXData> lessons = lessonService.getLessonListByHX(huanxinGroupNames, userId);
            //userCount为用户的数量，还要加上服务师的，因为系统限定一个课堂只有一个服务师，所以直接+1，不用去表里统计了
            if(lessons != null && lessons.size() > 0){
                for(GetLessonByHXData lessonByHXData : lessons){
                    int userCount = Integer.parseInt(lessonByHXData.getUserCount())+1;
                    lessonByHXData.setUserCount(String.valueOf(userCount));
                }
            }
            lessonUsersByHXData.setLessonList(lessons);
        }
        return ReturnDataAgent.success(lessonUsersByHXData);
    }
}
