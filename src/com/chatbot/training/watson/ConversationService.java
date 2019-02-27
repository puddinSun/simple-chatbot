package com.chatbot.training.watson;

import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.assistant.v2.model.MessageInput;
import com.ibm.watson.developer_cloud.assistant.v2.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v2.model.MessageResponse;

public class ConversationService {
	private static final String ASSISTANT_ID = "8fffd634-d67d-40f3-a78a-8173e16b55c0";
	private static final String REQUEST_TYPE = "text";
	
	public MessageResponse handleBotResponse(
			Assistant assistant, 
			String sessionId, 
			String inputText) {
		
		MessageInput input = new MessageInput.Builder()
				.messageType(REQUEST_TYPE).text(inputText)
				.build();
		
		MessageOptions options = new MessageOptions.Builder(ASSISTANT_ID, sessionId)
				.input(input)
				.build();
		
		return assistant.message(options).execute();
	}
}
