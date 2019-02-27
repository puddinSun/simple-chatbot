package com.chatbot.training.watson;

import java.util.Objects;

import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.developer_cloud.assistant.v2.model.DeleteSessionOptions;
import com.ibm.watson.developer_cloud.assistant.v2.model.SessionResponse;

public class SessionFactory {
	private static final String ASSISTANT_ID = "8fffd634-d67d-40f3-a78a-8173e16b55c0";
	private static SessionResponse session;

	public static String createSession(Assistant assistant) {
		if (Objects.isNull(session)) {
			// create new session
			CreateSessionOptions sessionOptions = new CreateSessionOptions
					.Builder(ASSISTANT_ID)
					.build();
			session = assistant.createSession(sessionOptions).execute();
		} 
		return session.getSessionId();
	}
	
	public static void deleteSession(Assistant assistant, String sessionId) {
		if (Objects.nonNull(sessionId)) {
			DeleteSessionOptions deleteOptions = new DeleteSessionOptions
					.Builder(ASSISTANT_ID, sessionId)
					.build();

			assistant.deleteSession(deleteOptions).execute();
		}
	}
}
