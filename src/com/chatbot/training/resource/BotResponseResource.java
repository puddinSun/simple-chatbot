package com.chatbot.training.resource;

import java.util.logging.LogManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.chatbot.training.watson.ConversationService;
import com.chatbot.training.watson.IAMAuthentication;
import com.chatbot.training.watson.SessionFactory;
import com.ibm.watson.developer_cloud.assistant.v2.Assistant;

@Path("/room")
public class BotResponseResource {
	
	@GET
	@Path("/text={input}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
	public String getBotResponse(@PathParam("input") String input) {
		LogManager.getLogManager().reset();

		// get authentication
		IAMAuthentication authen = new IAMAuthentication();
		Assistant assistant = authen.setup();
		
		// create session id
		String sessionId = SessionFactory.createSession(assistant);
		
		// start conversation with message
		ConversationService cService = new ConversationService();
		
		return cService.handleBotResponse(assistant, sessionId, input)
				.getOutput()
				.toString();
	}
}
