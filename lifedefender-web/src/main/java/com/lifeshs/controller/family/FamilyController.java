package com.lifeshs.controller.family;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.ServiceMessage;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.entity.member.TUser;

import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.family.GroupMemberVO;

import com.lifeshs.service.family.IFamilyService;
import com.lifeshs.service.member.IMemberService;

import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;

import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.StringUtil;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller(value = "webFamilyController")
@RequestMapping("familyControl")
public class FamilyController extends BaseController {
    @Autowired
    private IFamilyService familyService;
    @Autowired
    private ISessionManageService loginService;
    @Autowired
    private IMemberService memberService;

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月2日 上午11:34:42
     * @serverComment 查询用户所在家庭组的所有成员
     *
     * @return
     */
    @RequestMapping(params = "getFamilyMember")
    public String getFamilyMember(Model model) {
        LoginUser user = getLoginUser();

        List<GroupMemberVO> members = familyService.findGroupMember(user.getId());

        model.addAttribute("userGroup", members);

        return "com/lifeshs/member/family";
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月2日 下午2:05:11
     * @serverComment 跳转到添加用户到家庭组页面
     *
     * @return
     */
    @RequestMapping(params = "insertRegistedMemberPage")
    public String insertRegisteredMemberPage() {
        return "com/lifeshs/member/addMember";
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月3日 上午10:44:42
     * @serverComment 通过用户名查找已注册的用户
     *
     * @param
     * @return
     */
    @RequestMapping(params = "findRegistedUser", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson findRegistedUser(@RequestParam
    String registedUserName) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        LoginUser loginUser = getLoginUser();

        Map<String, Object> map = new HashMap<String, Object>();
        String msg = "查找失败";

        if (StringUtils.equals(registedUserName, loginUser.getUserName())) {
            resObject.setSuccess(false);
            resObject.setMsg("不能添加自己到家庭组中");

            return resObject;
        }

        // 检查参数是否为空
        if (StringUtils.isBlank(registedUserName)) {
            resObject.setSuccess(false);
            resObject.setMsg(msg + "：参数为空");

            return resObject;
        }

        Map<String, Object> userData = familyService.findUserListByUserName(registedUserName,
                loginUser.getId());

        if (userData.isEmpty()) {
            resObject.setMsg("该用户不存在");
            resObject.setSuccess(false);
        } else {
            map.put("userData", userData);
            resObject.setMsg("查找成功");
            resObject.setSuccess(true);
        }

        resObject.setAttributes(map);

        return resObject;
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月2日 上午11:35:58
     * @serverComment 添加已注册用户到家庭组
     *
     * @return
     */
    @RequestMapping(params = "insertRegistedMember", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson insertRegistedMember(@RequestParam
    String userName, @RequestParam
    String password) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "添加失败";

        LoginUser user = getLoginUser();
        int userId = user.getId();

        userName = StringUtil.decodeStr(userName);
        password = StringUtil.decodeStr(password);

        // 检查参数是否为空
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            resObject.setMsg(msg + "：参数为空");

            return resObject;
        }

        ServiceMessage sm = null;

        try {
            sm = familyService.updateUserGroupKey(userName, password, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resObject.setSuccess(sm.isSuccess());
        resObject.setMsg(sm.getMessage());

        return resObject;
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月2日 下午2:07:13
     * @serverComment 跳转到新增家庭组成员页面
     *
     * @return
     */
    @RequestMapping(params = "insertNewMemberPage")
    public String insertNewMemberPage() {
        return "com/lifeshs/member/newMember";
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月2日 上午11:36:25
     * @serverComment 添加未注册用户到家庭组
     *
     * @return
     */
    @RequestMapping(params = "insertNewMember", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson insertNewMember(@RequestParam
    String userName, @RequestParam
    String pwd) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "添加失败";

        // 检查参数是否为空
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(pwd)) {
            resObject.setMsg(msg + "：参数为空");

            return resObject;
        }

        userName = StringUtil.decodeStr(userName);
        pwd = StringUtil.decodeStr(pwd);

        // 判断用户是否存在
        if (memberService.userIsExist(userName)) {
            resObject.setMsg(msg + "：用户已存在");

            return resObject;
        }

        try {
            memberService.registMember(userName, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resObject.setSuccess(true);
        msg = "添加成功";
        resObject.setMsg(msg);

        return resObject;
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月4日 下午7:43:17
     * @serverComment 跳转到完善信息界面
     *
     * @return
     */
    @RequestMapping(params = "insertNewMemberInfoPage")
    public String insertNewMemberInfoPage(Model model,
        @RequestParam
    String userName) {
        TUser user = null;

        try {
            user = familyService.findUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("user", user);

        return "com/lifeshs/member/newMemberInfor";
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月3日 下午5:11:45
     * @serverComment 完善用户信息并加入家庭组
     *
     * @param
     * @return
     */
    @RequestMapping(params = "insertNewMemberInfo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson insertNewMemberInfo(@RequestBody
    Map<String, Object> map) {
        // //当前用户信息
        LoginUser user = getLoginUser();
        int userId = user.getId();

        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        UserDTO userDTO = new UserDTO();
        userDTO.setId((int) map.get("id"));
        userDTO.setRealName((String) map.get("realName"));
        userDTO.setMobile((String) map.get("mobile"));

        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId((int) map.get("id"));
        recordDTO.setBirthday(DateTimeUtilT.date((String) map.get("birthday")));
        recordDTO.setGender((boolean) map.get("sex"));
        recordDTO.setHeight((float) map.get("height"));
        recordDTO.setWeight((float) map.get("weight"));
        recordDTO.setWaist((float) map.get("waist"));
        recordDTO.setBust((float) map.get("bust"));
        recordDTO.setHip((float) map.get("hip"));
        userDTO.setRecordDTO(recordDTO);

        familyService.updateMemberInfo(userDTO, userId);

        resObject.setMsg("添加成功");
        resObject.setSuccess(true);

        return resObject;
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月5日 上午9:54:43
     * @serverComment 退出了家庭组
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "backGroup")
    public ModelAndView backGroup(@RequestParam
    Integer id) {
        memberService.leaveGroup(id);

        return new ModelAndView("redirect:/familyControl.do?getFamilyMember");
    }

    @RequestMapping(params = "switchUser")
    @ResponseBody
    public AjaxJson switchUser(@RequestParam
    Integer userId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);
        loginService.updateLoginUser(userId, 0);

        return resObject;
    }
}
