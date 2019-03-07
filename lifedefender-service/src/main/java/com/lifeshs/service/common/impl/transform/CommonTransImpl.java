package com.lifeshs.service.common.impl.transform;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.dao.common.IBaseDao;
import com.lifeshs.entity.log.TLogLogin;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.FileUtils;
import com.lifeshs.utils.ReflectUtils;


/**
 * 版权归 TODO 公共增、删、改、查 (对象和Map之间的转换)
 *
 * @author duosheng.mo
 * @DateTime 2016年4月20日 上午9:41:03
 */
@Component("commonTrans")
public class CommonTransImpl implements ICommonTrans {

    private static final Logger logger = Logger.getLogger(CommonTransImpl.class);

    @Autowired
    private IBaseDao baseDao;

    public <T> int batchSave(List<T> entitys) {
        // TODO Auto-generated method stub
        int num = 0;
        Map<String, Object> param = ReflectUtils.reflectBatchInsert(entitys);
        try {
            num = baseDao.batchInsert(param);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return num;
    }

    public <T> int save(T entity) {
        // TODO Auto-generated method stub
        int num = 0;
        Map<String, Object> param = null;
        param = ReflectUtils.reflectInsert(entity);
        num = baseDao.insert(param);
        //设置返回ID
        ReflectUtils.reflectSetId(entity, param);
        return num;
    }

    public <T> int delete(T entity) {
        int num = 0;
        try {
            Map<String, Object> param = ReflectUtils.reflectDelete(entity);
            num = baseDao.delete(param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return num;
    }

    public <T> int deleteAllEntitie(List<T> entities) {
        // TODO Auto-generated method stub
        int num = 0;
        try {
            Map<String, Object> param = ReflectUtils
                    .reflectBatchDelete(entities);
            num = baseDao.batchDelete(param);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return num;
    }

    public <T> int deleteEntityById(Class<?> entityName, Serializable id) {
        // TODO Auto-generated method stub
        int num = 0;
        try {
            Map<String, Object> param = ReflectUtils.reflectDelById(entityName, id);
            num = baseDao.delete(param);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return num;
    }
    
    public int deleteByProperty(Class<?> clazz, Map<String, Object> params) {
        int num = 0;
        
        String tableName = ReflectUtils.reflectTableName(clazz);
        Map<String, Object> param = new HashMap<>();
        param.put("table", tableName);
        param.put("column", params);
        num = baseDao.delete(param);
        
        return num;
    }

    public <T> int saveOrUpdate(T entity) {
        // TODO Auto-generated method stub
        int num = 0;
        try {
            Map<String, Object> param = ReflectUtils.saveOrUpdate(entity);
            if (param != null) {
                if (param.containsKey("condition")) {
                    num = baseDao.update(param);
                } else {
                    num = baseDao.insert(param);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return num;
    }

    @SuppressWarnings("unchecked")
    public <T> int updateEntitie(T pojo) {
        // TODO Auto-generated method stub
        int num = 0;
        Map<String, Object> param = ReflectUtils.saveOrUpdate(pojo);
        if (param != null) {
            if (param.containsKey("condition")) {
                Map<String, Object> condition = (Map<String, Object>) param
                        .get("condition");
                if (condition != null && !condition.isEmpty()) {
                    try {
                        num = baseDao.update(param);
                    } catch(Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                } else {
                    throw new NullPointerException("更新条件主键不能为空");
                }
            } else {
                throw new NullPointerException("更新条件主键不能为空");
            }
        }
        return num;
    }

    /**
     * 根据map更新
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment
     */
    public int updateByMap(Map<String, Object> condition) {
        int num = 0;
        try {
            num = baseDao.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findByProperty(Class<T> entityClass,
                                      String propertyName, Object value) {
        // TODO Auto-generated method stub
        Object obj = null;
        List<T> listObj = new ArrayList<T>();
        try {
            Map<String, Object> param = ReflectUtils.queryEntityByProperty(
                    entityClass, propertyName, value);
            List<Map<String, Object>> resMap = baseDao.select(param);
            if (resMap != null && !resMap.isEmpty()) {
                for (Map<String, Object> mapObj : resMap) {
                    obj = ReflectUtils.getBean(mapObj, entityClass);
                    listObj.add((T) obj);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return listObj;
    }

    @SuppressWarnings("unchecked")
    public <T> T findUniqueByProperty(Class<T> entityClass,
                                      String propertyName, Object value) {
        // TODO Auto-generated method stub
        Object obj = null;
        try {
            Map<String, Object> param = ReflectUtils.queryEntityByProperty(
                    entityClass, propertyName, value);
            Map<String, Object> resMap = baseDao.selectByMap(param);
            if (resMap != null && !resMap.isEmpty()) {
                obj = ReflectUtils.getBean(resMap, entityClass);
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return (T) obj;
    }

    public <T> T get(Class<T> class1, Serializable id) {
        // TODO Auto-generated method stub
        return getEntity(class1, id);
    }

    @SuppressWarnings("unchecked")
    public <T> T getEntity(Class<?> entityName, Serializable id) {
        // TODO Auto-generated method stub
        Object obj = null;
        try {
            Map<String, Object> param = ReflectUtils
                    .queryEntity(entityName, id);
            Map<String, Object> resMap = baseDao.selectByMap(param);
            if (resMap != null && !resMap.isEmpty()) {
                obj = ReflectUtils.getBean(resMap, entityName);
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadAll(Class<T> entityClass) {
        // TODO Auto-generated method stub
        Object obj = null;
        List<T> listObj = new ArrayList<T>();
        try {
            Map<String, Object> param = ReflectUtils
                    .reflectLoadAll(entityClass);
            List<Map<String, Object>> resMap = baseDao.select(param);

            long startTime = System.currentTimeMillis();
            if (resMap != null && !resMap.isEmpty()) {
                for (Map<String, Object> mapObj : resMap) {
                    obj = ReflectUtils.getBean(mapObj, entityClass);
                    listObj.add((T) obj);
                }
            }
            long endTime = System.currentTimeMillis();
            float excTime = (float) (endTime - startTime) / 1000;
            System.out.println(" Reflect 执行时间：" + excTime + "s");
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return listObj;
    }

    public <T> List<Map<String, Object>> loadAllByMap(Class<T> entityClass) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> resMap = null;
        try {
            Map<String, Object> param = ReflectUtils
                    .reflectLoadAll(entityClass);

            long startTime = System.currentTimeMillis();

            resMap = baseDao.select(param);

            long endTime = System.currentTimeMillis();
            float excTime = (float) (endTime - startTime) / 1000;
            System.out.println(" Reflect 执行时间：" + excTime + "s");
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return resMap;
    }

    public <T> Map<String, Object> getByMap(Class<?> entityName, Serializable id) {
        // TODO Auto-generated method stub
        Map<String, Object> resMap = null;
        try {
            Map<String, Object> param = ReflectUtils
                    .queryEntity(entityName, id);
            resMap = baseDao.selectByMap(param);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return resMap;
    }

    public <T> Map<String, Object> findUniqueByPropertyByMap(
            Class<T> entityClass, String propertyName, Object value) {
        // TODO Auto-generated method stub
        Map<String, Object> resMap = null;
        try {
            Map<String, Object> param = ReflectUtils.queryEntityByProperty(
                    entityClass, propertyName, value);
            resMap = baseDao.selectByMap(param);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return resMap;
    }

    public <T> List<Map<String, Object>> findByPropertyByMap(
            Class<T> entityClass, String propertyName, Object value) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> resMap = null;
//      try {
        Map<String, Object> param = ReflectUtils.queryEntityByProperty(
                entityClass, propertyName, value);
        resMap = baseDao.select(param);
//      } catch (Exception e) {
//          // TODO: handle exception
//          logger.error(e.getMessage(),e);
//      }
        return resMap;
    }

    public <T> List<Map<String, Object>> findByMap(Class<T> entityClass, Map<String, Object> params) {
        List<Map<String, Object>> resMap = null;
        Map<String, Object> param = ReflectUtils.queryEntityEnclosureParamsWholeColumn(entityClass, params);
        resMap = baseDao.select(param);
        return resMap;
    }

    @Override
    public <T> List<Map<String, Object>> findByMapWithKeys(Class<T> entityClass, List<String> keys,
                                                           Map<String, Object> params) {
        List<Map<String, Object>> resMap = null;
        try {
            Map<String, Object> param = ReflectUtils.queryEntityEnclosureParamsAssignColumn(entityClass, keys, params);
            resMap = baseDao.select(param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resMap;
    }

    @Override
    public <T> Integer getEntityAmount(Class<T> entityClass, String propertyName, Object value) {
        String tableName = ReflectUtils.reflectTableName(entityClass);
        Integer amount = baseDao.selectEntityAmount(tableName, propertyName, value);
        return amount;
    }

    @Override
    public <T> int getCount(Class<T> clazz, Map<String, Object> condition) {
        Map<String, Object> resMap = new HashMap<>();
        Table table = clazz.getAnnotation(Table.class);
        resMap.put("table", table.name());
        resMap.put("condition",condition);
        return baseDao.getCount(resMap);
    }

    @Override
    public <T> List<T> findEntityByPage(Class<T> clazz, @Nullable Map<String, Object> condition, int start, int max) {
        Map<String, Object> param = condition != null ? ReflectUtils.queryEntityEnclosureParamsWholeColumn(clazz, condition) : ReflectUtils.reflectLoadAll(clazz);
        param.put("startPage", ((start - 1) * max));
        param.put("maxLimit", max);
        List<Map<String, Object>> entity = baseDao.findEntityByPage(param);
        if (entity == null || entity.isEmpty())
            return null;
        List<T> entities = new ArrayList<T>();
        for (Map<String, Object> map : entity) {
            entities.add(ReflectUtils.getBean(map, clazz));
        }
        return entities;
    }

    @Override
    public <T> List<T> findEntityByPageDesc(Class<T> clazz, @Nullable Map<String, Object> condition, int start, int max, String descParam) {
        Map<String, Object> param = condition != null ? ReflectUtils.queryEntityEnclosureParamsWholeColumn(clazz, condition) : ReflectUtils.reflectLoadAll(clazz);
        param.put("startPage", ((start - 1) * max));
        param.put("maxLimit", max);
        if(descParam!=null)
            param.put("desc",descParam);
        List<Map<String, Object>> entity = baseDao.findEntityByPage(param);
        if (entity == null || entity.isEmpty())
            return null;
        List<T> entities = new ArrayList<T>();
        for (Map<String, Object> map : entity) {
            entities.add(ReflectUtils.getBean(map, clazz));
        }
        return entities;
    }


    @Override
    public void saveLogin(TLogLogin login) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("userId", login.getUserId());
//        params.put("userName", login.getUserName());
//
//        List<Map<String, Object>> logs = findByMap(TLogLogin.class, params);
//        if (logs.size() > 0) {
//            Map<String, Object> uniqueLog = logs.get(0);
//
//            int logId = (int) uniqueLog.get("id");
//
//            login.setId(logId);
//        }
        save(login);

        /**
         * 跟宇航沟通,没必要保存登录文件,直接注释掉
         * edit by wuj 2017-08-28 14:49:01
         */

        /*// 登录文件保存
        Date date = new Date();
        
        String rootFilePath = "/life/lifeshs/lifekeepers_files/log/";
        
        *//** log-id file *//*
        File logIdFile = FileUtils.getFile(rootFilePath + "1.log");
        
        int latestLogId = getLastLogId(logIdFile) + 1;
        
        *//** log file *//*
        String parentFolderName = DateTimeUtilT.yearMonthWithoutForm(date);
        String fileName = rootFilePath + parentFolderName + "/" + DateTimeUtilT.date(date) + ".log";
        
        File file = FileUtils.getFile(fileName);
        String content = FileUtils.readFile(file);
        
        JSONObject root = new JSONObject();
        if (StringUtils.isNotBlank(content)) {
            root = JSONObject.parseObject(content);
        } else {
            root.put("data", new JSONArray());
        }

        root.put("latest-id", latestLogId); // 此处保存的是最新的一个ID
        root.put("latest-date", DateTimeUtilT.dateTime(date));
        
        JSONArray root_data = root.getJSONArray("data");
        
        *//** 记录 *//*
        JSONObject data_child = new JSONObject(true);
        data_child.put("id", latestLogId);  // 记录ID 在原有的基础上加1
        data_child.put("userId", login.getUserId());    // 用户ID
        data_child.put("userName", login.getUserName());    // 用户登录名
        data_child.put("userType", login.getUserType());    // 用户类型
        data_child.put("terminalType", login.getTerminalType());    // 设备类型
        data_child.put("orgId", login.getOrgId());  // 机构ID
        data_child.put("ip", login.getIp());    // 登录IP
        data_child.put("time", DateTimeUtilT.dateTime(login.getLoginTime()));   // 记录时间
        
        root_data.add(data_child);
        
        FileUtils.writeFile(file, root.toString(), false);
        
        JSONObject logIdRoot = new JSONObject();
        logIdRoot.put("latest-id", latestLogId);
        logIdRoot.put("latest-date", DateTimeUtilT.dateTime(date));
        
        FileUtils.writeFile(logIdFile, logIdRoot.toString(), false);*/
    }
    
    /**
     *  获取最后记录的ID
     *  @author yuhang.weng 
     *  @DateTime 2017年2月13日 上午11:06:57
     *
     *  @param file
     *  @return
     */
    private Integer getLastLogId(File file) {
        String content = FileUtils.readFile(file);
        
        // 如果记录文件内容为空，返回默认ID(值为0)
        if (StringUtils.isBlank(content)) {
            return 0;
        }
        
        JSONObject root = JSONObject.parseObject(content);
        int latestId = root.getIntValue("latest-id");
        return latestId;
    }
}
