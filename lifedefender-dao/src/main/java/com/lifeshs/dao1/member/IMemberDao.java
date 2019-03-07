package com.lifeshs.dao1.member;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lifeshs.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.dto.manager.member.GetMemberByHXData;
import com.lifeshs.dto.manager.member.GetMemberListData;
import com.lifeshs.dto.manager.serve.LessonUserInfo;
import com.lifeshs.po.measure.HealthPackageMeasurePO;
import com.lifeshs.po.push.PushMessagePO;
import com.lifeshs.po.push.PushTaskMessagePo;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.vo.FullUserVO;
import com.lifeshs.vo.MemberVO;

/**
 * 会员的数据操作
 * Created by dengfeng on 2017/7/1 0001.
 */
@Repository("memberDao1")
public interface IMemberDao {


    /**
     * 获取用户的病种名
     *
     * @param memberId
     * @param employeeIds
     * @return
     */
    Map getDiseasesNameAndUserRemark(@Param(value = "memberId") int memberId, @Param("employeeIds") List<Integer> employeeIds);


    /**
     * 获得单个用户信息
     * 如果用户有多个订单,那么获取的是用户最近订单的消息
     *
     * @param memberId
     * @return
     */
    FullUserVO getMember(@Param(value = "memberId") int memberId, @Param("employeeIds") List<Integer> employeeIds);

    /**
     * 分页获取门店/服务师所属的会员列表
     *
     * @param realName 按会员姓名过滤
     * @return
     */
    List<GetMemberListData> findMemberList(@Param(value = "orgId") Integer orgId,
                                           @Param(value = "orgUserId") Integer orgUserId,
                                           @Param(value = "startRow") int startRow,
                                           @Param(value = "lenght") int lenght,
                                           @Param(value = "realName") String realName);

    /**
     * 获取门店/服务师所属的会员数量
     *
     * @param orgUserId
     * @return
     */
    int getMemberCount(@Param(value = "orgId") Integer orgId, @Param(value = "orgUserId") Integer orgUserId);

    /**
     * 获取健康课堂的用户成员
     *
     * @param projectCode
     * @return
     */
    List<LessonUserInfo> findMemberListByLesson(@Param(value = "projectCode") String projectCode);

    /**
     * 根据环信ID获取用户信息
     *
     * @param huanxinUserNames
     * @return
     */
    List<GetMemberByHXData> getUsersByHuanxinId(@Param(value = "huanxinUserNames") List<String> huanxinUserNames);

    /**
     * 根据用户ID查询用户
     *
     * @param memberId
     * @return
     */
    FullUserVO getMemberById(@Param("memberId") Integer memberId);

    /**
     * <p>根据服务师ID及服务ID获取会员列表</p>
     * <p>其它限制条件:</p>
     * <p>1. 订单<code>status = 3</code> 有效</p>
     * <p>[取消]2. now() 在 [startDate, endDate] 内</p>
     * <p>3. 用户状态正常</p>
     *
     * @param orgUserIds 服务师ID列表,可以是单个服务师
     * @param serveId    服务ID,可为空
     * @return
     */
    List<Map> getMemberCountByServe(@Param("orgUserIds") List orgUserIds, @Param("serveId") Integer serveId);

    /**
     * <p>根据服务师ID获取到全体会员数目</p>
     * <p>其它限制条件:</p>
     * <p>1. 订单status=3 有效</p>
     * <p>2. now() 在 [startDate, endDate] 内</p>
     * <p>3. 用户状态正常</p>
     *
     * @param employeeIds
     * @return
     */
    Map<String, Integer> getMemberCountByOrgIdWithServiceable(@Param("employeeIds") List<Integer> employeeIds);

    /**
     * <p>根据服务师ID获取到全体会员数目</p>
     * <p>其它限制条件:</p>
     * <p>1. 订单status=3 有效</p>
     * <p>[取消]2. now() 在 [startDate, endDate] 内</p>
     * <p>3. 用户状态正常</p>
     *
     * @param orgUserIds 服务师ID列表,可以是单个服务师
     * @return
     */
    List<MemberVO> getMemberListWithServiceable(@Param("orgUserIds") List orgUserIds, @Param("serveId") Integer serveId);

    /**
     * <p>获取历史用户列表</p>
     * <p>其它限制条件:</p>
     * <p>1. 订单status = 4 已完成</p>
     * <p>2. now() > order.endDate</p>
     * <p>3. 用户状态正常</p>
     *
     * @param employeeIds 服务师ID列表,可以是单个服务师
     * @param start       分页索引
     * @param pageSize    分页大小
     * @return
     */
    Set<MemberVO> getHistoricalMember(@Param("employeeIds") List<Integer> employeeIds,
                                      @Param("start") int start, @Param("size") int pageSize);


    int clearUnusuallyCondition(@Param("measureDate") String measureDate,
                                @Param("orgUserId") Integer orgUserId,
                                @Param("userId") Integer userId);

    /**
     * 修改订单用户的备注和病种名
     *
     * @param userDiseasesName
     * @param userRemark
     * @param orderId
     * @return
     */
    int modifyMemberInfo(@Param("userDiseasesName") String userDiseasesName,
                         @Param("userRemark") String userRemark,
                         @Param("orderId") Integer orderId);

    /**
     * 根据账号‘真实姓名、手机号获取用户列表
     * @param keyword
     * @return
     */
    List<UserDTO> findUserList(@Param("keyword") String keyword);

    /**
     * 根据账号‘真实姓名、手机号获取用户数目
     * @param keyword
     * @return
     */
    int countUser(@Param("keyword") String keyword);

    /**
     * 获取指定用户指定日期各个设备异常值总和
     * @param userId
     * @param measureDate
     * @return
     */
    int getDeviceStatus(@Param("userId") int userId, @Param("measureDate") String measureDate);

    /**
     * 添加用户测量设备异常数据
     * @param userId
     * @param healthPackageType
     * @param measureDate
     * @return
     */
    int addHealthpackageWarning(@Param("userId") int userId, @Param("healthPackageType") int healthPackageType, @Param("measureDate") Date measureDate);
    
    /**
     *  获取用户测量设备数据
     *  @author yuhang.weng 
     *  @DateTime 2017年11月6日 下午2:59:54
     *
     *  @param userId 用户id
     *  @param measureDate 测量日期
     *  @return
     */
    HealthPackageMeasurePO getHealthPackageWarning(@Param("userId") int userId, @Param("measureDate") Date measureDate);
    
    /**
     *  更新用户测量设备数据
     *  @author yuhang.weng 
     *  @DateTime 2017年11月6日 下午3:13:46
     *
     *  @param heathPackageMeasure
     *  @return
     */
    int updateHealthPackageWarning(HealthPackageMeasurePO heathPackageMeasure);

    /**
     * 根据ids数组获取用户列表
     * @param ids
     * @return
     */
    List<UserPO> listUserByIds(@Param("ids") int[] ids);

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
	 * 获取全部会员
	 * @param employeeId
	 * @return
	 */
	List<GetMemberListData> findMemberList1(@Param("orgUserId")Integer employeeId,@Param("orgId")Integer orgId);
    
}
