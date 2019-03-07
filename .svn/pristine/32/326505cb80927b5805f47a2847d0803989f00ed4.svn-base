package com.lifeshs.app.api.member.v24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.vip.VipCombo;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.workOrder.WorkOrderPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.vip.IVipWorkOrderService;
import com.lifeshs.vo.workOrder.WorkOrderVO;

/**
 * 工单
 * 
 * @author zizhen.huang
 * @version 1.0
 * @DateTime 2017年12月26日17:00:38
 *
 */

@RestController(value = "v24WorkOrderController")
@RequestMapping(value = "app/workOrder/v24")
public class WorkOrderController {

	@Resource(name = "vipWorkOrderService")
	private IVipWorkOrderService vipWorkOrderService;

	/**
	 * 减少vip用户套餐次数
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月26日17:59:52
	 * 
	 * @param json
	 * @return
	 * @throws OperationException
	 */
	@RequestMapping(value = "subtractComboNumber", method = RequestMethod.POST)
	public JSONObject subtractComboNumber(String json) throws OperationException {
		AppJSON appJSON = AppNormalService.parseAppJSON(json);
		int userId = appJSON.getData().getUserId();
		JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
		Integer comboId = mm_0.getIntValue(VipCombo.ID);
		Integer comboItemId = mm_0.getIntValue(VipCombo.COMBOITEM_ID);
		vipWorkOrderService.subtractComboNumber(userId, comboId, comboItemId);
		return AppNormalService.success();
	}

	/**
	 * 提交用户填写的工单
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月27日17:06:17
	 * 
	 * @param json
	 * @return
	 * @throws OperationException
	 */
	@RequestMapping(value = "submitWorkOrder", method = RequestMethod.POST)
	public JSONObject submitWorkOrder(String json) throws OperationException {
		AppJSON appJSON = AppNormalService.parseAppJSON(json);
		int userId = appJSON.getData().getUserId();
		JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
		int comboItemId = mm_0.getIntValue("comboItemId");
		int comboId = mm_0.getIntValue("comboId");
		WorkOrderPO workOrderPO = new WorkOrderPO();
		workOrderPO.setUserId(userId);
		workOrderPO.setVipComboId(comboId);
		workOrderPO.setVipComboItemId(comboItemId);
		workOrderPO.setUserRemark(mm_0.getString("userRemark"));
		workOrderPO.setAppoinDate(mm_0.getDate("appoinDate"));
		// 0:表示已提交,待审核
		workOrderPO.setStatus(0);
		vipWorkOrderService.submitWorkOrder(workOrderPO);
		// 提交成功后，减少用户套餐次数
		vipWorkOrderService.subtractComboNumber(userId, comboId, comboItemId);
		return AppNormalService.success();
	}

	/**
	 * 获取工单历史记录列表
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月28日16:00:42
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "listWorkOrderHistory", method = RequestMethod.POST)
	public JSONObject listWorkOrderHistory(String json) {
		AppJSON appJSON = AppNormalService.parseAppJSON(json);
		int userId = appJSON.getData().getUserId();
		JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
		int curPage = mm_0.getIntValue(Page.INDEX);
		int pageSize = mm_0.getIntValue(Page.SIZE);
		List<WorkOrderVO> dataList = vipWorkOrderService.findWorkOrderList(userId, curPage, pageSize).getData();
		if (dataList.isEmpty()) {
			return AppNormalService.success(NormalMessage.NO_DATA);
		}
		List<Map<String, Object>> returnDateList = enclosureComboList(dataList);
		return AppNormalService.success(returnDateList);
	}

	/**
	 * 工单数据封装
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年10月18日 下午3:44:11
	 *
	 * @param dataList
	 *            数据源
	 * @param withItem
	 *            是否添加服务详情项目
	 * @return
	 */
	private List<Map<String, Object>> enclosureComboList(List<WorkOrderVO> dataList) {
		List<Map<String, Object>> returnDataList = new ArrayList<>();
		for (WorkOrderVO data : dataList) {
			returnDataList.add(enclosureCombo(data));
		}
		return returnDataList;
	}

	/**
	 * 工单数据封装
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年10月18日 下午3:44:11
	 *
	 * @param data
	 *            数据源
	 * @param withItem
	 *            是否添加服务详情项目
	 * @return
	 */
	private Map<String, Object> enclosureCombo(WorkOrderVO data) {
		Map<String, Object> returnData = new HashMap<>();
		returnData.put("userId", data.getUserId());
		returnData.put("comboItemId", data.getVipComboItemId());
		returnData.put("appoinDate", data.getAppoinDate());
		returnData.put("userRemark", data.getUserRemark());
		returnData.put("customerRemark", data.getCustomerRemark());
		returnData.put("sureDate", data.getSureDate());
		returnData.put("orgName", data.getOrgName());
		returnData.put("address", data.getAddress());
		returnData.put("workOrderStatus", data.getStatus());
		returnData.put("comboItemName", data.getItem().getName());
		return returnData;
	}
}
