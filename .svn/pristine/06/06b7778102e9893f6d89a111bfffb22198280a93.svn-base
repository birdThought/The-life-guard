package com.lifeshs.dao.serve;

import java.util.List;

import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.org.TServerPO;
import com.lifeshs.pojo.org.service.ServiceCommonDTO;
import com.lifeshs.vo.serve.ServeStatisticsVO;

/**
 *  服务（底层）
 *  @author yuhang.weng
 *  @version 2.0
 *  @DateTime 2017年6月21日 上午10:28:44
 */
@Repository(value = "serveDao")
public interface ServeDao {

    /**
     * 查询所有服务类别（含二级服务类别）
     * @return
     */
    List<ServeTypeDTO> listFullServeType();

    /**
     *  根据ID获取二级服务类别
     *  @author dengfeng
     *	@DateTime 2018年2月3日 上午10:32:10
     *
     *  @param id
     *  @return
     */
    ServeTypeSecondDTO getServeType(@Param("id") int id);

    /**
     * 获取所有二级服务类别
     * @return
     */
    List<ServeTypeSecondDTO> listServeType();

    /**
     *  根据一级ID获取二级服务类别列表
     *  @author dengfeng
     *	@DateTime 2018年2月3日 上午10:32:10
     *
     *  @param code
     *  @return
     */
    List<ServeTypeDTO> listServeTypeByParentId(@Param("code") String code);

    /**
     *  获取所有的一级服务类别
     *  @author yuhang.weng 
     *	@DateTime 2017年6月28日 下午2:27:32
     *
     *  @return
     */
    List<ServeTypeDTO> listFirstServeType();

    /**
     *  根据t_order.serveItemId查询所属的服务类别
     *  @author dengfeng
     *	@DateTime 2018年2月3日 上午10:32:10
     *
     *  @param id
     *  @return
     */
    ServeTypeSecondDTO getByServeItemId(@Param("id") int id);

    /**
     *  获取一个通用的项目对象
     *  @author yuhang.weng 
     *	@DateTime 2017年6月27日 下午4:12:22
     *
     *  @param projectCode
     *  @return
     */
    ServiceCommonDTO getCommonProject(@Param("projectCode") String projectCode);

    /**
     *  获服务统计数据
     *  @author yuhang.weng 
     *  @DateTime 2018年2月8日 下午5:42:24
     *
     *  @return
     */
    List<ServeStatisticsVO> findServeStatistics(@Param("userNo") String userNo);
    
    /**
     *  添加
     *  @author yuhang.weng 
     *  @DateTime 2018年2月9日 下午4:00:06
     *
     *  @param serve
     *  @return
     */
    int addServe(TServerPO serve);
    
    /**
     *  修改
     *  @author yuhang.weng 
     *  @DateTime 2018年2月9日 下午4:01:11
     *
     *  @param serve
     *  @return
     */
    int updateServe(TServerPO serve);
    
    /**
     *  删除
     *  @author yuhang.weng 
     *  @DateTime 2018年2月9日 下午4:01:02
     *
     *  @param id
     *  @return
     */
    int delServe(@Param("id") int id);
}
