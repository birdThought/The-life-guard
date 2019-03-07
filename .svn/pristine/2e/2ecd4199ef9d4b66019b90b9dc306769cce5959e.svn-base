package com.lifeshs.service1.util.sms;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.lifeshs.component.sms.Reply;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "result", "confirmTime", "id", "replys" })
@XmlRootElement(name = "ReplyResponse")
public class ReplyResponse {

	@XmlElement(required = true, nillable = true)
	protected String result;
	@XmlElementRef(name = "confirm_time", namespace = "http://ws.flaginfo.com.cn", type = JAXBElement.class)
	protected JAXBElement<String> confirmTime;
	@XmlElementRef(name = "id", namespace = "http://ws.flaginfo.com.cn", type = JAXBElement.class)
	protected JAXBElement<String> id;
	@XmlElement(nillable = true)
	protected List<Reply> replys;

	/**
	 * Gets the value of the result property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the value of the result property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResult(String value) {
		this.result = value;
	}

	/**
	 * Gets the value of the confirmTime property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getConfirmTime() {
		return confirmTime;
	}

	/**
	 * Sets the value of the confirmTime property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setConfirmTime(JAXBElement<String> value) {
		this.confirmTime = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setId(JAXBElement<String> value) {
		this.id = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the replys property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the replys property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getReplys().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Reply }
	 * 
	 * 
	 */
	public List<Reply> getReplys() {
		if (replys == null) {
			replys = new ArrayList<Reply>();
		}
		return this.replys;
	}

}
