package com.lifeshs.component.sms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.tzcms.cxf.webservice.sms package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _ReplyResponseConfirmTime_QNAME = new QName(
			"http://ws.flaginfo.com.cn", "confirm_time");
	private final static QName _ReplyResponseId_QNAME = new QName(
			"http://ws.flaginfo.com.cn", "id");
	private final static QName _SmsIn10_QNAME = new QName(
			"http://ws.flaginfo.com.cn", "in10");
	private final static QName _SmsIn9_QNAME = new QName(
			"http://ws.flaginfo.com.cn", "in9");
	private final static QName _ReplyConfirmRequestIn4_QNAME = new QName(
			"http://ws.flaginfo.com.cn", "in4");
	private final static QName _ReplyRequestIn3_QNAME = new QName(
			"http://ws.flaginfo.com.cn", "in3");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.tzcms.cxf.webservice.sms
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Sms }
	 * 
	 */
	public Sms createSms() {
		return new Sms();
	}

	/**
	 * Create an instance of {@link ReplyResponse }
	 * 
	 */
	public ReplyResponse createReplyResponse() {
		return new ReplyResponse();
	}

	/**
	 * Create an instance of {@link Report }
	 * 
	 */
	public Report createReport() {
		return new Report();
	}

	/**
	 * Create an instance of {@link SmsResponse }
	 * 
	 */
	public SmsResponse createSmsResponse() {
		return new SmsResponse();
	}

	/**
	 * Create an instance of {@link ReplyConfirmResponse }
	 * 
	 */
	public ReplyConfirmResponse createReplyConfirmResponse() {
		return new ReplyConfirmResponse();
	}

	/**
	 * Create an instance of {@link Reply }
	 * 
	 */
	public Reply createReply() {
		return new Reply();
	}

	/**
	 * Create an instance of {@link ReplyConfirmRequest }
	 * 
	 */
	public ReplyConfirmRequest createReplyConfirmRequest() {
		return new ReplyConfirmRequest();
	}

	/**
	 * Create an instance of {@link SearchSmsNumResponse }
	 * 
	 */
	public SearchSmsNumResponse createSearchSmsNumResponse() {
		return new SearchSmsNumResponse();
	}

	/**
	 * Create an instance of {@link ReportResponse }
	 * 
	 */
	public ReportResponse createReportResponse() {
		return new ReportResponse();
	}

	/**
	 * Create an instance of {@link AuditingResponse }
	 * 
	 */
	public AuditingResponse createAuditingResponse() {
		return new AuditingResponse();
	}

	/**
	 * Create an instance of {@link SearchSmsNumRequest }
	 * 
	 */
	public SearchSmsNumRequest createSearchSmsNumRequest() {
		return new SearchSmsNumRequest();
	}

	/**
	 * Create an instance of {@link AuditingRequest }
	 * 
	 */
	public AuditingRequest createAuditingRequest() {
		return new AuditingRequest();
	}

	/**
	 * Create an instance of {@link ReplyRequest }
	 * 
	 */
	public ReplyRequest createReplyRequest() {
		return new ReplyRequest();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://ws.flaginfo.com.cn", name = "confirm_time", scope = ReplyResponse.class)
	public JAXBElement<String> createReplyResponseConfirmTime(String value) {
		return new JAXBElement<String>(_ReplyResponseConfirmTime_QNAME,
				String.class, ReplyResponse.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://ws.flaginfo.com.cn", name = "id", scope = ReplyResponse.class)
	public JAXBElement<String> createReplyResponseId(String value) {
		return new JAXBElement<String>(_ReplyResponseId_QNAME, String.class,
				ReplyResponse.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://ws.flaginfo.com.cn", name = "in10", scope = Sms.class)
	public JAXBElement<String> createSmsIn10(String value) {
		return new JAXBElement<String>(_SmsIn10_QNAME, String.class, Sms.class,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://ws.flaginfo.com.cn", name = "in9", scope = Sms.class)
	public JAXBElement<String> createSmsIn9(String value) {
		return new JAXBElement<String>(_SmsIn9_QNAME, String.class, Sms.class,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://ws.flaginfo.com.cn", name = "in4", scope = ReplyConfirmRequest.class)
	public JAXBElement<String> createReplyConfirmRequestIn4(String value) {
		return new JAXBElement<String>(_ReplyConfirmRequestIn4_QNAME,
				String.class, ReplyConfirmRequest.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://ws.flaginfo.com.cn", name = "in3", scope = ReplyRequest.class)
	public JAXBElement<String> createReplyRequestIn3(String value) {
		return new JAXBElement<String>(_ReplyRequestIn3_QNAME, String.class,
				ReplyRequest.class, value);
	}

}
