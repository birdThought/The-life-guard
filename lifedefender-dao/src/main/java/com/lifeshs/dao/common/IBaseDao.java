package com.lifeshs.dao.common;

import java.util.List;
import java.util.Map;

import com.lifeshs.po.UranPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *  版权归
 *  TODO 公共增、删、改、查
 *  @author duosheng.mo  
 *  @DateTime 2016年4月20日 上午9:41:03
 */
@Repository("baseDao")
public interface IBaseDao {
    
    /**
     * 新增
     * 
     * @throws Exception
     */
    public int insert(Map<String, Object> param);
    
    /**
     * 批量新增
     * 
     * @throws Exception
     */
    public int batchInsert(Map<String, Object> param) throws Exception;
    
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016-4-15 上午11:17:20
     *  @serverCode 服务代码
     *  @serverComment 删除
     *
     *  @param param
     *  @return
     */
    public int delete(Map<String, Object> param);

    /**
     *  @author duosheng.mo 
     *  @DateTime 2016-4-15 上午11:17:23
     *  @serverCode 服务代码
     *  @serverComment 批量删除
     *
     *  @param param
     *  @return
     */
    public int batchDelete(Map<String, Object> param);
    
    /**
     * 更新
     * 
     * @throws Exception
     */
    public int update(Map<String, Object> param) throws Exception;
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016-4-15 下午05:02:25
     *  @serverCode 服务代码
     *  @serverComment 查询
     *
     *  @param param
     *  @return
     *  @throws Exception
     */
    public List<Map<String,Object>> select(Map<String, Object> param);
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016-4-15 下午10:15:52
     *  @serverCode 服务代码
     *  @serverComment 查询 By Map
     *
     *  @param param
     *  @return
     *  @throws Exception
     */
    public Map<String,Object> selectByMap(Map<String, Object> param);
    
    /**
     *  查询记录数
     *  @author yuhang.weng 
     *  @DateTime 2016年8月12日 上午11:07:06
     *
     *  @param tableName
     *  @param propertyName
     *  @param value
     *  @return
     */
    public Integer selectEntityAmount(@Param("table") String tableName, @Param("key") String propertyName, @Param("value") Object value);
    
    /**
     * 根据查询条件
     * @author zhansi.Xu
     * @DateTime 2016年9月7日
     * @serverComment
     */
    public int getCount(Map<String, Object> condition);

    /**
     * 分页获取实体
     * @param params
     * @return
     */
    public List<Map<String,Object>> findEntityByPage(Map<String, Object> params);

    /**
     * 获取最后一条数据
     * @param userId
     * @return
     */
    UranPO getLastData(@Param("userId") int userId);
}