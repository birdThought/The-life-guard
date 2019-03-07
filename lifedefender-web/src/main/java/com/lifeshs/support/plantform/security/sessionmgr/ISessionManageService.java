package com.lifeshs.support.plantform.security.sessionmgr;

import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.client.OrgUserSharingData;


/**
 * @author Yue.Li
 * @date 3/9/17.
 */
public interface ISessionManageService {
    boolean logout();

    /**
     * <p> 更新sessino中的LoginUser对象
     * <p> 一般在修改用户登录名(Username)，用户真实姓名(RealName)，微信OpenId，手机号码(Mobiel)
     * <p> 邮箱号码(Email)，性别(Sex)，登录状态(Status禁用与正常)，健康包产品(HealthProduct)，
     * <p> 用户头像(photo)，个人档案(height,weight,waist……)
     * <p> type为0表示会员，1表示企业用户
     *
     * @throws NullPointerException
     * @author yuhang.weng
     * @DateTime 2016年7月29日 下午2:21:10
     */
    void updateLoginUser(int userId, int type);

    /**
     * @param userName   用户名
     * @param password   密码
     * @param randCode   验证码
     * @param rememberMe 记住我
     * @param ip         ip
     * @return
     * @author duosheng.mo
     */
    boolean checkUser(String userName, String password, String randCode,
        Boolean rememberMe, String ip);

    /**
     * 获取用户共享数据
     *
     * @param userId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月7日 上午9:04:32
     */
    MemberSharingData getCacheMemberSharingData(Integer userId);

    /**
     * 获取企业用户共享数据块
     *
     * @param userId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月9日 下午3:26:48
     */
    OrgUserSharingData getCacheOrgMemberSharingData(Integer userId);
}
