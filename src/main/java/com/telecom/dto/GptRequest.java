package com.telecom.dto;

import java.util.ArrayList;
import java.util.List;

public class GptRequest {

	private String model;
	private Integer temperature;
	private Integer max_tokens;
	private List<Message> messages;

	public GptRequest(String model, String prompt, Integer temperature, Integer max_tokens) {
		super();
		this.model = model;
		this.messages = new ArrayList<>();
		this.messages.add(new Message("user", prompt));
		this.max_tokens = max_tokens;
		this.temperature = temperature;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Integer getmax_tokens() {
		return max_tokens;
	}

	public void setmax_tokens(Integer max_tokens) {
		this.max_tokens = max_tokens;
	}

	@Override
	public String toString() {
		return "GptRequest [model=" + model + ", max_tokens=" + max_tokens + ", messages=" + messages + "]";
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
