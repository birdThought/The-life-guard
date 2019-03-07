package com.lifeshs.service1.util.sms;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

@WebServiceClient(name = "Sms", targetNamespace = "http://ws.flaginfo.com.cn", wsdlLocation = "http://gd.ums86.com:8899/sms_hb/services/Sms?wsdl")
public class SmsService extends Service {

	private final static URL SMS_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(SmsService.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = SmsService.class
					.getResource(".");
			url = new URL(baseUrl,
					"http://gd.ums86.com:8899/sms_hb/services/Sms?wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://gd.ums86.com:8899/sms_hb/services/Sms?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		SMS_WSDL_LOCATION = url;
	}

	public SmsService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public SmsService() {
		super(SMS_WSDL_LOCATION, new QName("http://ws.flaginfo.com.cn", "Sms"));
	}

	/**
	 * 
	 * @return returns SmsPortType
	 */
	@WebEndpoint(name = "SmsHttpPort")
	public SmsPortType getSmsHttpPort() {
		return super.getPort(new QName("http://ws.flaginfo.com.cn",
				"SmsHttpPort"), SmsPortType.class);
	}

}

