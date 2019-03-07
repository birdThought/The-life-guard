package com.lifeshs.dao.org.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.org.TServe;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.pojo.health.OrgServeVO;
import com.lifeshs.pojo.health.ServiceItem;
import com.lifeshs.pojo.org.OrgDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
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

/**
 * 服务管理操作的Dao
 * 
 * @author zhansi.Xu
 * @DateTime 2016年9月8日
 * @Comment
 */
@Repository("orgServiceDao")
public interface OrgServiceDao {
    
    /**
     * 定制服务栏目数据展现
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */
    List<TServe> getServeDatas(int orgId);

    /**
     * 获取该机构的服务列表信息和会员数
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    List<OrgServer> getOrgServiceListAndMemberCount(@Param("orgId") Integer orgId,@Param("orgUserId") Integer orgUserId);

    /**
     * 查询该服务下的所有群组
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    List<OrgServerGroupBase> findGroupByKey(
            @Param(value = "orgId") int orgId,
            @Param(value = "serveId") int serveId,
            @Param("orgUserId") Integer orgUserId);
    
    /**
     * 查询该服务下的所有群组(orgServeId)
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    List<OrgServerGroupBase> findGroupByKeyWithOrgServeId(
            @Param(value = "orgId") int orgId,
            @Param(value = "orgServeId") int orgServeId,
            @Param("orgUserId") Integer orgUserId);
    
    /**
     * 获取该机构下所有服务师以及该服务师管理的群数量
     * @author zhansi.Xu
     * @DateTime 2016年9月19日
     * @serverComment
     */
    List<OrgEmploy> getOrgGroupManager(int orgId);

    /**
     * 查询该群组的详细信息
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    OrgServerGroupInfo getGroupInfo(@Param(value = "orgId") int orgId,
            @Param(value = "serveId") int serveId,
            @Param(value = "groupId") int groupId);

    /**
     * 查询该群组的管理员和服务师
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月9日
     * @serverComment
     */
    List<Map<String, Object>> findAdminAndServer(
            @Param(value = "groupId") int groupId,@Param(value="isLimit") int isLimit);
    
    /**
     * 查找该群组的所有用户
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    List<OrgMemberBase> findAllUserInGroup(
            @Param(value = "groupId") int groupId,
            @Param(value = "page") int page,@Param(value="dPage") int dPage);
    
    /**
     *  查找该群组的所有用户（不分页）
     *  @author yuhang.weng 
     *  @DateTime 2016年11月26日 下午3:04:48
     *
     *  @param groupId
     *  @return
     */
    List<OrgMemberBase> findAllUserInGroupWithoutPageSplit(
            @Param(value = "groupId") int groupId);

    /**
     * 查询该管理员或者服务师的信息
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月9日
     * @serverComment
     */
    OrgEmploy getUserInfo(@Param(value = "orgId") int orgId,
            @Param(value = "serverId") int serverId);

    /**
     * 查询服务历史记录
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月12日
     * @serverComment
     */
    List<OrgServeRecord> findHistoryServeRecord(
            Map<String, Object> params);

    /**
     * 得到筛选的服务历史记录总条数
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月12日
     * @serverComment
     */
    int getHistoryServeRecordCount(Map<String, Object> params);

    /**
     * 获取该服务的分类
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月12日
     * @serverComment
     */
    String getClassify(@Param(value = "orgId") int orgId,
            @Param(value = "serverId") int serverId);

    /**
     * 获取该机构的所有服务师
     * 
     * @author zhansi.Xu
     * @DateTime 2016年9月12日
     * @serverComment
     */
    List<OrgEmploy> getServer(@Param(value = "orgId") int orgId);
    
    /**
     * 获取一星期、一月门店的交易数据
     * @author wenxian.cai
     * @DateTime 2016年9月13日上午11:28:29
     * @serverComment 
     * @param params{"orgId":"","date":"WEEK(星期)/MONTH(一月)"}
     */
    HashMap<String, Object> getTradeData(Map<String, Object> params);
    
    /**
     * 获取今日、昨日、一月新增以及全部会员数
     * @author wenxian.cai
     * @DateTime 2016年9月13日下午3:22:38
     * @serverComment 
     * @param params{"orgId":"","date":"DAY(今日)/YESTODAY(昨日)/WEEK(星期)/ALL(全部)"}
     */
    HashMap<String, Object> getMemberData(Map<String, Object> params);

    
    /**
     * 获取该机构的用户列表信息 
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */
    List<OrgMember> findMemberList(Map<String,Object> params);
    
    /**
     * 获取该机构的用户总数
     * @author zhansi.Xu
     * @DateTime 2016年9月13日
     * @serverComment
     */
    int getMemberCount(Map<String,Object> params);
    
    /**
     *  获取会员详细信息
     *  @author yuhang.weng 
     *  @DateTime 2016年9月19日 上午10:49:37
     *
     *  @param userId 用户ID
     *  @param groupId 群组ID
     *  @return
     */
    OrgMemberMessageDetailVO getMemberMessageDetail(@Param("userId") Integer userId, @Param("groupId") Integer groupId,@Param("chargeMode")Integer chargeMode);
    
    /**
     *  判断t_user是否属于该机构的会员（机构下的服务是否包含该会员的订单）</p>
     *  避免机构用户查看不属于自己机构的用户信息
     *  @author yuhang.weng 
     *  @DateTime 2016年9月18日 上午11:56:01
     *
     *  @param orgId
     *  @param userId
     *  @return
     */
    Integer isThisMemberBelongToTheOrg(@Param("orgId") Integer orgId, @Param("userId") Integer userId);
    
    /**
     *  获取所有服务
     *  @author yuhang.weng 
     *  @DateTime 2016年10月11日 下午2:25:33
     *
     *  @return
     */
    List<TServe> geTServers();
    
    /**
     *  按照limit数量获取推荐机构信息
     *  @author yuhang.weng 
     *  @DateTime 2016年10月11日 下午2:57:53
     *
     *  @param limit
     *  @return
     */
    List<OrgDTO> getRecommendManage(@Param("limit") Integer limit);
    
    /**
     *  统计按机构名或所属分类查询的机构数量
     *  @author yuhang.weng 
     *  @DateTime 2016年12月11日 下午2:42:42
     *
     *  @param value
     *  @return
     */
    Integer getCountOfQueryOrgListByValueName(
            @Param("value") String value,
            @Param("areaCodeRegex") String areaCodeRegex);
    
    /**
     *  按机构名或所属分类查询机构信息
     *  @author yuhang.weng 
     *  @DateTime 2016年12月11日 下午2:45:30
     *
     *  @param value
     *  @param startIndex
     *  @param pageSize
     *  @return
     */
    List<Map<String, Object>> queryOrgListByValueName(
            @Param("value") String value,
            @Param("areaCodeRegex") String areaCodeRegex,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    
    Integer getCountOfOrgListByClassifyOrServeCode(
            @Param("code") String code,
            @Param("classify") String classify,
            @Param("areaCodeRegex") String areaCodeRegex,
            @Param("value") String searchValue);
    
    List<Map<String, Object>> orgListByClassifyOrServeCode(
            @Param("code") String code,
            @Param("classify") String classify,
            @Param("areaCodeRegex") String areaCodeRegex,
            @Param("value") String searchValue,
            @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    
    /**
     *  获取服务机构列表总数，筛选条件包含"服务分类"与"服务代码","机构名称"(单个的服务分类)
     *  @author yuhang.weng 
     *  @DateTime 2016年10月11日 下午8:18:44
     *
     *  @param code
     *  @param classify
     *  @param orgName
     *  @return
     */
    int getCountOfOrgListPageSplit(@Param("code") String code,
        @Param("classify") String classify, @Param("orgName") String orgName);
    
    /**
     *  分页获取服务机构列表，筛选条件包含"服务分类"与"服务代码"(单个的服务分类)
     *  @author yuhang.weng 
     *  @DateTime 2016年10月11日 下午7:38:25
     *
     *  @param code
     *  @param classify
     *  @param orgName
     *  @param startIndex
     *  @param pageSize
     *  @return
     */
    List<Map<String, Object>> getOrgListByPageSplit(@Param("code") String code,
        @Param("classify") String classify,
        @Param("orgName") String orgName,
        @Param("startIndex") int startIndex,
        @Param("pageSize") int pageSize);
    
    /**
     *  获取服务机构的服务列表
     *  @author yuhang.weng 
     *  @DateTime 2016年10月12日 上午11:44:55
     *
     *  @param orgId
     *  @return
     */
    List<OrgServeVO> selectOrgServes(@Param("orgId") int orgId);
    
    /**
     *  通过orgServeId获取服务详细信息
     *  @author yuhang.weng 
     *  @DateTime 2016年10月12日 下午3:12:43
     *
     *  @param orgServeId
     *  @return
     */
    Map<String, Object> selectServeDetailByOrgServeId(@Param("orgServeId") int orgServeId);
    
    /**
     *  通过serveGroupId获取服务详细信息
     *  @author yuhang.weng 
     *  @DateTime 2016年11月22日 上午10:15:30
     *
     *  @param serveGroupId
     *  @return
     */
    ServeDetailDTO selectServeDetailByServeGroupId(@Param("serveGroupId") int serveGroupId);
    
    /**
     *  通过服务机构ID获取该服务下的服务师信息
     *  @author yuhang.weng 
     *  @DateTime 2016年10月12日 下午2:22:44
     *
     *  @param orgServeId
     *  @return
     */
    List<Map<String, Object>> selectServeUserByServeId(@Param("orgServeId") int orgServeId);

    /**
     * 获取健康问诊页面下的服务机构列表
     * @param params
     * @return
     */
    List<ServiceItem> getHealthConsultList(@Param("params") Map<String,Object> params,@Param("page")int page,@Param("dPage")int dPage);

    /**
     * 获取健康问诊页面下的服务机构列表（总数）
     * @param params
     * @return
     */
    int getHealthConsultListCount(@Param("params")Map<String,Object> params);
    
    /**
     *  获取服务机构的分类标签
     *  @author yuhang.weng 
     *  @DateTime 2016年10月18日 上午9:24:24
     *
     *  @param orgId
     *  @return
     */
    List<String> getServeOrgClassify(@Param("orgId") int orgId);
    
    /**
     *  获取用户
     *  @author yuhang.weng 
     *  @DateTime 2016年10月18日 下午2:03:16
     *
     *  @param orgUserId
     *  @return
     */
    List<String> getOrgUserClassifyByUserId(@Param("orgUserId") int orgUserId);
    
    /**
     *  获取企业用户的服务列表
     *  @author yuhang.weng 
     *  @DateTime 2016年10月18日 下午5:17:59
     *
     *  @param orgUserId
     *  @return
     */
    List<OrgServeVO> getOrgUserServeList(@Param("orgUserId") int orgUserId);
    
    /**
     *  通过群组获取服务师列表
     *  @author yuhang.weng 
     *  @DateTime 2016年10月25日 下午4:12:00
     *
     *  @param groupId
     *  @return
     */
    List<Map<String, Object>> getServeOrgUserList(@Param("groupId") int groupId);
    
    /**
     *  通过环信账号查询用户信息
     *  @author yuhang.weng 
     *  @DateTime 2016年12月7日 上午11:36:20
     *
     *  @param huanxinUserNames
     *  @return
     */
    List<Map<String, Object>> getUsersByHuanxinAccount(
            @Param("huanxinUserNames") List<String> huanxinUserNames);
    
    /**
     * 获取该机构定制服务下所有群组内的服务师
     * @param groups
     * @return
     */
    List<Map<String,Object>> getServersInOrgServeGroupId(@Param("group") List<String> groups);

    /**
     * 移动群组
     * @param params
     * @return
     */
    int moveGroup(Map<String,Object> params);
    
    /**
     *  通过群组ID获取服务信息
     *  @author yuhang.weng 
     *  @DateTime 2016年11月26日 下午4:48:12
     *
     *  @param groupId
     *  @return
     */
    Map<String, Object> getServeAndOrgInfoByGroupId(@Param("groupId") int groupId);
    
    /**
     *  获取指定群组的信息
     *  @author yuhang.weng 
     *  @DateTime 2016年11月30日 下午4:09:03
     *
     *  @param groupId
     *  @return
     */
    OrgServerGroupBase getGroupBaseInfo(@Param("groupId") int groupId);
    
    /**
     *  获取推荐服务
     *  @author yuhang.weng 
     *  @DateTime 2017年1月11日 上午9:43:38
     *
     *  @param count
     *  @return
     */
    List<RecommendServe> getRecommendServes(@Param("count") Integer count);
    
    /**
     *  获取机构信息
     *  @author yuhang.weng 
     *	@DateTime 2017年3月29日 上午9:50:45
     *
     *  @param id
     *  @return
     */
    OrgDTO getTOrg(@Param("id") Integer id);
    
    /**
     *  获取一个机构用户
     *  @author yuhang.weng 
     *	@DateTime 2017年3月29日 上午9:51:25
     *
     *  @param id
     *  @return
     */
    OrgUserDTO getOrgUser(@Param("id") Integer id);
    
    /**
     *  更新机构用户信息
     *  @author yuhang.weng 
     *	@DateTime 2017年3月30日 下午2:41:47
     *
     *  @param orgUser
     */
    void updateOrgUser(OrgUserDTO orgUser);
    
    /**
     *  获取机构服务的消费人次
     *  @author yuhang.weng 
     *  @DateTime 2017年3月7日 上午11:28:38
     *
     *  @param orgServeId
     *  @return
     */
    Integer getOrgServeConsumePersonTime(@Param("orgServeId") int orgServeId);
    
    /**
     *  通过id获取机构服务信息
     *  @author yuhang.weng 
     *  @DateTime 2017年3月8日 下午3:37:56
     *
     *  @param id
     *  @return
     */
    OrgServeDTO getOrgServeById(@Param("id") Integer id);

    /**
     * @Description: 获取机构的全部会员
     * @author: wenxian.cai
     * @create: 2017/5/4 16:57
     */
   /* List<UserDTO> listOrgMember(@Param("orgId") Integer orgId);*/

}
