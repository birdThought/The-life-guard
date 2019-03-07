package com.lifeshs.service.contacts;

import java.util.List;
import java.util.Map;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.entity.member.TUserContacts;

/**
 *  版权归
 *  TODO 联系人接口
 *  @author duosheng.mo  
 *  @DateTime 2016年5月26日 下午4:28:28
 */
public interface IContactsService {

    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月30日 下午3:38:32
     *  @serverComment 查询所有联系人
     *
     *  @param userId
     *  @return 
     */
    List<TUserContacts> findAllContacts(int userId);
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月30日 下午3:38:32
     *  @serverComment 查询亲情号和SOS
     *
     *  @param userId
     *  @return 
     */
    List<TUserContacts> findFamilyAndSos(int userId);
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月30日 下午3:38:32
     *  @serverComment 查询第一亲情号
     *
     *  @param userId
     *  @return 
     */
    String findFirstFamily(int userId);
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年6月15日 上午11:52:53
     *  @serverCode 查询亲情号和SOS(去重)
     *
     *  @param userId
     *  @return 号码1，号码2，。。。
     */
    String findFamilySos(int userId);
    
    
    /**
     *  @author zhiguo.lin 
     *  @DateTime 2016年8月19日 下午5:31:53
     *  @serverComment 添加预警&联系人
     *
     *  @param name
     *  @param mobile
     *  @return
     *  @throws OperationException
     */
    Integer addContact(Integer userId, String name, String mobile, int contactType) throws OperationException;

    /**
     *  <p> 修改联系人
     *  @author dachang.luo
     *  @DateTime 2016年6月17日上午11:31:18
     *
     *  @param contacts
     *  @return
     *  @throws Exception
     */
    void modifyContacts(TUserContacts contacts) throws OperationException;
    
    /**
     *  <p> 删除联系人
     *  @author dachang.luo
     *  @DateTime 2016年6月17日下午12:04:24
     *
     *  @param id
     *  @return
     *  @throws Exception
     */
    boolean deleteContacts(int id); 
    
    /**
     * 
    * @author: dachang.luo
    * @Title: getContactsById
    * @Description: TODO 根据id查询联系人
    * @DateTime: 2016年6月17日下午5:22:48
    * @param @return
    * @return TUserContacts
    * @throws
     */
    TUserContacts getContactsById(int id);



    /**
     * 获取所有联系人的map对象
     *  @author dachang.luo 
     *  @DateTime 2016年6月30日 上午11:10:11
     *  @serverComment 服务注解
     *
     *  @param id
     *  @return
     */
    List<Map<String , Object>> getAllContactsMap(int id);
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月15日上午10:54:19
     * @serverComment 获取短信预警接受人号码
     * @param 
     */
    String findReceiveSms(int userId);
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月26日上午11:51:38
     * @serverComment 根据用户ID和终端类型获取亲情号码
     * @param 
     */
    List<TUserContacts> findFamilyByTerminalType(int userId, String terminalType);
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月26日下午7:49:46
     * @serverComment 删除亲情号码
     * @param 
     */
    boolean deleteFamily(TUserContacts userContacts);
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月27日上午9:42:24
     * @serverComment 查询亲情号码
     * @param 
     */
    TUserContacts findSos(Integer userId, String terminalType);
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月27日下午3:52:20
     * @serverComment 修改sos号码
     * @param 
     */
    Boolean modifySos(Integer userId, String terminalType, String contactNumber);
//  /**
//   * 
//  * @author: dachang.luo
//  * @Title: findFamily
//  * @Description: TODO 查询亲情号
//  * @DateTime: 2016年6月17日下午3:22:46
//  * @param @param userId
//  * @param @return
//  * @return List<TUserContacts>
//  * @throws
//   */
//  List<TUserContacts> findFamily(int userId)throws Exception;
//  
//  /**
//   * 
//  * @author: dachang.luo
//  * @Title: findSOS
//  * @Description: TODO 查询预警号码
//  * @DateTime: 2016年6月17日下午3:23:48
//  * @param @param userId
//  * @param @return
//  * @return List<TUserContacts>
//  * @throws
//   */
//  List<TUserContacts> findSOS(int userId)throws Exception;
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月13日下午5:47:19
     * @serverComment 修改接受短信联系人
     * @param 
     */
    void modifyContactReceiveSMS(int id, int userId);
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月16日下午5:29:30
     * @serverComment 删除
     * @param 
     */
    ServiceMessage deleteContactReceiveSMS(int id, int userId);
}
