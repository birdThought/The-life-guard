package com.lifeshs.service1.util.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "out" })
@XmlRootElement(name = "AuditingResponse")
public class AuditingResponse {

	@XmlElement(required = true, nillable = true)
	protected String out;

	/**
	 * Gets the value of the out property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOut() {
		return out;
	}

	/**
	 * Sets the value of the out property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOut(String value) {
		this.out = value;
	}

}