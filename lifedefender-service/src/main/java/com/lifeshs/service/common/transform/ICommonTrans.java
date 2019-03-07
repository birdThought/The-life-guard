package com.lifeshs.service.common.transform;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.lifeshs.entity.log.TLogLogin;
import com.lifeshs.pojo.log.SensitiveOperationLogDTO;

/**
 * 版权归
 * TODO 公共增、删、改、查
 *
 * @author duosheng.mo
 * @DateTime 2016年4月20日 上午9:41:03
 * @Repository("commonDao")
 */
public interface ICommonTrans {

    /**
     * @param <T>
     * @param entity
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:26:55
     * @serverCode 服务代码
     * @serverComment 保存实体
     */
    <T> int save(T entity);

    /**
     * @param <T>
     * @param entity
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:27:13
     * @serverCode 服务代码
     * @serverComment 保存或更新实体
     */
    <T> int saveOrUpdate(T entity);

    /**
     * @param <T>
     * @param entity
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:27:31
     * @serverCode 服务代码
     * @serverComment 删除实体
     */
    <T> int delete(T entity);

    /**
     * @param <T>
     * @param entitys
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:27:41
     * @serverCode 服务代码
     * @serverComment 批量添加实体
     */
    <T> int batchSave(List<T> entitys);

    /**
     * @param <T>
     * @param class1
     * @param id
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:28:13
     * @serverCode 服务代码
     * @serverComment 根据实体名称和主键获取实体
     */
    <T> T get(Class<T> class1, Serializable id);

    /**
     * @param <T>
     * @param entityName
     * @param id
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:29:21
     * @serverCode 服务代码
     * @serverComment 根据实体名称和主键获取实体
     */
    <T> T getEntity(Class<?> entityName, Serializable id);

    /**
     * @param entityName
     * @param id
     * @return
     * @author duosheng.mo
     * @DateTime 2016年4月21日 下午12:10:34
     * @serverCode 服务代码
     * @serverComment 根据实体名称和主键获数据返回Map
     */
    <T> Map<String, Object> getByMap(Class<?> entityName, Serializable id);

    /**
     * @param <T>
     * @param entityClass
     * @param propertyName
     * @param value
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:29:37
     * @serverCode 服务代码
     * @serverComment 根据实体名称和字段名称和字段值获取唯一记录
     */
    <T> T findUniqueByProperty(Class<T> entityClass,
                                      String propertyName, Object value);

    /**
     * @param entityClass
     * @param propertyName
     * @param value
     * @return
     * @author duosheng.mo
     * @DateTime 2016年4月21日 下午12:09:54
     * @serverCode 服务代码
     * @serverComment 根据实体名称和字段名称和字段值获取唯一记录返回Map
     */
    <T> Map<String, Object> findUniqueByPropertyByMap(Class<T> entityClass,
                                                             String propertyName, Object value);

    /**
     * @param <T>
     * @param entityClass
     * @param propertyName
     * @param value
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:29:46
     * @serverCode 服务代码
     * @serverComment 按属性查找对象列表.
     */
    <T> List<T> findByProperty(Class<T> entityClass,
                                      String propertyName, Object value);

    /**
     * @param entityClass
     * @param propertyName
     * @param value
     * @return
     * @author duosheng.mo
     * @DateTime 2016年4月21日 下午12:08:32
     * @serverCode 服务代码
     * @serverComment 按属性查找返回Map
     */
    <T> List<Map<String, Object>> findByPropertyByMap(Class<T> entityClass,
                                                             String propertyName, Object value);

    /**
     * @param <T>
     * @param entityClass
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:29:57
     * @serverCode 服务代码
     * @serverComment 加载全部实体
     */
    <T> List<T> loadAll(final Class<T> entityClass);

    /**
     * @param entityClass
     * @return
     * @author duosheng.mo
     * @DateTime 2016年4月21日 下午12:01:22
     * @serverCode 服务代码
     * @serverComment 加载全部数据返回Map
     */
    <T> List<Map<String, Object>> loadAllByMap(final Class<T> entityClass);


    /**
     * @param <T>
     * @param entityName
     * @param id
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:30:10
     * @serverCode 服务代码
     * @serverComment 删除实体主键删除
     */
    <T> int deleteEntityById(Class<?> entityName, Serializable id);

    /**
     * @param <T>
     * @param entities
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:30:23
     * @serverCode 服务代码
     * @serverComment 删除实体集合
     */
    <T> int deleteAllEntitie(List<T> entities);
    
    /**
     *  删除
     *  @author yuhang.weng 
     *  @DateTime 2016年12月26日 下午4:42:48
     *
     *  @param clazz
     *  @param params
     *  @return
     */
    int deleteByProperty(Class<?> clazz, Map<String, Object> params);

    /**
     * @param <T>
     * @param pojo
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:30:34
     * @serverCode 服务代码
     * @serverComment 更新指定的实体
     */
    <T> int updateEntitie(T pojo);

    /**
     * 根据map更新
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    int updateByMap(Map<String, Object> condition);

    /**
     * @param entityClass
     * @param params
     * @return
     * @author yuhang.weng
     * @DateTime 2016年5月18日 下午5:26:15
     * @serverComment 按照params中的参数查询Map集合
     */
    <T> List<Map<String, Object>> findByMap(Class<T> entityClass, Map<String, Object> params);

    /**
     * @param entityClass
     * @param keys
     * @param params
     * @return
     * @author yuhang.weng
     * @DateTime 2016年5月20日 上午11:13:46
     * @serverComment 按照params中的参数查询指定字段(keys)的实体集合
     */
    <T> List<Map<String, Object>> findByMapWithKeys(Class<T> entityClass, List<String> keys, Map<String, Object> params);

    /**
     * <p> 根据实体名称和主键获取实体记录总数
     *
     * @param entityClass  实体类型
     * @param propertyName 属性名
     * @param value        属性值
     * @return
     * @author yuhang.weng
     * @DateTime 2016年8月12日 上午10:23:48
     */
    <T> Integer getEntityAmount(Class<T> entityClass, String propertyName, Object value);

    /**
     * 根据查询条件
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月7日
     * @serverComment
     */
    public<T> int getCount(Class<T> clazz,Map<String, Object> condition);

    /**
     * 根据实体类分页查询
     * @param clazz 实体名称
     * @param condition 条件 可为空
     * @param start 起始页数
     * @param end 结束页数
     * @param <T>
     * @return
     */
    <T> List<T> findEntityByPage(Class<T> clazz, @Nullable Map<String, Object> condition, int start, int end);

    /**
     * 根据实体类分页查询(根据某个字段倒序)
     * @param clazz
     * @param condition
     * @param start
     * @param end
     * @param descParam
     * @param <T>
     * @return
     */
    <T> List<T> findEntityByPageDesc(Class<T> clazz, @Nullable Map<String, Object> condition, int start, int end,String descParam);

    /**
     * 保存登录记录
     *
     * @param login
     * @author yuhang.weng
     * @DateTime 2016年12月8日 下午7:41:11
     */
    void saveLogin(TLogLogin login);
}
