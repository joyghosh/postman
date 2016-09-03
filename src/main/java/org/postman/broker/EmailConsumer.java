package org.postman.broker;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.postman.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Joy Ghosh.
 * @version 1.0
 * @since 1.0
 *
 */
@MessageDriven(mappedName=Resources.POSTMAN_EMAIL_QUEUE)
public class EmailConsumer implements MessageListener{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);
	
	@Override
	public void onMessage(Message message) {
			try {
		//			MapMessage payload = (MapMessage) message;
		//			Email email = (Email) EmailMessageConverter.fromMessage(payload);
					Object object;
				
					object = ((ObjectMessage)message).getObject();
					Email email = (Email) object;
					logger.debug(email.getBody());
				} catch (JMSException ex) {
						// TODO Auto-generated catch block
						logger.error(ex.getMessage());
				}
	}
}
