package com.telecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.telecom.dto.GptRequest;
import com.telecom.dto.GptResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatbotService {
	@Value("${openai.api.key}")
	private String apiKey;
	@Value("${openai.model}")
	private String model;
	@Value("${openai.max_tokens}")
	private Integer max_tokens;
	@Value("${openai.temperature}")
	private Integer temperature;
	@Autowired
	RestTemplate restTemplate;
	private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

	public String getChatbotResponse(String prompt) {
		if (prompt.equals("hi"))
			return "Hello, how can i assist you today!.";
		try {
			GptRequest gptRequest = new GptRequest(model, prompt, temperature, max_tokens);
			GptResponse gptResponse = restTemplate.postForObject(OPENAI_API_URL, gptRequest, GptResponse.class);
			return gptResponse.getChoices().get(0).getMessage().getContent();
		} catch (Exception e) {
			return "Note: this feature is under development stage, Please try after some time";
		}
	}
}