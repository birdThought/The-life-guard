package com.lifeshs.service.org.impl.lesson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.HuanxinCmdActionType;
import com.lifeshs.dao.order.IOrderDao;
import com.lifeshs.dao.org.lesson.ILessonGroupDao;
import com.lifeshs.dao.org.manage.OrgManageDao;
import com.lifeshs.entity.org.TOrgGroup;
import com.lifeshs.entity.org.TOrgGroupOrguser;
import com.lifeshs.pojo.huanxin.GroupDTO;
import com.lifeshs.pojo.order.UserOrderDTO;
import com.lifeshs.pojo.org.group.CourseTimeDTO;
import com.lifeshs.pojo.org.group.LessonDTO;
import com.lifeshs.pojo.org.group.LessonGroupOrgUserDTO;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.org.lesson.ILessonGroup;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 * 版权归 TODO 健康课堂实现
 * 
 * @author wenxian.cai
 * @datetime 2017年3月1日上午10:04:58
 */

@Service("lessonGroup")
public class LessonGroupImpl implements ILessonGroup {

    @Autowired
    ILessonGroupDao lessonDao;

    @Autowired
    private HuanXinService huanxinService;

    @Autowired
    private ICommonTrans common;

    @Autowired
    private OrgManageDao orgManageDao;

    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<LessonDTO> listServeLesson(int orgServeId) {
        List<LessonDTO> list = lessonDao.listServeLesson(orgServeId);
        // for (int i = 0; i < list.size(); i++) {
        //
        // String description =
        // huanxinService.getGroup(list.get(i).getHuanxinId()).getDesc();
        // if ("nothing left here".equals(description)) {
        // description = "暂无群描述";
        // }
        // list.get(i).setDescription(description);
        // }

        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addLesson(LessonDTO lesson, int userId, String ownerHuanxinId) {
        /** 注册环信群组 */
        GroupDTO group = new GroupDTO();
        group.setOwner(ownerHuanxinId);
        group.setApproval(false);
        group.setDesc(lesson.getDescription());
        group.setGroupName(lesson.getName());
        group.setMaxusers(200);
        group.setPublicGroup(true);
        String huanxinId = huanxinService.createGroup(group);
        /** 查询默认群组数量，如果没有默认群组，就给当前创建的群组设置为默认群组 */
        Map<String, Object> condition = new HashMap<>();
        condition.put("orgServeId", lesson.getOrgServeId());
        int groupCount = common.getCount(TOrgGroup.class, condition);
        if (groupCount == 0) {
            lesson.setDefaultGroup(true);
        } else {
            lesson.setDefaultGroup(false);
        }
        /** 在数据库中保存群组信息 */
        lesson.setHuanxinId(huanxinId);
        lesson.setCreatorId(userId);
        lessonDao.addLesson(lesson);
        List<CourseTimeDTO> courseTimeDTOs = lesson.getCourseTime();
        /** 保存课程时间 */
        if (courseTimeDTOs.size() > 0) {
            lessonDao.addCourseTime(lesson.getId(), courseTimeDTOs);
        }
        /** 把管理员加入到群组中 */
        TOrgGroupOrguser user = new TOrgGroupOrguser();
        user.setGroupId(lesson.getId());
        user.setCreateDate(new Date());
        user.setOrgUserId(userId);
        common.save(user);
    }

    @Override
    public Boolean deleteLesson(int id) {
        LessonDTO lessonDTO = lessonDao.getLesson(id);
        String huanxinId = lessonDTO.getHuanxinId();
        boolean bool = false;
        bool = huanxinService.deleteGroup(huanxinId);
        if (bool) {
            bool = lessonDao.deleteLesson(id);
        }
        if (bool) {
            lessonDao.deleteCourseTime(id);
        }
        if (bool) {
            // 把有效的订单修改为已完成
            orderDao.updateValidServeOrderToComplete(id, null);
            // 把未支付的订单修改为支付失败
            orderDao.updateBeforePayServeOrderToPayFailded(id, lessonDTO.getOrgServeId(), null);
        }
        return bool;
    }

    @Override
    public LessonDTO getLesson(int id) {
        LessonDTO lessonDTO = lessonDao.getLesson(id);

        /** 根据群组创建者的ID查询该用户的环信ID */
        String huanxinOwnerId = "";
        int creatorId = lessonDTO.getCreatorId();
        for (LessonGroupOrgUserDTO orgUser : lessonDTO.getOrgUsers()) {
            if (orgUser.getId() == creatorId) {
                huanxinOwnerId = orgUser.getUserCode();
                break;
            }
        }
        /** 设置群组服务师与群主身份 */
        for (LessonGroupOrgUserDTO orgUser : lessonDTO.getOrgUsers()) {
            String userCode = orgUser.getUserCode();
            /** 通过环信ID判断用户身份 */
            if (userCode.equals(huanxinOwnerId)) {
                orgUser.setGroupUserType(0);
            } else {
                orgUser.setGroupUserType(1);
            }
        }
        return lessonDTO;
    }

    @Override
    public boolean updateLesson(LessonDTO lesson) {
        /** 获取原群组信息 */
        int lessonId = lesson.getId();
        TOrgGroup lesson_old = getGroup(lessonId);

        String huanxinId = lesson_old.getHuanxinId();
        /** 原群组名称 */
        String groupName_old = lesson_old.getName();
        /** 原群组描述 */
        String description_old = lesson_old.getDescription();
        /** 原图片地址 */
        String photo_old = lesson_old.getPhoto();

        /** 新的群组名称 */
        String groupName = lesson.getName();
        /** 新的群组描述 */
        String description = lesson.getDescription();
        /** 新的图片地址 */
        String photo = lesson.getPhoto();

        /** 由于连接环信服务器速度缓慢，在这里判断数据是否做过修改，再决定是否发送修改请求 */
        boolean updateToHuanxinServer = false;
        if (groupName != null && !StringUtils.equals(groupName, groupName_old)) {
            updateToHuanxinServer = true;
        }
        if (description != null && !StringUtils.equals(description, description_old)) {
            updateToHuanxinServer = true;
        }

        /** 判断是否更新开课时间字段 */
        boolean updateCourseTime = (lesson.getCourseTime() != null);

        /** 环信更新状态，默认为成功,如果访问环信更新失败就停止更新数据库操作，并且返回失败 */
        boolean successUpdate = true;
        /** 更新环信群组信息 */
        if (updateToHuanxinServer) {
            successUpdate = huanxinService.modifyGroup(huanxinId, groupName, description, null);
        }

        if (!successUpdate) {
            return false;
        }

        /** 更新数据库中的群组信息 */
        /** 加一层判断避免空更新 */
        if (photo != null || groupName != null || lesson.getSilence() != null || description != null) {
            lessonDao.updateLesson(lesson.getId(), photo, groupName, lesson.getSilence(), description);
            /** 当图片修改时，删除原图 */
            if (photo_old != null) {
                ImageUtilV2.delImg(photo_old);
            }
        }

        /** 判断是否更新开课时间，如果涉及到开课时间字段，就删除原开课时间信息，保存新的开课时间信息 */
        if (updateCourseTime) {
            /** 删除群组开课时间信息 */
            lessonDao.deleteCourseTime(lesson.getId());
            /** 提交新的开课时间信息 */
            lessonDao.addCourseTime(lesson.getId(), lesson.getCourseTime());
        }

        return true;
    }

    @Override
    public boolean addOrgUser(int groupId, List<Integer> orgUserIds) {
        /** 获取群组环信ID */
        TOrgGroup group = getGroup(groupId);
        String huanxinId = group.getHuanxinId();
        /** 获取待加入用户环信ID */
        List<String> userNameList = orgManageDao.listUserCode(orgUserIds);
        /** 环信添加组员 */
        boolean success = huanxinService.joinGroup(huanxinId, userNameList);
        if (!success) {
            return false;
        }
        lessonDao.addOrgUser(groupId, orgUserIds);
        return true;
    }

    @Override
    public boolean removeOrgUser(int groupId, List<Integer> orgUserIds) {
        /** 获取群组环信ID */
        TOrgGroup group = getGroup(groupId);
        String huanxinId = group.getHuanxinId();
        /** 获取待移除用户环信ID */
        List<String> userNameList = orgManageDao.listUserCode(orgUserIds);
        /** 环信移除组员 */
        boolean success = huanxinService.removeGroupUser(huanxinId, userNameList);
        if (!success) {
            return false;
        }
        lessonDao.removeOrgUser(groupId, orgUserIds);
        return true;
    }

    @Override
    public boolean gag(int groupId) {
        TOrgGroup group = getGroup(groupId);
        String huanxinId = group.getHuanxinId();

        List<String> receiverHuanxinId = new ArrayList<>();
        receiverHuanxinId.add(huanxinId);
        boolean success = huanxinService.sendGroupTransparentMsg(HuanxinCmdActionType.GAG, receiverHuanxinId);
        if (!success) {
            return false;
        }

        lessonDao.updateLesson(groupId, null, null, true, null);
        return true;
    }

    @Override
    public boolean removeGag(int groupId) {
        TOrgGroup group = getGroup(groupId);
        String huanxinId = group.getHuanxinId();

        List<String> receiverHuanxinId = new ArrayList<>();
        receiverHuanxinId.add(huanxinId);
        boolean success = huanxinService.sendGroupTransparentMsg(HuanxinCmdActionType.GAG_REMOVE, receiverHuanxinId);
        if (!success) {
            return false;
        }

        lessonDao.updateLesson(groupId, null, null, false, null);
        return true;
    }

    private TOrgGroup getGroup(int id) {
        TOrgGroup group = common.getEntity(TOrgGroup.class, id);
        return group;
    }

    @Override
    public boolean removeUser(int groupId, int userId) {
        LessonDTO lessonDTO = lessonDao.getLesson(groupId);
        String userCode = "";
        /** 通过课堂遍历用户列表，获取用户的环信ID，如果找不到该用户的信息，直接返回失败 */
        for (UserOrderDTO userOrderDTO : lessonDTO.getUserOrders()) {
            if (userOrderDTO.getUserId().intValue() == userId) {
                userCode = userOrderDTO.getUserCode();
            }
        }
        if (StringUtils.isBlank(userCode)) {
            return false;
        }
        /** 环信退出群组 */
        boolean success = huanxinService.removeGroupUser(lessonDTO.getHuanxinId(), userCode);
        if (!success) {
            return false;
        }
        /** 有效订单修改为已完成 */
        orderDao.updateValidServeOrderToComplete(groupId, userId);
        return true;
    }

    @Override
    public List<LessonDTO> listLesson(List<String> huanxinIds, int memberId) {
        List<LessonDTO> lessonDTOs = lessonDao.listMemberLesson(memberId);
        Iterator<LessonDTO> iterator = lessonDTOs.iterator();
        /** 
         * 遍历List，把不存在huanxinIds中的对象移除
         * 注意，如果huanxinIds中包含用户不存在的群组，同样也不会获得相关信息
        */
        while (iterator.hasNext()) {
            LessonDTO lessonDTO = iterator.next();
            String huanxinId = lessonDTO.getHuanxinId();
            if (!huanxinIds.contains(huanxinId)) {
                iterator.remove();
            }
        }
        return lessonDTOs;
    }

    @Override
    public List<LessonDTO> listServeUserLesson(List<String> huanxinIds, int userId) {
        List<LessonDTO> lessonDTOs = lessonDao.listServeUserLesson(userId);
        Iterator<LessonDTO> iterator = lessonDTOs.iterator();
        while (iterator.hasNext()) {
            LessonDTO lessonDTO = iterator.next();
            String huanxinId = lessonDTO.getHuanxinId();
            if (!huanxinIds.contains(huanxinId)) {
                iterator.remove();
            }
        }
        return lessonDTOs;
    }
}
