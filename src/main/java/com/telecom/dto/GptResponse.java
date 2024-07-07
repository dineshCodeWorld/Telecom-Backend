package com.telecom.dto;

import java.util.List;

public class GptResponse {

	private List<Choice> choices;

	public GptResponse(List<Choice> choices) {
		super();
		this.choices = choices;
	}

	public GptResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GptResponse [choices=" + choices + "]";
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public static class Choice {
		private int index;
		private Message message;

		public Choice(int index, Message message) {
			super();
			this.index = index;
			this.message = message;
		}

		public Choice() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Choice [index=" + index + ", message=" + message + "]";
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public Message getMessage() {
			return message;
		}

		public void setMessage(Message message) {
			this.message = message;
		}
	}
}
