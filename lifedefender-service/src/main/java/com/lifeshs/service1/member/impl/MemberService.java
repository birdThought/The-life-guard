package com.lifeshs.service1.member.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.measure.UserInfoReadDao;
import com.lifeshs.dao1.member.IMemberDao;
import com.lifeshs.dao1.order.IOrderDao;
import com.lifeshs.dto.manager.member.GetMemberByHXData;
import com.lifeshs.dto.manager.member.GetMemberListData;
import com.lifeshs.dto.manager.serve.LessonUserInfo;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.measure.HealthPackageMeasurePO;
import com.lifeshs.po.push.PushMessagePO;
import com.lifeshs.po.push.PushTaskMessagePo;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service1.member.IMemberService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.vo.FullUserVO;
import com.lifeshs.vo.MemberGroupVO;
import com.lifeshs.vo.MemberVO;
import com.lifeshs.vo.WarningUserVO;

/**
 * 会员业务实现类
 * Created by dengfeng on 2017/6/19 0019.
 */
@Service("newMemberService")
public class MemberService implements IMemberService {
    @Autowired
    IMemberDao memberDao;

    @Autowired
    IOrderDao orderDao1;

    @Autowired
    UserInfoReadDao userInfoReadDao;

    @Resource(name = "v2OrderService")
    private OrderService orderService;

    @Autowired
    private IOrgUserService orgUserService;

    /**
     * 获得单个用户信息
     * 如果用户有多个订单,那么获取的是用户最近订单的消息,
     * 如果是门店管理员,那么获取的是用户在该门店最近订单的消息.
     *
     * @param memberId
     * @return
     */
    @Override
    public FullUserVO getMember(int memberId, TOrgUser orgUser) {
        List<Integer> employeeIds = new ArrayList<>();
        Integer userType = orgUser.getUserType();

        if (userType == 0 || userType == 2) {
            employeeIds = orgUserService.getOrgUsersByOrgId(orgUser.getOrgId()); // 获得门店所属服务师ID列表
        } else if (userType == 1) {
            employeeIds = Arrays.asList(orgUser.getId());
        }

        return memberDao.getMember(memberId, employeeIds);
    }

    @Override
    public List<GetMemberListData> findMemberListByEmployee(int orgUserId, int pageIndex, int pageSize, String search) {
        if ("".equals(search))
            search = null;
        return memberDao.findMemberList(null, orgUserId, (pageIndex - 1) * pageSize, pageSize, search);
    }

    @Override
    public int getMemberCountByEmployee(int orgUserId) {
        return memberDao.getMemberCount(null, orgUserId);
    }

    @Override
    public List<GetMemberListData> findMemberListByStore(int orgId, int pageIndex, int pageSize, String search) {
        if ("".equals(search))
            search = null;
        return memberDao.findMemberList(orgId, null, (pageIndex - 1) * pageSize, pageSize, search);
    }

    @Override
    public int getMemberCountByStore(int orgId) {
        int count = memberDao.getMemberCount(orgId, null);
        return count;
    }

    @Override
    public List<WarningUserVO> findWainingMemberListByStore(int orgId, int pageIndex, int pageSize) {

        return orderDao1.findWainingMemberList(orgId, null, (pageIndex - 1) * pageSize, pageSize);
    }

    @Override
    public List<WarningUserVO> findWainingMemberListByEmployee(int employeeId, int pageIndex, int pageSize) {

        return orderDao1.findWainingMemberList(null, employeeId, (pageIndex - 1) * pageSize, pageSize);
    }


    /**
     * 获取健康课堂的用户成员
     *
     * @param projectCode
     * @return
     */
    public List<LessonUserInfo> findMemberListByLesson(String projectCode) {
        return memberDao.findMemberListByLesson(projectCode);
    }

    /**
     * 获取服务详情
     *
     * @param userId
     * @param projectType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<OrderPO> queryServeList(int userId, ProjectType projectType, int pageIndex, int pageSize) {
        return orderService.getOrderPOByCodeAndUserId(userId, projectType, pageIndex, pageSize);
    }

    /**
     * 根据环信ID获取用户信息
     *
     * @param huanxinUserNames
     * @return
     */
    public List<GetMemberByHXData> getUsersByHuanxinId(List<String> huanxinUserNames) {
        return memberDao.getUsersByHuanxinId(huanxinUserNames);
    }


    /**
     * <p>根据门店ID及服务ID获取会员列表.</p>
     * <p>其它限制条件:</p>
     * <P>1. 订单status=3 有效</P>
     * <p>[取消]2. now() 在 [startDate, endDate] 内(serviceable,即可服务对象)</p>
     * <p>3. user.status = 0, 用户状态正常</p>
     *
     * @param orgUser 门店用户
     * @return
     */
    @Override
    public List<MemberGroupVO> getMemberListGroupByServe(TOrgUser orgUser) {
        Assert.notNull(orgUser, "orgUser不能为null");

        List<Integer> employeeIds = new ArrayList<>();
        Integer userType = orgUser.getUserType();

        if (userType == 0 || userType == 2) {
            employeeIds = orgUserService.getOrgUsersByOrgId(orgUser.getOrgId()); // 获得门店所属服务师ID列表
        } else if (userType == 1) {
            employeeIds = Arrays.asList(orgUser.getId());
        }
        // 获取服务对应用户数量
        List<Map> serve = memberDao.getMemberCountByServe(employeeIds, null);

        //通过服务师ID获取memberVO
        List<MemberVO> memberVOList = memberDao.getMemberListWithServiceable(employeeIds, null);

        Set<MemberVO> serveZero = new HashSet<>(); // 全部会员
        Set<MemberVO> serveOne = new HashSet<>(); // 健康咨询
        Set<MemberVO> serveTwo = new HashSet<>(); // 到店服务
        Set<MemberVO> serveFour = new HashSet<>(); // 健康课堂
        Set<MemberVO> serveThree = new HashSet<>(); // 上门服务

        serveZero.addAll(memberVOList);
        for (MemberVO memberVO : memberVOList) {
            if (memberVO.getProjectType() == 1) serveOne.add(memberVO);  //assembleMemberVo(serveOne, memberVO, employeeIds); // 健康咨询

            if (memberVO.getProjectType() == 2) serveTwo.add(memberVO);  //assembleMemberVo(serveTwo, memberVO, employeeIds); // 到店服务

            if (memberVO.getProjectType() == 3) serveThree.add(memberVO);  //assembleMemberVo(serveThree, memberVO, employeeIds); // 上门服务

            if (memberVO.getProjectType() == 4) serveFour.add(memberVO);  //assembleMemberVo(serveFour, memberVO, employeeIds); // 健康课堂
        }

        // 初始化返回结果集
        List<MemberGroupVO> temp = new ArrayList<>(5);
        MemberGroupVO serve1 = new MemberGroupVO(serveOne, "0", "1");
        MemberGroupVO serve2 = new MemberGroupVO(serveTwo, "0", "2");
        MemberGroupVO serve3 = new MemberGroupVO(serveThree, "0", "3");
        MemberGroupVO serve4 = new MemberGroupVO(serveFour, "0", "4");
        temp.add(serve1);
        temp.add(serve2);
        temp.add(serve3);
        temp.add(serve4);

        // 非空结果集替换
        for (Map map : serve) {
            int serveCode = (int) map.get("serveId");
            switch (serveCode) {
                case 1:
                    serve1.setCount(map.get("count") == null ? "" : map.get("count").toString());
                    serve1.setList(serveOne);
                    break;
                case 2:
                    serve2.setCount(map.get("count") == null ? "" : map.get("count").toString());
                    serve2.setList(serveTwo);
                    break;
                case 3:
                    serve3.setCount(map.get("count") == null ? "" : map.get("count").toString());
                    serve3.setList(serveThree);
                    break;
                case 4:
                    serve4.setCount(map.get("count") == null ? "" : map.get("count").toString());
                    serve4.setList(serveFour);
                    break;
                default:
                    throw new RuntimeException("获取到的服务ID:" + serveCode + "非法");
            }
        }

        // 全部结果集
        Map total = getMemberCountByOrgIdWithServiceable(employeeIds);
        MemberGroupVO serve0 = new MemberGroupVO();
        serve0.setCount(total.get("count") == null ? "" : total.get("count").toString());
        serve0.setList(serveZero);
        serve0.setServeId("0");
        temp.add(serve0);

        return temp;
    }

    /**
     * MemberVO对象装配
     *
     * @param set
     * @param memberVO
     * @param employeeIds
     */
    private void assembleMemberVo(Set<MemberVO> set, MemberVO memberVO, List<Integer> employeeIds) {
//        Map remark = memberDao.getDiseasesNameAndUserRemark(memberVO.getId(), employeeIds);
//        memberVO.setUserDiseasesName((String) remark.get("userDiseasesName"));
//        memberVO.setUserRemark((String) remark.get("userDiseasesName"));
//        memberVO.setOrderId((Integer) remark.get("orderId"));
        set.add(memberVO);
    }

    /**
     * <p>获取历史用户列表</p>
     * <p>其它限制条件:</p>
     * <p>1. 订单status=4 已完成</p>
     * <p>2. now() > order.endDate</p>
     * <p>3. 用户状态正常</p>
     *
     * @param orgUser   门店用户
     * @param pageIndex 分页索引
     * @param pageSize  分页大小
     * @return
     */
    @Override
    public List<MemberVO> getHistoricalMember(TOrgUser orgUser, int pageIndex, int pageSize) {

        Assert.notNull(orgUser, "orgUser不能为null");

        List<Integer> employeeIds = new ArrayList<>();
        Integer userType = orgUser.getUserType();

        if (userType == 0 || userType == 2) {
            employeeIds = orgUserService.getOrgUsersByOrgId(orgUser.getOrgId()); // 获得门店所属服务师ID列表
        } else if (userType == 1) {
            employeeIds = Arrays.asList(orgUser.getId());
        }

        Set<MemberVO> historicalMember = memberDao.getHistoricalMember(employeeIds, (pageIndex - 1) * pageSize, pageSize);
        for (MemberVO memberVO : historicalMember) {
            Map remark = memberDao.getDiseasesNameAndUserRemark(memberVO.getId(), employeeIds);
            memberVO.setUserDiseasesName((String) remark.get("userDiseasesName"));
        }



        return new ArrayList<>(historicalMember);
    }

    /**
     * 清除门店会员的异常状态
     * 1. 直接根据订单ID清理警告信息
     * 2. 根据 userId, orgUserId,warningDate清理警告信息
     *
     * @param measureDate 测量时间
     * @param orgUserId
     * @param userId
     * @return
     */
    @Override
    public boolean readUserInfo(Integer userId, Integer orgUserId, String measureDate) {
        // 逻辑上这里一定会存在一条记录
        int result1 = userInfoReadDao.readUserInfo(userId, orgUserId, measureDate);

        // 清理首页用户异常数据
        memberDao.clearUnusuallyCondition(measureDate, orgUserId, userId);
        return result1 > 0;
    }

    /**
     * 修改用户备注和病种名
     *
     * @param userDiseasesName 病种名
     * @param userRemark       备注
     * @param orderId          订单ID
     * @return
     */
    @Override
    public boolean modifyMemberInfo(String userDiseasesName, String userRemark, Integer orderId) {
        int result = memberDao.modifyMemberInfo(userDiseasesName, userRemark, orderId);
        return result > 0;
    }

    /**
     * <p>根据门店ID获取会员数量.</p>
     * <p>其它限制条件:</p>
     * <P>1. 订单status=3 有效</P>
     * <p>2. now() 在 [startDate, endDate] 内(serviceable,即可服务对象)</p>
     * <p>3. user.status = 0, 用户状态正常</p>
     *
     * @param employeeIds
     * @return
     */
    private Map<String, Integer> getMemberCountByOrgIdWithServiceable(List<Integer> employeeIds) {
        Map<String, Integer> serve0 = memberDao.getMemberCountByOrgIdWithServiceable(employeeIds);

        if (serve0.isEmpty()) {
            serve0 = new HashMap<>(1); // 所有服务
            serve0.put("count", 0);
            serve0.put("serveId", 0);
        }

        return serve0;
    }

    @Override
    public List<UserDTO> findUserList(String keyword) {
        return memberDao.findUserList(keyword);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void saveHealthpackageWarning(int userId, HealthPackageType healthPackageType, Date measureDate) throws OperationException {
        HealthPackageMeasurePO data = memberDao.getHealthPackageWarning(userId, measureDate);
        if (data == null) {
            int result = memberDao.addHealthpackageWarning(userId, healthPackageType.value(), measureDate);
            if (result == 0) {
                throw new OperationException("添加用户异常数据记录失败", ErrorCodeEnum.FAILED);
            }
            return;
        }
        int warningDevice = data.getWarningDevice();
        int wd = warningDevice | healthPackageType.value();
        HealthPackageMeasurePO hpm = new HealthPackageMeasurePO();
        hpm.setId(data.getId());
        hpm.setWarningDevice(wd);
        hpm.setStatus(1);
        int result = memberDao.updateHealthPackageWarning(hpm);
        if (result == 0) {
            throw new OperationException("更新用户异常数据记录失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public List<UserPO> listUserByIds(int[] ids) {
        return memberDao.listUserByIds(ids);
    }

	@Override
	public PushMessagePO addMessage(PushMessagePO pushMessage) {
		return memberDao.addMessage(pushMessage);
		
	}

	@Override
	public void addPushTask(PushTaskMessagePo pushTaskMessage) {
		memberDao.addPushTask(pushTaskMessage);
		
	}

	@Override
	public List<GetMemberListData> findMemberListByEmployee(int employeeId) {			    
		return memberDao.findMemberList1(employeeId,null);
		
	}

	@Override
	public List<GetMemberListData> findMemberListByStore(int orgId) {
		
		return  memberDao.findMemberList1(null,orgId);
	}

}
