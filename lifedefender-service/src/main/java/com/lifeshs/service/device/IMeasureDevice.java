package com.lifeshs.service.device;

import java.util.List;

import com.lifeshs.pojo.page.PaginationDTO;

/**
 * 定义测量设备的数据查询接口,所有测量设备业务类实现此接口
 * 
 * @author dengfeng
 * @DateTime 2016-5-13 下午02:29:30
 */
public interface IMeasureDevice<T> {

    /**
     * 获取设备 一天、星期、一月、三月 的数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月24日下午4:56:24
     * @serverComment
     * @param dateType:查询时间类型(一天、星期、一月、三月)
     */
    public List<T> getMeasureDataWithDate(Integer userId, String deviceType, String dateType);

    /**
     * 获取分页数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月26日下午2:34:46
     * @serverComment
     * @param
     */
    public PaginationDTO<T> getMeasureDataWithSplit(Integer userId, String deviceType, int pageIndex, int pageSize);
}
