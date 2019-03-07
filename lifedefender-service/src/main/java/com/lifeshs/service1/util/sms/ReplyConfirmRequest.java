package com.lifeshs.service1.util.sms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "in0", "in1", "in2", "in3", "in4" })
@XmlRootElement(name = "ReplyConfirmRequest")
public class ReplyConfirmRequest {

	@XmlElement(required = true, nillable = true)
	protected String in0;
	@XmlElement(required = true, nillable = true)
	protected String in1;
	@XmlElement(required = true, nillable = true)
	protected String in2;
	@XmlElement(required = true, nillable = true)
	protected String in3;
	@XmlElementRef(name = "in4", namespace = "http://ws.flaginfo.com.cn", type = JAXBElement.class)
	protected JAXBElement<String> in4;

	/**
	 * Gets the value of the in0 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIn0() {
		return in0;
	}

	/**
	 * Sets the value of the in0 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIn0(String value) {
		this.in0 = value;
	}

	/**
	 * Gets the value of the in1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIn1() {
		return in1;
	}

	/**
	 * Sets the value of the in1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIn1(String value) {
		this.in1 = value;
	}

	/**
	 * Gets the value of the in2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIn2() {
		return in2;
	}

	/**
	 * Sets the value of the in2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIn2(String value) {
		this.in2 = value;
	}

	/**
	 * Gets the value of the in3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIn3() {
		return in3;
	}

	/**
	 * Sets the value of the in3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIn3(String value) {
		this.in3 = value;
	}

	/**
	 * Gets the value of the in4 property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIn4() {
		return in4;
	}

	/**
	 * Sets the value of the in4 property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIn4(JAXBElement<String> value) {
		this.in4 = ((JAXBElement<String>) value);
	}

}
