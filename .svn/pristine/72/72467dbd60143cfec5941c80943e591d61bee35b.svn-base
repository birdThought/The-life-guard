package com.lifeshs.common.constants.app.cache.table;

/**
 * 表映射枚举类
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月22日 下午3:39:04
 */
public enum RelationEnum {

    HOBBY(1, "t_data_hobby", "兴趣爱好标签"),

    RECOMMEND_ORG_SERVE(2, "t_data_recommend_org_serve", "推荐(机构)服务");

    /** 值 */
    private int value;
    /** 表名称 */
    private String tableName;
    /** 描述文字 */
    private String remark;

    private RelationEnum(int value, String tableName, String remark) {
        this.value = value;
        this.tableName = tableName;
        this.remark = remark;
    }

    public int value() {
        return this.value;
    }

    public String tableName() {
        return this.tableName;
    }

    public String remark() {
        return this.remark;
    }

    public static RelationEnum get(String tableName) {
        for (RelationEnum r : RelationEnum.values()) {
            if (r.tableName().equals(tableName)) {
                return r;
            }
        }
        return null;
    }
}
