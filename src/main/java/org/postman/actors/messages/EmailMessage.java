package org.postman.actors.messages;

import org.postman.model.Email;

/**
 * 
 * @author Joy Ghosh
 *
 */
public class EmailMessage {
	private final Email email;
	
	public EmailMessage(final Email email){
		this.email = email;
	}
	
	public Email getMessage(){
		return this.email;
	}
}
