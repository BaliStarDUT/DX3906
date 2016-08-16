package lol.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Example {
	private MessageSource messages;
	private String name;
    private int age;
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public void excute(){
		String message = this.messages.getMessage("argument.required", new Object[]{"userDao"}, "Reeuired", Locale.CHINA);
		System.out.println(message);
	}
	
}
