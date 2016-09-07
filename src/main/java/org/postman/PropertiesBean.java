package org.postman;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * App properties place-holder.
 * 
 * @author Joy Ghosh.
 * @version 1.0
 * @since 1.0
 */

@Startup
@Singleton
public class PropertiesBean {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertiesBean.class);
	private static Properties properties;
	private static InputStream input;
	
	@PostConstruct
	private void init(){
		logger.debug("Initializing properties bean.");
		input = getClass().getClassLoader().getResourceAsStream(Property.CONFIG_FILE);
		properties = new Properties();
		if(input == null){
			logger.error("No such file found: "+Property.CONFIG_FILE);
			System.exit(0);
		}

		try {
			properties.load(input);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
	}
	
	@PreDestroy
	private void cleanUp(){
		if(input != null){
			try {
				input.close();
			} catch (IOException ex) {
				logger.error(ex.getMessage());
			}
		}
	}
	
	public String getValue(String key){
		return properties.getProperty(key);
	}
}
