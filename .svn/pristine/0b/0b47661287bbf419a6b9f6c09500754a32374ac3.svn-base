package com.lifeshs.pojo.order;

import com.lifeshs.pojo.health.ServiceItem;

import java.util.Date;

/**
 * Created by XuZhanSi on 2016/10/18 0018. 订单pojo
 */
public class ServiceOrder extends ServiceItem {
	/** 描述 */
	private static final long serialVersionUID = -7887294201117148185L;

	private Integer orderId;// 订单主键id
	private Integer orderNumber;// 订单号
	private Date createDate;// 订单的创建时间
	private Integer chargeMode;// '收费方式:0_免费，1_按次，2_按月，3_按年'
	private Double price;// 订单的价格
	private Integer number;// 订购的数量
	private Integer status;// 状态：待付款_1, 付款失败_2，有效_3，已完成_4，退款失效_5
	private String serviceName;// 订单的服务名称

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(Integer chargeMode) {
		this.chargeMode = chargeMode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "ServiceOrder [orderId=" + orderId + ", orderNumber=" + orderNumber + ", createDate=" + createDate
				+ ", chargeMode=" + chargeMode + ", price=" + price + ", number=" + number + ", status=" + status
				+ ", serviceName=" + serviceName + "]";
	}
}
