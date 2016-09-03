//package org.postman;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.postman.broker.EmailConsumer;
//import org.postman.broker.EmailProducer;
//import org.postman.model.Email;
//
///**
// * 
// * @author Joy Ghosh
// * @version 1.0
// * @since 1.0
// * 
// */
//
//@WebServlet(urlPatterns={"/postmanservlet"})
//public class PostmanServlet extends HttpServlet{
//
//	private static final long serialVersionUID = -2636605043785736500L;
//
//	@EJB
//	EmailProducer producer;
//	
//	@EJB
//	EmailConsumer consumer;
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		processRequest(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		processRequest(req, resp);
//	}
//	
//	protected void processRequest(HttpServletRequest req, HttpServletResponse resp){
//		List<String> recipients = new ArrayList<String>();
//		recipients.add("r1@email.com");
//		recipients.add("r2@email.com");
//		recipients.add("r3@email.com");
//		recipients.add("r4@email.com");
//		
//		Email email = new Email("test-subject", "test-body", "abc@email.com", recipients, null, null, false);
//		producer.enqueEmail(email);
//	}
//}
