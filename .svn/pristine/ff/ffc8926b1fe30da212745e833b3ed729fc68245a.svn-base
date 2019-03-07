package com.lifeshs.support.plantform.security.sessionmgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.log.TLogLogin;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.pojo.client.Client;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.support.plantform.security.sessionmgr.ClientManager;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;
import com.lifeshs.support.plantform.security.token.CustomUsernamePasswordToken;

/**
 * 版权归 TODO 登录验证业务实现类
 * 
 * @author duosheng.mo
 * @DateTime 2016年4月20日 上午9:41:03
 */
@Service("loginService")
public class SessionManageServiceImpl extends CommonServiceImpl implements ISessionManageService {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ICacheService cacheService;

    @Override
    public boolean checkUser(String userName, String password, String randCode, Boolean rememberMe, String ip) {
        boolean bool = false;
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            // if(StringUtils.isBlank(lut)){
            // lut = "m";
            // }
            // 构造登陆令牌环
            CustomUsernamePasswordToken token = new CustomUsernamePasswordToken(userName, password.toCharArray(), true,
                    "", randCode, "");
            token.setRememberMe(rememberMe);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            String lut = token.getLut();
            if (subject.isAuthenticated()) {
                // 登录成功
                Client client = new Client();
                client.setLogindatetime(new Date());

                LoginUser user = queryLoginUser(userName, lut);

                client.setUser(user);
                client.setAdmin(false);
                //权限设置
                List<String> permissions = listPermissions(user.getUserType());
                client.setPermissions(permissions);
                // shiro管理的session
                Session session = subject.getSession();
                if (rememberMe) {
                    session.setTimeout(2592000000L); // timeout 30*24*3600*1000
                }

                TLogLogin logLogin = new TLogLogin();

                logLogin.setTerminalType("browse");
                logLogin.setUserId(user.getId());
                logLogin.setUserName(user.getUserName());
                logLogin.setLoginTime(new Date());
                logLogin.setIp(ip);

                switch (lut) {
                case "o":
                    logLogin.setUserType(2);
                    logLogin.setOrgId(user.getOrgId());
                    break;
                case "m":
                    logLogin.setUserType(1);
                    break;
                }

                commonTrans.saveLogin(logLogin);

                ClientManager.getInstance().addClinet((String) session.getId(), client);
                bool = true;
            }
        }

        return bool;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年5月3日 下午3:20:40
     * @serverCode 服务代码
     * @serverComment 查询登录用户信息
     *
     * @param lut
     *            登录用户类型（loginUserType）
     * @return
     */
    private LoginUser queryLoginUser(String userName, String lut) {
        LoginUser loginUser = new LoginUser();
        if ("o".equals(lut)) {
            TOrgUser orgUser = commonTrans.findUniqueByProperty(TOrgUser.class, "userName", userName);
            TOrg org = commonTrans.getEntity(TOrg.class, orgUser.getOrgId());
            loginUser = getTOrgUserLoginUser(orgUser, org);

            saveOrgSharingData(orgUser, org);
        } else {
            UserDTO user = memberService.getUser(userName);
            loginUser = getUserLoginUser(user);

            List<TUserTerminal> terminals = commonTrans.findByProperty(TUserTerminal.class, "userId", user.getId());
            saveMemberSharginData(user, terminals);
        }
        // if cache exist do not do anything
        return loginUser;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年5月3日 下午5:26:34
     * @serverCode 服务代码
     * @serverComment 退出登录
     * @return
     * @see ISessionManageService#logout()
     */
    @Override
    public boolean logout() {
        // TODO Auto-generated method stub
        // shiro管理的session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ClientManager.getInstance().removeClinet((String) session.getId());
        // shiro销毁登录
        subject.logout();
        return true;
    }

    @Override
    public void updateLoginUser(int userId, int type) {
        LoginUser loginUser = new LoginUser();
        if (type == 0) {
            UserDTO user = memberService.getUser(userId);
            List<TUserTerminal> terminals = commonTrans.findByProperty(TUserTerminal.class, "userId", user.getId());

            /** 用户SESSION缓存 */
            loginUser = getUserLoginUser(user);
            saveMemberSharginData(user, terminals);
        }
        if (type == 1) {
            TOrgUser orgUser = commonTrans.getEntity(TOrgUser.class, userId);
            TOrg org = commonTrans.getEntity(TOrg.class, orgUser.getOrgId());

            loginUser = getTOrgUserLoginUser(orgUser, org);
            saveOrgSharingData(orgUser, org);
        }

        /** loginUser */
        Client client = ClientManager.getClient();
        client.setUser(loginUser);
        ClientManager.getInstance().addClinet((String) SecurityUtils.getSubject().getSession().getId(), client);
    }

    @Override
    public MemberSharingData getCacheMemberSharingData(Integer userId) {
        String cacheKey = userId + "_m";
        return (MemberSharingData) getSharingData(cacheKey, userId, 0);
    }

    /**
     * <p>
     * 获取一个TUser类型的LoginUser对象
     * 
     * @author yuhang.weng
     * @DateTime 2016年8月10日 上午11:02:01
     *
     * @param user
     * @return
     */
    private LoginUser getUserLoginUser(UserDTO user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setLut("m");
        loginUser.setId(user.getId());
        loginUser.setUserName(user.getUserName());
        loginUser.setMobile(user.getMobile());
        loginUser.setUserCode(user.getUserCode());
        return loginUser;
    }

    private LoginUser getTOrgUserLoginUser(TOrgUser orgUser, TOrg org) {
        LoginUser loginUser = new LoginUser();
        loginUser.setLut("o");
        loginUser.setType(org.getType());
        loginUser.setId(orgUser.getId());
        loginUser.setUserNo(orgUser.getUserNo());
        loginUser.setUserName(orgUser.getUserName());
        loginUser.setMobile(orgUser.getMobile());
        loginUser.setOrgId(orgUser.getOrgId());
        loginUser.setUserType(orgUser.getUserType());
        loginUser.setUserCode(orgUser.getUserCode());
        loginUser.setRealName(orgUser.getRealName());
        loginUser.setHead(orgUser.getPhoto());
        loginUser.setOrgVerified(org.getOrgVerified());

        return loginUser;
    }

    private void saveOrgSharingData(TOrgUser user, TOrg org) {
        String cacheKey = user.getId() + "_o";
        OrgUserSharingData od = new OrgUserSharingData();

        od.setId(user.getId());
        od.setUserNo(user.getUserNo());
        od.setUserName(user.getUserName());

        od.setBirthday(user.getBirthday());
        od.setEmail(user.getEmail());
        od.setEmailVerified(user.getEmailVerified());
        od.setId(user.getId());
        od.setMobile(user.getMobile());
        od.setMobileVerified(user.getMobileVerified());
        od.setPhotoPath(user.getPhoto());
        od.setRealName(user.getRealName());
        if (user.getSex() != null) {
            String sex_s = user.getSex() ? "男" : "女";
            od.setSex(sex_s);
        }

        od.setUserType(user.getUserType());
        od.setCity(org.getCity());
        od.setContact(org.getContacts());
        od.setContactInformation(org.getContactInformation());
        od.setDistrict(org.getDistrict());
        od.setOrgType(org.getOrgType());
        od.setProvince(org.getProvince());
        od.setStatus(user.getStatus());
        od.setStreet(org.getStreet());
        od.setType(org.getType());
        od.setProfessionalName(user.getProfessionalName());

        Integer orgVerified = org.getOrgVerified();
//        boolean isBusinessLicenseBlank = StringUtils.isBlank(org.getBusinessLicense());
//        if (orgVerified.intValue() != 1 && isBusinessLicenseBlank) {
//            orgVerified = null;
//        }

        od.setOrgVerified(orgVerified);
        od.setVerifiedCause(org.getVerifiedCause());

        /** 缓存 */
        cacheService.saveKeyValue(CacheType.USER_SHARE_DATA, cacheKey, od);
    }

    private void saveMemberSharginData(UserDTO user, List<TUserTerminal> terminals) {
        String cacheKey = user.getId() + "_m";
        MemberSharingData md = new MemberSharingData();

        md.setId(user.getId());
        md.setUserNo(user.getUserNo());
        md.setUserName(user.getUserName());

        md.setBirthday(user.getRecordDTO().getBirthday());
        md.setEmail(user.getEmail());
        md.setEmailVerified(user.getEmailVerified());
        md.setId(user.getId());
        md.setMobile(user.getMobile());
        md.setMobileVerified(user.getMobileVerified());
        md.setPhotoPath(user.getPhoto());
        md.setRealName(user.getRealName());
        Boolean gender = user.getRecordDTO().getGender();
        if (gender != null) {
            String sex_s = gender ? "男" : "女";
            md.setSex(sex_s);
        }

        md.setHealthProduct(user.getHealthProduct());
        md.setHeight(user.getRecordDTO().getHeight());
        md.setWeight(user.getRecordDTO().getWeight());
        md.setWaist(user.getRecordDTO().getWaist());
        md.setBust(user.getRecordDTO().getBust());
        md.setHip(user.getRecordDTO().getHip());

        boolean isBindHL03 = false;
        boolean isBindHL031 = false;
        boolean isBindC3 = false;

        if (terminals != null) {
            for (TUserTerminal terminal : terminals) {
                if (StringUtils.equals(terminal.getTerminalType(), TerminalType.HL03.getName())) {
                    isBindHL03 = true;
                    continue;
                }
                if (StringUtils.equals(terminal.getTerminalType(), TerminalType.HL031.getName())) {
                    isBindHL031 = true;
                    continue;
                }
                if (StringUtils.equals(terminal.getTerminalType(), TerminalType.C3.getName())) {
                    isBindC3 = true;
                    continue;
                }
            }
        }

        md.setBindHL03(isBindHL03);
        md.setBindHL031(isBindHL031);
        md.setBindC3(isBindC3);

        /** 缓存 */
        cacheService.saveKeyValue(CacheType.USER_SHARE_DATA, cacheKey, md);
    }

    @Override
    public OrgUserSharingData getCacheOrgMemberSharingData(Integer userId) {
        String cacheKey = userId + "_o";
        return (OrgUserSharingData) getSharingData(cacheKey, userId, 1);
    }

    private Object getSharingData(String cacheKey, Integer userId, int type) {
        Object object = cacheService.getCacheValue(CacheType.USER_SHARE_DATA, cacheKey);
        if (object == null) {
            updateLoginUser(userId, type);
            object = cacheService.getCacheValue(CacheType.USER_SHARE_DATA, cacheKey);
        }
        return object;
    }

    private List<String> listPermissions(Integer userType) {
        List<String> permissions = new ArrayList<>();
        if (userType == null) {
            return permissions;
        }
        //todo 之后改为存在properties文件
        permissions.add("my/home");
        permissions.add("login");
        permissions.add("org/home");
        if (userType == 0) {    //管理员
            permissions.add("store/home");
            permissions.add("orderManage");
            permissions.add("order");
            permissions.add("org/employee");
            permissions.add("org/employee/addemployee");
//            permissions.add("org/memberManage/store");
            permissions.add("org/memberManage");
            permissions.add("store/finance");
            permissions.add("store/finance/bankInfo");
            permissions.add("org/service");
            permissions.add("org/service/publishservice/online");
            permissions.add("org/service/publishservice/offline");
            permissions.add("org/service/publishservice/visit");
            permissions.add("message");
            permissions.add("org/profile/store");
            permissions.add("org/profile/services/accountsecurity");
            permissions.add("org/profile/services/modifypassword");
            permissions.add("org/data-statistics");
            permissions.add("org/push");
            permissions.add("org/offlineManage");
            permissions.add("");
        }
        if (userType == 1) {    //服务师
            permissions.add("org/services/home");
            permissions.add("org/services/todayprofit");
            permissions.add("org/services/todayorder");
            permissions.add("message");
            permissions.add("org/memberManage");
            permissions.add("order");
            permissions.add("orderManage/ordertodo");
            permissions.add("orderManage/orderdone");
            permissions.add("orderManage/ordercomments");
            permissions.add("orderManage/orderdetail");
            permissions.add("org/profile/services");
            permissions.add("org/profile/services/accountsecurity");
            permissions.add("org/profile/services/modifypassword");
            permissions.add("org/profile/services/mobile");

        }
        if (userType == 2) {     //都有（个体门店）
            permissions.add("store/home");
            permissions.add("store/home/todayprofit");
            permissions.add("store/home/todayorder");
            permissions.add("orderManage");
            permissions.add("order");
            permissions.add("org/employee/addemployee");
            permissions.add("org/memberManage");
            permissions.add("store/finance");
            permissions.add("store/finance/bankInfo");
            permissions.add("org/service");
            permissions.add("org/service/publishservice/online");
            permissions.add("org/service/publishservice/offline");
            permissions.add("org/service/publishservice/visit");
            permissions.add("message");
            permissions.add("org/profile/store");
            permissions.add("org/profile/services");
            permissions.add("org/profile/services/accountsecurity");
            permissions.add("org/profile/services/modifypassword");
            permissions.add("org/profile/services/mobile");
            permissions.add("org/data-statistics");
            permissions.add("org/push");
            permissions.add("org/offlineManage");
        }
        return permissions;
    }
}