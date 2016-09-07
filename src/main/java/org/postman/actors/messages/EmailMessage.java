package org.postman.actors.messages;

import org.postman.model.Email;

/**
 * Actor message for passing email object.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
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
