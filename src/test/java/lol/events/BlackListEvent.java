package lol.events;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * 2016年8月5日 下午4:28:14
 */
public class BlackListEvent extends ApplicationEvent {
	
	private final String address;
    private final String test;

	public BlackListEvent(Object source,String address , String test) {
		super(source);
		this.address = address;
		this.test = test;
	}
	
}
