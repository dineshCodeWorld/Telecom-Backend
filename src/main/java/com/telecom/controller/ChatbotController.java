package com.telecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin
public class ChatbotController {

	@Autowired
	private com.telecom.service.ChatbotService chatbotService;

	@GetMapping("/prompt/{prompt}")
	public String getChatbotResponse(@PathVariable String prompt) {
		return chatbotService.getChatbotResponse(prompt);
	}
}
