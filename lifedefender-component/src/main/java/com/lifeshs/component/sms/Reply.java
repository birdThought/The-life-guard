package com.lifeshs.component.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Reply complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Reply">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="callMdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reply_time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reply", propOrder = { "callMdn", "mdn", "content", "replyTime" })
public class Reply {

	@XmlElement(required = true, nillable = true)
	protected String callMdn;
	@XmlElement(required = true, nillable = true)
	protected String mdn;
	@XmlElement(required = true, nillable = true)
	protected String content;
	@XmlElement(name = "reply_time", required = true, nillable = true)
	protected String replyTime;

	/**
	 * Gets the value of the callMdn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCallMdn() {
		return callMdn;
	}

	/**
	 * Sets the value of the callMdn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCallMdn(String value) {
		this.callMdn = value;
	}

	/**
	 * Gets the value of the mdn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMdn() {
		return mdn;
	}

	/**
	 * Sets the value of the mdn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMdn(String value) {
		this.mdn = value;
	}

	/**
	 * Gets the value of the content property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the value of the content property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * Gets the value of the replyTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReplyTime() {
		return replyTime;
	}

	/**
	 * Sets the value of the replyTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReplyTime(String value) {
		this.replyTime = value;
	}

}
