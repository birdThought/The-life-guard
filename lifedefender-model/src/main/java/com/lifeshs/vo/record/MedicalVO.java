package com.lifeshs.vo.record;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  用户病历信息</p>
 *  包含信息</p>
 *  科室名称：√</p>
 *  病程：√</p>
 *  病程图片：√</p>
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月14日 上午11:06:06
 */
@Getter
@Setter
@ToString
public class MedicalVO extends MedicalBasicVO {

    /** 病程 */
    private List<MedicalCourseVO> courseList;
}
