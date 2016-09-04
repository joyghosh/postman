package org.postman.broker;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
@Path("/producer")
@Stateless
public class EmailProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailProducer.class);
		
	@Resource(lookup=Resources.POSTMAN_EMAIL_QUEUE)
	private Queue emailQueue;
	
	@Inject
	private JMSContext context;
	
	
	@Path("/enqueue")
	@GET
	public String enqueEmail(){
		List<String> recipients = new ArrayList<String>();
		recipients.add("r1@email.com");
		recipients.add("r2@email.com");
		recipients.add("r3@email.com");
		recipients.add("r4@email.com");
		
		Email email = new Email("test-subject", "test-body", "abc@email.com", recipients, null, null, false);
		
		ObjectMessage message = context.createObjectMessage();
		try {
			message.setObject(email);
		} catch (JMSException ex) {
			logger.error(ex.getMessage());
		}
		context.createProducer().send(emailQueue, message);
		
		return "OK";
	}
}
