package com.lifeshs.service1.serve;

import java.util.List;

import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.serve.ServeConditionDTO;
import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.org.TServerPO;
import com.lifeshs.vo.serve.ServeStatisticsVO;

/**
 *  服务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月28日 下午2:34:07
 */
public interface ServeService {

    /**
     *  获取所有的一级服务
     *  @author yuhang.weng 
     *	@DateTime 2017年6月28日 下午2:33:54
     *
     *  @return
     */
    List<ServeTypeDTO> listFirstServeType();

    /**
     * 获取所有的二级服务
     * @return
     */
    List<ServeTypeSecondDTO> listServeType();

    /**
     * 根据t_order.serveItemId查询所属的服务类别
     * @param serveItemId
     * @return
     */
    ServeTypeSecondDTO getByServeItemId(int serveItemId);

    /**
     * 获取服务查询的筛选条件
     * @return
     */
    public ServeConditionDTO getServeCondition();
    /**
     *  获取服务统计数据
     *  @author yuhang.weng
     *  @DateTime 2018年2月8日 下午5:43:16
     *
     *  @return
     */
    List<ServeStatisticsVO> listServeStatistics(String userNo);

    /**
     *  添加
     *  @author yuhang.weng
     *  @DateTime 2018年2月9日 下午3:59:08
     *
     *  @param serve
     *  @throws OperationException
     */
    void addServe(TServerPO serve) throws OperationException;

    /**
     *  编辑
     *  @author yuhang.weng
     *  @DateTime 2018年2月9日 下午3:58:59
     *
     *  @param serve
     *  @throws OperationException
     */
    void modifyServe(TServerPO serve) throws OperationException;

    /**
     *  删除
     *  @author yuhang.weng
     *  @DateTime 2018年2月9日 下午3:58:53
     *
     *  @param id
     *  @throws OperationException
     */
    void deleteServe(int id) throws OperationException;
}
