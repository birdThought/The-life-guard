package com.lifeshs.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 版权归
 * TODO Map转实体类
 *
 * @author duosheng.mo
 * @DateTime 2016-2-26 下午10:55:31
 */
public class ReflectUtils {

    private static final Logger logger = Logger.getLogger(ReflectUtils.class);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> T getBean(Map<String, Object> param, Class<T> clazz) {

        if (param == null || param.size() == 0) {
            return null;
        }

        Object value = null;
        Class[] paramTypes = new Class[1];
        Object obj = null;
        try {
            //创建实例
            obj = clazz.newInstance();
            Field[] f = clazz.getDeclaredFields();
            List<Field[]> flist = new ArrayList<Field[]>();
            flist.add(f);

            Class superClazz = clazz.getSuperclass();
            while (superClazz != null) {
                f = superClazz.getFields();
                flist.add(f);
                superClazz = superClazz.getSuperclass();
            }

            for (Field[] fields : flist) {
                for (Field field : fields) {
                    String fieldName = field.getName();
                    if (!"serialVersionUID".equals(fieldName)) {
                        paramTypes[0] = field.getType();
                        Method setMethod = null;
                        //调用相应对象的set方法
                        String methodName = "set" + StringUtil.firstUpperCase(fieldName);
                        String getMethodName = "get" + StringUtil.firstUpperCase(fieldName);
                        setMethod = clazz.getMethod(methodName, paramTypes);
                        Column column = clazz.getMethod(getMethodName).getAnnotation(Column.class);//加上这个执行10条记录要慢两秒
                        if (column != null) {
                            value = param.get(column.name());
                            if (value != null) {
                                setMethod.invoke(obj, ConvertUtil.getValue(value.toString(), fieldName, paramTypes[0]));
//                              clazz.getMethod(methodName.toString(), value.getClass()).invoke(obj, value);
                            }
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        } catch (SecurityException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
        return (T) obj;
    }


    /**
     * @param <T>
     * @param listObj
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-14 下午09:51:46
     * @serverCode 服务代码
     * @serverComment 批量记录写入
     */
    public static <T> Map<String, Object> reflectBatchInsert(List<T> listObj) {
        if (listObj != null) {
            Map<String, Object> resMap = new LinkedHashMap<String, Object>();
            List<Map<String, Object>> listColumnMap = new ArrayList<Map<String, Object>>();
            StringBuffer columnNames = new StringBuffer();
            boolean bool = true;
            for (Object obj : listObj) {
                Class<?> objClass = obj.getClass();
                if (bool) {
                    Table table = objClass.getAnnotation(Table.class);
                    resMap.put("table", table.name());
                }
                Map<String, Object> columnMap = getBatchInsertColumns(obj, objClass, columnNames, bool);
                listColumnMap.add(columnMap);
                bool = false;
            }
            List<String> cs = new ArrayList<>();
            if (columnNames.length() > 0) {
                for (String c : columnNames.substring(0, columnNames.length() - 1).split(",")) {
                    if (!"id".equals(c)) {
                        cs.add(c);
                    }
                }
                resMap.put("columnNames", cs);
            }
            resMap.put("columns", listColumnMap);
            return resMap;
        }
        return null;
    }

    private static Map<String, Object> getBatchInsertColumns(Object obj, Class<?> objClass, StringBuffer columnNames, boolean bool) {
        Map<String, Object> columnMap = getColumns(obj, objClass, columnNames, bool, false);
        return columnMap;
    }

    private static Map<String, Object> getInsertColumns(Object obj, Class<?> objClass) {
        Map<String, Object> columnMap = getColumns(obj, objClass, null, false, false);
        return columnMap;
    }

    /**
     * 获取字段
     * @param obj
     * @param objClass
     * @param columnNames 数据库字段名
     * @param bool        是否反回字段名
     * @param condition   是否返回ID作为条件
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:07:26
     * @serverComment 该方法的(where)条件一定是id
     */
    private static Map<String, Object> getColumns(Object obj, Class<?> objClass, StringBuffer columnNames, boolean bool, boolean condition) {
        if (obj == null || objClass == null) {
            throw new NullPointerException();
        }
        
//        List<String> columnNameList = new ArrayList<>();
        Map<String, Object> columnMap = new LinkedHashMap<String, Object>();
        Map<String, Object> conditionMap = new LinkedHashMap<String, Object>();
        Field[] fields = objClass.getDeclaredFields();
//        if (columnNames != null) {
//            for (String columnName : columnNames.toString().split(",")) {
//                columnNameList.add(columnName);
//            }
//        }
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();
                if (!"serialVersionUID".equals(name)) {
                    String getMethodName = "get" + StringUtil.firstUpperCase(name);
//                  String setMethodName = "set"+StringUtil.firstUpperCase(name);  
                    Method method = objClass.getMethod(getMethodName);
                    javax.persistence.Column column = method.getAnnotation(javax.persistence.Column.class);
                    
                    // 避免没有Column标签的参数被扫描到
                    if (column != null) {
                        if (bool) {
//                            columnNameList.add(column.name());
                            columnNames.append(column.name() + ",");
                        }
                        Object value = method.invoke(obj);
                        /**
                         *  判断该column是否为id
                         *  如果是id，需要判断condition是否true
                         *  为true的话，就把id作为查询条件condition保存到返回结果中
                        */
                        // column 是 id
                        if ("id".equalsIgnoreCase(column.name())) {
                            // 判断是否需要添加id作为(查询、查询字段)条件
                            if (condition) {
                                // where查询条件
                                conditionMap.put("id", value);
                                columnMap.put("condition", conditionMap);
                                // id的值
                                columnMap.put("id", value);
                            } else {
//                                columnNameList.remove("id");
                            }
                        } else {
                            columnMap.put(column.name(), value);
                        }
//                        if ("id".equalsIgnoreCase(column.name())) {
//                            if (value == null || 0 == Integer.valueOf(value.toString())) {
////                              value = UUIDGenerator.generate();
////                              objClass.getMethod(setMethodName, value.getClass()).invoke(obj, value);
//                            } else {
//                                conditionMap.put(column.name(), value);
//                            }
//                        } else {
//                            
//                        }
//                        if (condition) {
//                            if (!conditionMap.isEmpty()) {
//                                // 该方法的(where)条件一定是id
//                                columnMap.put("condition", conditionMap);
//                            }
//                        }
//                        columnMap.put(column.name(), value);
                    }
                    
                }
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }
        
//        columnNames = new StringBuffer();
//        for (int i = 0; i < columnNameList.size(); i++) {
//            if (i == 0) {
//                columnNames.append(columnNameList.get(0));
//            } else {
//                columnNames.append("," + columnNameList.get(i));
//            }
//        }
        return columnMap;
    }


    /**
     * @param obj
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-14 下午09:20:19
     * @serverCode 服务代码
     * @serverComment 单条记录写入
     */
    public static Map<String, Object> reflectInsert(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        Map<String, Object> resMap = new LinkedHashMap<String, Object>();
        Class<?> objClass = obj.getClass();
        Table table = objClass.getAnnotation(Table.class);
        resMap.put("table", table.name());
        Map<String, Object> columnMap = getInsertColumns(obj, objClass);
        columnMap.remove("id");
        resMap.put("column", columnMap);
        return resMap;
    }


    /**
     * @param obj
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午01:54:56
     * @serverCode 服务代码
     * @serverComment 删除实体参数
     */
    public static Map<String, Object> reflectDelete(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        Map<String, Object> resMap = new LinkedHashMap<String, Object>();
        Class<?> objClass = obj.getClass();
        Table table = objClass.getAnnotation(Table.class);
        resMap.put("table", table.name());
        Map<String, Object> columnMap = delete(obj, objClass);
        if (columnMap == null || columnMap.isEmpty()) {
            throw new NullPointerException();
        }
        resMap.put("column", columnMap);
        return resMap;
    }

    /**
     * @param objClass
     * @param objVal
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午12:43:39
     * @serverCode 服务代码
     * @serverComment 删除实体By Id
     */
    public static Map<String, Object> reflectDelById(Class<?> objClass, Object objVal) {
        if (objClass == null) {
            throw new NullPointerException();
        }
        Map<String, Object> resMap = new LinkedHashMap<String, Object>();
        Map<String, Object> columnMap = new LinkedHashMap<String, Object>();
        Table table = objClass.getAnnotation(Table.class);
        resMap.put("table", table.name());
        Field[] fields = objClass.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();
                if (!"serialVersionUID".equals(name)) {
                    String getMethodName = "get" + StringUtil.firstUpperCase(name);
                    Method method = objClass.getMethod(getMethodName);
                    javax.persistence.Column column = method
                            .getAnnotation(javax.persistence.Column.class);
                    if ("id".equalsIgnoreCase(column.name())) {
                        if (objVal == null || StringUtils.isBlank(String.valueOf(objVal))) {
                            throw new NullPointerException("主键ID不能为空");
                        }
                        columnMap.put(column.name(), objVal);
                    }
                }
            }
            resMap.put("column", columnMap);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }
        return resMap;
    }

    /**
     * @param <T>
     * @param listObj
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午01:57:01
     * @serverCode 服务代码
     * @serverComment 批量删除实体
     */
    public static <T> Map<String, Object> reflectBatchDelete(List<T> listObj) {
        if (listObj != null) {
            Map<String, Object> resMap = new LinkedHashMap<String, Object>();
            List<Map<String, Object>> listColumnMap = new ArrayList<Map<String, Object>>();
            for (Object obj : listObj) {
                Class<?> objClass = obj.getClass();
                Table table = objClass.getAnnotation(Table.class);
                resMap.put("table", table.name());
                Map<String, Object> columnMap = delete(obj, objClass);
                if (columnMap != null && !columnMap.isEmpty()) {
                    listColumnMap.add(columnMap);
                }
            }
            resMap.put("column", listColumnMap);
            return resMap;
        }
        return null;
    }

    /**
     * @param obj
     * @param objClass
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午02:16:07
     * @serverCode 服务代码
     * @serverComment 获取删除实体数据库
     */
    private static Map<String, Object> delete(Object obj, Class<?> objClass) {
        if (obj == null || objClass == null) {
            throw new NullPointerException();
        }
        Map<String, Object> columnMap = new LinkedHashMap<String, Object>();
        Field[] fields = objClass.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();
                if (!"serialVersionUID".equals(name)) {
                    String getMethodName = "get" + StringUtil.firstUpperCase(name);
                    Method method = objClass.getMethod(getMethodName);
                    javax.persistence.Column column = method.getAnnotation(javax.persistence.Column.class);
                    Object value = method.invoke(obj);
                    if ("id".equalsIgnoreCase(column.name())) {
                        if (value == null || StringUtils.isBlank(value.toString())) {
                            throw new NullPointerException("主键ID不能为空");
                        }
                    }
                    if (value != null)
                        columnMap.put(column.name(), value);
                }
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }
        return columnMap;
    }

    /**
     * @param obj
     * @param saveOrUpdate true:更新、false:新增   注：此参数做为返回参数，默认为false
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午03:00:47
     * @serverCode 服务代码
     * @serverComment 保存或更新实体
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> saveOrUpdate(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        Map<String, Object> resMap = new LinkedHashMap<String, Object>();
        Class<?> objClass = obj.getClass();
        Table table = objClass.getAnnotation(Table.class);
        resMap.put("table", table.name());
        Map<String, Object> columnMap = getSaveOrUpdateColumns(obj, objClass, true);
        columnMap.remove("id");
        if (columnMap != null) {
            //也可以根据 Map 中是否包condition来判断是更新还是新增
            if (columnMap.containsKey("condition")) {
                Map<String, Object> conditionMap = (Map<String, Object>) columnMap.get("condition");
                if (conditionMap == null || conditionMap.isEmpty()) {
                    throw new NullPointerException("条件不能为空");
                }
                
                Set<Entry<String, Object>> entrySet = conditionMap.entrySet();
                Iterator<Map.Entry<String, Object>> it = entrySet.iterator();
                while (it.hasNext()) {
                    Entry<String, Object> e = it.next();
                    if (e.getValue() == null) {
                        it.remove();
                    }
                }
                if (!conditionMap.isEmpty()) {
                    resMap.put("condition", conditionMap);
                }
                columnMap.remove("condition");
            }
            resMap.put("column", columnMap);
        }
        return resMap;
    }

    /**
     * @param obj
     * @param objClass
     * @param condition 是否返回ID作为条件
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午03:30:37
     * @serverCode 服务代码
     * @serverComment 获取字段
     */
    private static Map<String, Object> getSaveOrUpdateColumns(Object obj, Class<?> objClass, boolean condition) {
        Map<String, Object> columnMap = getColumns(obj, objClass, null, false, condition);
        return columnMap;
    }

    /**
     * @param objClass
     * @param id
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午06:04:30
     * @serverCode 服务代码
     * @serverComment 按主键查询
     */
    public static Map<String, Object> queryEntity(Class<?> objClass, Serializable id) {
        if (id == null) {
            throw new NullPointerException();
        }
        return queryEntity(objClass, id, false, null, null);
    }

    /**
     * @param objClass
     * @param id
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午09:52:03
     * @serverCode 服务代码
     * @serverComment 属性名查询实体
     */
    public static Map<String, Object> queryEntityByProperty(Class<?> objClass, String propertyName, Object value) {
        return queryEntity(objClass, null, true, propertyName, value);
    }

    /**
     * @param objClass
     * @param id
     * @param bool         是否按属性查询
     * @param propertyName 属性名
     * @param value        值
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-15 下午05:52:05
     * @serverCode 服务代码
     * @serverComment 服务注解
     */
    private static Map<String, Object> queryEntity(Class<?> objClass, Serializable id, boolean bool, String propertyName, Object value) {
        if (objClass == null) {
            throw new NullPointerException();
        }
        if (bool) {
            if (propertyName == null || StringUtils.isBlank((String) propertyName)) {
                throw new NullPointerException("属性名为空");
            }
        }
        boolean propertyNameBool = false;//属性名是否存在
        Map<String, Object> resMap = new LinkedHashMap<String, Object>();
        Map<String, Object> conditionMap = new LinkedHashMap<String, Object>();
        StringBuffer columnNames = new StringBuffer();
        Table table = objClass.getAnnotation(Table.class);
        resMap.put("table", table.name());
        Field[] fields = objClass.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();
                if (!"serialVersionUID".equals(name)) {
                    String getMethodName = "get" + StringUtil.firstUpperCase(name);
                    Method method = objClass.getMethod(getMethodName);
                    javax.persistence.Column column = method.getAnnotation(javax.persistence.Column.class);
                    if (column != null) {
                        if (!bool && id != null) {
                            if ("id".equalsIgnoreCase(column.name())) {
                                conditionMap.put(column.name(), id);
                            }
                        }
                        columnNames.append(column.name() + ",");
                        if (bool) {
                            if (name.equals(propertyName)) {
                                propertyNameBool = true;
                                conditionMap.put(column.name(), value);
                            }
                        }
                    }
                }
            }
            if (bool && !propertyNameBool) {
                throw new NullPointerException("属性名不存在");
            }
            if (columnNames.length() > 0) {
                String columnName = columnNames.substring(0, columnNames.length() - 1);
                resMap.put("columnNames", columnName);
            }
            resMap.put("condition", conditionMap);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }
        return resMap;
    }

    /**
     * @param objClass
     * @return
     * @author duosheng.mo
     * @DateTime 2016-4-16 上午12:08:30
     * @serverCode 服务代码
     * @serverComment 查询所有实体
     */
    public static Map<String, Object> reflectLoadAll(Class<?> objClass) {
        return queryEntity(objClass, null, false, null, null);
    }

    /**
     * @param obj
     * @author duosheng.mo
     * @DateTime 2016年5月23日 下午4:32:50
     * @serverComment 设置实体ID值
     */
    public static void reflectSetId(Object obj, Map<String, Object> param) {
        if (obj == null) {
            throw new NullPointerException();
        }
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String name = field.getName();
            if (!"serialVersionUID".equals(name)) {
                String getMethodName = "get" + StringUtil.firstUpperCase(name);
                String setMethodName = "set" + StringUtil.firstUpperCase(name);
                Method method = null;
                try {
                    method = objClass.getMethod(getMethodName);
                } catch (NoSuchMethodException e) {
                    logger.error("找不到该方法:" + e.getMessage());
                } catch (SecurityException e) {
                    logger.error("无权限访问资源:" + e.getMessage());
                }
                Column column = method.getAnnotation(Column.class);
                if (column != null) {
                    if ("id".equalsIgnoreCase(column.name())) {
                        if (param.containsKey(column.name())) {
                            Object valObj = param.get(column.name());
                            Method setMethod = null;

                            try {
                                setMethod = objClass.getMethod(setMethodName, method.getReturnType());
                            } catch (NoSuchMethodException e) {
                                logger.error("找不到该方法:" + e.getMessage());
                            } catch (SecurityException e) {
                                logger.error("无权限访问资源:" + e.getMessage());
                            }

                            try {
                                setMethod.invoke(obj, valObj);
                            } catch (IllegalAccessException e) {
                                logger.error("不允许调用该方法（比如private类型的方法）:" + e.getMessage());
                            } catch (IllegalArgumentException e) {
                                logger.error("传递的参数不正确:" + e.getMessage());
                                logger.error("参数:setMethodName(" + setMethodName + ") method.getReturnType(" + method.getReturnType() + ")");
                            } catch (InvocationTargetException e) {
                                logger.error("调用的方法抛出异常:" + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @param objectClass
     * @param params      查询参数
     * @param keys        查询字段
     * @return
     * @author yuhang.weng
     * @DateTime 2016年5月19日 下午5:22:08
     * @serverComment 自定义查询条件选定字段查询
     */
    public static Map<String, Object> queryEntityEnclosureParamsAssignColumn(Class<?> objectClass, List<String> keys, Map<String, Object> params) {
        return queryEntityEnclosureParams(objectClass, keys, params, false);
    }

    /**
     * @param objectClass
     * @param params      查询参数
     * @return
     * @author yuhang.weng
     * @DateTime 2016年5月19日 下午5:22:11
     * @serverComment 自定义查询条件全字段查询
     */
    public static Map<String, Object> queryEntityEnclosureParamsWholeColumn(Class<?> objectClass, Map<String, Object> params) {
        return queryEntityEnclosureParams(objectClass, null, params, true);
    }

    /**
     * @param objectClass 实体类的对象类型
     * @param params      使用key/value的形式封装的查询条件
     * @param bool        是否全字段段查询
     * @return {table=table_name, columnNames=xxx,xxx,xxx , condition={key=value,key=value}}
     * @author yuhang.weng
     * @DateTime 2016年5月18日 下午2:53:28
     * @serverComment 根据传递的obejctClass查找实体类，并且将params共同封装到查询条件condition中
     * @version 0.3
     * @versionDescription 0.1 param条件查询
     * @versionDescription 0.2    添加key结果限制
     * @versionDescription 0.3    取消对大小写的转换
     */
    private static Map<String, Object> queryEntityEnclosureParams(Class<?> objectClass, List<String> keys, Map<String, Object> params, boolean bool) {
        if (objectClass == null) {
            throw new NullPointerException("实体不存在");
        }
        Map<String, Object> resMap = new LinkedHashMap<String, Object>();    // 函数返回结果的map对象
        Map<String, Object> conditionMap = new LinkedHashMap<String, Object>();    // 查询条件存储的map对象
        StringBuffer columnNames = new StringBuffer();
        Table table = objectClass.getAnnotation(Table.class);    // 获取表
        resMap.put("table", table.name());    // 将表名保存到resMap中
        Field[] fields = objectClass.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();
                if (!"serialVersionUID".equals(name)) {    // 将serialVersionUID字段剔除
                    // 通过获取getXxx()方法的注解Column来获取类包含的属性
                    String getMethodName = "get" + StringUtil.firstUpperCase(name);
                    Method method = objectClass.getMethod(getMethodName);
                    javax.persistence.Column column = method.getAnnotation(javax.persistence.Column.class);
                    if (column != null) {
                        columnNames.append(column.name() + ",");    // 将属性名连接为 xxx,xxx,xxx 的形式保存在columnNames中
                    }
                }
            }
            if (columnNames.length() > 0) {
                String columnName_temp = columnNames.substring(0, columnNames.length() - 1);    // 将columnNames最后一个逗号去掉
                // 按属性查询
                if (!bool) {
                    if (keys.size() > 0) {
                        StringBuffer columnName_assign = new StringBuffer();
                        String[] tempString = columnName_temp.split(",");
                        List<String> columnList = new ArrayList<String>(tempString.length);
                        for (String str : tempString) {
                            columnList.add(str);
                        }
                        for (String str : keys) {
                            if (!columnList.contains(str)) {
                                throw new NullPointerException("查询字段名称" + str + "不存在");
                            }
                            columnName_assign.append(str + ",");
                        }
                        columnName_temp = columnName_assign.substring(0, columnName_assign.length() - 1);
                    } else {
                        throw new NullPointerException("查询字段不能为空");
                    }
                }
                resMap.put("columnNames", columnName_temp);    // 如果全字段查询则直接使用columnName_temp就可以了
            }
            /**
             * 1.遍历params(Map集合)中的key值
             * 2.将StringBuffer中的数据保存到List中
             * 3.使用List的contains(key)方法来检测是否包含key
             * 4.如果某一个params中的key不存在于columnNames中，提示属性名不存在
             * 注意事项： 如果数据库对大小写有严格的校验，需要修改部分代码
             * */
            String[] tempString = columnNames.toString().split(",");
            List<String> tempList = new ArrayList<String>();
            for (int i = 0; i < tempString.length; i++) {
                tempList.add(tempString[i]);
            }

            Set<String> tempSet = params.keySet();
            // 避免空参数传递
            if (tempSet.size() > 0) {
                Iterator<String> it = tempSet.iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    if (!tempList.contains(key)) {
                        throw new NullPointerException("属性名不存在");    // 如果在反射取属性的时候，发现param中包含的key并不存在于该类的属性中，就抛出异常提示 属性名不存在
                    } else {
                        // 检测包含在params中的属性值是否为空
                        if (StringUtils.isNotEmpty(String.valueOf(params.get(key)))) {
                            conditionMap.put(key, params.get(key));    // 将数据存储到conditionMap中
                        } else {
                            throw new NullPointerException("属性值为空");
                        }
                    }
                }
            } else {
                throw new NullPointerException("查询参数不能为空");
            }
            resMap.put("condition", conditionMap);
        } catch (SecurityException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
        }
        return resMap;
    }

    /**
     * <p> 获取Class对应的TABLE名
     *
     * @param objClass
     * @return
     * @author yuhang.weng
     * @DateTime 2016年8月12日 上午11:00:37
     */
    public static String reflectTableName(Class<?> objClass) {
        if (objClass == null) {
            throw new NullPointerException();
        }
        Table table = objClass.getAnnotation(Table.class);
        return table.name();
    }

    /**
     * 批量复制
     *
     * @param dest  数据源
     * @param clazz 要复制到的类
     * @param <T>
     * @return
     */
    public static <T, P> List<T> batchCopyBeans(List<P> dest, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Object object : dest) {
            T entity = null;
            try {
                entity = clazz.newInstance();
                BeanUtils.copyProperties(entity, object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            list.add(entity);
        }
        return list;
    }


}
