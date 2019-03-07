package com.lifeshs.common.constants.common;

/**
 *  终端类型
 *  @author wenxian.cai 
 *  @datetime 2016年12月25日上午11:34:50
 */
public enum ContactTerminalType {
	
	/**
	 * 健康包
	 */
//	HealthPackage(1, "健康包"),
	/*
	 * C3
	 */
//	C3(2, "C3"),
	/*
	 * HL
	 */
//	HL(4, "HL"),
	
	HEALTH_PACKAGE_FAMILY(1, "健康包预警号"),
	
	C3_FAMILY(2, "C3亲情号"),
	
	C3_SOS(4, "C3-SOS");
	
	private int _value;
	private String _name;

	ContactTerminalType(int value, String name) {
		_value = value;
		_name = name;
	}

	public int value() {
		return _value;
	}

	public String getName() {
		return _name;
	}
}
