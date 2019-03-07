package com.lifeshs.common.constants.common;

public enum OrgStatus {

	/**
	 * 正常
	 */
	normal(0, "正常"),
	/**
	 * 停用
	 */
	disenable(1, "停用");
	
	private int _value;
    private String _name;

    private OrgStatus(int value, String name)
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
