package org.postman.broker;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.postman.InitializationBean;
import org.postman.actors.messages.EmailMessage;
import org.postman.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;

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
	
	@EJB
	InitializationBean init;
	
	@Override
	public void onMessage(Message message) {
			
		try {
				Object object = ((ObjectMessage)message).getObject();
				Email email = (Email) object;
				logger.debug(email.getBody());
				
				//Dispatch email job to actor system 
				init.getMaster().tell(new EmailMessage(email), ActorRef.noSender());
			}catch (JMSException ex){
				logger.error(ex.getMessage());
			}
	}
}
