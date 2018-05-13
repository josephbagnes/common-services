package com.bagnesapps.email.model;

import com.bagnesapps.common.model.RequestBodyWithKey;

public class Email extends RequestBodyWithKey {

	private String to;
	private String cc;
	private String replyTo;
	private String subject;
	private String htmlContent;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}	
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}	
	
}
