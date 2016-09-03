package org.postman.broker;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
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
	
//	@Resource(lookup="java:comp/DefaultJMSConnectionFactory")
//	ConnectionFactory factory;
//	
//	@Resource(mappedName=Resources.POSTMAN_EMAIL_QUEUE)
//	Queue emailQueue;
	
	@Resource(lookup=Resources.POSTMAN_EMAIL_QUEUE)
	private Queue emailQueue;
	
	@Inject
	private JMSContext context;
	
	
	@Path("/enqueue")
	@GET
	public String enqueEmail(){
		Connection conn = null;
		List<String> recipients = new ArrayList<String>();
		recipients.add("r1@email.com");
		recipients.add("r2@email.com");
		recipients.add("r3@email.com");
		recipients.add("r4@email.com");
		
		Email email = new Email("test-subject", "test-body", "abc@email.com", recipients, null, null, false);
		
		//MapMessage message = (MapMessage) EmailMessageConverter.toMessage(email, context);
		TextMessage message = context.createTextMessage("Hi there!");
		context.createProducer().send(emailQueue, message);
		
		return "OK";
	}
}
