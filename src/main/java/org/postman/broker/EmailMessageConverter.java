package org.postman.broker;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.postman.model.Email;

/**
 * Utility class for message conversion for the message pipeline.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class EmailMessageConverter{
	
	/**
	 * Converts from <code>Email</code> to <code>Object Message</code> 
	 * suitable for queue.
	 * 
	 * @param email
	 * @param context
	 * @return
	 * @throws JMSException
	 */
	public static Message toMessage(Email email, JMSContext context) throws JMSException{
		ObjectMessage message = context.createObjectMessage(email);
		return message;
	}

	/**
	 * Converts from Object message type to Email object.
	 * 
	 * @param message to be converted.
	 * @return Converted Email object.
	 * @throws JMSException in-case an invalid message object is received.
	 */
	public static Email fromMessage(Message message) throws JMSException{
		Object object = ((ObjectMessage) message).getObject();
		
		if(object.getClass().equals(Email.class)){
			Email email = (Email)object;
			return email;
		}
		throw new JMSException("Not a valid Email message object.");
	}
}
