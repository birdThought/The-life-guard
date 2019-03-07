package com.lifeshs.app.api.store;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.PushMsgType;
import com.lifeshs.common.constants.common.PushSendType;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.dto.manager.UpPagingData;
import com.lifeshs.dto.manager.UpSearchPagingData;
import com.lifeshs.dto.manager.member.GetMemberListData;
import com.lifeshs.service1.util.HealthTypeAnalysis;
import com.lifeshs.vo.FullUserVO;
import com.lifeshs.vo.WarningUserVO;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.push.PushMessagePO;
import com.lifeshs.po.push.PushTaskMessagePo;
import com.lifeshs.service1.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 与门店相关的会员业务控制器，member与order的区别是主视角在哪边
 * Created by dengfeng on 2017/6/21 0021.
 */
@RestController
@RequestMapping("mapp/member")
public class MemberController {
    @Autowired
    IMemberService memberService;

    /**
     * 得到异常信息用户列表
     *
     * @return
     */
    @RequestMapping(value = "getWarningUserList", method = RequestMethod.POST)
    public JSONObject getWarningUserList(SubmitDTO submitDTO, UpPagingData upPagingData) {
        TOrgUser orgUser = submitDTO.getUser();
        int pageIndex = upPagingData.getPageIndex();
        int pageSize = upPagingData.getPageSize();
        List<WarningUserVO> members;
        //用户类型:管理员_0,服务师_1,都有_2
        if (orgUser.getUserType() == 1) {
            members = memberService.findWainingMemberListByEmployee(orgUser.getId(), pageIndex, pageSize);
        } else {
            members = memberService.findWainingMemberListByStore(orgUser.getOrgId(), pageIndex, pageSize);
        }
        for (WarningUserVO warningUserVO : members) {
            Long warningValue = Long.valueOf(warningUserVO.getHasWarning());
            warningUserVO.setHasWarning(HealthTypeAnalysis.parseString(warningValue));
        }
        return ReturnDataAgent.success(members);
    }

    /**
     * 获得单个用户信息
     *
     * @return
     */
    @RequestMapping(value = "getMember", method = RequestMethod.POST)
    public JSONObject getMember(SubmitDTO submitDTO, String userId) {
        TOrgUser orgUser = submitDTO.getUser();
        FullUserVO member = memberService.getMember(Integer.parseInt(userId), orgUser);
        if (member != null) {
            member.setWbh();
        }
        return ReturnDataAgent.success(member);
    }

    /**
     * 获得门店/服务师的用户列表
     *
     * @return
     */
    @RequestMapping(value = "getMemberList", method = RequestMethod.POST)
    public JSONObject getMemberList(SubmitDTO submitDTO, UpSearchPagingData upSearchPagingData) {
        int pageIndex = upSearchPagingData.getPageIndex();
        int pageSize = upSearchPagingData.getPageSize();
        String search = upSearchPagingData.getSearch();   //有的话按用户姓名过滤
        List<GetMemberListData> memberList;
        //用户类型:管理员_0,服务师_1,都有_2
        if (submitDTO.getUser().getUserType() == 1) {
            int employeeId = submitDTO.getUser().getId();
            memberList = memberService.findMemberListByEmployee(employeeId, pageIndex, pageSize, search);
        } else {
            int orgId = submitDTO.getUser().getOrgId();
            memberList = memberService.findMemberListByStore(orgId, pageIndex, pageSize, search);
        }
        return ReturnDataAgent.success(memberList);
    }

    /**
     * 返回根据服务分组的会员列表
     *
     * @param submitDTO
     * @return
     */
    @PostMapping("getMemberListGroupByServe")
    public JSONObject getMemberListGroupByServe(SubmitDTO submitDTO) {
        TOrgUser orgUser = submitDTO.getUser();
        return ReturnDataAgent.success(memberService.getMemberListGroupByServe(orgUser));
    }

    /**
     * 返回历史会员信息列表
     *
     * @param submitDTO
     * @param upPagingData
     * @return
     */
    @PostMapping("getHistoricalMemberList")
    public JSONObject getHistoricalMemberList(SubmitDTO submitDTO, UpPagingData upPagingData) {
        TOrgUser orgUser = submitDTO.getUser();
        int pageIndex = upPagingData.getPageIndex();
        int pageSize = upPagingData.getPageSize();
        return ReturnDataAgent.success(memberService.getHistoricalMember(orgUser, pageIndex, pageSize));
    }

    /**
     * 设置用户healthData为已读
     *
     * @param submitDTO
     * @return
     */
    @PostMapping("readUserInfo")
    public JSONObject readUserInfo(SubmitDTO submitDTO) {
        JSONObject json = JSON.parseObject(submitDTO.getJson());
        JSONObject params = json.getJSONObject("data");

        String measureDate = params.getString("measureDate");
        Integer userId = params.getInteger("userId");
        Integer orgUserId = submitDTO.getUser().getId();

        boolean result = memberService.readUserInfo(userId, orgUserId, measureDate);
        return result ? ReturnDataAgent.success("操作成功") : ReturnDataAgent.error("操作失败");
    }

    /**
     * 修改用户备注或病种名
     *
     * @return
     */
    @PostMapping("modifyMemberInfo")
    public JSONObject modifyMemberInfo(SubmitDTO submitDTO) {
        JSONObject json = JSON.parseObject(submitDTO.getJson());
        JSONObject params = json.getJSONObject("data");
        String userDiseasesName = params.getString("userDiseasesName");
        String userRemark = params.getString("userRemark");
        Integer orderId = params.getInteger("orderId");
        if (userDiseasesName == null && userRemark == null) {
            return ReturnDataAgent.error("病种名与备注不能同时为空");
        }

        boolean result = memberService.modifyMemberInfo(userDiseasesName, userRemark, orderId);
        return result ? ReturnDataAgent.success("修改成功") : ReturnDataAgent.error("修改失败");
    }
    
    
    /**
     * 服务师添加提醒用户任务
     * @param submitDTO
     * @return
     */
    @PostMapping("addTimingTask")
    public JSONObject addTimingTask(SubmitDTO submitDTO) {
        TOrgUser orgUser = submitDTO.getUser();
        JSONObject json = JSON.parseObject(submitDTO.getJson());
        JSONObject params = json.getJSONObject("data");
       
        /*PushMessagePO pushMessage = new PushMessagePO();*/
        PushTaskMessagePo pushTaskMessage = new PushTaskMessagePo();
        String receiceIdStr = params.getString("receiceIdStr");
        List<GetMemberListData> memberList;
        String idStr = "";
        if(receiceIdStr.equals("0")){        	
        	 //用户类型:管理员_0,服务师_1,都有_2
            if (submitDTO.getUser().getUserType() == 1) {
                int employeeId = submitDTO.getUser().getId();
                pushTaskMessage.setSendId(employeeId);
                memberList = memberService.findMemberListByEmployee(employeeId);
            } else {
                int orgId = submitDTO.getUser().getOrgId();
                pushTaskMessage.setSendId(orgId);
                memberList = memberService.findMemberListByStore(orgId);
            }        	
            for (GetMemberListData member : memberList) {
				idStr += member.getId()+",";
			}           
            pushTaskMessage.setReceiceId(idStr.substring(0, idStr.length()-1));            
        }else{
        	if(submitDTO.getUser().getUserType() == 1){
        		int employeeId = submitDTO.getUser().getId();             
                pushTaskMessage.setSendId(employeeId);
        	}else{
        		int orgId = submitDTO.getUser().getOrgId();            
                pushTaskMessage.setSendId(orgId);
        	}
        	 
        	pushTaskMessage.setReceiceId(receiceIdStr);
        }
       
        pushTaskMessage.setContent(params.getString("message"));
        
        String time = params.getString("sendTime");
        pushTaskMessage.setSendTime(time);
        /*pushTaskMessage.setCycle(params.getString("cycle"));*/
        memberService.addPushTask(pushTaskMessage);     //添加推送任务
        return ReturnDataAgent.success();
    }
}
