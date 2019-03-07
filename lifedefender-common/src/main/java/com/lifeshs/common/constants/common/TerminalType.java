package com.lifeshs.common.constants.common;

/**
 * 终端类型
 * @author dengfeng
 *
 */
public enum TerminalType
{
	/**
	 * APP(android、ios）
	 */
	APP(1, "APP"),
	/**
	 * HL-03
	 */
	HL03(2, "HL-03"),
	/**
	 * C3
	 */
	C3(4, "C3"), 
	/**
	 * 浏览器
	 */
	Browser(8, "Browser"),
	/**
	 * HL-031(TCP)
	 */
	HL031(16, "HL-031"),
	/**
	*  Watch(GPRS)
	*/
	Watch(32, "Watch"),
	/**
	 *  LCH-B(TCP)
	 */
	LCHB(64,"LCH-B");

    private int _value;
    private String _name;

    private TerminalType(int value, String name)
    {
       _value = value;
        _name = name;
    }

    public int value()
    {
        return _value;
    }

    public String getName()
    {
        return _name;
     }
}