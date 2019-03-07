package com.lifeshs.component.sms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="confirm_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="replys" type="{http://ws.flaginfo.com.cn}Reply" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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
