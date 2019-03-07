package com.lifeshs.service.terminal.impl;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.constants.common.healthPackage.DataTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.entity.data.TDataImei;
import com.lifeshs.entity.device.TMeasureBloodpressure;
import com.lifeshs.entity.device.TMeasureBodyfatscale;
import com.lifeshs.entity.device.TMeasureGlucometer;
import com.lifeshs.entity.device.TMeasureLunginstrument;
import com.lifeshs.entity.device.TMeasureOxygen;
import com.lifeshs.entity.device.TMeasureTemperature;
import com.lifeshs.entity.device.TSportLocation;
import com.lifeshs.entity.device.TTerminalCommond;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.entity.member.TUserBlackWhiteList;
import com.lifeshs.entity.member.TUserContacts;
import com.lifeshs.entity.member.TUserElectronicFence;
import com.lifeshs.entity.member.TUserMonitorTrack;
import com.lifeshs.entity.member.TUserNotice;
import com.lifeshs.entity.member.TUserOperationDetail;
import com.lifeshs.pojo.healthDevice.HealthPackageBaseDTO;
import com.lifeshs.pojo.healthDevice.HealthPackageDTO;
import com.lifeshs.pojo.jsp.GpsModel;
import com.lifeshs.pojo.member.HealthDataDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.commond.ClearSMSDto;
import com.lifeshs.pojo.member.commond.ContactDto;
import com.lifeshs.pojo.member.commond.DataTestDto;
import com.lifeshs.pojo.member.commond.LocationDto;
import com.lifeshs.pojo.member.commond.LogDto;
import com.lifeshs.pojo.member.commond.ModeDefineDto;
import com.lifeshs.pojo.member.commond.ModnumDto;
import com.lifeshs.pojo.member.commond.MoniterDto;
import com.lifeshs.pojo.member.commond.PowerOffDto;
import com.lifeshs.pojo.member.commond.RunModeDto;
import com.lifeshs.pojo.member.commond.SendSMSDto;
import com.lifeshs.pojo.member.commond.TimerDto;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.common.impl.BaseTerminal;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.device.IDeviceService;
import com.lifeshs.service.device.impl.BloodPressure;
import com.lifeshs.service.device.impl.Bodyfatscale;
import com.lifeshs.service.device.impl.Glucometer;
import com.lifeshs.service.device.impl.Lunginstrument;
import com.lifeshs.service.device.impl.Oxygen;
import com.lifeshs.service.device.impl.Temperature;
import com.lifeshs.service.device.impl.product.Product;
import com.lifeshs.service.fence.IFenceService;
import com.lifeshs.service.member.IMemberCommondService;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.SMSCommand;

@Component("terminalService")
public class TerminalServiceImpl extends CommonServiceImpl implements ITerminalService {

    protected static final Logger logger = Logger.getLogger(TerminalServiceImpl.class);
    
    private CacheType locationCache = CacheType.LOCATION;
    
    @Autowired
    private BaseTerminal baseTerminal;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IMemberCommondService memberCommond; // 终端指令的操作对象，在自实现功能时调用

    @Autowired
    private TerminalAdaptee terminal;

    @Autowired
    private IFenceService fenceService;

    @Autowired
    private IContactsService contactService;

    @Autowired
    private SMSService smsService;
    
    @Autowired
    private IMemberDao memberDao;

    /**
     * 短信的模板
     */
    SMSCommand smsCommand = SMSCommand.MONITOR;

    final static VcodeTerminalType vcodeTerminalType = VcodeTerminalType.USER_PLATFORM;

    @Resource(name = "bloodPressure")
    private BloodPressure bloodPressure;

    @Resource(name = "lunginstrument")
    private Lunginstrument lunginstrument;

    @Resource(name = "glucometer")
    private Glucometer glucometer;

    @Resource(name = "oxygen")
    private Oxygen oxygen;

    @Resource(name = "bodyfatscale")
    private Bodyfatscale bodyfatscale;

    @Resource(name = "temperature")
    private Temperature temperature;

    @Override
    public int sendsms(SendSMSDto smsDto) throws Exception {
        return memberCommond.save(smsDto); // save
    }

    @Override
    public int timer(TimerDto timerDto) throws Exception {
        return memberCommond.save(timerDto); // save
    }

    @Override
    public int monitor(MoniterDto moniterDto) throws Exception {
        return memberCommond.save(moniterDto); // save
    }

    @Override
    public int sendLocation(LocationDto locationDto) throws Exception {
        return memberCommond.save(locationDto); // save
    }

    @Override
    public int clearsms(ClearSMSDto cDto) throws Exception {
        return memberCommond.save(cDto); // save
    }

    @Override
    public int contact(ContactDto cDto) throws Exception {
        return memberCommond.save(cDto); // save
    }

    @Override
    public int datatest(DataTestDto dataTestDto) throws Exception {
        return memberCommond.save(dataTestDto); // save
    }

    @Override
    public int modedefine(ModeDefineDto mDto) throws Exception {
        return memberCommond.save(mDto); // save
    }

    @Override
    public int runMode(RunModeDto mDto) throws Exception {
        return memberCommond.save(mDto); // save
    }

    @Override
    public int sendLog(LogDto logDto) throws Exception {
        return memberCommond.save(logDto); // save
    }

    @Override
    public int sendPoweroff(PowerOffDto pDto) throws Exception {
        // 修改设备状态为关机
        TUserTerminal device = getHL(pDto.getImei());
        device.setStatus(0);
        this.commonTrans.updateEntitie(device);
        return memberCommond.save(pDto); // save
    }

    @Override
    public int sendModifyMessage(ModnumDto modnumDto) throws Exception {

        // 如果修改的数据是心跳间隔，需要在设备上做出相应的修改
        if (modnumDto.getEventname().equals("heart")) {
            int heartFrequency = Integer.valueOf(modnumDto.getNewData());
            String imei = modnumDto.getImei();
            TUserTerminal td = getHL(imei);
            td.setHeartFrequency(heartFrequency);
            this.commonTrans.updateEntitie(td);
        }

        return memberCommond.save(modnumDto); // save
    }

    @Override
    public ServiceMessage bindTerminal(int userId, String imei, String name, String mobile, String terminalType)
            throws Exception {
        // 更新设备入库表 数据
        if (StringUtils.isBlank(imei)) {
            return new ServiceMessage(false, "imei码不能为空");
        }
        TDataImei dataImei = commonTrans.findUniqueByProperty(TDataImei.class, "imei", imei);
        if (dataImei == null)
            return new ServiceMessage(false, "找不该设备的信息");
        if (dataImei.status == 0)
            return new ServiceMessage(false, "设备尚未卖出");
        if (dataImei.status == 2)
            return new ServiceMessage(false, "设备已被绑定，需要解绑后才可以继续绑定操作");
        dataImei.setStatus(2); // 设备状态修改为已绑定
        // dataImei.setPassword("tzdq1234");
        commonTrans.updateEntitie(dataImei); // 更新数据

        // 添加关联
        TUserTerminal device = new TUserTerminal();
        device.setImei(imei);
        device.setName(name);
        device.setUserId(userId);
        device.setMobile(mobile);
        device.setTerminalType(terminalType); // HL, C3
        device.setStatus(0); // 默认离线状态
        device.setCreateDate(new Date());
        device.setHeartFrequency(30);
        device.setLocationFrequency(60);
        commonTrans.save(device); // 保存实体
        return new ServiceMessage(true, "设备绑定成功");
    }

    @Override
    public boolean unBindTerminal(int userId, String imei, String deviceType) throws Exception {
        TDataImei dataImei = commonTrans.findUniqueByProperty(TDataImei.class, "imei", imei);
        if (dataImei.getStatus() == 2) { // 设备已绑定
            TUserTerminal device = deviceService.selectDeviceIsBinding(imei, deviceType);

            if (device == null)
                return false;
            // 删除设备黑名单列表
            List<TUserBlackWhiteList> list = baseTerminal.getDeviceBlackList(device.getId());
            if (list != null && list.size() > 0) {
                for (TUserBlackWhiteList entity : list) {
                    commonTrans.delete(entity);
                }
            }
            // 删除模式详细设置内容
            TUserOperationDetail detail = baseTerminal.getTerminalOperationDetails(device.getId());
            if (detail != null) {
                commonTrans.delete(detail);
            }
            // 删除电子围栏
            List<TUserElectronicFence> fences = fenceService.findFenceByListOrderByNumber(device.getId());
            if (fences != null && fences.size() > 0) {
                for (TUserElectronicFence fence : fences) {
                    commonTrans.delete(fence);
                }
            }
            // 删除运动轨迹监控设置
            List<TUserMonitorTrack> tracks = deviceService.findTracksOrderByParam(device.getId(), "number", 0);
            if (tracks != null && tracks.size() > 0) {
                for (TUserMonitorTrack track : tracks) {
                    commonTrans.delete(track);
                }
            }
            // 删除提醒
            List<TUserNotice> notices = baseTerminal.getTUserNotices(device.getId());
            if (notices != null && notices.size() > 0) {
                for (TUserNotice notice : notices) {
                    commonTrans.delete(notice);
                }
            }
            // 修改预警联系人
            TUserContacts userContacts = contactService.findSos(userId, "C3");
            // userContacts.setIsSOS(false);
            // userContacts.setIsFamily(false);
            userContacts.setContactType(userContacts.getContactType() - 2);
            userContacts.setUserId(userId);
            contactService.modifyContacts(userContacts);

            commonTrans.delete(device); // 删除实体

            // 修改 status = 1 token = null deviceType = 0
            dataImei.setStatus(1); // 把设备修改为已卖出
            return (commonTrans.updateEntitie(dataImei) > 0 ? true : false); // 更新数据
        }
        return false; // 设备未绑定
    }

    @Override
    public boolean isUserBindSameTerminal(int userId, String terminalType) {
        List<TUserTerminal> devices = commonTrans.findByProperty(TUserTerminal.class, "userId", userId);
        if (devices == null) // 查找不到该用户的设备
            return false;
        for (TUserTerminal device : devices) {
            if (StringUtils.equals(terminalType, device.getTerminalType())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<TUserTerminal> geTUserTerminals(int userId) {
        return baseTerminal.getDeviceList(userId);
    }

    @Override
    public List<TUserBlackWhiteList> getDeviceBlackList(int userId, String terminalType) {
        List<TUserBlackWhiteList> resList = null;
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return resList;
        resList = baseTerminal.getDeviceBlackList(terminal.getId());
        return resList; // 如果查询结果为空不做处理
    }

    @Override
    public boolean addBlackList(int userId, String mobile, String name, String terminalType) throws Exception {
        TUserTerminal userTerminal = getTerminal(userId, terminalType);
        int terminalId = userTerminal.getId(); // 获取设备ID
        String imei = userTerminal.getImei();
        // 保存黑名单数据
        TUserBlackWhiteList blackWhiteList = new TUserBlackWhiteList();
        blackWhiteList.setUserDeviceId(terminalId);
        blackWhiteList.setType(2); // 2表示黑名单
        blackWhiteList.setLimited(mobile);
        blackWhiteList.setCreateDate(new Date());
        boolean bool = commonTrans.save(blackWhiteList) > 0 ? true : false;
        /* 存储命令到数据库 */
        if (!sendContactCommond(userTerminal)) {
            bool = false;
        }
        /*
         * ContactDto contactDto = new ContactDto(); String family = "no";
         * List<Map<String, Object>> list = getUserContacts(userId, mobile); if
         * (list.size() > 0){ for (int i = 0; i < list.size(); i++) { family =
         * list.get(i).get("isFamily") == "1" ? "yes" : "no"; if
         * (family.equals("yes")) { break; } } } contactDto.setUserId(userId);
         * contactDto.setImei(imei); contactDto.setMsg("[{'family':'"+ family
         * +"','number':'"+ mobile +"','name':'"+ name +"', 'relat':'black'}]");
         * contactDto.setMsgsize(1); commonTrans.save(contactDto.getEntity());
         */
        return bool;
    }

    @Override
    public boolean isBlackListLimited(int userId, String terminalType) {
        TUserTerminal terminal = getTerminal(userId, terminalType); // 获取设备对象
        if (terminal == null)
            return true; // 查找不到该设备信息，不能设置黑名单
        // 获取黑名单列表，并对黑名单列表信息进行筛选
        List<TUserBlackWhiteList> list = baseTerminal.getDeviceBlackList(terminal.getId());
        if (list == null)
            return false; // 如果尚未创建该记录，表示黑名单数量未到达上限
        if (list.size() <= 8)
            return false; // 默认最大值为8个
        return true;
    }

    @Override
    public boolean isBlackListMobileExist(int userId, String mobile, String terminalType) {
        int terminalId = getTerminal(userId, terminalType).getId(); // 获取设备ID
        if (terminal == null)
            return true; // 查找不到该设备信息，不能设置黑名单
        // 获取黑名单列表，并对黑名单列表信息进行筛选
        List<TUserBlackWhiteList> list = baseTerminal.getDeviceBlackList(terminalId);
        for (TUserBlackWhiteList entity : list) {
            if (StringUtils.equals(mobile, entity.getLimited())) { // 避免在黑名单中添加重复的号码
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delBlackList(int userId, int mobileId, String terminalType) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType); // 获取设备对象
        TUserBlackWhiteList userBlackWhiteList = commonTrans.getEntity(TUserBlackWhiteList.class, mobileId);
        String mobile = userBlackWhiteList.getLimited();
        if (terminal == null)
            return false; // 找不到设备，不能删除黑名单
        // 获取黑名单列表，然后将号码相同的黑名单记录删除
        List<TUserBlackWhiteList> list = baseTerminal.getDeviceBlackList(terminal.getId());
        if (list != null && list.size() > 0) {
            for (TUserBlackWhiteList entity : list) {
                if (mobileId == entity.getId()) {
                    boolean bool = commonTrans.delete(entity) > 0 ? true : false; // 删除黑名单记录
                    /* 存储命令到数据库 */
                    if (!sendContactCommond(terminal)) {
                        bool = false;
                    }
                    return bool;
                }
            }
        }
        return false;
    }

    @Override
    public List<TUserNotice> getTerminalNotices(int userId, String terminalType) {
        List<TUserNotice> notices = new ArrayList<>();
        List<TUserNotice> notices2 = new ArrayList<>();
        TUserTerminal terminal = null;
        if (terminalType != null) {
            terminal = getTerminal(userId, terminalType); // 获取设备对象
            if (terminal == null) {
                return notices;
            }
        }
        notices = baseTerminal.getTUserNotices(userId); // 获取设备提醒

        for (TUserNotice notice : notices) {
            boolean isHealthPackageBandData = notice.getUserDeviceId().intValue() == 0;
            if (terminalType == null) {
                if (isHealthPackageBandData) {
                    notices2.add(notice);
                }
            } else {
                int terminalId = terminal.getId();
                int userDeviceId = notice.getUserDeviceId();
                boolean isSameTerminal = terminalId == userDeviceId;
                if (isSameTerminal) {
                    notices2.add(notice);
                }
            }
        }
        return notices2;
        // List<TUserNotice> notices = new ArrayList<>();
        //
        // TUserTerminal terminal = null;
        // if (terminalType != null) {
        // terminal = getTerminal(userId, terminalType); // 获取设备对象
        // if (terminal == null) {
        // return notices;
        // }
        // }
        // notices = baseTerminal.getTUserNotices(userId); // 获取设备提醒
        //
        // List<TUserNotice> notices2 = new ArrayList<>();
        //
        // for (TUserNotice notice : notices) {
        // boolean isHealthPackageBandData = notice.getUserDeviceId().intValue()
        // == 0;
        // if (terminalType == null) {
        // if (isHealthPackageBandData) {
        // notices2.add(notice);
        // }
        // } else {
        // int terminalId = terminal.getId();
        // int userDeviceId = notice.getUserDeviceId();
        // boolean isSameTerminal = terminalId == userDeviceId;
        // if (isSameTerminal) {
        // notices2.add(notice);
        // }
        // }
        // }
        // return notices2;
    }

    @Override
    public boolean isNoticeLimited(int userId, String terminalType) {
        TUserTerminal terminal = null;
        if (terminalType != null) {
            terminal = getTerminal(userId, terminalType);
            if (terminal == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addTerminalNotice(int userId, String terminalType, String weeks, String time, String content,
            Double intervalM) throws Exception {

        TUserTerminal terminal = null;
        int userDeviceId = 0;
        if (terminalType != null) {
            terminal = getTerminal(userId, terminalType); // 获取设备对象
            if (terminal == null) {
                return false;
            }
            userDeviceId = terminal.getId();
        } else {
            userDeviceId = 0;
        }

        // 添加数据
        TUserNotice notice = new TUserNotice();
        notice.setUserDeviceId(userDeviceId);
        notice.setUserId(userId);
        notice.setWeeks(weeks);
        notice.setTime(time);
        notice.setContent(content);
        notice.setStatus(1); // 默认提醒状态为开启
        notice.setIntervalM(intervalM);
        notice.setCreateDate(new Date()); // 创建日期
        if (userDeviceId != 0) { // 判断是否为健康包手环(0表示为健康包手环)
            // TODO 发送指令 (提醒内容)
            sendTerminalNoticeCommond(terminal, notice, "modify");
        }
        return commonTrans.save(notice) > 0 ? true : false;
    }

    @Override
    public boolean modTerminalNotice(int userId, String terminalType, String weeks, String time, String content,
            Double intervalM, Integer status, int noticeId) {

        TUserTerminal terminal = null;
        TUserNotice entity = null;
        int userDeviceId = 0;
        if (terminalType != null) {
            terminal = getTerminal(userId, terminalType); // 获取设备对象
            if (terminal == null) { // 找不到设备
                return false;
            }
            userDeviceId = terminal.getId();
        } else {
            userDeviceId = 0;
        }

        List<TUserNotice> notices = baseTerminal.getTUserNotices(userId);
        if (notices == null) // 找不到需要修改的信息
            return false;
        // 查找是否项有该记录
        for (TUserNotice notice : notices) {
            if (notice.getId() == noticeId) {
                entity = notice;
                break;
            }
           
        }
        if (entity == null) // 查找不到该项记录，直接返回false
            return false;

        // 更新数据
        entity.setWeeks(weeks);
        entity.setTime(time);
        entity.setContent(time);
        entity.setContent(content);
        entity.setIntervalM(intervalM);
        entity.setModifyDate(new Date());

        if (status != null) {
            entity.setStatus(status);
        }

        commonTrans.updateEntitie(entity);
        if (userDeviceId != 0) { // 判断是否为健康包手环(0表示为健康包手环)
	        // // TODO 发送指令
	        sendTerminalNoticeCommond(terminal, entity, "modify");
        }
        return true;
    }

    @Override
    public boolean toggleTerminalNotice(int userId, String terminalType, int noticeId) {

        TUserTerminal terminal = null;
        TUserNotice entity = null;
        int userDeviceId = 0;
        if (terminalType != null) {
            terminal = getTerminal(userId, terminalType); // 获取设备对象
            if (terminal == null) { // 找不到设备
                return false;
            }
            userDeviceId = terminal.getId();
        }  else {
            userDeviceId = 0;
        }

        List<TUserNotice> notices = baseTerminal.getTUserNotices(userId);
        if (notices == null) // 找不到需要修改的信息
            return false;
        // 查找是否项有该记录
        for (TUserNotice notice : notices) {
            if (notice.getId() == noticeId) {
                entity = notice;
                break;
            }
        }
        if (entity == null) // 查找不到该项记录，直接返回false
            return false;
        // 如果是1就转为0，如果是0就转为1
        int status = entity.getStatus() == 1 ? 0 : 1;
        entity.setStatus(status);
        if (userDeviceId != 0) { // 判断是否为健康包手环(0表示为健康包手环)
	        if (status == 0) {
	            sendTerminalNoticeCommond(terminal, entity, "del");
	        } else {
	            sendTerminalNoticeCommond(terminal, entity, "modify");
	        }
        }

        return commonTrans.updateEntitie(entity) > 0 ? true : false;
    }

    @Override
    public TUserNotice getTerminalNotice(int userId, String terminalType, int noticeId) {

        TUserTerminal terminal = null;
        if (terminalType != null) {
            terminal = getTerminal(userId, terminalType); // 获取设备对象
            if (terminal == null) {
                return null;
            }
        }

        List<TUserNotice> notices = baseTerminal.getTUserNotices(userId);
        if (notices == null) // 查找不到该设备对应的notice列表
            return null;
        // 查询noticeId对应的记录
        for (TUserNotice notice : notices) {
            if (notice.getId() == noticeId) {
                return notice;
            }
        }
        return null; // 找不到该项记录
    }

    @Override
    public boolean delTerminalNotice(int userId, int noticeId, String terminalType) {

        TUserTerminal terminal = null;
        int userDeviceId = 0;
        if (terminalType != null) {
            terminal = getTerminal(userId, terminalType); // 获取设备对象
            if (terminal == null) {
                return false;
            }
            userDeviceId = terminal.getId();
        } else {
			userDeviceId = 0;
		}

        List<TUserNotice> notices = baseTerminal.getTUserNotices(userId);
        if (notices == null)
            return false;
        // 查找该项记录
        for (TUserNotice notice : notices) {
            if (notice.getId() == noticeId) {
            	if (userDeviceId != 0) { // 判断是否为健康包手环(0表示为健康包手环)
            		sendTerminalNoticeCommond(terminal, notice, "del");
            	}
                commonTrans.delete(notice); // 删除实体

                return true;
            }
        }
        return false; // 找不到对应的实体返回
    }

    @Override
    public Map<String, Object> getTerminalOperationMode(int userId, String terminalType) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        Map<String, Object> resMap = new HashMap<>();
        int operationMode = 0; // 默认正常模式0
        if (terminal != null && terminal.getOperationMode() != null) {
            operationMode = terminal.getOperationMode();
        }
        if (operationMode == 6) { // 上课隐身模式
            // 获取详细设置内容
            TUserOperationDetail entity = baseTerminal.getTerminalOperationDetails(terminal.getId());
            resMap.put("detail", entity);
        }
        resMap.put("mode", operationMode);
        return resMap;
    }

    @Override
    public boolean modTerminalOperationMode(int userId, String terminalType, TUserOperationDetail detail)
            throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return false; // 查找不到该设备的信息

        // 先保存t_user_terminal.oprationMode字段
        terminal.setOperationMode(detail.getOperationMode());
        terminal.setModifyDate(new Date());
        commonTrans.updateEntitie(terminal);

        // 如果是上课隐身模式，需要处理t_user_operationDetail表
        if (detail.getOperationMode() == 6) {
            TUserOperationDetail entity = baseTerminal.getTerminalOperationDetails(terminal.getId());
            // 如果查找不到，则添加新的数据, 否则更新数据
            if (entity == null) {
                detail.setUserDeviceId(terminal.getId());
                detail.setCreateDate(new Date());
                commonTrans.save(detail);
            } else {
                entity.setOperationMode(detail.getOperationMode());
                entity.setStartTime1(detail.getStartTime1());
                entity.setStartTime2(detail.getStartTime2());
                entity.setStartTime3(detail.getStartTime3());
                entity.setEndTime1(detail.getEndTime1());
                entity.setEndTime2(detail.getEndTime2());
                entity.setEndTime3(detail.getEndTime3());
                entity.setEnable(detail.getEnable());
                entity.setWeeks(detail.getWeeks());
                entity.setModifyDate(new Date());
                commonTrans.updateEntitie(entity);
            }
        }
        sendRunModeCommond(terminal);
        // runMode(runDto);
        return true;
    }

    @Override
    public UserRecordDTO getUserHealthPackageParams(int userId) {
        UserRecordDTO recordDTO = memberDao.getUserRecord(userId);
        if (recordDTO == null)
            return null;
        return recordDTO;
    }

    @Override
    public boolean modifyUserHealthPackageParams(int userId, Boolean gender, Float height, Float weight, Float waist, Float bust, Float hip, int age){
        UserRecordDTO recordDTO = memberDao.getUserRecord(userId);
        // 年龄处理
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        int yearBirth = yearNow - age;

        String newBirth = yearBirth + "-" + DateTimeUtilT.monthDay(recordDTO.getBirthday()); // yyyy-MM-dd

        // 其它参数设置
        recordDTO.setGender(gender);
        recordDTO.setHeight(height);
        recordDTO.setWeight(weight);
        recordDTO.setWaist(waist);
        recordDTO.setBust(bust);
        recordDTO.setHip(hip);
        recordDTO.setBirthday(DateTimeUtilT.date(newBirth));
        memberDao.updateUserRecord(recordDTO);
        return true;
    }

    @Override
    public List<HealthPackageDTO> getUserHealthProductsList(Integer userId) {
        List<HealthPackageDTO> models = new ArrayList<>();
        Integer healthProduct = null;

        if (userId != null) {
            TUser user = getTUser(userId);
            healthProduct = user.getHealthProduct();
        }

        for (HealthPackageBaseDTO hpbDTO : Product.listHealthPackageBaseDTO()) {
            HealthPackageDTO dm = new HealthPackageDTO();
            /** 把父类属性赋值给子类 */
            BeanUtils.copyProperties(hpbDTO, dm);

            /** 图片 */
            dm.setImg("static/images/device/" + hpbDTO.getName_en() + ".png");
            dm.setImg_no_circle("static/images/device/no_circle/" + hpbDTO.getName_en() + ".png");
            /** 绑定状态 */
            if (healthProduct != null && ((hpbDTO.getDeviceValue() | healthProduct) == healthProduct)) {
                dm.setStatus(1);
            } else {
                dm.setStatus(0);
            }

            models.add(dm);
        }

        return models;
    }

    @Override
    public boolean addUserHealthProducts(int userId, String typeName) {
        TUser user = getTUser(userId);
        int healthProduct = user.getHealthProduct();
        for (HealthPackageType deviceType : HealthPackageType.values()) {
            if (StringUtils.equals(typeName, deviceType.name())) {
                healthProduct |= deviceType.value(); // 勾选该项健康包
                break;
            }
        }
        user.setHealthProduct(healthProduct);
        return (commonTrans.updateEntitie(user) > 0 ? true : false);
    }

    @Override
    public boolean delUserHealthProducts(int userId, String typeName) {
        TUser user = getTUser(userId);
        int healthProduct = user.getHealthProduct();
        for (HealthPackageType deviceType : HealthPackageType.values()) {
            if (StringUtils.equals(typeName, deviceType.name())) { // 查找到typeName对应的健康包
                if ((deviceType.value() | healthProduct) == healthProduct) { // 如果该健康包已勾选，则取消该选项，否则不做处理
                    healthProduct -= deviceType.value(); // 取消该项健康包
                }
                break;
            }
        }
        user.setHealthProduct(healthProduct);
        return (commonTrans.updateEntitie(user) > 0 ? true : false);
    }

    @Override
    public boolean isHealthDataBinded(String healthType, Integer userHealthProduct) {
        if (StringUtils.isBlank(healthType))
            return false; // 数据为空直接返回false

        // 判断用户是否勾选了改健康包
        for (HealthPackageBaseDTO hpbDTO : Product.listHealthPackageBaseDTO()) {
            if (!StringUtils.equals(healthType, hpbDTO.getName_en())) // 找不到相应的设备，继续遍历
                continue;
            if ((userHealthProduct | hpbDTO.getDeviceValue()) == userHealthProduct)
                return true; // 已勾选
            return false; // 未勾选
        }

        return false; // 找不到healthData所属的设备
    }

    @Override
    public boolean addHealthData(HealthDataDTO healthData, int userId) throws OperationException{
        String healthType = healthData.getHealthType();
        switch (healthType) {
        case "BloodPressure":
            TMeasureBloodpressure tmb = new TMeasureBloodpressure();
            tmb.setDeviceType(TerminalType.APP.getName());
            tmb.setUserId(userId);
            tmb.setCreateDate(new Date());
            tmb.setMeasureDate(healthData.getMeasureDate());
            tmb.setDiastolic(healthData.getBloodpressure().getDiastolic());
            tmb.setHeartRate(healthData.getBloodpressure().getHeartRate());
            tmb.setSystolic(healthData.getBloodpressure().getSystolic());
            tmb.setDataType(DataTypeEnum.MANUAL.value());
            bloodPressure.save(tmb);
            return true;
        case "Lunginstrument":
            TMeasureLunginstrument tml = new TMeasureLunginstrument();
            tml.setDeviceType(TerminalType.APP.getName());
            tml.setUserId(userId);
            tml.setCreateDate(new Date());
            tml.setMeasureDate(healthData.getMeasureDate());
            tml.setVitalCapacity(healthData.getLunginstrument().getVitalCapacity());
            tml.setDataType(DataTypeEnum.MANUAL.value());
            lunginstrument.save(tml);
            return true;
        case "Glucometer":
            TMeasureGlucometer tmg = new TMeasureGlucometer();
            tmg.setDeviceType(TerminalType.APP.getName());
            tmg.setUserId(userId);
            tmg.setCreateDate(new Date());
            tmg.setMeasureDate(healthData.getMeasureDate());
            tmg.setMeasureType(healthData.getGlucometer().getMeasureType());
            tmg.setBloodSugar(healthData.getGlucometer().getBloodSugar());
            tmg.setDataType(DataTypeEnum.MANUAL.value());
            glucometer.save(tmg);
            return true;
        case "Oxygen":
            TMeasureOxygen tmo = new TMeasureOxygen();
            tmo.setDeviceType(TerminalType.APP.getName());
            tmo.setUserId(userId);
            tmo.setCreateDate(new Date());
            tmo.setMeasureDate(healthData.getMeasureDate());
            tmo.setHeartRate(healthData.getOxygen().getHeartRate());
            tmo.setSaturation(healthData.getOxygen().getSaturation());
            tmo.setDataType(DataTypeEnum.MANUAL.value());
            oxygen.save(tmo);
            return true;
        case "BodyFatScale":
            TMeasureBodyfatscale tmbf = new TMeasureBodyfatscale();
            tmbf.setDeviceType(TerminalType.APP.getName());
            tmbf.setUserId(userId);
            tmbf.setCreateDate(new Date());
            tmbf.setMeasureDate(healthData.getMeasureDate());
            tmbf.setAxungeRatio(healthData.getBodyfatscale().getAxungeRatio());
            tmbf.setBaseMetabolism(healthData.getBodyfatscale().getBaseMetabolism());
            tmbf.setBMI(healthData.getBodyfatscale().getBMI());
            tmbf.setBodyage(healthData.getBodyfatscale().getBodyage());
            tmbf.setBoneWeight(healthData.getBodyfatscale().getBoneWeight());
            tmbf.setFatFreeWeight(healthData.getBodyfatscale().getFatFreeWeight());
            tmbf.setMoisture(healthData.getBodyfatscale().getMoisture());
            tmbf.setMuscle(healthData.getBodyfatscale().getMuscle());
            tmbf.setWeight(healthData.getBodyfatscale().getWeight());
            tmbf.setWHR(healthData.getBodyfatscale().getWHR());
            tmbf.setVisceralFat(healthData.getBodyfatscale().getVisceralFat());
            tmbf.setDataType(DataTypeEnum.MANUAL.value());
            bodyfatscale.save(tmbf);
            return true;
        case "HeartRate":
            /*
             * TMeasureBloodpressure tmb = new TMeasureBloodpressure();
             * tmb.setDeviceType(TerminalType.Browser.getName());
             * tmb.setUserId(userId); tmb.setCreateDate(new Date());
             * tmb.setMeasureDate(format.parse((String)healthData.get(
             * "measureDate")));
             * tmb.setDiastolic((Integer)healthData.get("diastolic"));
             * tmb.setHeartRate((Integer)healthData.get("heartRate"));
             * tmb.setSystolic((Integer)healthData.get("systolic")); return
             * (bloodPressure.save(tmb, userId) > 0 ? true : false);
             */
        case "Temperature":
            TMeasureTemperature tmt = new TMeasureTemperature();
            tmt.setDeviceType(TerminalType.APP.getName());
            tmt.setUserId(userId);
            tmt.setCreateDate(new Date());
            tmt.setMeasureDate(healthData.getMeasureDate());
            tmt.setTemperature(healthData.getTemperature().getTemperature());
            tmt.setDataType(DataTypeEnum.MANUAL.value());
            temperature.save(tmt);
            return true;
        }
        return false; // 查找不到对应的设备
    }

    @Override
    public TUserTerminal getMoniterFrequency(int userId, String terminalType) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        TUserTerminal entity = new TUserTerminal();
        if (terminal == null)
            return null;
        entity.setHeartFrequency(terminal.getHeartFrequency());
        entity.setLocationFrequency(terminal.getLocationFrequency());
        entity.setAutoFrequency30(terminal.getAutoFrequency30());
        entity.setAutoFrequency50(terminal.getAutoFrequency50());
        entity.setAutoFrequency70(terminal.getAutoFrequency70());
        return entity;
    }

    @Override
    public boolean modifyMoniterFrequency(int userId, String terminalType, int heartFrequency, int locationFrequency,
            int autoFrequency70, int autoFrequency50, int autoFrequency30) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return false;
        terminal.setHeartFrequency(heartFrequency);
        terminal.setLocationFrequency(locationFrequency);
        terminal.setAutoFrequency30(autoFrequency30);
        terminal.setAutoFrequency50(autoFrequency50);
        terminal.setAutoFrequency70(autoFrequency70);
        terminal.setModifyDate(new Date());

        return (commonTrans.updateEntitie(terminal) > 0 ? true : false);
    }

    @Override
    public List<TUserElectronicFence> getElectronicFences(int userId, String terminalType) {
        // 获取设备
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return null;
        // 获取围栏
        List<TUserElectronicFence> fences = fenceService.findFenceByListOrderByNumber(terminal.getId());
        return fences;
    }

    @Override
    public boolean addElectronicFence(int userId, String terminalType, TUserElectronicFence fence) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        // fence.setEnabled(true);
        if (terminal == null)
            return false;
        fence.setUserDeviceId(terminal.getId());
        // TODO mapType 默认
        fence.setMapType("B");
        /* 存储命令到数据库 */
        boolean bool = commonTrans.save(fence) > 0 ? true : false;
        if (!sendElectronicFenceAndMonitorTrackCommond(terminal)) {
            bool = false;
        }
        return bool;
    }

    @Override
    public int isElectronicFenceLimited(int userId, String terminalType) {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return 0;
        // 获取电子围栏列表，返回最大number数
        List<TUserElectronicFence> fences = commonTrans.findByProperty(TUserElectronicFence.class, "userDeviceId",
                terminal.getId());
        if (fences == null || fences.size() == 0)
            return 1; // 查找不到电子围栏列表对象，表示还未建立，直接返回1
        if (fences.size() > 3) // 默认只能设置3个电子围栏
            return 0;
        // 创建一个Map对象，以电子围栏编号为key，存放电子围栏对象
        // 通过非空判断查找最小下标用来作为返回值
        Map<Integer, TUserElectronicFence> map_temp = new HashMap<>();
        for (TUserElectronicFence fence : fences) {
            map_temp.put(fence.getNumber(), fence);
        }
        for (int i = 1; i <= 3; i++) {
            if (map_temp.get(i) == null) {
                return i; // 返回最小下标
            }
        }
        return 0; // 不可能到这里吧...随便返回个0
    }

    @Override
    public boolean modifyElectronicFence(int userId, String terminalType, TUserElectronicFence fence) {
        // 目前电子围栏的编号为1..3
        TUserTerminal userTerminal = getTerminal(userId, terminalType);
        if (fence.getNumber() <= 3 && fence.getNumber() >= 1) {
            TUserElectronicFence temp = getElectronicFence(userId, fence.getNumber(), terminalType);
            temp.setRadius(fence.getRadius());
            temp.setLatitude(fence.getLatitude());
            temp.setLongitude(fence.getLongitude());
            temp.setWarningType(fence.getWarningType());
            temp.setWarningPhone(fence.getWarningPhone());
            temp.setAddress(fence.getAddress());
            temp.setStartTime1(fence.getStartTime1());
            temp.setStartTime2(fence.getStartTime2());
            temp.setStartTime3(fence.getStartTime3());
            temp.setEndTime1(fence.getEndTime1());
            temp.setEndTime2(fence.getEndTime2());
            temp.setEndTime3(fence.getEndTime3());
            temp.setEnabled(fence.getEnabled());
            boolean bool = commonTrans.updateEntitie(temp) > 0 ? true : false;
            if (!sendElectronicFenceAndMonitorTrackCommond(userTerminal)) {
                bool = false;
            }
            return bool;
        }

        return false;
    }

    @Override
    public boolean delElectronicFence(int userId, String terminalType, int number) throws Exception {

        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return false;
        // 获取电子围栏列表，将围栏编号为number的电子围栏数据删除
        for (TUserElectronicFence fence : commonTrans.findByProperty(TUserElectronicFence.class, "userDeviceId",
                terminal.getId())) {
            if (fence.getNumber() == number) {
                boolean bool = commonTrans.deleteEntityById(TUserElectronicFence.class, fence.getId()) > 0 ? true : false;
                if (!sendElectronicFenceAndMonitorTrackCommond(terminal)) {
                    bool = false;
                }
                return bool;
                // return (commonTrans.delete(fence) > 0 ? true : false);
            }
        }
        return false; // 查找不到相应编号的电子围栏信息
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月22日 上午9:43:21
     * @serverComment 获取TUser对象
     *
     * @param userId
     * @return
     * @throws Exception
     */
    private TUser getTUser(int userId) {
        return commonTrans.getEntity(TUser.class, userId);
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月18日 下午2:36:29
     * @serverComment 获取用户已绑定、指定类型的设备
     *
     * @param userId
     * @param terminalType
     * @return
     */
    public TUserTerminal getTerminal(int userId, String terminalType) {
        // 获取设备列表，并对设备列表信息进行筛选
        List<TUserTerminal> terminals = baseTerminal.getDeviceList(userId);
        if (terminals == null)
            return null; // 设备列表为空，找不到设备ID
        for (TUserTerminal terminal : terminals) {
            if (StringUtils.equals(terminal.getTerminalType(), terminalType)) { // 查找相应的设备
                return terminal; // 返回设备
            }
        }
        return null;
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月16日 下午4:35:34
     * @serverComment 获取已绑定的HL设备
     *
     * @param imei
     * @return
     */
    private TUserTerminal getHL(String imei) {
        return terminal.getDeviceService().selectDeviceIsBinding(imei, "HL03");
    }

    @Override
    public boolean setTerminalMobile(int userId, String terminalType, String mobile) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return false;
        terminal.setMobile(mobile);
        return commonTrans.updateEntitie(terminal) > 0;
    }

    @Override
    public boolean setTerminalOperationMode(int userId, String terminalType, int mode) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        terminal.setOperationMode(mode);
        boolean bool = false;
        if (commonTrans.saveOrUpdate(terminal) > 0) {
            bool = true;
        }
        return bool;
    }

    @Override
    public int getTerminalOperationModeNumber(int userId, String terminalType) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        return terminal.getOperationMode();
    }

    @Override
    public List<TUserMonitorTrack> getMonitorTrack(int userId, String terminalType) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return null;
        int userDeviceId = terminal.getId();
        List<TUserMonitorTrack> tmpList = commonTrans.findByProperty(TUserMonitorTrack.class, "userDeviceId", userDeviceId);
        /**
         * 对查询结果tmpList进行判断 为空时需要帮助生成3个enable为false的监控轨迹
         */
        if (tmpList == null || tmpList.size() == 0) {
            for (int i = 1; i <= 3; i++) {
                TUserMonitorTrack track = new TUserMonitorTrack();
                track.setCreateDate(new Date());
                track.setEnabled(false);
                track.setStartTime(Time.valueOf("00:00:00"));
                track.setEndTime(Time.valueOf("00:00:00"));
                track.setName("监控" + i);
                track.setNumber(i);
                track.setUserDeviceId(userDeviceId);
                commonTrans.save(track);
                tmpList.add(track);
            }
        }

        return tmpList;
    }

    @Override
    public boolean modifyMonitorTrack(int userId, String terminalType, List<TUserMonitorTrack> tracks)
            throws Exception {
        int resultInt = 0;
        TUserTerminal userTerminal = getTerminal(userId, terminalType);
        int userDeviceId = userTerminal.getId();

        List<TUserMonitorTrack> list = commonTrans.findByProperty(TUserMonitorTrack.class, "userDeviceId", userDeviceId);

        /**
         * 循环次数 最大3*3
         */
        for (TUserMonitorTrack tmp : tracks) {
            for (TUserMonitorTrack track : list) {
                if (tmp.getNumber().intValue() == track.getNumber().intValue()) {
                    track.setStartTime(tmp.getStartTime());
                    track.setEndTime(tmp.getEndTime());
                    track.setEnabled(tmp.getEnabled());
                    resultInt += commonTrans.updateEntitie(track) > 0 ? 1 : 0;
                    break;
                }
            }
        }
        boolean bool = resultInt == 3 ? true : false;
        if (!sendElectronicFenceAndMonitorTrackCommond(userTerminal)) {
            bool = false;
        }
        return bool;
    }

    @Override
    public boolean modifyMonitorTrack(int userId, String terminalType, int number, String name, Time startTime,
            Time endTime, boolean enabled) throws Exception {
        int userDeviceId = getTerminal(userId, terminalType).getId();
        TUserMonitorTrack tUserMonitorTrack = commonTrans.getEntity(TUserMonitorTrack.class, userDeviceId);

        tUserMonitorTrack.setNumber(number);
        tUserMonitorTrack.setName(name);
        tUserMonitorTrack.setStartTime(startTime);
        tUserMonitorTrack.setEndTime(endTime);
        tUserMonitorTrack.setEnabled(enabled);

        return (commonTrans.updateEntitie(tUserMonitorTrack)) > 0 ? true : false;
    }

    @Override
    public boolean deleteMonitorTrack(int userId, String terminalType, int number) throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return false;
        // 获取电子围栏列表，将围栏编号为number的电子围栏数据删除
        for (TUserMonitorTrack track : commonTrans.findByProperty(TUserMonitorTrack.class, "userDeviceId",
                terminal.getId())) {
            if (track.getNumber() == number)
                return (commonTrans.delete(track) > 0 ? true : false);
        }
        return false; // 查找不到相应编号的电子围栏信息
    }

    @Override
    public boolean addMonitorTrack(int userId, String terminalType, int number, String name, Time startTime,
            Time endTime, boolean enabled) throws Exception {
        int userDeviceId = getTerminal(userId, terminalType).getId();
        TUserMonitorTrack tUserMonitorTrack = new TUserMonitorTrack();
        tUserMonitorTrack.setUserDeviceId(userDeviceId);
        tUserMonitorTrack.setNumber(number);
        tUserMonitorTrack.setName(name);
        tUserMonitorTrack.setStartTime(startTime);
        tUserMonitorTrack.setEndTime(endTime);
        tUserMonitorTrack.setEnabled(enabled);
        tUserMonitorTrack.setCreateDate(new Date());

        return (commonTrans.save(tUserMonitorTrack)) > 0 ? true : false;
    }

    @Override
    public GpsModel getGps(int userId, String terminalType) {
        // 获取TSportLocation对象
        List<TSportLocation> locations = deviceService.findLatestGpsMessage(userId, terminalType, 1);
        /**
         * 如果limit条件错误，locations的值就会变成null 如果查询不到定位信息，locations的size就会等于0
         * 这时候都需要返回null TODO
         */
        if (locations == null || locations.size() == 0)
            return null;
        GpsModel gps = new GpsModel();
        gps.setLat(locations.get(0).getLatitude());
        gps.setLng(locations.get(0).getLongitude());
        gps.setAddress(locations.get(0).getAddress());
        gps.setMeasureDate(locations.get(0).getMeasureDate());
        return gps;
    }

    @Override
    public TUserElectronicFence getElectronicFence(int userId, int number, String terminalType) {
        // 获取设备
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return null;
        // 获取围栏List
        List<TUserElectronicFence> fences = commonTrans.findByProperty(TUserElectronicFence.class, "userDeviceId",
                terminal.getId());
        // 查找围栏编号为指定number的电子围栏
        for (TUserElectronicFence fence : fences) {
            if (fence.getNumber() == number) {
                return fence;
            }
        }
        return null;
    }

    @Override
    public List<GpsModel> getOrbit(int userId, String terminalType, int number, String date) {
        // 获取设备对象
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return null;
        List<GpsModel> resList = new ArrayList<>();
        /**
         * 首先通过设备的ID获取所有的轨迹设置信息 然后查找number对应的对象
         * 把对象的startTime与endTime作为条件对findLocationByDateTime()查询到的List结果进行筛选
         * 
         * 注意： number的数值为1、2、3 如果number对应的轨迹设置信息不存在，就选取最小编号
         */

        /**
         * 在这里对轨迹编号number进行处理 保证number的值为1、2、3中的一个 小于1的改成1，大于3的改为3
         */
        if (number < 1)
            number = 1;
        if (number > 3)
            number = 3;
        // 通过number查询时间段
        List<TUserMonitorTrack> tracks = deviceService.findTracksOrderByParam(terminal.getId(), "number", 0);
        if (tracks == null || tracks.size() == 0)
            return null;
        TUserMonitorTrack track = null;
        for (TUserMonitorTrack tmp : tracks) {
            if (number == tmp.getNumber()) {
                track = tmp;
                break;
            }
        }
        if (track == null)
            track = tracks.get(0);

        // 获取日期date里保存的定位数据 通过比较定位数据的测量时间与轨迹设置的时间段
        String start = date + " " + track.getStartTime().toString();
        String end = date + " " + track.getEndTime().toString();
        List<TSportLocation> locations = deviceService.findLocationByDateTime(userId, terminalType, start, end);
        if (locations != null && locations.size() > 0) {
            for (int i = 0, j = 0; i < locations.size(); i++) {
                GpsModel m = new GpsModel();
                m.setLng(locations.get(i).getLongitude());
                m.setLat(locations.get(i).getLatitude());
                m.setAddress(locations.get(i).getAddress());
                resList.add(m);
            }
        }
        return resList;
    }

    @Override
    public boolean isUserHealthDataComplete(int userId) {
        UserRecordDTO recordDTO = memberDao.getUserRecord(userId);

        Float height = recordDTO.getHeight();
        Float weight = recordDTO.getWeight();
        Float waist = recordDTO.getWaist();
        Float bust = recordDTO.getBust();
        Float hip = recordDTO.getHip();

        if (height == null || weight == null || waist == null || bust == null || hip == null)
            return false;
        return true;
    }

    @Override
    public boolean modifyTerminalMonitorContactId(int userId, String terminalType, String contactNumber)
            throws Exception {
        TUserTerminal terminal = getTerminal(userId, terminalType);
        if (terminal == null)
            return false;
        List<TUserContacts> contacts = commonTrans.findByProperty(TUserContacts.class, "userId", userId);
        // 遍历contacts，查找number相同的contact，然后将ID赋值到terminal的属性monitorContactId中，再做保存
        if (contacts != null && contacts.size() > 0) {
            for (TUserContacts contact : contacts) {
                if (StringUtils.equals(contact.getContactNumber(), contactNumber)) {
                    terminal.setMonitorContactId(contact.getId());
                    /* 监听号码不能存在t_user_terminal,直接存进指令表 */
                    // boolean bool = commonTrans.updateEntitie(terminal) > 0 ? true
                    // : false;

                    sendMonitorContactCommond(terminal, contactNumber); // 发送监听命令
                    return true; // 大于0表示修改成功
                }
            }
        }
        return false;
    }

    @Override
    public boolean addUserHealthWarning(int userId, String typeName) throws Exception {
        TUser user = getTUser(userId);
        int healthWarning = user.getHealthWarning();
        for (HealthPackageType deviceType : HealthPackageType.values()) {
            if (StringUtils.equals(typeName, deviceType.name())) {
                healthWarning |= deviceType.value(); // 勾选该项设备预警
                break;
            }
        }
        user.setHealthProduct(healthWarning);
        return (commonTrans.updateEntitie(user) > 0 ? true : false);
    }

    @Override
    public boolean delUserHealthWarning(int userId, String typeName) throws Exception {
        TUser user = getTUser(userId);
        int healthWarning = user.getHealthWarning();
        for (HealthPackageType deviceType : HealthPackageType.values()) {
            if (StringUtils.equals(typeName, deviceType.name())) { // 查找到typeName对应的健康包
                if ((deviceType.value() | healthWarning) == healthWarning) { // 如果该健康包已勾选，则取消该选项，否则不做处理
                    healthWarning -= deviceType.value(); // 取消该项健康包
                }
                break;
            }
        }
        user.setHealthProduct(healthWarning);
        return (commonTrans.updateEntitie(user) > 0 ? true : false);
    }

    public List<Map<String, Object>> getUserContacts(int userId, String contactNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("contactNumber", contactNumber);
        List<Map<String, Object>> userContacts = commonTrans.findByMap(TUserContacts.class, map);
        return userContacts;
    }

    @Override
    public PaginationDTO getSportLocationRecord(int userId, int start, int end) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("productModel", "C3");
        List records = commonTrans.findEntityByPageDesc(TSportLocation.class, param, start, end, "measureDate");
        int count = commonTrans.getCount(TSportLocation.class, param);
        PaginationDTO vo = new PaginationDTO(start, end, count, records);
        return vo;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月22日上午10:19:53
     * @serverComment 处理多个时间段时间的覆盖问题
     * @param
     */
    public List<Map<String, Object>> getTimes(List<Map<String, Object>> list2) {
    	if (list2.size() == 0) {
			return new ArrayList<>();
		}
        Collections.sort(list2, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> map_1, Map<String, Object> map_2) {
                Time map1value = (Time) map_1.get("start");
                Time map2value = (Time) map_2.get("start");
                return map1value.compareTo(map2value);
            }
        });
        List<Map<String, Object>> newList = new ArrayList<>();
        newList.add(list2.get(0));
        int newIndex = 0;
        for (int i = 1; i < list2.size(); i++) {
            if (((Time) list2.get(i).get("start")).compareTo((Time) newList.get(newIndex).get("end")) == -1
                    || ((Time) list2.get(i).get("start")).compareTo((Time) newList.get(newIndex).get("end")) == 0) {
                if (((Time) list2.get(i).get("end")).compareTo((Time) newList.get(newIndex).get("end")) == 1) {
                    newList.get(newIndex).put("end", list2.get(i).get("end"));
                }
            } else {
                newList.add(list2.get(i));
                newIndex++;
            }

        }
        return newList;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月22日下午1:54:08
     * @serverComment 存储电子围栏、轨迹命令（暂时为覆盖机制）
     * @param
     */
    public boolean sendElectronicFenceAndMonitorTrackCommond(TUserTerminal userTerminal) {
        boolean bool = false;
        /* 获取电子围栏的时间段 */
        List<TUserElectronicFence> fences = fenceService.findFenceByListOrderByNumber(userTerminal.getId());
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> listAll = new ArrayList<>();
        for (int i = 0; i < fences.size(); i++) {
            String enable = fences.get(i).getEnabled();
            list = new ArrayList<>();
            List<Map<String, Object>> list2 = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            Map<String, Object> map2 = new HashMap<>();
            Map<String, Object> map3 = new HashMap<>();
            map1.put("start", fences.get(i).getStartTime1());
            map1.put("end", fences.get(i).getEndTime1());
            map2.put("start", fences.get(i).getStartTime2());
            map2.put("end", fences.get(i).getEndTime2());
            map3.put("start", fences.get(i).getStartTime3());
            map3.put("end", fences.get(i).getEndTime3());
            switch (enable) {
			case "111":
				list2.add(map1);
                list2.add(map2);
                list2.add(map3);
                list = getTimes(list2);
				break;
			case "110":
				list2.add(map1);
                list2.add(map2);
                list = getTimes(list2);			
				break;
			case "101":
				list2.add(map1);
                list2.add(map3);
                list = getTimes(list2);
				break;
			case "100":
				list.add(map1);
				break;
			case "011":
				list2.add(map2);
                list2.add(map3);
                list = getTimes(list2);
				break;
			case "010":
				list.add(map2);
				break;
			case "001":
				list.add(map3);
				break;
			case "000":
				break;
			default:
				break;
			}
            listAll.addAll(list); // 加入总list
        }
        /* 获取轨迹的时间段 */
        List<TUserMonitorTrack> MonitorList = commonTrans.findByProperty(TUserMonitorTrack.class, "userDeviceId",
                userTerminal.getId());
        for (int i = 0; i < MonitorList.size(); i++) {
            if (MonitorList.get(i).getEnabled()) {
                Map<String, Object> map = new HashMap<>();
                map.put("start", MonitorList.get(i).getStartTime());
                map.put("end", MonitorList.get(i).getEndTime());
                listAll.add(map); // 加入总list
            }
        }
        listAll = getTimes(listAll); // 总list进行最后处理覆盖问题
        String timeStr = null;
        if (listAll.size() > 0) {
            Object start = listAll.get(0).get("start");
            Object end = listAll.get(0).get("end");
            timeStr = "{'start':'" + start + "','stop':'" + end + "','weeks':'1111111'}";
            for (int j = 1; j < listAll.size(); j++) {
                start = listAll.get(j).get("start");
                end = listAll.get(j).get("end");
                timeStr = timeStr + "," + "{'start':'" + start + "','stop':'" + end + "','weeks':'1111111'}";
            }
        } else {
        	timeStr = "{'start':'00:00:00','stop':'00:00:00','weeks':'1111111'}";
        }
        LocationDto locationDto = new LocationDto();
        locationDto.setUserId(userTerminal.getUserId());
        locationDto.setImei(userTerminal.getImei());
        locationDto.setEventname("GPS");
        locationDto.setOp("modify");
        locationDto.setTime("[" + timeStr + "]");
        locationDto.setTimesize(listAll.size());
        locationDto.setRepeat("{'times':'0','interval':'" + userTerminal.getLocationFrequency() + "'}");
        /* 先查询是否存在未发指令，存在则删除之前指令，反之添加指令 */
        Map<String, Object> params = new HashMap<>();
        params.put("name", "location");
        params.put("status", "0");
        List<Map<String, Object>> old = commonTrans.findByMap(TTerminalCommond.class, params);
        boolean bool1 = commonTrans.save(locationDto.getEntity()) > 0 ? true : false;
        if (bool1) {
            if (old.size() > 0) {
                for (int i = 0; i < old.size(); i++) {
                    commonTrans.deleteEntityById(TTerminalCommond.class, (Integer) old.get(i).get("id"));
                }
            }
        }
        cacheService.delCacheValue(locationCache, String.valueOf(userTerminal.getUserId()));
        return true;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月22日下午3:45:51
     * @serverComment 存储设备提醒命令（暂时为覆盖机制）
     * @param
     */
    public boolean sendTerminalNoticeCommond(TUserTerminal userTerminal, TUserNotice userNotice, String op) {
        // List<TUserNotice> list = commonTrans.findByProperty(TUserNotice.class,
        // "userDeviceId", userTerminal.getUserId());
        DecimalFormat df = new DecimalFormat("0 ");
        TimerDto timerDto = new TimerDto();
        timerDto.setUserId(userTerminal.getUserId());
        timerDto.setImei(userTerminal.getImei());
        timerDto.setEventname("alarm");
        // alarm设置间隔没有作用
        // timerDto.setRepeats("{'times':0,'interval':"+
        // df.format(userNotice.getIntervalM()) +"}");
        timerDto.setRepeats("{}");
        timerDto.setOp(op);
        timerDto.setTime("[{'start':'" + userNotice.getTime() + ":00" + "','msg':'alarm'}]");
        timerDto.setTimesize(1);
        /* 先查询是否存在未发指令，存在则删除之前指令 */
        Map<String, Object> params = new HashMap<>();
        params.put("name", "playvoice");
        params.put("status", "0");
        List<Map<String, Object>> old = commonTrans.findByMap(TTerminalCommond.class, params);
        boolean bool1 = commonTrans.save(timerDto.getEntity()) > 0 ? true : false;
        if (bool1) {
            if (old.size() > 0) {
                for (int i = 0; i < old.size(); i++) {
                    // commonTrans.deleteEntityById(TTerminalCommond.class,
                    // (Integer)old.get(i).get("id"));
                }
            }
        }
        return bool1;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月22日下午3:45:07
     * @serverComment 存储SOS号码、亲情号码、白名单、黑名单命令（为覆盖机制）
     * @param
     * @throws Exception
     */
    @Override
    public boolean sendContactCommond(TUserTerminal userTerminal) {

        String family = null;
        String mobile = null;
        String name = null;
        String relat = null;
        String msgStr = null;
        /* 黑名单列表 */
        List<TUserBlackWhiteList> resList = baseTerminal.getDeviceBlackList(userTerminal.getId());
        if (resList.size() > 0) {
            family = "no";
            mobile = resList.get(0).getLimited();
            name = resList.get(0).getName() == null ? "" : resList.get(0).getName();
            relat = "black";
            msgStr = "{'family':'" + family + "','number':'" + mobile + "','name':'" + name + "', 'relat':'" + relat
                    + "'}";
            for (int i = 1; i < resList.size(); i++) {
                mobile = resList.get(i).getLimited();
                name = resList.get(i).getName() == null ? "" : resList.get(i).getName();
                msgStr = msgStr + "," + "{'family':'" + family + "','number':'" + mobile + "','name':'" + name
                        + "', 'relat':'" + relat + "'}";
            }
        }
        String familyMsg = null;
        /* 亲情号码列表 */
        List<TUserContacts> contactList = contactService.findFamilyByTerminalType(userTerminal.getUserId(), "C3");
        if (contactList.size() > 0) {
            family = "yes";
            mobile = contactList.get(0).getContactNumber();
            name = contactList.get(0).getName();
            relat = "no";
            familyMsg = "{'family':'" + family + "','number':'" + mobile + "','name':'" + name + "', 'relat':'" + relat
                    + "'}";
            for (int i = 1; i < contactList.size(); i++) {
                mobile = contactList.get(i).getContactNumber();
                name = contactList.get(i).getName() == null ? "" : contactList.get(i).getName();
                familyMsg = familyMsg + "," + "{'family':'" + family + "','number':'" + mobile + "','name':'" + name
                        + "', 'relat':'" + relat + "'}";
            }
        }
        String sosStr = null;
        /* SOS号码 */
        TUserContacts userContacts = contactService.findSos(userTerminal.getUserId(), "C3");
        if (userContacts != null) {
            family = "no";
            mobile = userContacts.getContactNumber();
            name = userContacts.getName();
            relat = "sos";
            sosStr = "{'family':'" + family + "','number':'" + mobile + "','name':'" + name + "', 'relat':'" + relat
                    + "'}";
        }
        String allStr = "";
        ContactDto contactDto = new ContactDto();
        contactDto.setUserId(userTerminal.getUserId());
        contactDto.setImei(userTerminal.getImei());
        if (msgStr != null) {
            allStr = allStr + msgStr + ",";
        }
        if (familyMsg != null) {
            allStr += familyMsg + ",";
        }
        if (sosStr != null) {
            allStr += sosStr;
        }
        contactDto.setMsg("[" + allStr + "]");
        contactDto.setMsgsize(resList.size() + contactList.size() + (userContacts == null ? 0 : 1));
        /* 先查询是否存在未发指令，存在则删除之前指令，反之添加指令 */
        Map<String, Object> params = new HashMap<>();
        params.put("name", "contact");
        params.put("status", "0");
        params.put("userId", userTerminal.getUserId());
        List<Map<String, Object>> old = commonTrans.findByMap(TTerminalCommond.class, params);
        boolean bool1 = commonTrans.save(contactDto.getEntity()) > 0 ? true : false;
        if (bool1) {
            if (old.size() > 0) {
                for (int i = 0; i < old.size(); i++) {
                    commonTrans.deleteEntityById(TTerminalCommond.class, (Integer) old.get(i).get("id"));
                }
            }
        }
        return bool1;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月28日下午2:48:55
     * @serverComment 发送运行模式命令
     * @param
     * @throws ParseException
     */
    public boolean sendRunModeCommond(TUserTerminal userTerminal) throws ParseException {
        // 增加硬件回发命令(暂时发送固定时间的)
        String timeStr = null;
        int timeSize = 1;
        RunModeDto runDto = new RunModeDto();
        if (userTerminal.getOperationMode() == 6) { // 校园隐身模式，特殊处理
            Time start1 = null;
            Time start2 = null;
            Time start3 = null;
            Time end1 = null;
            Time end2 = null;
            Time end3 = null;
            String enable = null;
            String weeks = null;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            List<TUserOperationDetail> list = commonTrans.findByProperty(TUserOperationDetail.class, "userDeviceId",
                    userTerminal.getId());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getOperationMode() == 6) {
                    start1 = new Time(sdf.parse(list.get(i).getStartTime1()).getTime());
                    start2 = new Time(sdf.parse(list.get(i).getStartTime2()).getTime());
                    start3 = new Time(sdf.parse(list.get(i).getStartTime3()).getTime());
                    end1 = new Time(sdf.parse(list.get(i).getEndTime1()).getTime());
                    end2 = new Time(sdf.parse(list.get(i).getEndTime2()).getTime());
                    end3 = new Time(sdf.parse(list.get(i).getEndTime3()).getTime());
                    enable = list.get(i).getEnable();
                    weeks = list.get(i).getWeeks();
                }
            }
            List<Map<String, Object>> list2 = new ArrayList<>();
            List<Map<String, Object>> list3 = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            Map<String, Object> map2 = new HashMap<>();
            Map<String, Object> map3 = new HashMap<>();
            map1.put("start", start1);
            map1.put("end", end1);
            map2.put("start", start2);
            map2.put("end", end2);
            map3.put("start", start3);
            map3.put("end", end3);
            if (enable.equals("111")) {
                list2.add(map1);
                list2.add(map2);
                list2.add(map3);
                list3 = getTimes(list2);
            } else if (enable.equals("110")) {
                list2.add(map1);
                list2.add(map2);
                list3 = getTimes(list2);
            } else if (enable.equals("101")) {
                list2.add(map1);
                list2.add(map3);
                list3 = getTimes(list2);
            } else if (enable.equals("100")) {
                list3.add(map1);
            } else if (enable.equals("011")) {
                list2.add(map2);
                list2.add(map3);
                list3 = getTimes(list2);
            } else if (enable.equals("010")) {
                list3.add(map2);
            } else if (enable.equals("001")) {
                list3.add(map3);
            } else if (enable.equals("000")) {

            }

            if (list3.size() > 0) {
                Object start = list3.get(0).get("start");
                Object end = list3.get(0).get("end");
                timeStr = "{'start':'" + start + "','stop':'" + end + "','section':'inter','weeks':'" + weeks + "'}";
                for (int j = 1; j < list3.size(); j++) {
                    start = list3.get(j).get("start");
                    end = list3.get(j).get("end");
                    timeStr = timeStr + "," + "{'start':'" + start + "','stop':'" + end
                            + "','section':'inter','weeks':'" + weeks + "'}";
                }
            }
            timeSize = list3.size();
            runDto.setType("event");
        } else {
            timeStr = "[]";
            timeSize = 0;
            runDto.setType("once");
        }
        runDto.setUserId(userTerminal.getUserId());
        runDto.setImei(userTerminal.getImei());
        runDto.setTime(timeStr);
        runDto.setTimesize(timeSize);
        /* 暂时将运行模式存储在repeats字段 */
        runDto.setRepeats(userTerminal.getOperationMode() + "");
        /* 先查询是否存在未发指令，存在则删除之前指令 */
        Map<String, Object> params = new HashMap<>();
        params.put("name", "runmode");
        params.put("status", "0");
        List<Map<String, Object>> old = commonTrans.findByMap(TTerminalCommond.class, params);
        boolean bool1 = commonTrans.save(runDto.getEntity()) > 0 ? true : false;
        if (bool1) {
            if (old.size() > 0) {
                for (int i = 0; i < old.size(); i++) {
                    commonTrans.deleteEntityById(TTerminalCommond.class, (Integer) old.get(i).get("id"));
                }
            }
        }
        return bool1;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月28日下午3:04:44
     * @serverComment 发送监听指令
     * @param
     * @throws SMSException
     */
    public boolean sendMonitorContactCommond(TUserTerminal userTerminal, String contactNumber) throws SMSException {
        /* 存储指令到数据库 */
        /*
         * MoniterDto moniterDto = new MoniterDto();
         * moniterDto.setUserId(userTerminal.getUserId());
         * moniterDto.setImei(userTerminal.getImei());
         * moniterDto.setMsg("[{'timer':'0','number':'"+ contactNumber +"'}]");
         * moniterDto.setMsgsize(1);
         * 
         * 先查询是否存在未发指令，存在则删除之前指令， Map<String, Object> params = new HashMap<>();
         * params.put("name", "monitor"); params.put("status", "0");
         * List<Map<String, Object>> old =
         * commonTrans.findByMap(TTerminalCommond.class, params);
         * commonTrans.save(moniterDto.getEntity()); if (old.size() > 0) { for (int i
         * = 0; i < old.size(); i++) {
         * commonTrans.deleteEntityById(TTerminalCommond.class,
         * (Integer)old.get(i).get("id")); } }
         */
        // 采用短信发送指令方式
        String password = userTerminal.getImei().substring(userTerminal.getImei().length() - 6,
                userTerminal.getImei().length());
        try {
            smsService.send(userTerminal.getUserId(), vcodeTerminalType, userTerminal.getMobile(), smsCommand, false, password, contactNumber);
        } catch (OperationException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean sendPoweroffCommond(TUserTerminal userTerminal) {
        /* 存储指令到数据库 */
        PowerOffDto powerOffDto = new PowerOffDto();
        powerOffDto.setImei(userTerminal.getImei());
        powerOffDto.setUserId(userTerminal.getUserId());

        /* 先查询是否存在未发指令，存在则删除之前指令， */
        Map<String, Object> params = new HashMap<>();
        params.put("name", "poweroff");
        params.put("status", "0");
        List<Map<String, Object>> old = commonTrans.findByMap(TTerminalCommond.class, params);
        commonTrans.save(powerOffDto.getEntity());
        if (old.size() > 0) {
            for (int i = 0; i < old.size(); i++) {
                commonTrans.deleteEntityById(TTerminalCommond.class, (Integer) old.get(i).get("id"));
            }
        }
        return true;
    }

}
