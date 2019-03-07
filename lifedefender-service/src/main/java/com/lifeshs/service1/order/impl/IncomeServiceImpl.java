package com.lifeshs.service1.order.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao.org.user.OrgUserDao;
import com.lifeshs.dao.user.UserDao;
import com.lifeshs.dao1.combo.ComboDao;
import com.lifeshs.dao1.customer.ICustomerUserDao;
import com.lifeshs.dao1.order.IOrderDao;
import com.lifeshs.dao1.order.vip.VipUserOrderDao;
import com.lifeshs.dao1.org.OrgDao;
import com.lifeshs.dao1.project.IProjectDao;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.OrgPO;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.po.order.VipUserOrderPO;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service1.order.IIncomeService;
import com.lifeshs.vo.agent.AgetnIncomeVo;
import com.lifeshs.vo.combo.ComboVo;

/**
 * 
 * 分成通用类
 * 
 * @author liaoguo
 * @version 1.0
 * @DateTime 2018年11月6日 下午5:59:34
 */
@Service(value = "incomeService")
public class IncomeServiceImpl implements IIncomeService{
    private static final Logger logger = Logger.getLogger(IncomeServiceImpl.class);
    
    @Resource(name = "vipUserOrderDao")
    private VipUserOrderDao orderDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "comboDao")
    private ComboDao comboDao;

    @Resource(name = "customerUserDao")
    private ICustomerUserDao customerUserDao;

    @Resource(name = "orgDao")
    private OrgDao orgDao;

    @Autowired
    private IOrderDao orderDao1;

    @Resource(name = "projectDao")
    private IProjectDao projectDao;
    
    @Resource(name="orgUserDao")
    private OrgUserDao orgUserDao;

    /**
     * 
     * vip订单分成
     * 
     * @author liaoguo
     * @DateTime 2018年11月6日 下午6:19:51
     *
     * @param orderNumber
     *            订单编号
     * @param orderType
     *            订单类型 如：vip：vip套餐 server：服务
     */
    public AgetnIncomeVo orderIncome(String orderNumber, int payCost, String type) {
        System.out.println("####代理商分成计算start###"+orderNumber);
        AgetnIncomeVo vo = new AgetnIncomeVo();

        Double profit = 0.0; // 分成比例
        int price = payCost; // 订单总金额
        int userId = 0; // 用户id
        int balance = 0; // 余额
        String sysId = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2; //平台ID
        ProjectPO projectPo = null;
        String serviceOrgUserNo = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2; // 服务机构编号,初始值为vip套餐的编号
        
        // vip套餐
        if ("VIP".equals(type)) {
            // 查询该订单编号对应的订单信息
            VipUserOrderPO order = orderDao.findOrderByOrderNumber(orderNumber);
            // 注册码开通的服务不进行分成
            if (order.getType() == 2) {
                return null;
            }
            // 免费的套餐不进行分成
//            price = order.getPrice();
//            if (price <= 0) {
//                return null;
//            }
            // 获取用户id
            userId = order.getUserId();
            
            ComboVo vipCombo = comboDao.getCombovoById(order.getVipComboId());
            serviceOrgUserNo = vipCombo.getPublisher();
            
        } else if ("Service".equals(type)) {
            // 服务
            OrderPO order = orderDao1.getOrderDetailByOrderNumber(orderNumber);
            // 免费的套餐不进行分成
//            price = order.getPrice();
//            if (price <= 0) {
//                return null;
//            }
            // 获取用户id
            userId = order.getUser().getId();
            
            projectPo = projectDao.findProjectByOrderNumber(orderNumber);
            //服务机构
            serviceOrgUserNo = AgentConstant.AGENT_USER_TYPE_O + projectPo.getOrgId();
            
        }else{
            logger.error("不存在此类服务分成!");
            return null;
        }
        
        UserPO user = userDao.getUserInfo(userId);
        String parentId = user.getParentId(); // 用户推荐人id
        
        
        // 服务机构分成 70%
        serviceOrgIncomeMethod(vo, serviceOrgUserNo, price);
        balance = price - vo.getServiceOrgIncome();
        
        System.out.println(String.format("######parentId:%s,serviceOrgUserNo:%s", parentId,serviceOrgUserNo));
        if(parentId.indexOf(AgentConstant.AGENT_USER_TYPE_A)!=-1){
            // 引入机构分成 30%
            introduceIncomeMethod(vo, parentId, balance);
            
            /******服务机构链分成*****/
            //如果是vip套餐
            if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                
                //平台分成
                profit = AgentConstant.AGENT_AGENT_PROFIT_02 + AgentConstant.AGENT_SALESMAN_PROFIT_03;
                
                
            }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                
                //代理商分成(代理商+业务员)
                //代理商
                agentIncomeMethod(vo, serviceOrgUserNo, balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                
                //平台分成
                profit = 0.0;
                
                
            }else {
                //不是vip套餐,查找服务机构
                int orgId = Integer.parseInt(serviceOrgUserNo.substring(1,serviceOrgUserNo.length()));
                OrgPO orgPo = orgDao.getOrg(orgId);
                if (orgPo == null) {
                    logger.error(orgId+"服务机构不存在!");
                    return null;
                }
                
                System.out.println("######orgPo.getParentId():"+orgPo.getParentId());
                //服务机构上级如果是业务员
                if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1){
                    CustomerUserPO customerUser = findCustomerUser(orgPo.getParentId(), "业务员");
                    if (customerUser == null) {
                        return null;
                    }
                    
                    // 业务员分成
                    salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                    
                    
                    CustomerUserPO customerUser1 = findCustomerUser(customerUser.getParentId(), "代理商");
                    if (customerUser1 == null) {
                        return null;
                    }
                    
                    // 代理商分成
                    agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                    
                    //平台分成
                    profit = 0.0;
                    
                }else if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                    CustomerUserPO customerUser = findCustomerUser(orgPo.getParentId(), "代理商");
                    if (customerUser == null) {
                        return null;
                    }
                    
                    // 代理商分成(业务员)
                    agentIncomeMethod(vo, customerUser.getUserNo(), balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                    
                    
                    //平台分成
                    profit = 0.0;
                    
                }else if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                    
                    //代理商
                    String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                    
                    
                    // 代理商分成
                    agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                    
                    
                    //平台分成
                    profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                    
                    
                }
            }
            
        }else if (parentId.indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1) {
            
            // 引入机构分成 30%
            introduceIncomeMethod(vo, parentId, balance);
            
            
            /******服务机构链分成*****/
            //如果是vip套餐
            if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
               
                String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                
                //用户是在同一个市购买的vip
                if(parentId.equals(agentUserNo)){
                    
                    // 代理商分成
                    agentIncomeMethod(vo, parentId, balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                
                    //平台
                    profit = 0.0;
                    
                }else{
                    
                    // 代理商分成
                    agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                    
                    //平台
                    profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                }
                
                
            }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
               
                
                //用户是在同一个市购买的vip
                if(parentId.equals(serviceOrgUserNo)){
                    
                    // 代理商分成
                    agentIncomeMethod(vo, parentId, balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                
                    //平台
                    profit = 0.0;
                    
                }else{
                    
                    // 代理商分成
                    agentIncomeMethod(vo, serviceOrgUserNo, balance, 0.0);
                    
                    //平台
                    profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                }
                
            }else {
                //不是vip套餐,查找服务机构
                OrgPO serviceOrgPo = orgDao.getOrg(projectPo.getOrgId());
                if (serviceOrgPo == null) {
                    logger.error(projectPo.getOrgId()+"服务机构不存在!");
                    return null;
                }
                
                System.out.println("######serviceOrgPo.getParentId():"+serviceOrgPo.getParentId());
                //服务机构上级如果是业务员
                if(serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1){
                    
                    CustomerUserPO customerUser = findCustomerUser(serviceOrgPo.getParentId(), "业务员");
                    if (customerUser == null) {
                        return null;
                    }
                    
                    CustomerUserPO customerUser1 = findCustomerUser(customerUser.getParentId(), "代理商");
                    if (customerUser1 == null) {
                        return null;
                    }
                    
                    //用户在同一个市购买服务
                    if(parentId.equals(customerUser1.getUserNo())){
                        
                        // 业务员分成
                        salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                        
                        
                        // 代理商分成
                        agentIncomeMethod(vo, parentId, balance, 0.0);
                        
                        //平台分成
                        profit = 0.0;
                        
                    }else{
                        
                        // 业务员分成
                        salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                        
                        // 代理商分成
                        agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                        
                        //平台分成
                        profit = 0.0;
                        
                    }
                    
                }else if(serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                    
                    //用户在同一个市购买服务
                    if(parentId.equals(serviceOrgPo.getParentId())){
                        
                        // 代理商分成
                        agentIncomeMethod(vo, parentId, balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                        
                        //平台分成
                        profit = 0.0;
                        
                    }else{
                        
                        // 代理商分成
                        agentIncomeMethod(vo, serviceOrgPo.getParentId(), balance, 0.0);
                        
                        //平台分成
                        profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                        
                    }
                    
                }else if(serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                    
                    String agentUserNo = findAgentUserNoByCity(serviceOrgPo.getUserNo());
                    //用户在同一个市购买服务
                    if(parentId.equals(agentUserNo)){
                        
                        // 代理商分成
                        agentIncomeMethod(vo, parentId, balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                        
                        //平台分成
                        profit = 0.0;
                        
                    }else{
                        
                        // 代理商分成
                        agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                        
                        //平台分成
                        profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                        
                    }
                }
            }
            
        } else if (parentId.indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1) {
            
            // 引入机构分成 30%
            introduceIncomeMethod(vo, parentId, balance);
            
            CustomerUserPO customerUser = findCustomerUser(parentId, "业务员");
            if (customerUser == null) {
                return null;
            }
            
            CustomerUserPO customerUser1 = findCustomerUser(customerUser.getParentId(), "代理商");
            if (customerUser1 == null) {
                return null;
            }
            
            /******服务机构链分成*****/
            //如果是vip套餐
            if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                
                String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                //用户在同一个市购买服务
                if(customerUser1.getUserNo().equals(agentUserNo)){
                    
                    //业务员分成
                    salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                    
                    // 代理商分成
                    agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                    
                    //平台分成
                    profit = 0.0;
                    
                }else{
                    
                    // 代理商分成
                    agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                    
                    //平台分成
                    profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                    
                }
                
            }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                
                if(customerUser1.equals(serviceOrgUserNo)){
                    
                    // 业务员分成
                    salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                   
                    // 代理商分成
                    agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                    
                    
                    //平台分成 
                    profit = 0.0;
                    
                }else{
                    // 代理商分成
                    agentIncomeMethod(vo, serviceOrgUserNo, balance, 0.0);
                    
                    
                    //平台分成 
                    profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                }
                
            }else {
                //不是vip套餐,查找服务机构
                OrgPO serviceOrgPo = orgDao.getOrg(projectPo.getOrgId());
                if (serviceOrgPo == null) {
                    logger.error(projectPo.getOrgId() + "服务机构不存在!");
                    return null;
                }
                
                System.out.println("######serviceOrgPo.getParentId():"+serviceOrgPo.getParentId());
                //服务机构上级如果是业务员
                if((serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1)){
                    
                    //业务员
                    CustomerUserPO ywy = findCustomerUser(serviceOrgPo.getParentId(), "业务员");
                    if (ywy == null) {
                        return null;
                    }
                    
                    //代理商
                    CustomerUserPO dls = findCustomerUser(ywy.getParentId(), "代理商");
                    if (dls == null) {
                        return null;
                    }
                    
                    //同一个市购买服务
                    if(customerUser1.getUserNo().equals(dls.getUserNo())){
                        
                        // 业务员分成
                        salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                       
                        // 代理商分成
                        agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                        
                        
                        //平台分成 
                        profit = 0.0;
                        
                    }else{
                        // 代理商分成
                        agentIncomeMethod(vo, dls.getUserNo(), balance, 0.0);
                        
                        
                        //平台分成 
                        profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                    }
                    
                }else if (serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1) {
                    
                    //同一个市购买服务
                    if(customerUser1.getUserNo().equals(serviceOrgPo.getParentId())){
                        
                        // 业务员分成
                        salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                       
                        // 代理商分成
                        agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                        
                        
                        //平台分成 
                        profit = 0.0;
                        
                    }else{
                        // 代理商分成
                        agentIncomeMethod(vo, serviceOrgPo.getParentId(), balance, 0.0);
                        
                        
                        //平台分成 
                        profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                    }
                    
                    
                }else if(serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                    
                    String agentUserNo = findAgentUserNoByCity(serviceOrgPo.getUserNo());
                    //同一个市购买服务
                    if(customerUser1.getUserNo().equals(agentUserNo)){
                        
                        // 业务员分成
                        salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                       
                        // 代理商分成
                        agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                        
                        
                        //平台分成 
                        profit = 0.0;
                        
                    }else{
                        // 代理商分成
                        agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                        
                        
                        //平台分成 
                        profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                    }
                }  
            }
            
        } else if (parentId.indexOf(AgentConstant.AGENT_USER_TYPE_O) != -1) {
            
            //根据用户推荐人编号查找机构用户
            OrgUserPO orgUser = orgUserDao.findOrgUserByParam(parentId, null);
            if (orgUser == null) {
                logger.error(parentId + "推荐人不存在!");
                return null;
            }
            //设置用户引荐人的编号
            vo.setOrgUserUserId(orgUser.getUserNo());
            
            //引荐机构
            OrgPO orgPo = orgDao.getOrg(orgUser.getOrgId());
            if (orgPo == null) {
                logger.error(orgUser.getOrgId() + "引入机构不存在!");
                return null;
            }
            
            // 引入机构分成 30%
            introduceIncomeMethod(vo, orgPo.getUserNo(), balance);
            
            //引入机构与服务机构一致
            if(orgPo.getUserNo().equals(serviceOrgUserNo)){
                //机构上级是平台
                if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                    
                    
                    String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                    //代理商分成
                    agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                    
                    
                    //平台
                    profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                    
                }else if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                    
                    
                    //代理商
                    agentIncomeMethod(vo, orgPo.getParentId(), balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);

                    
                    //平台
                    profit = 0.0;
                    
                    
                }else if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1){
                    //机构上级是业务员
                    
                    CustomerUserPO customerUser = findCustomerUser(orgPo.getParentId(), "业务员");
                    if (customerUser == null) {
                        return null;
                    }
                    
                    //业务员
                    salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                    
                    CustomerUserPO customerUser1 = findCustomerUser(customerUser.getParentId(), "代理商");
                    if (customerUser1 == null) {
                        return null;
                    }
                    
                    //代理商分成
                    agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                    
                    //平台
                    profit = 0.0;
                }
            }else{
                
                //引入机构上级是平台
                if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                    
                    //服务机构上级是平台
                    if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                        
                        String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                        //代理商分成
                        agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                        
                        //业务员的转给平台
                        profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                        
                    }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                        
                        // 代理商分成
                        agentIncomeMethod(vo, serviceOrgUserNo, balance, 0.0);
                        
                        //业务员的转给平台
                        profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                        
                    }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_O) != -1){
                        
                        int orgId = Integer.parseInt(serviceOrgUserNo.substring(1,serviceOrgUserNo.length()));
                        OrgPO serviceOrgPo = orgDao.getOrg(orgId);
                        if(serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                            
                            //根据服务查找服务代理商
                            String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                            
                            // 代理商分成
                            agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                            
                            //平台
                            profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                            

                        }else if (serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                            
                            //代理商
                            agentIncomeMethod(vo, serviceOrgPo.getParentId(), balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                            
                            //平台
                            profit = 0.0;
                            
                            
                        }else if (serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1){
                            
                            //机构上级是业务员
                            CustomerUserPO customerUser = findCustomerUser(serviceOrgPo.getParentId(), "业务员");
                            if (customerUser == null) {
                                return null;
                            }
                            
                            //业务员
                            salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                            
                            CustomerUserPO customerUser1 = findCustomerUser(customerUser.getParentId(), "代理商");
                            if (customerUser1 == null) {
                                return null;
                            }
                            
                            //机构上级是代理商
                            agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                            
                            //平台
                            profit = 0.0;
                            
                        }
                    }
                    
                }else if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                    
                    //服务机构上级是平台
                    if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                        
                        // 代理商分成
                        agentIncomeMethod(vo, orgPo.getParentId(), balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                        
                        //平台
                        profit = 0.0;
                        
                    }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                        
                        //代理商
                        agentIncomeMethod(vo, orgPo.getParentId(), balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                        
                        
                        //平台
                        profit = 0.0;
                        
                    }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_O) != -1){
                        
                        int orgId = Integer.parseInt(serviceOrgUserNo.substring(1,serviceOrgUserNo.length()));
                        OrgPO serviceOrgPo = orgDao.getOrg(orgId);
                        if(serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                            
                            String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                            
                            //在同一个市购买的服务
                            if(orgPo.getParentId().equals(agentUserNo)){
                                
                                //代理商
                                agentIncomeMethod(vo, orgPo.getParentId(), balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                                
                                //平台
                                profit = 0.0;
                                
                            }else{
                                
                                //代理商
                                agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                                
                                //平台
                                profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                                
                            }
                            
                        }else if (serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                            
                            
                            //在同一个市购买的服务
                            if(orgPo.getParentId().equals(serviceOrgPo.getParentId())){
                                
                                // 代理商分成
                                agentIncomeMethod(vo, orgPo.getParentId(), balance, AgentConstant.AGENT_SALESMAN_PROFIT_03);
                                
                                //平台
                                profit = 0.0;
                                
                            }else{
                                
                                //代理商
                                agentIncomeMethod(vo, serviceOrgPo.getParentId(), balance, 0.0);
                                
                                //平台
                                profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                                
                            }
                            
                        }else if (serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1){
                            
                            //机构上级是业务员
                            CustomerUserPO customerUser = findCustomerUser(serviceOrgPo.getParentId(), "业务员");
                            if (customerUser == null) {
                                return null;
                            }
                            
                            //代理商
                            CustomerUserPO customerUser1 = findCustomerUser(customerUser.getParentId(), "代理商");
                            if (customerUser1 == null) {
                                return null;
                            }
                            
                            
                            //在同一个市购买的服务
                            if(orgPo.getParentId().equals(customerUser1.getUserNo())){
                                
                                //业务员
                                salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                                
                                
                                
                                //机构上级是代理商
                                agentIncomeMethod(vo, orgPo.getParentId() , balance, 0.0);
                                
                                //平台
                                profit = 0.0;
                                
                            }else{
                                
                                //代理商
                                agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                                
                                //平台
                                profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                            }
                        }
                    }
                    
                }else if(orgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1){
                    
                    CustomerUserPO customerUser = findCustomerUser(orgPo.getParentId(), "业务员");
                    if (customerUser == null) {
                        return null;
                    }
                    
                    CustomerUserPO customerUser1 = findCustomerUser(customerUser.getParentId(), "代理商");
                    if (customerUser1 == null) {
                        return null;
                    }
                    
                    //服务机构上级是平台
                    if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                        
                        String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                        //用户是在同一个市购买的vip
                        if(agentUserNo.equals(customerUser1.getUserNo())){
                            
                            //业务员
                            salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                        
                            // 代理商分成
                            agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                        
                            //平台
                            profit = 0.0;
                            
                        }else{
                            
                            // 代理商分成
                            agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                            
                            //平台
                            profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                        }
                          
                    }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                        
                        //用户是在同一个市购买的vip
                        if(serviceOrgUserNo.equals(customerUser1.getUserNo())){
                            
                            //业务员
                            salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                        
                            // 代理商分成
                            agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                        
                            //平台
                            profit = 0.0;
                            
                        }else{
                            
                            // 代理商分成
                            agentIncomeMethod(vo, serviceOrgUserNo, balance, 0.0);
                            
                            //平台
                            profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                        }
                        
                    }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_O) != -1){
                        
                        int orgId = Integer.parseInt(serviceOrgUserNo.substring(1,serviceOrgUserNo.length()));
                        OrgPO serviceOrgPo = orgDao.getOrg(orgId);
                        if(serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_A) != -1){
                            
                            String agentUserNo = findAgentUserNoByCity(serviceOrgUserNo);
                            //用户是在同一个市购买的vip
                            if(agentUserNo.equals(customerUser1.getUserNo())){
  
                                //业务员
                                salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                            
                                // 代理商分成
                                agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                            
                                //平台
                                profit = 0.0;
                                
                            }else{
                                
                                // 代理商分成
                                agentIncomeMethod(vo, agentUserNo, balance, 0.0);
                                
                                //平台
                                profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                            }
                            
                        }else if (serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_D) != -1){
                            
                            //用户是在同一个市购买的vip
                            if(serviceOrgPo.getParentId().equals(customerUser1.getUserNo())){
  
                                //业务员
                                salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                            
                                // 代理商分成
                                agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                            
                                //平台
                                profit = 0.0;
                                
                            }else{
                                // 代理商分成
                                agentIncomeMethod(vo, serviceOrgPo.getParentId(), balance, 0.0);
                                
                                //平台
                                profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                            }
                            
                        }else if (serviceOrgPo.getParentId().indexOf(AgentConstant.AGENT_USER_TYPE_Y) != -1){
                            
                            //机构上级是业务员
                            CustomerUserPO yew = findCustomerUser(serviceOrgPo.getParentId(), "业务员");
                            if (yew == null) {
                                return null;
                            }
                            //代理商
                            CustomerUserPO dls = findCustomerUser(yew.getParentId(), "代理商");
                            if (dls == null) {
                                return null;
                            }
                            
                            
                            //用户是在同一个市购买的vip
                            if(dls.getUserNo().equals(customerUser1.getUserNo())){
  
                                //业务员
                                salesmanIncomeMethod(vo, customerUser.getUserNo(), balance, 0.0);
                            
                                // 代理商分成
                                agentIncomeMethod(vo, customerUser1.getUserNo(), balance, 0.0);
                            
                                //平台
                                profit = 0.0;
                                
                            }else{
                                // 代理商分成
                                agentIncomeMethod(vo, dls.getUserNo(), balance, 0.0);
                                
                                //平台
                                profit = AgentConstant.AGENT_SALESMAN_PROFIT_03;
                            }
                        }
                    }
                }
            }
        }
        // 平台分成
        sysIncomeMethod(vo, sysId, balance, profit);
        System.out.println("####代理商分成计算end###"+vo.toString());
        
        return vo;
    }
    
    
    // 引入机构分成
    private void introduceIncomeMethod(AgetnIncomeVo vo, String parentId, int balance) {
        // 引入机构id
        vo.setIntroduceOrgId(parentId);
        vo.setIntroduceOrgProfitShare((int)(AgentConstant.AGENT_INTRODUCE_ORG_PROFIT_03 * 100));
        // 引入机构分成
        vo.setIntroduceOrgIncome(amountCalculation(balance, AgentConstant.AGENT_INTRODUCE_ORG_PROFIT_03));
    }

    // 业务员分成
    private void salesmanIncomeMethod(AgetnIncomeVo vo, String parentId, int balance, Double profit) {
        vo.setSalesmanId(parentId);
        vo.setSalesmanProfitShare((int)((AgentConstant.AGENT_SALESMAN_PROFIT_03 + profit) * 100));
        // 业务员分成(业务员分成+引入机构分成)
        vo.setSalesmanIncome(amountCalculation(balance,AgentConstant.AGENT_SALESMAN_PROFIT_03 + profit));
    }

    // 代理商分成
    private void agentIncomeMethod(AgetnIncomeVo vo, String orgParentId, int balance, Double profit) {
        // 代理商
        vo.setAgentId(orgParentId);
        vo.setAgentProfitShare((int)((AgentConstant.AGENT_AGENT_PROFIT_02 + profit) * 100));
        // 代理商分成 (代理商分成+业务员分成)
        vo.setAgentIncome(amountCalculation(balance, AgentConstant.AGENT_AGENT_PROFIT_02 + profit));
    }

    // 平台分成
    private void sysIncomeMethod(AgetnIncomeVo vo, String parentId, int balance, Double profit) {
        // 平台
        vo.setSysPlatformId(parentId);
        vo.setSysProfitShare((int)((AgentConstant.AGENT_AGENT_PROFIT_02 + profit) * 100));
        // 平台分成(剩余的金额)
        vo.setSysIncome(amountCalculation(balance ,AgentConstant.AGENT_AGENT_PROFIT_02 + profit));
    }

    // 服务机构分成
    private void serviceOrgIncomeMethod(AgetnIncomeVo vo, String orgId, int price) {
        // 服务机构id
        vo.setServiceOrgId(orgId);
        vo.setServiceOrgProfitShare((int)(AgentConstant.AGENT_SERVICE_ORG_PROFIT_07 * 100));
        // 服务机构分成
        vo.setServiceOrgIncome(amountCalculation(price,AgentConstant.AGENT_SERVICE_ORG_PROFIT_07));
    }
    
    //计算金额
    private int amountCalculation(int balance,Double profit){
        Double amount = balance * profit;
        BigDecimal decimal_amount = new BigDecimal(amount);
        return decimal_amount.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
    }

    // 获取平台、代理商、业务员信息
    private CustomerUserPO findCustomerUser(String userNo, String message)  {
        // 获取业务员信息
        List<CustomerUserPO> customerUser = customerUserDao.getUserByParam(null, userNo, null);
        if (customerUser.size() < 1) {
            logger.error(userNo + message + "不存在");
            return null;
        }
        return customerUser.get(0);
    }
    
    //获取代理商的userNo
    private String findAgentUserNoByCity(String serviceOrgUserNo){
        String agentUserNo = "";
        
        //VIP
        if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_A) !=-1){
            agentUserNo = AgentConstant.AGENT_GZRP_ID_D5;
        }else if(serviceOrgUserNo.indexOf(AgentConstant.AGENT_USER_TYPE_D) !=-1){
            agentUserNo = serviceOrgUserNo;
        }else{
            //服务
            int orgId = Integer.parseInt(serviceOrgUserNo.substring(1,serviceOrgUserNo.length()));
            OrgPO orgPo = orgDao.getOrg(orgId);
            CustomerUserPO customerUser = findCustomerUser(orgPo.getParentId(), "");
            
            if(customerUser.getUserNo().indexOf(AgentConstant.AGENT_USER_TYPE_Y)!= -1){
                CustomerUserPO customerUser1 = findCustomerUser(customerUser.getParentId(), "");
                if(customerUser1.getUserNo().indexOf(AgentConstant.AGENT_USER_TYPE_D)!= -1){
                    agentUserNo = customerUser1.getUserNo();
                }
            }else if(customerUser.getUserNo().indexOf(AgentConstant.AGENT_USER_TYPE_D)!= -1){
                //根据服务查找代理商
                agentUserNo = customerUser.getUserNo();
            }

            if(StringUtils.isBlank(agentUserNo)){
                //根据服务机构地址，查找相关区域代理商
                String area = null;
                if(StringUtils.isNotBlank(orgPo.getDistrict())){
                    if(orgPo.getDistrict().startsWith("8")){
                        area = orgPo.getDistrict();
                    }
                }
                CustomerUserPO agentUser = customerUserDao.getAgentByCity(orgPo.getProvince(), orgPo.getCity(), area);
                agentUserNo = agentUser.getUserNo();
            }
        }
        
        return agentUserNo;
    }
    
    
}
