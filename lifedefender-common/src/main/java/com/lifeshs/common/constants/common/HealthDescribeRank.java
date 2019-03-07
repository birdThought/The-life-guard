package com.lifeshs.common.constants.common;

/**
 *  健康描述级别
 *  @author yuhang.weng  
 *  @DateTime 2017年2月17日 上午10:35:29
 */
public enum HealthDescribeRank {

    /** 低 */
    less(1, "低"),
    
    /** 偏低 */
    min(2, "偏低"),
    
    /** 正常 */
    normal(3, "正常"),
    
    /** 偏高 */
    max(4, "偏高"),
    
    /** 高 */
    more(5, "高");
    
    private int _value;
    private String _name;
    
    private HealthDescribeRank(int value, String name) {
        _value = value;
        _name = name;
    }
    
    public int getRankValue() {
        return _value;
    }
    
    public String getRankDescription() {
        return _name;
    }
}
