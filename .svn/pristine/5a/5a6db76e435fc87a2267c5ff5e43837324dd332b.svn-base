package com.lifeshs.common.constants.common.sort.serve;

/**
 *  服务排序枚举
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年7月5日 下午1:55:34
 */
public enum SortEnum {

    /** 默认 */
    DEFAULT(0),
    /** 购买数量 */
    QUANTITY(1),
    /** 平均评分 */
    SCORE_AVG(2),
    /** 距离 */
    DISTANCE(3);
    
    private int value;
    
    private SortEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    /**
     *  通过值来查找对应的枚举对象
     *  @author yuhang.weng 
     *	@DateTime 2017年7月8日 下午4:10:31
     *
     *  @param value
     *  @return
     */
    public static SortEnum getSortEnum(int value) {
        for (SortEnum s : SortEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }
}
