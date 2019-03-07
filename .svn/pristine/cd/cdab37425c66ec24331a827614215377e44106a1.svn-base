package com.lifeshs.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.vip.IVipComboService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.record.RecordComboVo;
import com.lifeshs.vo.record.RecordSpreadComboVo;

/**
 *  vip计划任务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年11月3日 上午11:35:45
 */
@Component
public class Vip {

    private static final Logger logger = Logger.getLogger("task");
    
    @Resource(name = "vipUserService")
    private IVipUserService vipService;
    
    @Resource(name = "vipComboService")
    private IVipComboService comboService;
    
    @Resource(name = "v2MessageService")
    private MessageService messageService;
    
    @Autowired
    private TaskPoolService taskService;
    
    /**
     *  检查vip会员状态
     *  <p>间隔为30分钟
     *  @author yuhang.weng 
     *  @DateTime 2017年11月3日 上午11:34:23
     *
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void checkVipStatus() {
        // 把需要做的事情添加到任务池中
        taskService.put(new Runnable() {
            
            @Override
            public void run() {
                List<MessageDTO> messageDTOList = new ArrayList<>();  // 用户待发送消息集合
                List<Integer> expireVipIdList = new ArrayList<>();
                Map<Integer, String> comboIdNameMap = new HashMap<>();
                
                List<VipUserPO> expireVipUserList = vipService.listVipUserOutOfEndDate(0);
                if (expireVipUserList.isEmpty()) {
                    return;
                }
                
                for (VipUserPO evu : expireVipUserList) {
                    comboIdNameMap.put(evu.getVipComboId(), null);
                    expireVipIdList.add(evu.getId());
                }
                
                List<Integer> comoboIdList = new ArrayList<>(comboIdNameMap.keySet());
                List<VipComboPO> comboList = comboService.findVipComboList(comoboIdList);
                for (VipComboPO combo : comboList) {
                    comboIdNameMap.put(combo.getId(), combo.getName());
                }
                for (VipUserPO evu : expireVipUserList) {
                    int comboId = evu.getVipComboId();
                    
                    // 消息通知
                    MessageDTO messageDTO = new MessageDTO();
                    String message = "由于vip『" + comboIdNameMap.get(comboId) + "』剩余有效时间不足，您的vip服务已经自动结束";
                    messageDTO.setTitle("vip服务到期通知");
                    messageDTO.setContent(message);
                    messageDTO.setUserId(evu.getUserId());
                    messageDTO.setUserType(UserType.member.getValue());
                    messageDTO.setOpenType(UMengOpenTypeEnum.TEXT.value());
                    messageDTOList.add(messageDTO);
                    logger.info("[vipUserPO Id:" + evu.getId() + "] " + messageDTO);
                }
                
                // 修改vip服务状态为过期
                if (!expireVipIdList.isEmpty()) {
                    try {
                        vipService.expireVip(expireVipIdList);
                    } catch (OperationException e) {
                        logger.error("更新失败:" + e.getMessage());
                    }
                }
                // 保存系统消息
                if (!messageDTOList.isEmpty()) {
                    messageService.saveMessage(messageDTOList, MessageType.SYSTEM);
                }
            }
        });
    }

    /**
     *  推广套餐月结算
     *  <p>每月28号晚上23、58分触发</p> 0 0 23/58 28 * ?
     */
    @Scheduled(cron = "0 0 23/58 28 * ?" )
    public void recordCombo(){
        taskService.put(new Runnable() {
            @Override
            public void run() {
                List<RecordSpreadComboVo> orderComboData = comboService.getOrderComboData();
                if (orderComboData.size() != 0) {
                    List<RecordComboVo> list = new ArrayList<>();
                    for (RecordSpreadComboVo rs : orderComboData) {
                        RecordComboVo rv = new RecordComboVo();
                        rv.setMoon(rs.getMoon());
                        rv.setBusinessId(rs.getBusinessId());
                        rv.setCount(rs.getCount());
                        rv.setAmount(rs.getPrice());
                        double v = NumberUtils.changeF2Y(String.valueOf(rs.getPrice()));
                        rv.setSplit(NumberUtils.changeY2F(String.valueOf(v * 0.2)));
                        rv.setStatus(1);
                        rv.setCreateDate(new Date());
                        list.add(rv);
                    }
                    boolean b = vipService.addRecordComboData(list);
                    if (!b){
                        logger.error("更新结算数据失败！！！");
                    }
                }
            }
        });
    }
}
