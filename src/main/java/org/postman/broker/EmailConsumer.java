package org.postman.broker;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.postman.ActorSystemBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Email message consumer of queue.
 * 
 * @author Joy Ghosh.
 * @version 1.0
 * @since 1.0
 */
@MessageDriven(mappedName=Resources.POSTMAN_EMAIL_QUEUE)
public class EmailConsumer implements MessageListener{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);
	
//	@EJB
//	ActorSystemBean actorSystem;
	
	@Inject
	Handler handler;
	
	@Override
	public void onMessage(Message message) {
			logger.debug("Dispatching message to handler.");
//		try {
//				Object object = ((ObjectMessage)message).getObject();
//				Email email = (Email) object;
//				logger.debug(email.getBody());
//				
//				//Dispatch email job to actor system 
//				actorSystem.getMaster().tell(new EmailMessage(email), ActorRef.noSender());
				handler.process(message);
//			}catch (JMSException ex){
//				logger.error(ex.getMessage());
//			}
	}
}
