package com.lifeshs.service1.member;

import java.util.Date;
import java.util.List;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dto.manager.member.GetMemberByHXData;
import com.lifeshs.dto.manager.member.GetMemberListData;
import com.lifeshs.dto.manager.serve.LessonUserInfo;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.UserPO;
import com.lifeshs.po.push.PushMessagePO;
import com.lifeshs.po.push.PushTaskMessagePo;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.vo.FullUserVO;
import com.lifeshs.vo.MemberGroupVO;
import com.lifeshs.vo.MemberVO;
import com.lifeshs.vo.WarningUserVO;

/**
 * 会员业务接口
 * Created by dengfeng on 2017/6/19 0019.
 */
public interface IMemberService {

    /**
     * 获得单个用户信息
     *
     * @param memberId
     * @return
     */
    FullUserVO getMember(int memberId, TOrgUser orgUser);

    /**
     * @Description: 获取服务师所属的会员
     */
    List<GetMemberListData> findMemberListByEmployee(int orgUserId, int pageIndex, int pageSize, String search);

    /**
     * @Description: 获取服务师所属的会员数量
     * @Author: wenxian.cai
     * @Date: 2017/6/13 15:42
     */
    int getMemberCountByEmployee(int orgUserId);

    /**
     * @Description: 获取门店的全部会员
     * @Author: wenxian.cai
     * @Date: 2017/6/12 16:25
     */
    List<GetMemberListData> findMemberListByStore(int orgId, int pageIndex, int pageSize, String search);

    /**
     * 统计门店的会员数量
     *
     * @param orgId
     * @return
     * @author yuhang.weng
     * @DateTime 2017年6月6日 下午2:07:46
     */
    int getMemberCountByStore(int orgId);

    /**
     * 获取门店的异常会员
     * dengfeng
     *
     * @param orgId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<WarningUserVO> findWainingMemberListByStore(int orgId, int pageIndex, int pageSize);

    /**
     * 获取服务师所属的异常会员
     * dengfeng
     *
     * @param employeeId 服务师
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<WarningUserVO> findWainingMemberListByEmployee(int employeeId, int pageIndex, int pageSize);

    /**
     * 获取健康课堂的用户成员
     *
     * @param projectCode
     * @return
     */
    List<LessonUserInfo> findMemberListByLesson(String projectCode);

    /**
     * 用户查询已购买但没有使用的服务
     *
     * @param userId
     * @param projectType
     * @return
     */
    List<OrderPO> queryServeList(int userId, ProjectType projectType, int pageIndex, int pageSize);

    /**
     * 根据环信ID获取用户信息
     *
     * @param huanxinUserNames
     * @return
     */
    List<GetMemberByHXData> getUsersByHuanxinId(List<String> huanxinUserNames);


    /**
     * <pre>
     * 根据门店ID及服务ID获取会员列表数量.
     * 其它限制条件:
     *  1. 订单status=3 有效
     *  [取消]2. now() 在 [startDate, endDate] 内
     * </pre>
     *
     * @param orgUser 门店用户
     * @return
     */
    List<MemberGroupVO> getMemberListGroupByServe(TOrgUser orgUser);

    /**
     * 返回门店历史用户
     *
     * @udpateBy wuj 2017-09-15 15:36:32 reason: 将list集合改为Set集合,便于去重
     *
     * @param orgUser
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<MemberVO> getHistoricalMember(TOrgUser orgUser, int pageIndex, int pageSize);


    boolean readUserInfo(Integer userId, Integer orgUserId, String measureDate);

    /**
     * 修改用户备注和病种名
     *
     * @param userDiseasesName 病种名
     * @param userRemark 备注
     * @param orderId 订单ID
     * @return
     */
    boolean modifyMemberInfo(String userDiseasesName, String userRemark, Integer orderId);

    /**
     * 根据账号、姓名、电话号码获取用户列表
     * @param keyword 关键词
     * @return
     */
    List<UserDTO> findUserList(String keyword);

    /**
     * 存储用户测量设备异常信息
     * @param userId 用户id
     * @param healthPackageType 设备类型
     * @param measureDate 测量时间
     * @exception OperationException
     * @return
     */
    void saveHealthpackageWarning(int userId, HealthPackageType healthPackageType, Date measureDate) throws OperationException;

    /**
     * 根据数组id获取用户列表
     * author: wenxian.cai
     * date: 2017/11/21 17:12
     */
    List<UserPO> listUserByIds(int[] ids);
    
    /**
     * 添加推送消息
     * @param pushMessage
     */
    PushMessagePO addMessage(PushMessagePO pushMessage);
    
    /**
     * 添加定时提醒推送
     * @param pushTaskMessage
     */
	void addPushTask(PushTaskMessagePo pushTaskMessage);
	
	/**
	 * 获取门店/服务师所属的会员列表
	 * @param employeeId
	 * @return
	 */
	List<GetMemberListData> findMemberListByEmployee(int employeeId);
	List<GetMemberListData> findMemberListByStore(int orgId);
	
	

}
