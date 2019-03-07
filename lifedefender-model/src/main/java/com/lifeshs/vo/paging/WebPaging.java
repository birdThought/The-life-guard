package com.lifeshs.vo.paging;

import java.util.List;

import lombok.Data;

/**
 *  Web页面分页数据模型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年11月8日 上午10:41:50
 *  @param <T>
 */
@Data
public class WebPaging<T> {
    /** 数据模型 */
    private List<T> data;
    /** 当前页码 */
    private int curPage;
    /** 页面大小 */
    private int pageSize;
    /** 总页数 */
    private int totalPage;
    /** 总记录数 */
    private int totalSize;
}
