package org.postman.broker;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.postman.ActorSystemBean;
import org.postman.actors.messages.EmailMessage;
import org.postman.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;

@Singleton
@DependsOn("ActorSystemBean")
public class EmailMessageHandler implements Handler {

	private static final Logger logger = LoggerFactory.getLogger(EmailMessageHandler.class);
	@EJB
	ActorSystemBean actorSystem;
	
	@Override
	public void process(Message message) {
		
		try{
			Object object = ((ObjectMessage)message).getObject();
			Email email = (Email) object;
			logger.debug(email.getBody());
			
			//Dispatch email job to actor system 
			actorSystem.getMaster().tell(new EmailMessage(email), ActorRef.noSender());
		}catch(JMSException ex){
			logger.error(ex.getMessage());
		}
	}

}
