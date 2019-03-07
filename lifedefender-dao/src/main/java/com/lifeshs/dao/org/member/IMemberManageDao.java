package com.lifeshs.dao.org.member;

import com.lifeshs.pojo.org.OrgMemberDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 机构-会员管理dao
 * Created by Administrator on 2017/5/5.
 */
@Repository("memberManageDao")
public interface IMemberManageDao {

    /**
     * @Description: 获取与服务师相关的机构会员
     * @author: wenxian.cai
     * @create: 2017/5/5 11:05
     * @param orgUserId 服务师id
     * @param status 订单状态
     */
    List<OrgMemberDTO> listMemberByOrgUser(@Param(value = "orgUserId") int orgUserId, @Param(value = "status") int status);

    /**
     * @Description: 获取服务师旗下会员数量
     * @Author: wenxian.cai
     * @Date: 2017/6/13 15:56
     */
    int getCountOfMemberByOrgUser(@Param(value = "orgUserId") int orgUserId, @Param(value = "status") int status);
    /**
     *  统计机构下的会员数量
     *  @author yuhang.weng 
     *	@DateTime 2017年6月6日 下午2:06:49
     *
     *  @param orgId
     *  @return
     */
    int countMemberByOrgId(@Param(value = "orgId") Integer orgId);

    /**
     * @Description: 获取机构显的所有会员
     * @Author: wenxian.cai
     * @Date: 2017/6/12 16:28
     */
    List<OrgMemberDTO> listMemberByOrg(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status);
}
