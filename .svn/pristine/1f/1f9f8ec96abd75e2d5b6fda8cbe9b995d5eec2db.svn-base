package com.lifeshs.dto.manager.serve;

import java.util.List;

/**
 * 功能描述
 * Created by dengfeng on 2017/7/5 0005.
 */
public class GetLessonGroupData {
    private String projectCode;    //服务项目id
    private String photo;    //服务头像
    private String projectName;    //服务名称
    private List<CourseTime> courseTime;    //课程时间
    private String descript;    //课堂描述
    private int silence;    //0表示可以正常聊天，1表示禁言
//    private List<LessonUserInfo> userList;   //用户群组
//    private List<LessonUserInfo> employeeList;   //服务师群组
    private List<LessonUserInfoTemp> groupUsers;  //先按老接口格式来吧

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<CourseTime> getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(List<CourseTime> courseTime) {
        this.courseTime = courseTime;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getSilence() {
        return silence;
    }

    public void setSilence(int silence) {
        this.silence = silence;
    }

//    public List<LessonUserInfo> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<LessonUserInfo> userList) {
//        this.userList = userList;
//    }
//
//    public List<LessonUserInfo> getEmployeeList() {
//        return employeeList;
//    }
//
//    public void setEmployeeList(List<LessonUserInfo> employeeList) {
//        this.employeeList = employeeList;
//    }

    public List<LessonUserInfoTemp> getGroupUsers() {
        return groupUsers;
    }


    public void setGroupUsers(List<LessonUserInfoTemp> groupUsers) {
        this.groupUsers = groupUsers;
    }

    public static class CourseTime {
        private String week;        //周（1-7对应星期一到星期天）
        private String startTime;	   //开始时间（格式 hh:mm）

        public CourseTime(String week, String startTime){
            this.week = week;
            this.startTime = startTime;
        }
        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
    }

}
