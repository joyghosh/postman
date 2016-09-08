package org.postman.broker;

import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

/**
 * Resources for postman. 
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
@JMSDestinationDefinitions(
		@JMSDestinationDefinition(
				name=Resources.POSTMAN_EMAIL_QUEUE,
				resourceAdapter="jmsra",
				interfaceName="javax.jms.Queue",
				destinationName="postmanQueue",
				description="Email message queue for postman"))
public class Resources {
	public static final String POSTMAN_EMAIL_QUEUE = "java:global/jms/postmanEmailQueue";
}
