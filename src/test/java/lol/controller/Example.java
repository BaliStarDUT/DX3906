package lol.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class Example {
	private MessageSource messages;
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}

	public void excute(){
		String message = this.messages.getMessage("argument.required", new Object[]{"userDao"}, "Reeuired", Locale.CHINA);
		System.out.println(message);
	}
	
}
