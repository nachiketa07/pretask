/**
 * 
 */
package com.pretask.mail.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author gudlu
 *
 */

@Component
public class MailVO {
	
	String  address_TO;
	String subject;
	String body;
	
	private String requestDate;
	private String runDate;
	
	public String getRunDate() {
		return runDate;
	}
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getAddress_TO() {
		return address_TO;
	}
	public void setAddress_TO(String address_TO) {
		this.address_TO = address_TO;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

}
