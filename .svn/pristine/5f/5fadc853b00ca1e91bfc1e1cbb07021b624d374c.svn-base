package com.lifeshs.service.org;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifeshs.common.exception.org.AuthorityException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.entity.org.TOrgServe;
import com.lifeshs.entity.org.TServe;
import com.lifeshs.pojo.health.OrgServeVO;
import com.lifeshs.pojo.health.ServiceDoctorVO;
import com.lifeshs.pojo.health.ServiceItem;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.org.OrgDTO;
import com.lifeshs.pojo.org.employee.OrgEmploy;
import com.lifeshs.pojo.org.server.OrgMember;
import com.lifeshs.pojo.org.server.OrgMemberBase;
import com.lifeshs.pojo.org.server.OrgMemberMessageDetailVO;
import com.lifeshs.pojo.org.server.OrgServeDTO;
import com.lifeshs.pojo.org.server.OrgServeRecord;
import com.lifeshs.pojo.org.server.OrgServer;
import com.lifeshs.pojo.org.server.OrgServerGroupBase;
import com.lifeshs.pojo.org.server.OrgServerGroupInfo;
import com.lifeshs.pojo.org.server.RecommendServe;
import com.lifeshs.pojo.org.server.ServeDetailDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.record.DietVO;
import com.lifeshs.pojo.record.MedicalVO;
import com.lifeshs.pojo.record.PhysicalsVO;

/**
 * 
 * 服务机构服务接口（底层机构，提供服务的门店等）
 * 
 * @author dengfeng
 * @DateTime 2016-6-1 下午04:16:28
 */
public interface IServiceOrgService {

    /**
     * 获取可定制的服务信息
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */
    List<TServe> getServeDatas(int orgId);

    /**
     * 开通一个服务
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */
    Integer openOrderService(TOrgServe serve, String createrName, Integer orgId);

    /**
     * 获取该机构的服务列表信息和会员总数
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    List<OrgServer> getOrgServiceListAndMemberCount(int orgId, Integer orgUserId);

    /**
     * 添加群组
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月19日
     * @serverComment 返回的int为群组的主键id
     */
    int addGroup(int orgId, Integer serveId, Map<String, Object> params, Integer orgServeId);

    /**
     * 查询该服务下的所有群组
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    List<OrgServerGroupBase> findGroupByKey(int orgId, int orgServeId, Integer orgUserId);

    List<OrgServerGroupBase> findGroupByKeyWithOrgServeId(int orgId, int orgServeId, Integer orgUserId);

    /**
     * 查询该群组的详细信息
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    OrgServerGroupInfo getGroupInfo(int orgId, int serveId, int groupId);

    /**
     * 查找该群组的所有用户
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    List<OrgMemberBase> findAllUserInGroup(int groupId, int page);

    /**
     * 查找该群组的所有用户（不分组）
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月26日 下午3:05:34
     *
     * @param groupId
     * @return
     */
    List<OrgMemberBase> findAllUserInGroupWithoutPageSplit(int groupId);

    /**
     * 获取该机构下所有服务师以及该服务师管理的群数量
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月19日
     * @serverComment
     */
    List<OrgEmploy> getOrgServersWithGroupCount(int orgId);

    /**
     * 查询该管理员或者服务师的信息
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月9日
     * @serverComment
     */
    OrgEmploy getUserInfo(int orgId, int serverId);

    /**
     * 获取该机构拥有的服务师（用于展示服务历史记录的筛选下拉框）
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月12日
     * @serverComment
     */
    List<OrgEmploy> getServers(int orgId);

    /**
     * 查询服务历史记录
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月12日
     * @serverComment
     */
    List<OrgServeRecord> findHistoryServeRecord(Map<String, Object> params);

    /**
     * 得到服务历史记录总条数
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月12日
     * @serverComment
     */
    int getRecordCount(Map<String, Object> params);

    /**
     * 获取最近一星期、一月门店的数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年9月13日上午11:32:28
     * @serverComment
     * @param orgId:
     *            服务机构Id,dateType: 查询日期类型(WEEK:星期,MONTH:一月)
     */
    HashMap<String, Object> getTradeData(int orgId, String dateType);

    /**
     * @author wenxian.cai
     * @DateTime 2016年9月13日下午3:26:39
     * @serverComment
     * @param orgId:
     *            服务机构Id,dateType: 查询日期类型(DAY:今日,YESTODAY:昨日,MONTH:一月,ALL:全部)
     */
    HashMap<String, Object> getMemberData(int orgId, String dateType);

    /**
     * 获取该机构的用户列表信息
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */
    List<OrgMember> findMemberList(Map<String, Object> params);

    /**
     * 获取该机构的用户总数
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */
    int getMemberCount(Map<String, Object> params);

    /**
     * 获取会员基本信息（详细）
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月14日 下午5:07:52
     *
     * @param memberId
     *            会员ID
     * @param serveGroupId
     *            服务群组ID
     * @return
     */
    OrgMemberMessageDetailVO getMemberMessageDetail(Integer memberId, Integer serveGroupId, Integer mode);

    /**
     * 根据测量日期获取会员健康数据
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月18日 下午12:06:55
     *
     * @param userId
     * @param measureDate
     * @return
     */
    Map<String, Object> getMemberHealthDataSplitByMeasureDate(Integer userId, String measureDate);

    /**
     * 根据测量日期获取会员病历数据
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月19日 下午8:16:58
     *
     * @param userId
     * @param visitingDate
     * @return
     */
    List<MedicalVO> getMemberMedicalDataSplitByVisitingDate(Integer userId, String visitingDate);

    /**
     * 根据测量日期获取会员体检数据
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月20日 上午11:43:02
     *
     * @param userId
     * @param physicalsDate
     * @return
     */
    List<PhysicalsVO> getPhysicalDataSplitByPhysicalsDate(Integer userId, String physicalsDate);

    /**
     * 根据测量日期获取会员饮食数据
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月20日 下午3:12:20
     *
     * @param userId
     * @param recordDate
     * @return
     */
    List<DietVO> getDietDataSplitByRecordDate(Integer userId, String recordDate);

    /**
     * 判断t_user是否属于该机构的会员（机构下的服务是否包含该会员的订单）
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月18日 上午11:58:16
     *
     * @param orgId
     *            机构ID
     * @param userId
     *            会员ID
     * @throws AuthorityException
     */
    void isThisMemberBelongToTheOrg(Integer orgId, Integer userId) throws AuthorityException;

    /**
     * 获取该群下管理的服务师
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月20日
     * @serverComment
     */
    List<Map<String, Object>> getControlGroupServer(int groupId);

    /**
     * 编辑群组
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月21日
     * @serverComment
     */
    void editGroup(Map<String, Object> params);

    /**
     * 获取一个已定制的服务信息
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月30日
     * @serverComment
     */
    TOrgServe getOrderServiceDetail(Integer orgId, Integer serId);

    /**
     * 修改已定制的服务
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月30日
     * @serverComment
     */
    void updateOrderServiceDetail(Integer orgId, TOrgServe service);

    /**
     * 获取服务列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午2:23:29
     *
     * @return
     */
    List<TServe> getTServers();

    /**
     * 获取count数量的推荐机构
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午3:00:21
     *
     * @param count
     *            null表示不限制
     * @return
     */
    List<OrgDTO> getRecommendManage(Integer count);

    List<RecommendServe> getRecommendServes(Integer count);

    /**
     * 通过类型与服务code队服务机构数据进行分页获取
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午8:02:02
     *
     * @param curPage
     * @param pageSize
     * @param code null表示不限制服务
     * @param classify null表示不限制标签
     * @param areaCode null表示不限制地区
     * @param searchValue 查询内容
     * @return
     */
    PaginationDTO<Map<String, Object>> getServeOrgsPageSplit(int curPage, int pageSize, String code, String classify, String areaCode, String searchValue);

    /**
     * 通过服务机构名称模糊查询机构列表进行分页获取
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月17日 下午2:09:06
     *
     * @param curPage
     * @param pageSize
     * @param orgNameOrClassifyName
     *            null表示不限制查询条件
     * @return
     */
    PaginationDTO getServeOrgsPageSplitForValueName(int curPage, int pageSize, String orgNameOrClassifyName, String areaCode);

    /**
     * 获取服务机构信息，以及该机构提供的服务信息列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月14日 下午2:42:39
     *
     * @param orgId
     * @return
     */
    OrgDTO getServeOrgWithServeList(int orgId);

    /**
     * 获取服务机构
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月12日 上午10:08:20
     *
     * @param orgId
     * @return
     */
    List<OrgServeVO> getOrgServeList(int orgId);

    /**
     * 通过orgServeId获取服务的详细信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月12日 下午3:02:30
     *
     * @return
     */
    Map<String, Object> getServeDetailByOrgServeId(int orgServeId);

    /**
     * 通过serveGroupId获取服务的详细信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月22日 上午10:16:25
     *
     * @param serveGroupId
     * @return
     */
    ServeDetailDTO getServeDetailByServeGroupId(int serveGroupId);

    /**
     * 通过机构ID获取该服务机构下的服务师信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月12日 下午2:23:42
     *
     * @param orgServeId
     * @return
     */
    List<Map<String, Object>> getServeUserByServeId(int orgServeId);

    /**
     * 获取分类标签
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月14日 上午10:37:22
     *
     * @return
     */
    List<Map<String, Object>> getClassifyTags();

    /**
     * 获取服务标签
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月14日 上午10:37:39
     *
     * @return
     */
    List<Map<String, Object>> getServiceTags();

    /**
     * 获取健康问诊页面下的服务机构列表
     * 
     * @param params
     * @return
     */
    List<ServiceItem> getConsultItemList(Map<String, Object> params, int startPage, int max);

    /**
     * 获取健康问诊页面下的服务机构列表（总数）
     * 
     * @param params
     * @return
     */
    int getHealthConsultListCount(Map<String, Object> params);

    /**
     * 获取服务机构的分类标签
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月18日 上午9:36:19
     *
     * @param orgId
     * @return
     */
    String getServeOrgClassify(int orgId);

    /**
     * 获取服务师信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月18日 下午3:59:54
     *
     * @param orgUserId
     * @return
     */
    ServiceDoctorVO getServiceDoctor(int orgUserId);

    /**
     * 通过环信账号查询用户信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月7日 上午11:28:58
     *
     * @param huanxinUserNames
     * @return
     */
    List<Map<String, Object>> getUsersByHuanxinAccount(List<String> huanxinUserNames);

    /**
     * 获取该机构服务的详情并且获取该机构服务下的所有服务师团队信息
     * 
     * @param orgServeId
     * @return
     */
    OrgServeDTO getOrgServeDetail(Integer orgServeId);

    /**
     * 移动群组
     * 
     * @param params
     * @param orgId
     * @return
     */
    boolean moveGroup(Map<String, Object> params, Integer orgId);

    /**
     * 删除群组
     * 
     * @param params
     * @param orgId
     * @return
     */
    ServiceMessage delGroup(Map<String, Object> params, Integer orgId, boolean moveToDefaultGroup);

    /**
     * 通过群组ID获取服务信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月26日 下午4:49:32
     *
     * @param groupId
     * @return
     */
    Map<String, Object> getServeAndOrgInfoByGroupId(int groupId);

    /**
     * 获取指定群组的信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月30日 下午4:09:56
     *
     * @param groupId
     * @return
     */
    OrgServerGroupBase getOrgGroupBaseInfo(int groupId);

    /**
     *  获取机构信息
     *  @author yuhang.weng 
     *  @DateTime 2017年3月8日 上午9:56:35
     *
     *  @param id
     *  @return
     */
    OrgDTO getOrg(Integer id);
    
    /**
     *  获取机构服务消费人次
     *  @author yuhang.weng 
     *  @DateTime 2017年3月7日 上午11:37:45
     *
     *  @param orgServeId
     *  @return
     */
    Integer getOrgServeConsumePersonTime(int orgServeId);

    /**
     * @Description: 获取机构全部会员
     * @author: wenxian.cai
     * @create: 2017/5/4 16:59
     */
    List<UserDTO> listOrgMember(Integer orgId);
}
