package com.chatbot.training;

import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;

import com.chatbot.training.watson.ConversationService;
import com.chatbot.training.watson.IAMAuthentication;
import com.chatbot.training.watson.SessionFactory;
import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.assistant.v2.model.DialogNodeOutputOptionsElement;
import com.ibm.watson.developer_cloud.assistant.v2.model.MessageOutput;
import com.ibm.watson.developer_cloud.assistant.v2.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v2.model.RuntimeIntent;

public class ChatbotApplication {

	public static void main(String[] args) {
		// Suppress log messages.
		LogManager.getLogManager().reset();

		// set up assistant service
		IAMAuthentication authen = new IAMAuthentication();
		Assistant assistant = authen.setup();

		String sessionId = SessionFactory.createSession(assistant);

		String input = "";
		Scanner sc = new Scanner(System.in);
		do {
			// start conversation with message
			ConversationService cService = new ConversationService();
			MessageResponse response = cService.handleBotResponse(assistant, sessionId, input);

			String textResponse = response.getOutput().getGeneric().get(0).getText();
			
			// detects response intents
			List<RuntimeIntent> intents = response.getOutput().getIntents();
			if (intents.size() > 0) {
				System.out.println("Detected intents: " + intents.get(0).getIntent());
			}

			MessageOutput output = response.getOutput();

			// print BOT response
			if (isExistEntities(output)) {
				System.out.println("Detected Entities ...");
				output.getEntities().forEach(entity -> {
					System.out.println(entity.getValue());
				});
				
				if(textResponse != null) {
					System.out.println(textResponse);
				}
			} else if (isExistOption(output)) {
				System.out.println("Detected Options ...");
				output.getGeneric().get(0).getOptions().forEach(action -> {
					System.out.println(action.getLabel());
				});
				
				if(textResponse != null) {
					System.out.println(output.getGeneric().get(0).getText());
				}
			} else {
				System.out.println("Bot response: " + textResponse);
			}

			System.out.print(">> ");
			input = sc.nextLine();

		} while (!input.equals("exit"));

		// Delete session after conversation
		sc.close();
		SessionFactory.deleteSession(assistant, sessionId);
	}

	public static boolean isExistEntities(MessageOutput output) {
		return output.getEntities().size() > 0;
	}

	public static boolean isExistOption(MessageOutput output) {
		List<DialogNodeOutputOptionsElement> options = output.getGeneric().get(0).getOptions();
		if (options != null) {
			return options.size() > 0;
		}
		return false;
	}
}
