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
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.postman.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rest based email producer. It doesn't actually produces email but enqueues them to message pipeline.
 * 
 * @author Joy Ghosh.
 * @version 1.0
 * @since 1.0
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
	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
	public String enqueEmail(
			@FormParam("from") String from, 
			@FormParam("to") String to,
			@FormParam("cc") String cc,
			@FormParam("bcc") String bcc,
			@FormParam("subject") String subject,
			@FormParam("content") String content){
		
		List<String> recipients = new ArrayList<String>();
		for(String r:to.split(",")){
			recipients.add(r);
		}
		Email email = new Email(subject, content, from, recipients, null, null, false);
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
