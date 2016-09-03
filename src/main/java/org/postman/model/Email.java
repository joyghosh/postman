package org.postman.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Joy Ghosh.
 * @version 1.0
 * @since 1.0
 *
 */
public class Email {
	
	private String subject;
	private String body;
	private String from;
	private List<String> recipients;
	private List<String> ccList;
	private List<String> bccList;
	private boolean isHtml;
	
	public Email(final String subject, final String body, 
							final String from, final String to, final boolean isHtml) {
		this.subject = subject;
		this.body = body;
		this.from = from;
		this.recipients = new ArrayList<String>();
		this.recipients.add(to);
		this.isHtml = isHtml;
	}
	
	public Email(final String subject, final String body, 
			final String from, final List<String> recipients, final List<String> cc,
			final List<String> bcc, final boolean isHtml){
		this.subject = subject;
		this.body = body;
		this.from = from;
		this.recipients = new ArrayList<String>();
		this.recipients.addAll(recipients);
		
		if(cc != null){
			this.ccList = new ArrayList<String>();
			this.ccList.addAll(cc);
		}
		
		if(bcc != null){
			this.bccList = new ArrayList<String>();
			this.bccList.addAll(bcc);
		}
		this.isHtml = isHtml;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getFrom() {
		return from;
	}

	public List<String> getRecipients() {
		return recipients;
	}
	
	public List<String> cc() {
		return ccList;
	}

	public List<String> bcc() {
		return bccList;
	}

	public boolean isHtml() {
		return isHtml;
	}
}
