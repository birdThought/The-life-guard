package com.lifeshs.entity.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_org_group_lesson
 */
@Table(name = "t_org_group_lesson", schema = "")
@SuppressWarnings("serial")
public class TOrgGroupLesson implements Serializable {

    private Integer id;

    /** 群组ID */
    private Integer groupId;

    /** 星期设定,从低到高代表星期一到星期日,开启_1,关闭_0 */
    private String weeks;

    /** 开课时间 */
    private String startTime;

    public TOrgGroupLesson() {
        super();
    }

    public TOrgGroupLesson(Integer id, Integer groupId, String weeks, String startTime) {
        super();
        this.id = id;
        this.groupId = groupId;
        this.weeks = weeks;
        this.startTime = startTime;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "groupId", nullable = false, length = 11)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Column(name = "weeks", nullable = false, length = 7)
    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    @Column(name = "startTime", nullable = false, length = 10)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}