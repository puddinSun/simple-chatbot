package com.chatbot.training.watson;

import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class IAMAuthentication {
	private static final String API_KEY = "mSEofbYK9hxD6royIUYWon6m3PIw0Dd4SUmWvFxZZrUV";
	private static final String URL = "https://gateway-wdc.watsonplatform.net/assistant/api";
	private static final String VERSION_DATE = "2018-11-08";
	
	private static Assistant assistant;
	
	public Assistant setup() {
		if(assistant == null) {
			IamOptions iamOptions = new IamOptions.Builder().apiKey(API_KEY).build();

			assistant = new Assistant(VERSION_DATE, iamOptions);
			assistant.setEndPoint(URL);
			
		}
		return assistant;
	}
}	
