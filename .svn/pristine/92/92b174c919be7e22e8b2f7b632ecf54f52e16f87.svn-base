package com.lifeshs.service.contacts.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.ContactTerminalType;
import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.contacts.IContactsDao;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserContacts;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.utils.Toolkits;

/**
 * 版权归 TODO C3业务的实现类
 * 
 * @author duosheng.mo
 * @DateTime 2016年5月26日 下午5:18:37
 */
@Service("contactsService")
public class ContactsServiceImpl extends CommonServiceImpl implements IContactsService {

    @Autowired
    private IContactsDao contactsDao;

    @Autowired
    ITerminalService terminalService;

    @Override
    public List<TUserContacts> findAllContacts(int userId) {
        // TODO Auto-generated method stub
        List<TUserContacts> lsitContacts = this.commonTrans.findByProperty(TUserContacts.class, "userId", userId);
        return lsitContacts;
    }

    @Override
    public List<TUserContacts> findFamilyAndSos(int userId) {
        // TODO Auto-generated method stub
        return contactsDao.findFamilyAndSos(userId);
    }

    @Override
    public String findFamilySos(int userId) {
        // TODO Auto-generated method stub
        List<TUserContacts> lsitContacts = contactsDao.findFamilyAndSos(userId);
        String mobiles = "";
        // 去重
        Set<String> mobile = new HashSet<String>();
        if (lsitContacts != null) {
            for (TUserContacts uc : lsitContacts) {
                mobile.add(uc.getContactNumber());
            }
        }
        for (String mob : mobile) {
            // 判断不能为空并且是手机号
            if (StringUtils.isNotBlank(mob) && Toolkits.verifyPhone(mob)) {
                mobiles += mob + ",";
            }
        }
        if (StringUtils.isNotBlank(mobiles)) {
            mobiles = mobiles.substring(0, mobiles.length() - 1);
        }
        return mobiles;
    }

    @Override
    public String findFirstFamily(int userId) {
        // TODO Auto-generated method stub
        List<TUserContacts> lsitContacts = contactsDao.findFamilyAndSos(userId);
        String mobiles = "";
        if (lsitContacts != null) {
            for (TUserContacts uc : lsitContacts) {

                int t = uc.getContactType();

                if ((isHealthPackageFamilyContactNumber(t) || isC3FamilyContactNumber(t))
                        && Toolkits.verifyPhone(uc.getContactNumber())) {
                    mobiles = uc.getContactNumber();
                    break;
                }
            }
        }
        return mobiles;
    }

    private boolean isHealthPackageFamilyContactNumber(int contactType) {
        int hfValue = ContactTerminalType.HEALTH_PACKAGE_FAMILY.value();
        return ((contactType & hfValue) == hfValue);
    }

    private boolean isC3FamilyContactNumber(int contactType) {
        int cfValue = ContactTerminalType.C3_FAMILY.value();
//        return ((contactType & cfValue) == cfValue);
        boolean bool = ((contactType | cfValue) == contactType);
        return bool;
    }

    private boolean isC3SOSContactNumber(int contactType) {
        int csValue = ContactTerminalType.C3_SOS.value();
//        return ((contactType & csValue) == csValue);
        return ((contactType | csValue) == csValue);
    }

    private boolean isC3ContactNumber(int contactType) {
        if (isC3FamilyContactNumber(contactType))
            return true;
        if (isC3SOSContactNumber(contactType))
            return true;
        return false;
    }

    @Override
    public String findReceiveSms(int userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("receiveSMS", true);
        List<Map<String, Object>> list = commonTrans.findByMap(TUserContacts.class, map);
        if (list.size() > 0) {
            return (String) list.get(0).get("contactNumber");
        } else {
            return "";
        }
    }

    @Override
    public Integer addContact(Integer userId, String name, String contactNumber, int contactType) throws OperationException {
        // 获取用户的通讯录
        List<TUserContacts> contacts = contactsDao.findFamilyAndSos(userId);

        if (isRepeatContact(contacts, contactNumber, null)) {
            if (isC3SOSContactNumber(contactType)) {
                /**
                 *  在这里，如果提交的号码类型为C3，并且在这个用户的联系人列表中，找到了重复的号码
                 *  为这个重复的号码，赋予C3的号码类型，并且强制修改联系人的名称为"C3预警号码"
                 */
                for (int i = 0; i < contacts.size(); i++) {
                    int list_contactType = (int) contacts.get(i).getContactType();
                    if (!isC3SOSContactNumber(list_contactType)) {
                        contacts.get(i).setName("C3预警号码");
                        contacts.get(i).setContactType(contactType & ContactTerminalType.C3_SOS.value());
                        this.commonTrans.updateEntitie(contacts.get(i));
                        break;
                    }
                }

            }
            throw new OperationException("该号码已经添加,请检查!", ErrorCodeEnum.REPEAT);
        }

        // 构造需要添加的联系人
        TUserContacts newContact = new TUserContacts();

        newContact.setUserId(userId);
        newContact.setName(name);
        newContact.setContactNumber(contactNumber);
        newContact.setReceiveSMS(false);
        newContact.setContactType(contactType);

        if (isC3ContactNumber(contactType)) {
            TUserTerminal userTerminal = terminalService.getTerminal(userId, TerminalType.C3.getName());
            if (userTerminal == null) {
                throw new OperationException("请先绑定C3设备", ErrorCodeEnum.FAILED);
            }
        }
        if (isC3SOSContactNumber(contactType)) {
            contactsDao.removeContactTypeInAllContact(userId, ContactTerminalType.C3_SOS.value());
        }
        // 保存联系人信息
        this.commonTrans.save(newContact);

        return newContact.getId();
    }

    @Override
    public void modifyContacts(TUserContacts newContact) throws OperationException {
        Integer newContactType = newContact.getContactType();
        if (newContactType == null) {
            newContactType = 0;
        }

        TUserContacts oldContact = commonTrans.getEntity(TUserContacts.class, newContact.getId());

        int userId = newContact.getUserId();

        if (oldContact == null) {
            throw new OperationException("联系人不存在", ErrorCodeEnum.NOT_FOUND);
        }

        // 查找到该用户的通讯录
        List<TUserContacts> contacts = contactsDao.findFamilyAndSos(userId);
        if (newContactType == 0) { // 为web端联系人进入,没有传类型进来（只能修改姓名和号码）
            oldContact.setContactNumber(newContact.getContactNumber());
            oldContact.setName(newContact.getName());
        } else {

            oldContact.setReceiveSMS(false);
            oldContact.setContactNumber(newContact.getContactNumber());
            oldContact.setName(newContact.getName());

            if (isC3SOSContactNumber(newContactType)) { // 预警号码
                // 获取预警号码数
                Integer c3SosNumberSize = countC3SOS(contacts, newContact);
                if (c3SosNumberSize >= 1) {
                    throw new OperationException("预警号码超出上限", ErrorCodeEnum.FAILED);
                }
            }
            if (isHealthPackageFamilyContactNumber(newContactType)) { // 亲情号码
                modifyContactReceiveSMS(newContact.getId(), userId);
                oldContact.setFamily(0);
                oldContact.setSos(1);
                oldContact.setReceiveSMS(true);
            }
            if (isC3FamilyContactNumber(newContactType)) {
                int c3FC = countC3Family(contacts, newContact);

                if (c3FC >= 3) {
                    throw new OperationException("C3亲情号码超出上限", ErrorCodeEnum.FAILED);
                }
            }
            oldContact.setContactType(newContactType);
        }
        if (isRepeatContact(contacts, newContact.getContactNumber(), newContact.getId())) {
            throw new OperationException("该号码已经添加,请检查!", ErrorCodeEnum.FAILED);
        }

        TUserTerminal userTerminal = terminalService.getTerminal(userId, TerminalType.C3.getName());
        if (isC3ContactNumber(newContactType)) {
            if (userTerminal == null) {
                throw new OperationException("请先绑定C3设备", ErrorCodeEnum.FAILED);
            }
        }

        // 更新联系人信息
        this.commonTrans.updateEntitie(oldContact);
        // 如果是修改SOS号码，也需要发送指令
        if (isC3ContactNumber(newContactType)) {
            terminalService.sendContactCommond(userTerminal);
        }
    }

    // /**
    // * 计算亲情号码数和预警号码数
    // * </p>
    // * 输入contactNumber对该号码的类型进行判断
    // * </p>
    // * 如果该号码已经存在于电话本中，查询出来的结果大小会减一用来避免不修改号码，导致号码数量溢出的错误
    // *
    // * @author zhiguo.lin
    // * @DateTime 2016年8月20日 下午2:11:36
    // *
    // * @param userId
    // */
    // private Integer countFamilyAndSosSize(int userId, List<TUserContacts>
    // contacts, TUserContacts newContact) {
    // /** 数量 */
    // int size = 0;
    //
    // boolean isSOS = newContact.getIsSOS().booleanValue();
    // Integer contactId = newContact.getId();
    //
    // if (contacts != null) {
    // // 遍历用户联系人
    // for (TUserContacts contact : contacts) {
    //
    // boolean contactIsSOS = contact.getIsSOS().booleanValue();
    //
    // if (contactId != null) {
    // if (contact.getId().intValue() == contactId) {
    // if (isSOS == contactIsSOS) { // ID相同，类型没有改变，表示size大小在有效范围内
    // return 0;
    // } else { // ID相同，类型改变了，需要判断isSOS的值，统计size大小
    // // 不需要叠加了
    // }
    // } else {
    // if (isSOS == contactIsSOS) {
    // size ++;
    // }
    // }
    // } else {
    // if (isSOS == contactIsSOS) {
    // size ++;
    // }
    // }
    // }
    // }
    //
    // return size;
    // }

    private Integer countC3SOS(List<TUserContacts> contacts, TUserContacts newContact) {
        return countC3ContactNumber(contacts, newContact, true);
    }

    private int countC3Family(List<TUserContacts> contacts, TUserContacts newContact) {
        return countC3ContactNumber(contacts, newContact, false);
    }

    private int countC3ContactNumber(List<TUserContacts> contacts, TUserContacts newContact, boolean isSOS) {
        int size = 0;

        for (TUserContacts c : contacts) {
            int c_contactType = c.getContactType();

            if (isSOS) {
                if (isC3SOSContactNumber(c_contactType)
                        && isSameContactTypeNumber(newContact, c, ContactTerminalType.C3_SOS)) {
                    size++;
                }
            } else {
                if (isC3FamilyContactNumber(c_contactType)
                        && !isSameContactTypeNumber(newContact, c, ContactTerminalType.C3_FAMILY)) {
                    size++;
                }
            }
        }

        return size;
    }

    private boolean isSameContactTypeNumber(TUserContacts newContact, TUserContacts oldContact,
            ContactTerminalType ct) {

        int n_id = newContact.getId();
        int n_ct = newContact.getContactType();

        int o_id = oldContact.getId();
        int o_ct = oldContact.getContactType();

        int ct_v = ct.value();

        if (n_id != o_id)
            return false;
        if ((n_ct & ct_v) != n_ct)
            return false;
        if ((o_ct & ct_v) != o_ct)
            return false;
        // 两个实体的contactType值都包含有ct的value
        return true;
    }

    /**
     * 判断不重复号码
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月28日 下午8:28:54
     *
     * @param contacts
     * @param newContact
     * @return
     */
    private boolean isRepeatContact(List<TUserContacts> contacts, String newContactNumber, Integer contactId) {
        for (TUserContacts contact : contacts) {
            String contactNumber = contact.getContactNumber();
            if (StringUtils.equals(contactNumber, newContactNumber)) {
                if (contactId != null && contactId.equals(contact.getId())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteContacts(int id) {
        return (contactsDao.deleteContact(id) > 0 ? true : false);
    }

    @Override
    public TUserContacts getContactsById(int id) {
        return commonTrans.getEntity(TUserContacts.class, id);
    }

    @Override
    public List<Map<String, Object>> getAllContactsMap(int userId) {
        return commonTrans.findByPropertyByMap(TUserContacts.class, "userId", userId);
    }

    @Override
    public List<TUserContacts> findFamilyByTerminalType(int userId, String terminalType) {

        // List<TUserContacts> list =
        // commonTrans.findByProperty(TUserContacts.class, "userId", userId);
        List<TUserContacts> cs = findAllContacts(userId);

        int t_cValue = 0;

        if (terminalType.equals("C3")) {
            t_cValue = ContactTerminalType.C3_FAMILY.value();
        }
        // 其它设备暂时不考虑
        if (terminalType.contains("Health")) {
            t_cValue = ContactTerminalType.HEALTH_PACKAGE_FAMILY.value();
        }
        // if (terminalType.equals(""))

        // for (ContactTerminalType ct : ContactTerminalType.values()) {
        // if (ct.getName().contains(terminalType)) {
        // contactTypes.add(ct.value());
        // }
        // }

        List<TUserContacts> returnList = new ArrayList<>();

        for (TUserContacts c : cs) {
            int c_contactType = c.getContactType();

            if ((c_contactType & t_cValue) == t_cValue) {
                returnList.add(c);
            }
        }

        // int terminalTypeValue = 0;
        // for (ContactTerminalType contactTerminalType :
        // ContactTerminalType.values()) {
        // if (terminalType.equals(contactTerminalType.getName())) {
        // terminalTypeValue = contactTerminalType.value();
        // }
        // }
        // for (int i = 0; i < list.size(); i++) {
        // int t = list.get(i).getTerminalType();
        // if ((terminalTypeValue | t) == t) {
        // if (list.get(i).getIsFamily()) {
        // returnList.add(list.get(i));
        // }
        // // continue;
        // }
        // }

        return returnList;

    }

    @Override
    public boolean deleteFamily(TUserContacts contact) {

        int contactType = contact.getContactType();

        TUserContacts oldContact = getContactsById(contact.getId());
        if (oldContact == null) {
            return true;
        } else {

            int oldContactType = oldContact.getContactType();

            // if ((oldContactType | contactType) == oldContactType) {
            oldContact.setContactType(oldContactType - contactType);
            // }

            commonTrans.updateEntitie(oldContact);
            // 发送指令
            TUserTerminal userTerminal = terminalService.getTerminal(contact.getUserId(), "C3");
            if (userTerminal != null) {
                terminalService.sendContactCommond(userTerminal);
            }
        }
        return false;
    }

    @Override
    public TUserContacts findSos(Integer userId, String terminalType) {
        // int terminalTypeValue = 0;
        // for (ContactTerminalType contactTerminalType :
        // ContactTerminalType.values()) {
        // if (contactTerminalType.getName().equals(terminalType)) {
        // terminalTypeValue = contactTerminalType.value();
        // }
        // }
        // Map<String, Object> params = new HashMap<>();
        // params.put("userId", userId);
        // params.put("isSOS", "1");
        // TUserContacts userContacts = new TUserContacts();
        // List<Map<String, Object>> list =
        // commonTrans.findByMap(TUserContacts.class, params);
        // if (list.size() > 0) {
        // for (int i = 0; i < list.size(); i++) {
        // if (((Integer) list.get(i).get("terminalType") | terminalTypeValue)
        // == (Integer) list.get(i)
        // .get("terminalType")) {
        // userContacts.setContactNumber((String)
        // list.get(i).get("contactNumber"));
        // userContacts.setName((String) list.get(i).get("name"));
        // userContacts.setTerminalType((Integer)
        // list.get(i).get("terminalType"));
        // userContacts.setIsSOS((boolean) list.get(i).get("isSOS"));
        // userContacts.setId((Integer) list.get(i).get("id"));
        // userContacts.setIsFamily((boolean) list.get(i).get("isFamily"));
        // userContacts.setReceiveSMS((boolean) list.get(i).get("receiveSMS"));
        // userContacts.setUserId((Integer) list.get(i).get("userId"));
        // }
        // }
        //
        // return userContacts;
        // }

        List<TUserContacts> cs = findAllContacts(userId);

        int t_cValue = 0;

        if (terminalType.equals("C3")) {
            t_cValue = ContactTerminalType.C3_SOS.value();
        }
        // 其它设备暂时不考虑

        for (TUserContacts c : cs) {
            int c_contactType = c.getContactType();

            if ((c_contactType & t_cValue) == t_cValue) {
                return c;
            }
        }

        return null;
    }

    @Override
    public Boolean modifySos(Integer userId, String terminalType, String contactNumber) {
        // int terminalTypeValue = 0;
        // for (ContactTerminalType contactTerminalType :
        // ContactTerminalType.values()) {
        // if (contactTerminalType.getName().equals(terminalType)) {
        // terminalTypeValue = contactTerminalType.value();
        // }
        // }
        // List<TUserContacts> list = findAllContacts(userId);
        // TUserContacts userContacts = new TUserContacts();
        // for (int i = 0; i < list.size(); i++) {
        // if (((terminalTypeValue | list.get(i).getTerminalType()) ==
        // list.get(i).getTerminalType())
        // && list.get(i).getIsSOS()) {
        // userContacts = list.get(i);
        // userContacts.setContactNumber(contactNumber);
        // }
        // }

        TUserContacts userContacts = findSos(userId, "C3");
        if (userContacts != null) {
            userContacts.setContactNumber(contactNumber);
            boolean bool = this.commonTrans.updateEntitie(userContacts) > 0 ? true : false;
            if (bool) {
                // 发送指令
                TUserTerminal userTerminal = terminalService.getTerminal(userContacts.getUserId(), "C3");
                terminalService.sendContactCommond(userTerminal);
                return true;
            }
        } else {
        	int result = contactsDao.addContanct(userId, contactNumber);
        	if(result>0) {
        		TUserTerminal userTerminal = terminalService.getTerminal(userId, "C3");
                terminalService.sendContactCommond(userTerminal);
                return true;
        	}
        }
        return false;
    }

    @Override
    public void modifyContactReceiveSMS(int id, int userId) {
        List<TUserContacts> list = this.commonTrans.findByProperty(TUserContacts.class, "userId", userId);
        for (TUserContacts contacts : list) {
            if (contacts.getId().equals(id)) {
                
                contacts.setReceiveSMS(true);
                contacts.setContactType(contacts.getContactType() | ContactTerminalType.HEALTH_PACKAGE_FAMILY.value());
                contacts.setFamily(0);
                contacts.setSos(1);
                commonTrans.updateEntitie(contacts);
            }
            if (contacts.getReceiveSMS().equals(true)) {
                if (!contacts.getId().equals(id)) {
                    contacts.setReceiveSMS(false);
                    contacts.setContactType(contacts.getContactType() - ContactTerminalType.HEALTH_PACKAGE_FAMILY.value());
                    commonTrans.updateEntitie(contacts);
                }
                
            }
        }
    }
    
    @Override
    public ServiceMessage deleteContactReceiveSMS(int id, int userId) {
        ServiceMessage serviceMessage = new ServiceMessage();
        TUserContacts userContacts = commonTrans.getEntity(TUserContacts.class, id);
        if (userContacts == null) {
            serviceMessage.setSuccess(false);
            serviceMessage.setMessage("用户不存在");
            return serviceMessage;
        }
//      contactsDao.updateContactReceiveSMS(false, userId);
        userContacts.setReceiveSMS(false);
        // userContacts.setIsFamily(true);
        userContacts.setContactType(userContacts.getContactType() - ContactTerminalType.HEALTH_PACKAGE_FAMILY.value());
        commonTrans.updateEntitie(userContacts);
        serviceMessage.setSuccess(true);
        return serviceMessage;
    }
}
