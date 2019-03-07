
package com.lifeshs.thirdservice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.HuanxinCmdActionType;
import com.lifeshs.common.constants.common.HuanxinTargetType;
import com.lifeshs.entity.huanxin.TUnregistHx;
import com.lifeshs.pojo.huanxin.GroupDTO;
import com.lifeshs.pojo.huanxin.GroupMemberDTO;
import com.lifeshs.pojo.huanxin.HuanxinUserVO;
import com.lifeshs.pojo.huanxin.ResultDTO;
import com.lifeshs.service.common.impl.transform.CommonTransImpl;
import com.lifeshs.utils.HttpUtils;
import com.lifeshs.utils.JSONHelper;

/**
 * 环信工具类
 *
 * @author zhansi.Xu
 * @DateTime 2016年9月23日
 * @Comment
 */
public class HuanXinService {

    private final Logger logger = Logger.getLogger(HuanXinService.class);

    private String appKey;
    private String clientId;
    private String clientSecret;
    private String url;
    private String token = null;
    private Instant expireTokenTime = null;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Autowired
    private CommonTransImpl commonTrans;

    /**
     * 授权的基本header
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月23日
     * @serverComment
     */
    private Map<String, String> getHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer " + getToken());
        return map;
    }

    /**
     * 获取token
     *
     * @param
     * @author zhansi.Xu
     * @DateTime 2016年9月23日
     * @serverComment
     */
    private String getToken() {
        Instant instant = Instant.now();
        if (token == null) { // 第一次获取token
            queryTokenData(instant);
        } else { // 已查询过一次token
            if (instant.isAfter(expireTokenTime)) { // token已过期
                // 获取新的token
                queryTokenData(instant);
            } else { // token未过期
                // 沿用原来的token
            }
        }
        return token;
    }

    // private static Object lock = 1;

    /**
     * 向环信发送查询token请求
     *
     * @param calendar
     * @author yuhang.weng
     * @DateTime 2016年9月29日 上午10:12:01
     */
    private synchronized void queryTokenData(Instant instant) {
        if (token != null) {// 防止高并发状态下继续获取
            if (!instant.isAfter(expireTokenTime)) {
                return;
            }
        }
        // get new token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("grant_type", "client_credentials");
        jsonObject.put("client_id", clientId);
        jsonObject.put("client_secret", clientSecret);
        Map<String, String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        String result = HttpUtils.getResultEntity(url + "/token", map, jsonObject, HttpUtils.POST, true, null);
        JSONObject obj = JSONObject.parseObject((String) result);
        if (obj.containsKey("access_token")) {
            token = obj.getString("access_token");
            int expires = obj.getIntValue("expires_in");// 有效时间
            expireTokenTime = instant.plusSeconds(expires);// 把秒数添加上去
        }

    }

    /**
     * 注册一个用户
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月23日
     * @serverComment
     */
    public void registryUser(String userCode) {
        HuanxinUserVO userVO = new HuanxinUserVO();
        userVO.setPassword("123456");
        userVO.setUsername(userCode);
        registry((JSON) JSONObject.toJSON(userVO)); // 0代表注册失败，1代表注册成功
    }

    public boolean registryUserWhenIsNotExist(HuanxinUserVO user) {
        int status = HttpUtils.getResultStatus(url + "/users/" + user.getUsername(), getHeaders(), null, HttpUtils.GET,
                true, null);
        if (status == 404) {
            registry((JSON) JSONObject.toJSON(user));
            return true;
        } else if (status == 200) {
            logger.info("该用户存在");
            return true;
        } else {
            logger.info("status=" + status);
            saveRegistFailRecord(user, status);
            return false;
        }
    }

    /**
     * 批量注册
     *
     * @param users
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月26日 下午8:09:58
     */
    public void registryUsers(List<HuanxinUserVO> users) {
        JSON body = (JSON) JSONObject.toJSON(users);
        registry(body);
    }

    @SuppressWarnings("rawtypes")
    private void registry(final JSON body) {
        HttpUtils.getResultStatus(url + "/users", getHeaders(), body, HttpUtils.POST, false,
                new HttpUtils.HttpCallBack() {
                    @Override
                    public void resultCallBack(Object result) {
                        int code = (int) result;
                        switch (code) {
                        case 401:// 未授权[无token、token错误、token过期]
                            logger.error("未授权[无token、token错误、token过期]");
                            System.out.println("未授权[无token、token错误、token过期]");
                            saveRegistFailRecord(body, code);
                            break;
                        case 400:// 用户已存在、用户名或密码为空、用户名不合法[见用户名规则]
                            logger.error("用户已存在、用户名或密码为空、用户名不合法[见用户名规则]");
                            System.out.println("用户已存在、用户名或密码为空、用户名不合法[见用户名规则]");
                            saveRegistFailRecord(body, code);
                            break;
                        case 200:
                            // 注册成功
                            logger.info("注册成功");
                            System.out.println("注册成功");
                            break;
                        }
                    }
                });
    }

    /**
     * 删除一个用户
     *
     * @param userName
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月26日 下午7:02:33
     */
    public void deleteUser(final String userName) {
        int result = HttpUtils.getResultStatus(url + "/users/" + userName, getHeaders(), null, HttpUtils.DELETE, true,
                null);
        if (result == 200) {// 删除成功

        }
    }
    
    public void deleteUser(int limit) {
        String result = HttpUtils.getResultEntity(url + "/users?limit=" + limit, getHeaders(), null, HttpUtils.DELETE, true, null);
        System.out.println(result);
    }

    /**
     * 获取用户在线状态，1表示在线，0表示离线
     *
     * @param userName
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月29日 上午9:12:35
     */
    public int userStatus(String userName) {
        int userStatus = 0;
        JSONObject result = JSONObject.parseObject(HttpUtils.getResultEntity(url + "/users/" + userName + "/status",
                getHeaders(), null, HttpUtils.GET, true, null));
        JSONObject data = result.getJSONObject("data");
        if (data == null) {
            return 0;
        }
        String status = data.getString(userName);
        if (StringUtils.equalsIgnoreCase(status, "online")) {
            userStatus = 1;
        }
        return userStatus;
    }

    /**
     * 查询离线消息数
     *
     * @param userName
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月29日 上午9:27:00
     */
    public int offlineMsgCount(String userName) {
        int count = 0;
        JSONObject result = JSONObject.parseObject(HttpUtils.getResultEntity(
                url + "/users/" + userName + "/offline_msg_count", getHeaders(), null, HttpUtils.GET, true, null));
        JSONObject data = result.getJSONObject("data");
        count = data.getIntValue(userName);
        return count;
    }

    /**
     * 用户账号禁用
     *
     * @param userName
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月29日 上午9:33:18
     */
    public boolean freeze(String userName) {
        int status = HttpUtils.getResultStatus(url + "/users/" + userName + "/deactivate", getHeaders(), null,
                HttpUtils.GET, true, null);
        if (status == 200) {
            return true;
        } else {
            logger.error("禁用账号失败：" + status);
            return false;
        }
    }

    /**
     * 用户账号解禁
     *
     * @param userName
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月29日 上午9:37:15
     */
    public boolean unFreeze(String userName) {
        int status = HttpUtils.getResultStatus(url + "/users/" + userName + "/activate", getHeaders(), null,
                HttpUtils.GET, true, null);
        if (status == 200) {
            return true;
        } else {
            logger.error("解禁账号失败：" + status);
            return false;
        }
    }

    /**
     * 强制用户下线
     *
     * @param userName
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月29日 上午9:40:06
     */
    @SuppressWarnings("rawtypes")
    public void disconnect(final String userName) {
        HttpUtils.getResultStatus(url + "/users/" + userName + "/disconnect", getHeaders(), null, HttpUtils.GET, false,
                new HttpUtils.HttpCallBack() {
                    @Override
                    public void resultCallBack(Object result) {
                        if ((int) result == 200) {

                        } else {
                            logger.error("强制用户下线失败：" + (int) result);
                        }
                    }
                });
    }

    /**
     * 记录注册失败的用户
     *
     * @param user
     */
    private void saveRegistFailRecord(Object user, int errorCode) {
        if (user instanceof JSON) {
            String json = ((JSON) user).toJSONString();
            if (json.startsWith("[")) {// 批量注册
                JSONArray array = JSONObject.parseArray(json);
                List<TUnregistHx> hxList = JSONHelper.toBean(array, TUnregistHx.class);
                for (TUnregistHx hx : hxList) {
                    hx.setCreateDate(new Date());
                    hx.setErrorCode(errorCode);
                }
                commonTrans.batchSave(hxList);
                return;
            }
            TUnregistHx hx = JSONHelper.toBean(JSONHelper.toJSONObject(json), TUnregistHx.class);
            hx.setErrorCode(errorCode);
            hx.setCreateDate(new Date());
            commonTrans.save(hx);
        } else if (user instanceof HuanxinUserVO) {
            TUnregistHx register = new TUnregistHx(null, ((HuanxinUserVO) user).getUsername(),
                    ((HuanxinUserVO) user).getPassword(), new Date(), errorCode);
            commonTrans.save(register);
        }
    }

    /**
     * 创建群组
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月24日 下午4:06:37
     *
     */
    public String createGroup(GroupDTO group) {
        JSONObject body = (JSONObject) JSONObject.toJSON(group);
        String result = HttpUtils.getResultEntity(url + "/chatgroups", getHeaders(), body, HttpUtils.POST, true, null);
        JSONObject resultJSON = JSONObject.parseObject(result);
        ResultDTO resultEntity = JSONObject.toJavaObject(resultJSON, ResultDTO.class);

        JSONObject data = (JSONObject) resultEntity.getData();
        String id = data.getString("groupid");
        return id;
    }

    /**
     * 获取群组信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:32:54
     *
     * @param groupId
     * @return
     */
    public GroupDTO getGroup(String groupId) {
        String result = HttpUtils.getResultEntity(url + "/chatgroups/" + groupId, getHeaders(), null, HttpUtils.GET,
                true, null);
        JSONObject resultJSON = JSONObject.parseObject(result);
        ResultDTO resultEntity = JSONObject.toJavaObject(resultJSON, ResultDTO.class);

        JSONObject data_0 = ((JSONArray) resultEntity.getData()).getJSONObject(0);

        String id = data_0.getString("id");
        String name = data_0.getString("name");
        JSONArray affiliations = data_0.getJSONArray("affiliations");
        String owner = affiliations.getJSONObject(0).getString("owner");
        List<String> members = new ArrayList<>();
        for (int i = 0; i < affiliations.size(); i++) {
            JSONObject user = affiliations.getJSONObject(i);
            String ownerId = user.getString("owner");
            String memberId = user.getString("member");
            if (StringUtils.isNotBlank(ownerId)) {
                owner = ownerId;
            } else {
                members.add(memberId);
            }
        }

        boolean publicGroup = data_0.getBooleanValue("public");
        String description = data_0.getString("description");
        int maxusers = data_0.getIntValue("maxusers");

        GroupDTO group = new GroupDTO();
        group.setId(id);
        group.setGroupName(name);
        group.setOwner(owner);
        group.setMembers(members);
        group.setPublicGroup(publicGroup);
        group.setDesc(description);
        group.setMaxusers(maxusers);

        return group;
    }

    /**
     * 获取用户所属群组ID列表
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:39:46
     *
     * @param userId
     * @return
     */
    public List<String> listUserJoinGroupId(String userName) {
        String result = HttpUtils.getResultEntity(url + "/users/" + userName + "/joined_chatgroups", getHeaders(), null,
                HttpUtils.GET, true, null);
        JSONObject resultJSON = JSONObject.parseObject(result);
        ResultDTO resultEntity = JSONObject.toJavaObject(resultJSON, ResultDTO.class);

        List<String> groupIdList = new ArrayList<>();
        JSONArray data = (JSONArray) resultEntity.getData();
        for (int i = 0; i < data.size(); i++) {
            String groupId = data.getJSONObject(i).getString("groupid");
            groupIdList.add(groupId);
        }

        return groupIdList;
    }

    /**
     * 获取群组下所有用户环信ID
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:41:37
     *
     * @param groupId
     * @return
     */
    public List<GroupMemberDTO> listGroupUser(String groupId) {
        String result = HttpUtils.getResultEntity(url + "/chatgroups/" + groupId + "/users", getHeaders(), null,
                HttpUtils.GET, true, null);
        JSONObject resultJson = JSONObject.parseObject(result);
        ResultDTO resultEntity = JSONObject.toJavaObject(resultJson, ResultDTO.class);

        List<GroupMemberDTO> members = new ArrayList<>();
        JSONArray data = (JSONArray) resultEntity.getData();
        for (int i = 0; i < data.size(); i++) {
            String memberId = data.getJSONObject(i).getString("member");
            String ownerId = data.getJSONObject(i).getString("owner");

            GroupMemberDTO member = new GroupMemberDTO();
            if (StringUtils.isNotBlank(memberId)) {
                member.setMember(true);
                member.setMemberId(memberId);
            } else {
                member.setOwnerId(ownerId);
            }
            members.add(member);
        }

        return members;
    }

    /**
     * 修改群组信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:43:49
     *
     * @param id
     * @param name
     * @param desc
     * @param maxusers
     * @return
     */
    public boolean modifyGroup(String id, String name, String desc, Integer maxusers) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupname", name);
        jsonObject.put("description", desc);
        jsonObject.put("maxusers", maxusers);
        String result = HttpUtils.getResultEntity(url + "/chatgroups/" + id, getHeaders(), jsonObject, HttpUtils.PUT,
                true, null);

        JSONObject resultJSON = JSON.parseObject(result);
        ResultDTO resultEntity = JSONObject.toJavaObject(resultJSON, ResultDTO.class);
        JSONObject data_0 = (JSONObject) resultEntity.getData();

        Boolean maxusersUpdate = data_0.getBoolean("maxusers");
        Boolean groupNameUpdate = data_0.getBoolean("groupname");
        Boolean descriptionUpdate = data_0.getBoolean("description");

        boolean success = true;
        /** 这里做个非空判断，如果用户没有修改该项信息，就会获取到空对象 */
        if (maxusersUpdate != null && !maxusersUpdate) {
            success = false;
        }
        if (groupNameUpdate != null && !groupNameUpdate) {
            success = false;
        }
        if (descriptionUpdate != null && !descriptionUpdate) {
            success = false;
        }
        return success;
    }

    /**
     * 加入群组
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:45:47
     *
     * @param id
     * @param userName
     * @return
     */
    public boolean joinGroup(String id, String userName) {
        int status = HttpUtils.getResultStatus(url + "/chatgroups/" + id + "/users/" + userName, getHeaders(), null,
                HttpUtils.POST, true, null);
        boolean success = false;
        switch (status) {
        case 200:
            success = true;
            break;
        case 400:
            logger.error("被添加的IM用户不存在");
            break;
        case 403:
            logger.error("IM用户已经是群成员");
            break;
        case 404:
            logger.error("此群组id不存在");
            break;
        case 401:
            logger.error("未授权[无token、token错误、token过期]");
            break;
        default:
            logger.error("未知错误");
        }
        return success;
    }

    /**
     * 加入群组[批量]
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:48:49
     *
     * @param id
     * @param userNameList
     * @return
     */
    public boolean joinGroup(String id, List<String> userNameList) {
        /** 批量添加用户数量上限为60 */
        if (userNameList.size() > 60) {
            return false;
        }

        JSONObject body = new JSONObject();
        JSONArray userNames = new JSONArray();
        for (String userName : userNameList) {
            userNames.add(userName);
        }
        body.put("usernames", userNames);

        boolean success = false;
        int status = HttpUtils.getResultStatus(url + "/chatgroups/" + id + "/users", getHeaders(), body, HttpUtils.POST,
                true, null);
        switch (status) {
        case 200:
            success = true;
            break;
        case 400:
            logger.error("用户不存在");
            break;
        case 403:
            logger.error("所有用户均已是群成员");
            break;
        case 404:
            logger.error("群组id不存在、添加owner为member");
            break;
        case 401:
            logger.error("未授权[无token、token错误、token过期]");
            break;
        default:
            logger.error("未知错误");
        }

        return success;
    }

    /**
     * 移除群组用户
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:49:01
     *
     * @param id
     * @param userName
     * @return
     */
    public boolean removeGroupUser(String id, String userName) {
        int status = HttpUtils.getResultStatus(url + "/chatgroups/" + id + "/users/" + userName, getHeaders(), null,
                HttpUtils.DELETE, true, null);
        boolean success = false;
        switch (status) {
        case 200:
            success = true;
            break;
        case 403:
            logger.error("被移除用户不在群组里等");
            break;
        case 404:
            logger.error("被移除的IM用户不存在，此群组id不存在");
            break;
        case 401:
            logger.error("未授权[无token、token错误、token过期]");
            break;
        default:
            logger.error("未知错误");
            break;
        }
        return success;
    }

    /**
     * 移除群组用户[批量]
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:49:11
     *
     * @param id
     * @param userNameList
     * @return
     */
    public boolean removeGroupUser(String id, List<String> userNameList) {
        if (userNameList.size() == 0) {
            return false;
        }
        StringBuffer sb = new StringBuffer();
        for (String userName : userNameList) {
            /** 除了第一次添加，每次append数据的时候，追加一个逗号 */
            /** 第一次append的特征是StringBuffer的长度为0 */
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(userName);
        }

        int status = HttpUtils.getResultStatus(url + "/chatgroups/" + id + "/users/" + sb.toString(), getHeaders(),
                null, HttpUtils.DELETE, true, null);
        boolean success = false;
        switch (status) {
        case 200:
            success = true;
            break;
        case 403:
            logger.error("被移除用户不在群组里等");
            break;
        // case 404:
        // logger.error("被移除的IM用户不存在，此群组id不存在");
        // break;
        case 401:
            logger.error("未授权[无token、token错误、token过期]");
            break;
        default:
            logger.error("未知错误");
            break;
        }
        return success;
    }

    /**
     * 删除群组
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月27日 下午1:51:28
     *
     * @param id
     * @return
     */
    public boolean deleteGroup(String id) {
        int status = HttpUtils.getResultStatus(url + "/chatgroups/" + id, getHeaders(), null, HttpUtils.DELETE, true,
                null);
        boolean success = false;
        switch (status) {
        case 200:
            success = true;
            break;
        case 401:
            logger.error("未授权[无token、token错误、token过期]");
            break;
        default:
            logger.error("未知错误");
            break;
        }
        return success;
    }

    /**
     *  环信发送群组透传消息
     *  @author yuhang.weng 
     *	@DateTime 2017年3月3日 上午10:21:15
     *
     *  @param action
     *  @param receiverHuanxinId
     *  @return
     */
    public boolean sendGroupTransparentMsg(HuanxinCmdActionType action, List<String> receiverHuanxinId) {
        /** 控制接收信息群体数量为不超过20 */
        if (receiverHuanxinId.size() > 20) {
            return false;
        }

        JSONObject message = new JSONObject();
        message.put("type", "cmd");
        message.put("action", action.getAction());

        JSONObject root = new JSONObject();
        root.put("target_type", HuanxinTargetType.CHAT_GROUPS.getValue());
        root.put("target", receiverHuanxinId);
        root.put("msg", message);
        root.put("from", "admin");

        String result = HttpUtils.getResultEntity(url + "/messages", getHeaders(), root, HttpUtils.POST, true, null);

        JSONObject resultJSON = JSON.parseObject(result);
        ResultDTO resultEntity = JSONObject.toJavaObject(resultJSON, ResultDTO.class);
        JSONObject data = (JSONObject) resultEntity.getData();

        /** 遍历结果，如果有一个透传消息发送失败，就返回false */
        for (String key : data.keySet()) {
            if (!"success".equals(data.getString(key))) {
                return false;
            }
        }
        return true;
    }
    
    public List<String> getGroupMuteListFromServerWithId(String groupId) {
        String result = HttpUtils.getResultEntity(url + "/chatgroups/" + groupId + "/mute", getHeaders(), null,
                HttpUtils.GET, true, null);
        JSONObject resultJSON = JSONObject.parseObject(result);
        ResultDTO resultEntity = JSONObject.toJavaObject(resultJSON, ResultDTO.class);
        JSONArray data = (JSONArray) resultEntity.getData();
        
        List<String> muteUserIdList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String groupUserId = data.getJSONObject(i).getString("user");
            muteUserIdList.add(groupUserId);
        }
        return muteUserIdList;
    }
}
