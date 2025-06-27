package kda.chatapp.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kda.chatapp.server.model.Greeting;

@RestController
@Scope
public class Data {

	@Autowired
	private SimpMessagingTemplate template;
	
	private final Logger log = LoggerFactory.getLogger(Data.class);

	@RequestMapping(path = "/greetings")
	// @Scheduled(fixedRate = 1000)
	//@SendTo("/topic/greetings")
	public Greeting greetingScheduled() throws Exception {
		log.info("@Scheduled");
		
		var greeting = new Greeting("Hello, @Scheduled!");
		
		template.convertAndSend("/topic/greetings", greeting);
		//template.convertAndSendToUser(null, null, log)
		
		return greeting;
	}

}
