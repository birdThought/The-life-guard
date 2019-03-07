package com.lifeshs.service.org.member;

import java.util.List;

import com.lifeshs.pojo.org.OrgMemberDTO;

/**
 * 机构-会员管理
 * Created by Administrator on 2017/5/5.
 */

public interface IMemberManageService {

    /**
     * @Description: 获取与服务师相关的会员
     * @author: wenxian.cai
     * @create: 2017/5/5 11:12
     */
    List<OrgMemberDTO> listMemberByOrgUser(int orgUserId, int status);

    /**
     * @Description: 获取与服务师相关的会员数量
     * @Author: wenxian.cai
     * @Date: 2017/6/13 15:42
     */
    int getCountOfMemberByOrgUser(int orgUserId, int status);

    /**
     * @Description: 获取机构下全部会员
     * @Author: wenxian.cai
     * @Date: 2017/6/12 16:25
     */
    List<OrgMemberDTO> listMemberByOrg(int orgId, int status);

    /**
     *  统计机构下会员的数量
     *  @author yuhang.weng 
     *	@DateTime 2017年6月6日 下午2:07:46
     *
     *  @param orgId
     *  @return
     */
    int countMemberByOrgId(int orgId);
}
