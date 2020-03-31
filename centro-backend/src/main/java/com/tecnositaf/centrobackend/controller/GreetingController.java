//package com.tecnositaf.centrobackend.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.util.HtmlUtils;
//
//import com.tecnositaf.centrobackend.model.Greeting;
//import com.tecnositaf.centrobackend.model.HelloMessage;
//
//@Controller
//public class GreetingController {
//	
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
//	private SimpMessagingTemplate template;
//	@Autowired
//	public GreetingController(SimpMessagingTemplate template) {
//		this.template = template;
//	}
//	@MessageMapping("/hello")
//	public void greeting(HelloMessage message) throws Exception{
//		Thread.sleep(1000);
//	  this.template.convertAndSend("/topic/greetings", message);
//	  log.info(message.getName());
//	}
//	//non scrive content hello, name!
//	
////	@MessageMapping("/hello")
////    @SendTo("/topic/greetings")
////    public Greeting greeting(HelloMessage message) throws Exception {
////        Thread.sleep(1000); // simulated delay
////        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
////        scrive content hello, name !
////    }
//}
