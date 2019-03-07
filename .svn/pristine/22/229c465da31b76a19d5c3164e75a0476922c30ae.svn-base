package com.lifeshs.controller.org.financial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.po.OrgPO;
import com.lifeshs.po.OrgStatementPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.org.bank.BankInfoDTO;
import com.lifeshs.pojo.org.profit.ProfitDTO;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service1.order.IStatementService;
import com.lifeshs.utils.StringUtil;

@RestController("StoreFinancialController")
@RequestMapping(value = "store/finance")
public class StoreFinancialController extends BaseController {

    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private IManagerOrgService orgService;

    @Autowired
    private IStatementService statementService;
    
    @RequestMapping
    public ModelAndView manager() {
        LoginUser user = getLoginUser();
        int orgId = user.getOrgId();
        
        ModelAndView modelAndView = new ModelAndView("platform/org/finance/finance-manager");
        
        /*Double weekProfitSum = orderService.countLastMonthProfit(orgId).getEarning();
        Double monthIncome = orderService.countLastMonthIncome(orgId);*/

        OrgPO org = orgService.getOrg(orgId);
        String bankAccount = org.getBankAccount();
        if (StringUtils.isNotBlank(bankAccount)) {
            bankAccount = StringUtil.cover(bankAccount, 4, 3, "****");
        }
        String bankBranch = org.getBankBranch();
        LocalDate date = LocalDate.now();

        LocalDate previousMonth = date.minus(1, ChronoUnit.MONTHS);
        // 获取上月的第一天
        OrgStatementPO statementPO = statementService.findStatement(orgId, previousMonth.toString().substring(0, 7));
        Double orderCharge = 0.0;
        Double statmentCharge = 0.0;
        if (statementPO != null) {
            orderCharge = statementPO.getOrderCharge();
            statmentCharge = statementPO.getStatementCharge();
        }
        Map<String, Object> attributeValue = new HashMap<>();
        attributeValue.put("weekProfitSum", orderCharge);
        attributeValue.put("monthIncome", statmentCharge);
        attributeValue.put("bankAccount", bankAccount);
        attributeValue.put("bankName", bankBranch);
        modelAndView.addObject("data", attributeValue);
        modelAndView.addObject("orgType", user.getType());
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/data/{type}")
    public AjaxJson profitData(@PathVariable("type") int type) {
        AjaxJson result = new AjaxJson();
        result.setSuccess(true);
        LoginUser user = getLoginUser();
        int orgId = user.getOrgId();
        int userId = user.getId();
        List<ProfitDTO> data = new ArrayList<>();
        switch(type) {
        case 1: // 周
            if (user.getUserType() == 0 || user.getUserType() == 2) {
                data = orderService.listWeekProfit(orgId);
                break;
            }
            data = orderService.listWeekProfitByOrgUser(userId);
            break;
        case 2: // 两周
            if (user.getUserType() == 0 || user.getUserType() == 2) {
                data = orderService.listTwoWeekProfit(orgId);
                break;
            }
            data = orderService.listTwoWeekProfitByOrgUser(userId);
            break;
        case 3: // 月
            if (user.getUserType() == 0 || user.getUserType() == 2) {
                data = orderService.listMonthProfit(orgId);
                break;
            }
            data = orderService.listMonthProfitByOrgUser(userId);

            break;
        default:
            result.setSuccess(false);
            result.setMsg("请输入正确的参数");
        }
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("data", data);
        result.setAttributes(attributes);
        return result;
    }
    
    @RequestMapping(value = "/data/{startDate}/{endDate}")
    public AjaxJson profitData(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        AjaxJson result = new AjaxJson();
        result.setSuccess(true);
        LoginUser user = getLoginUser();
        int orgId = user.getOrgId();
        int userId = user.getId();

        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (start.isAfter(end)) {
            result.setSuccess(false);
            result.setMsg("请输入正确的参数");
            return result;
        }
        
        List<ProfitDTO> data = null;
        if (user.getUserType() == 0 || user.getUserType() == 2) {
            data = orderService.listProfit(orgId, startDate, endDate);
        } else {
            data = orderService.listProfitByOrgUser(userId, startDate, endDate);
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("data", data);
        result.setAttributes(attributes);
        
        return result;
    }
    
    @RequestMapping(value = "/bankInfo")
    public ModelAndView bankInfo() {
        ModelAndView modelAndView = new ModelAndView("platform/org/finance/store-bank-info");
        LoginUser user = getLoginUser();
        
        OrgPO orgDTO = orgService.getOrg(user.getOrgId());
        
        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("orgName", orgDTO.getOrgName());
        return modelAndView;
    }
    
    @RequestMapping(value = "/bankInfo", method = RequestMethod.POST)
    public AjaxJson bindBankInfo(@RequestBody BankInfoDTO data) {
        AjaxJson result = new AjaxJson();
        result.setSuccess(false);
        
        int orgId = getLoginUser().getOrgId();
        data.setOrgId(orgId);
        
        OrgPO org = orgService.getOrg(orgId);
        String bankAccount = org.getBankAccount();
        if (StringUtils.isNotBlank(bankAccount)) {
            result.setMsg("请勿重复设置该项信息");
            return result;
        }
        
        boolean success = orgService.updateOrgBankInfo(data);
        if (!success) {
            result.setMsg("数据更新失败");
            return result;
        }
        result.setSuccess(true);
        result.setMsg("数据提交成功");
        return result;
    }

    /**
     * 获取机构账单
     * @param startDate
     * @param endDate
     * @return
     */
    /*@ResponseBody
    @RequestMapping(value = "/data/month/{startDate}/{endDate}")
    public AjaxJson profitDataByMonth(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate,
                                      @PathVariable("date") String date) {
        AjaxJson result = new AjaxJson();
        result.setSuccess(true);

        int orgId = getLoginUser().getOrgId();

        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (start.isAfter(end)) {
            result.setSuccess(false);
            result.setMsg("请输入正确的参数");
            return result;
        }

//        List<ProfitDTO> data = orderService.countProfitByMonth(orgId, startDate, endDate);
        List<OrgStatementPO> data = statementService.findStatementList(orgId, start.toString().substring(0, 7), end.toString().substring(0, 7));
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("data", data);
        result.setAttributes(attributes);

        return result;
    }*/

    @ResponseBody
    @RequestMapping(value = "/data/month/{date}")
    public AjaxJson profitDataByMonth(@PathVariable("date") String date) {
        AjaxJson result = new AjaxJson();
        result.setSuccess(true);

        int orgId = getLoginUser().getOrgId();

        LocalDate start = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<OrgStatementPO> data = statementService.findStatementList(orgId, start.toString().substring(0, 7), start.toString().substring(0, 7));
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("data", data);
        result.setAttributes(attributes);

        return result;
    }


    /**
     * 机构确认账单
     * author: wenxian.cai
     * date: 2017/8/21 10:14
     */
    @ResponseBody
    @RequestMapping(value = "/confirm-bill")
    public AjaxJson confirmBill(@RequestParam("id") Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        if (user.getUserType() != 0 && user.getUserType() != 2) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("您没有权限执行该操作");
            return ajaxJson;
        }

        try {
            statementService.updateStatementToUnTransfer(user.getOrgId(), id);
        } catch (OperationException o) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(o.getMessage());
            return ajaxJson;
        }
        return ajaxJson;
    }

}
