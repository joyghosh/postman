package org.postman.broker;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
@RunWith(Arquillian.class)
public class EmailMessageHandlerTest {
	
//	@Resource(mappedName=Resources.POSTMAN_EMAIL_QUEUE)
//	private ConnectionFactory factory;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailMessageHandlerTest.class);
	
	@Resource(lookup=Resources.POSTMAN_EMAIL_QUEUE)
	private Queue queue;
	
	@Inject
	JMSContext context;
	
	@Inject
	private Handler handler;
	
	@Deployment
	public static WebArchive createDeployment(){
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "postman.war")
				.addPackages(true, EmailConsumer.class.getPackage())
				.addAsWebInfResource("beans-alternative.xml", "beans.xml");
		logger.debug(archive.toString());
		return archive;
	}
	
	@Test
	public void testMessageHandling() throws JMSException, InterruptedException{
		ObjectMessage message = context.createObjectMessage();
		message.setObject(null);
		context.createProducer().send(queue, message);
		
		MockEmailMessageHandler mockHandler = (MockEmailMessageHandler)handler;
		Assert.assertTrue(mockHandler.poll(10) instanceof Message);
	}
}
